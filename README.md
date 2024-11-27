Chat Java Simples feito na faculdade.

Necessita de um computador host.
<br>

Intalar o Ant:  <br>
sudo apt-get install ant <br>

Caso queira remover:  <br>
sudo apt-get remove ant

Depois rodar: <br>
ant clean <br>
ant compile <br>
ant jar <br>
<br>


Para criar e rodar o jar host: <br>
java -cp build/classes servidor.ServidorSocket <br>
 <br>
 
Para criar e rodar o jar client: <br>
java -jar dist/ChatJava.jar
