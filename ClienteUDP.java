package conexion;

//Importaciones necesarias para trabajar con sockets
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JOptionPane;


public class ClienteUDP {

	//Creamos una variable global con un puerto determinado
	private static int SERVER_PORT = 9091;
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//Enviar packets
		String serverAddress=JOptionPane.showInputDialog("Ingresar direccion IP :\n" + "corriendo el servicio en el puerto "+ SERVER_PORT +":");
		//Creamos un datagrama para tener una conexi�n entre las m�quinas a conectar, en este caso del tipo DatagramSocket
		DatagramSocket clienteSocket = new DatagramSocket();
		//Las siguientes l�neas son para enviar informaci�n como una secuencia de bytes entre m�quinas
		byte bufferSend[] = serverAddress.getBytes();
		//En este paso creamos un paquete de env�o con todo lo creado anteriormente 
		DatagramPacket sendPacket =  new DatagramPacket(bufferSend,bufferSend.length,InetAddress.getByName(serverAddress),SERVER_PORT);
		//Finalmente enviamos la informaci�n
		clienteSocket.send(sendPacket);
		
		//Recibir packets
		byte bufferReceive[] = new byte[128];
		//En este paso recibimos la informaci�n y la guardamos en la variable receivePacket
		DatagramPacket receivePacket = new DatagramPacket(bufferReceive, bufferReceive.length);
		//Guardamos esa informaci�n en el clienteSocket
		clienteSocket.receive(receivePacket);
		
		//Transformar de bytes a STring
		InputStream myInputStream = new ByteArrayInputStream(receivePacket.getData());
		//La clase BufferReader es usada incluso para la entrada de teclado
		BufferedReader input = new BufferedReader(new InputStreamReader(myInputStream));
		String answer = input.readLine();
		
		//Despliega mensaje
		JOptionPane.showMessageDialog(null, answer);
		//Una vez recibido el mensaje cerramos la comunicaci�n
		clienteSocket.close();
		System.exit(0);
	}

}
