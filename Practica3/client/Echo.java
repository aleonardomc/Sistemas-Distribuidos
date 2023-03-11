
package client;
import java.io.*;
import java.net.*;

public class Echo {
    //definimos el Stub del cliente
    private static EchoObjectStub ss;
    
    public static void main(String[] args) 
    {
        String cadena="";
        //creamos el STUB
        ss = new EchoObjectStub();
        //EJERCICIO: crear una instancia del stub
    // le asignamos al STUB el puerto y nombre del equipo HOST 
        //( es decir el nombre del servidor)
        ss.setHostAndPort("127.0.0.1",7);
       // leemos de teclado 
        BufferedReader stdIn = new BufferedReader( new InputStreamReader(System.in));
        //escribimos en pantalla
        PrintWriter stdOut = new PrintWriter(System.out);
        String input,output;
        try {  
        
            while(true){
            //EJERCICIO: el bucle infinito:
                BufferedReader in =
                new BufferedReader(new InputStreamReader(System.in));//EJERCICIO: Leer de teclado
                // leo del teclado 
                System.out.print("Ingrese una operación aritmética básica (por ejemplo, 2+3): ");
                cadena=in.readLine();
                     //EJERCICIO: Invocar el stub
                //EJERCICIO: Imprimir por pantalla
                System.out.println("Resultado: " + ss.echo(cadena));

            }
        } 
        //catch (UnknownHostException e) {
            //System.err.println("Don't know about host: "+ args[0]);
        //} 
        catch (IOException e) {
            System.err.println("Falla conexion de E/S con el host:"+args[0]);
        }
    }
}
