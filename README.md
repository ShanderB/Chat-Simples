Chat Java Simples feito na faculdade.

Necessita de um computador host.
<br>

Intalar o Ant:
sudo apt-get install ant <br>

Caso queira remover:
sudo apt-get remove ant

Depois rodar:
ant clean <br>
ant compile <br>
ant jar <br>
<br>


Para criar e rodar o jar host:
java -cp build/classes servidor.ServidorSocket <br>
 <br>
 
Para criar e rodar o jar client:
java -jar dist/ChatJava.jar
