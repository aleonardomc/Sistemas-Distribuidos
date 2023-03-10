import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class calculadora {
    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(4444);
        Socket sc;
        boolean con = true;

        while (con){
            sc = server.accept();

            DataInputStream sockIn = new DataInputStream(sc.getInputStream());
            DataOutputStream sockOut = new DataOutputStream(sc.getOutputStream());

            sockOut.writeUTF("Realize las poreaciones a continuacion: ");
            String operacion = sockIn.readUTF();

            calculadoraHilo hijo = new calculadoraHilo(sockIn, sockOut, operacion);
            hijo.start();

            System.out.println("Conectado con: " + operacion);
        }

    }
}
