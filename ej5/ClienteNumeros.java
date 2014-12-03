//
// ClienteNumeros
// 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteNumeros {
    private int port = 8989;
    private String host = "localhost";
    private PrintWriter outPrinter;
    private BufferedReader inReader;
    
    public static void main(String[] args) {
	try {
	    // Crea un socket y los flujos
	    Socket socketServicio = new Socket(host,port);
	    InputStreamReader isr = new InputStreamReader(socketServicio.getInputStream());
	    outPrinter = new PrintWriter(socketServicio.getOutputStream(), true);
	    inReader = new BufferedReader(isr);

	    // Empieza el juego
	    jugar();
	    
	    // Cierra el socket
	    socketServicio.close();
	}
	catch (UnknownHostException e) {
	    System.err.println("Error: Nombre de host no encontrado.");
	} catch (IOException e) {
	    System.err.println("Error de entrada/salida al abrir el socket.");
	}
    }

    public void jugar() {
	Boolean finalizado = false;

	// Pide número al jugador
	Scanner in = new Scanner(System.in);
	System.out.println("Escribe tu número: ");
	int num = in.nextInt();
	
	while (!finalizado) {
	    // Lee intento del cliente
	    System.out.println("Adivina al servidor: ");
	    int n = in.nextInt();
	    outPrinter.println(n);

	    // Lee respuesta del servidor
	    int respuesta = Integer.parseInt(inReader.readLine());
	    switch (respuesta) {
	    case 0:  System.out.println("¡Acierto!");
	    case 1:  System.out.println("El número es mayor");
	    case -1: System.out.println("El número es menor");
	    }

	    finalizado = (respuesta == 0);

	    if (!finalizado) {
		// Lee predicción del servidor
		int prediccion = Integer.parseInt(inReader.readLine());
		int resultado = 0;
		if (prediccion >= num)
		    resultado = 1;
		if (prediccion <= num)
		    resultado = -1;

		// Envía respuesta
		outPrinter.println(resultado);
		finalizado = (respuesta == 0);
	    }
	}
    }
}
