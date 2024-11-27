Chat Java Simples feito na faculdade.

Necessita de um computador host.


Intalar o Ant:
sudo apt-get install ant

Caso queira remover:
sudo apt-get remove ant

Depois rodar:
ant clean
ant compile
ant jar

Para criar e rodar o jar host:
java -cp build/classes servidor.ServidorSocket

Para criar e rodar o jar client:
java -jar dist/ChatJava.jar
