import java.io.*;
import java.net.*;

public class cliente {
    public static void main(String[] args) throws IOException {

        /* Estableciendo IP y puerto */
        String hostName = "127.0.0.1";
        int portNumber = 4444;
        /* creando socket */
        Socket firstSocket = new Socket(hostName, portNumber);
        /* se crea buffer de salida para comunicacion bilateral */
        PrintWriter out = new PrintWriter(firstSocket.getOutputStream(), true);
        /* se crean los buffers de entrada para el teclado y el socket desde c */
        BufferedReader in = new BufferedReader(new InputStreamReader(firstSocket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput = null;
        int aux = 0;
        /* ciclo condicionado para terminar la conexion cuando se escribe un cero */
        while (aux == 0) 
        {
            userInput = stdIn.readLine();
            if(userInput.equals("0")){
                aux = 1;
                System.out.println("received: Conexion terminada");
            }
            else{
                out.println(userInput);
                System.out.println("received: " + in.readLine());
            }
        }
        in.close();
        stdIn.close();
        firstSocket.close();

    }
}