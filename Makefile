
all: YodafyClienteTCP.class YodafyServidorIterativo.class

YodafyClienteTCP.class: YodafyClienteTCP.java
	javac $^

YodafyServidorIterativo.class: YodafyServidorIterativo.java ProcesadorYodafy.java
	javac $^