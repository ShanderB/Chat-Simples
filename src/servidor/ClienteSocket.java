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
import java.net.Socket;
import javax.swing.JTextArea;
public class ClienteSocket extends Thread {
    
    private Socket conexao;
    private JTextArea jTextArea;
    
    public ClienteSocket(Socket socket, JTextArea jTextArea) {
        this.conexao = socket;
        this.jTextArea = jTextArea;
    }
    
    public void run()
    {
        try {
            BufferedReader entrada = 
				new BufferedReader(new InputStreamReader(this.conexao.getInputStream()));
            while (true)
            {
               String msg = entrada.readLine();
                if (msg == null) {
                    jTextArea.setText("Conexão encerrada!");
                    break;
                }
                jTextArea.setText(jTextArea.getText()+"\n"+msg);
                
            }
        } catch (IOException e) {
            // caso ocorra alguma exceção de E/S, mostra qual foi.
            System.out.println("Ocorreu uma Falha... .. ." + 
				" IOException: " + e);
        }
    }
}
