
all: Cliente.class Servidor.class

Cliente.class: YodafyClienteTCP.java
	javac $^

Servidor.class: ProcesadorYodafy.java YodafyServidorIterativo.java
	javac $^