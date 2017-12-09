package lab2;
//Codigo obtenido de:
//https://stackoverflow.com/questions/34694835/java-beginner-client-server-sending-multiple-integers-to-a-socket
//modificado para que cumpla con lo requisitos del laboratorio 2

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SumaCliTCP 
{
	 private static InetAddress host;
	 	//Abrimos un puerto por el cual enviaremos los datos
	    private static final int PORT = 1234;

	    public static void main(String[] args) {
	        try {
	        	//realizamos un conexión mediante la IP del localhost
	            host = InetAddress.getLocalHost();
	        } catch (UnknownHostException uhEx) {
	        	//se encierra en un try-catch de pronto haya errores
	            System.out.println("ID del host no encontrada");
	            System.exit(1);
	        }
	        accessServer();
	    }

	    private static void accessServer() {
	        Socket link = null;
	        try {
	            link = new Socket(host, PORT);
	            Scanner input = new Scanner(link.getInputStream());
	            PrintWriter output = new PrintWriter(link.getOutputStream(), true);
	            //Aquí ingresamos por teclado los números a sumar
	            Scanner userEntry = new Scanner(System.in);
	            int firstInt, secondInt, answer;
	            boolean verificador = true;
	            do {
	                System.out.print("Ingrese el primer número: ");
	                firstInt = userEntry.nextInt();
	                System.out.print("Ingrese el segundo número: ");
	                secondInt = userEntry.nextInt();
	                //Enviamos los números al servidor
	                output.println(firstInt);
	                output.println(secondInt);
	                //Obtenemos la respuesta del servidor
	                answer = input.nextInt();
	                System.out.println("\nRespuesta del servidor: " + answer);
	                System.out.println("");
	                verificador = false;
	            } while (verificador=true);
	            verificador = false;
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        catch (NoSuchElementException ne){
	        	//Este error saldría en el caso en que el servidor termine la conexión
	            System.out.println("Conexión cerrada");
	        }
	        finally {
	            try {
	            	//Una vez finalizado 
	                System.out.println("\n Cerrando conexión");
	                link.close(); //Step 4.
	            } catch (IOException ioEx) {
	                System.out.println("Imposible de desconectar");
	                System.exit(1);
	            }
	        }
	    }

}
