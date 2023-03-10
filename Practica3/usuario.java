import java.io.*;
import java.net.*;
import java.util.Scanner;

public class usuario {

    public static void main(String[] args){

        try {
            Scanner sn = new Scanner(System.in);
            sn.useDelimiter("\n");

            Socket sc = new Socket("127.0.0.1", 4444);

            DataInputStream sockIn = new DataInputStream(sc.getInputStream());
            DataOutputStream sockOut = new DataOutputStream(sc.getOutputStream());

            String mensaje = sockIn.readUTF();
            System.out.print(mensaje);

            String operacion = sn.next();
            sockOut.writeUTF(operacion);

            String resultado = sockIn.readUTF();
            System.out.println(resultado);

            // usuarioHilo hijo = new usuarioHilo(sockIn, sockOut);

            // hijo.start();
            // hijo.join();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
