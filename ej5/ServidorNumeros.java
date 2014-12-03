//
// Servidor Números
// 

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ServidorNumeros {
    public static void main(String[] args) {
	// Puerto de escucha
	int port = 8989;

	// Inicia un socket escuchando en el puerto dado
	// Crea una hebra para atender cada petición
	try {
	    ServerSocket socketServer = new ServerSocket(port);
	    while (true) {
		HebraServidor h = new HebraServidor(socketServer.accept());
		h.start();
	    }
	} 
	catch (IOException e) {
	    System.err.println("Error al escuchar en el puerto " + port);
	}	
    }
}

class HebraServidor extends Thread {
    private Socket socketServicio;
    private BufferedReader inReader;
    private PrintWriter outPrinter;

    // Constructor de una hebra, dado un socket.
    HebraServidor (Socket socketServicio) {
	this.socketServicio = socketServicio;

	// Obtiene los flujos de escritura/lectura
	try {
	    InputStreamReader isr = new InputStreamReader(socketServicio.getInputStream());
	    inReader = new BufferedReader(isr); 
	    outPrinter = new PrintWriter(socketServicio.getOutputStream(), true);
	}
	catch (IOException e) {
	    System.err.println("Error al leer los flujos.");
	}
    }

    public void run() {
	Adivina adivinador = new Adivina();
	Boolean finalizado = false;
	
	while (!finalizado) {
	    // Lee un intento del cliente
	    int resultado = 0;

	    try {
		int datoRecibido = Integer.parseInt(inReader.readLine());
		resultado = adivinador.intento(datoRecibido);
	    }
	    catch (IOException e) {
		System.err.println("Error al leer los flujos.");
	    }

	    // Envía la respuesta del servidor
	    switch (resultado) {
	    case 0:  outPrinter.println("¡Acierto!");
	    case 1:  outPrinter.println("El número es mayor");
	    case -1: outPrinter.println("El número es menor");
	    }

	    finalizado = (resultado == 0);

	    // Intenta adivinar al cliente
	    if (!finalizado) {
		outPrinter.println(adivinador.estima());
	    }
	}
    }
}
