//
// Servidor Números
// 


import java.io.IOException;
import java.io.InputStream;
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
    
    // Constructor de una hebra, dado un socket.
    HebraServidor (Socket socketServicio) {
	this.socketServicio = socketServicio;
    }

    public void run() {
	
    }
}
