package lab2;
//Codigo obtenido de:
//https://stackoverflow.com/questions/34694835/java-beginner-client-server-sending-multiple-integers-to-a-socket
//modificado para que cumpla con lo requisitos del laboratorio 2
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SumaSerTCP 
{
	private static ServerSocket serverSocket;
    private static final int PORT = 1234;

    public static void main(String[] args)
    {
        //Abrimos un puerto por el cual recibiremos la información
    	System.out.println("Abriendo puerto:  " +PORT);
    	long initialTime = System.currentTimeMillis();
		System.out.println("Tiempo inicial: "+initialTime+"\n");
        try {
            serverSocket = new ServerSocket(PORT);        }
        catch (IOException ioex){
            System.out.println("Puerto no abierto!");
            //Se colocan en un try-catch de pronto se produzcan errores
            System.exit(1);        }
          handleClient();
  }

    private static void handleClient()
    {
        Socket link = null;
        try {
            link = serverSocket.accept();
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);
            int firstInt = input.nextInt();
            int secondInt = input.nextInt();
            boolean verificador = true;
            int answer;

            while (verificador=true)
            {
            	//aquí sumamos los valores enviados por el cliente
            	answer = firstInt + secondInt;
                //aquí enviamos la respuesta al cliente
            	output.println(answer);
                firstInt = input.nextInt();
                secondInt = input.nextInt();
                verificador = false;
            }
            verificador = false;

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                System.out.println("Cerrando conexión");
                link.close();
            }
            catch (IOException ie)
            {
                System.out.println("Imposible cerrar conexión");
                System.exit(1);
            }
        }
    }

}
