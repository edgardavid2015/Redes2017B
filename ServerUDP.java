package conexion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class ServerUDP {
	private static int PORT = 9091;
	public static void main(String[] args) throws IOException{
		DatagramSocket serverSocket = new DatagramSocket(PORT);
		//En este línea imprimimos por pantalla el listener con el cual se va a establecer la información
		System.err.println(" Server listening on port " + PORT + "using UDP connection\n");
		//Línea de código para obtener el tiempo que se demora en iniciar el servicio.
		long initialTime = System.currentTimeMillis();
		System.out.println("Tiempo inicial: "+initialTime+"\n");
		
		try{
			//while(true) es una línea que siempre se va a ejecutar
			while(true){
				//Receive Packet
				  byte bufferReceive[] = new byte[128];
				  DatagramPacket receivePacket = new DatagramPacket(bufferReceive,bufferReceive.length);
				  serverSocket.receive(receivePacket);
				  InetAddress clientAddress = receivePacket.getAddress();
				  //Obtenemos el puerto del cliente y lo guardamos en la variable clientePuerto
				  int clientePuerto = receivePacket.getPort();
				  //Imprimimos el puerto del cliente
				  System.out.println("Puerto del cliente: "+clientePuerto+"\n");
				//Send Packet
				  //enviamos un mensaje de confirmación de una conexión correcta
				  String msg = "Message from Edgar-Server";
				  byte bufferSend[] = msg.getBytes();
				  //sendPacket tendrá como parámetros la longitud del buffer, las direcciones del cliente y del puerto de conexion
				  DatagramPacket sendPacket =  new DatagramPacket(bufferSend,bufferSend.length,clientAddress,clientePuerto);
				  serverSocket.send(sendPacket);
			}
		}
		finally{
			serverSocket.close();
			//Es recomendable cerrar la conexión y por ende el socket por motivos de seguridad.	
		}
	}
}
