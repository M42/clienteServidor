/*********************************************

    Fundamentos de Redes
    Práctica 2
    Ejercicio 2

    Mario Román
    Francisco David Charte

*********************************************/

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
public class YodafyServidorIterativo {

    public static void main(String[] args) {
    
        // Puerto de escucha
        int port=8989;

        // Socket para el servidor
        ServerSocket socketServer;
        Socket socketServicio = null;
        
        try {
            socketServer = new ServerSocket(port);
            
            do {
                try {
                    socketServicio = socketServer.accept();
                } catch (IOException e) {
                    System.out.println("Error: no se pudo aceptar la conexión solicitada.");
                }
                
                // Creamos un objeto de la clase ProcesadorYodafy, pasándole como 
                // argumento el nuevo socket, para que realice el procesamiento
                // Este esquema permite que se puedan usar hebras más fácilmente.
                ProcesadorYodafy procesador=new ProcesadorYodafy(socketServicio);
                procesador.procesa();
                
            } while (true);
            
        } catch (IOException e) {
            System.err.println("Error al escuchar en el puerto "+port);
        }

    }

}
