import java.net.*; 
import java.io.*;

public class servidor {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Debe ingresar un puerto.");
            System.exit(1);
        }
        
        int puerto = Integer.parseInt(args[0]);
        ServerSocket socketServidor = null;
        
        try{
            socketServidor = new ServerSocket(puerto);
        }
        catch (Exception e){
            System.out.println(e);
        }
            
        Socket socketCliente = null;
            
        try{
            socketCliente = socketServidor.accept();
        }
        
        catch (Exception e){
            System.out.println(e);
        }

        PrintWriter escritor = new PrintWriter(socketCliente.getOutputStream(), true);
        BufferedReader lector = new BufferedReader(new InputStreamReader (socketCliente.getInputStream()));
    
        String mensajeLeido = "";
        String mensajeEnviado[] = {"Hola", "Buenas tardes", "Lo siento, no comprendo", "Saludos coordiales"};
        int posicion, tam = 0;

        System.out.println("Conectado con: " + socketCliente.getPort());
        while (mensajeLeido != null){
            mensajeLeido = lector.readLine();
            posicion = (int) (Math.random() * mensajeEnviado.length);
            escritor.println("--> Servidor: " + mensajeEnviado[posicion]);
            tam += 1;
            if (tam == mensajeEnviado.length + 1)
                break;
            /*System.out.println("--> Cliente: " + mensajeLeido);
            System.out.println("--> Servidor: " + mensajeEnviado[2]);*/
        }        
    }
}