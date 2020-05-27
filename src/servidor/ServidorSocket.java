/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
public class ServidorSocket extends Thread {
    private static ArrayList<PrintStream> listUsuarios;
    private Socket conexao;
    private String nomeCliente;
    private static List LISTA_DE_NOMES = new ArrayList();
    public ServidorSocket(Socket socket) {
        this.conexao = socket;
    }
    public boolean armazena(String newName){
       for (int i=0; i < LISTA_DE_NOMES.size(); i++){
         if(LISTA_DE_NOMES.get(i).equals(newName))
           return true;
       }
       LISTA_DE_NOMES.add(newName);
       return false;
    }
    public void remove(String oldName) {
       for (int i=0; i< LISTA_DE_NOMES.size(); i++){
         if(LISTA_DE_NOMES.get(i).equals(oldName))
           LISTA_DE_NOMES.remove(oldName);
       }
    }
    public static void main(String args[]) {
        listUsuarios = new ArrayList<PrintStream>();
        try {
            ServerSocket server = new ServerSocket(5555);
            System.out.println("ServidorSocket rodando na porta 5555");
            while (true) {
                Socket conexao = server.accept();
                Thread t = new ServidorSocket(conexao);
                t.start();
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
    public void run()
    {
        try {
            BufferedReader entrada = 
				new BufferedReader(new InputStreamReader(this.conexao.getInputStream()));
            
			PrintStream saida = new PrintStream(this.conexao.getOutputStream());
            this.nomeCliente = entrada.readLine();
            if (armazena(this.nomeCliente)){
              saida.println("Este nome ja existe! Conecte novamente com outro Nome.");
              listUsuarios.add(saida);
              this.conexao.close();
              return;
            } else {
               System.out.println(this.nomeCliente + " : Conectado ao Servidor!");
            }
            if (this.nomeCliente == null) {
                return;
            }
            enviarTodosUsuarios(" conectado");
            listUsuarios.add(saida);
        
            String msg = entrada.readLine();
          
            while (msg != null && !(msg.trim().equals("sair")))
            {
                enviarTodosUsuarios(msg);
                msg = entrada.readLine();
            }
            System.out.println(this.nomeCliente + " saiu do bate-papo!");
           
            enviarTodosUsuarios(" saiu  do bate-papo!");
          
            remove(this.nomeCliente);
            listUsuarios.remove(saida);
            this.conexao.close();
        } catch (IOException e) {
           
            System.out.println("Falha na Conexao... .. ."+" IOException: " + e);
        }
    }

    public void enviarTodosUsuarios(String msg) throws IOException {
       for(PrintStream chat : listUsuarios){
           chat.println(this.nomeCliente+" : :"+msg.toUpperCase());
        }
      }
}
