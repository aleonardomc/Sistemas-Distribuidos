import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;

public class FileTransferClient extends UnicastRemoteObject implements FileTransfer {
    public FileTransferClient() throws RemoteException {
        super();
    }

    int conta = 0;
    byte[][] bufferTemp = new byte[11][];
    ArrayList <Integer> position = new ArrayList<>();

    @Override
    public void transferFile(byte[] data, String filename) throws RemoteException {

        try (FileOutputStream fos = new FileOutputStream(filename, false)) {
            
            // Agregar el número de paquete recibido a una lista 
            position.add((int)data[0]);
            // Agregar el paquete sin la cabecera a una matriz
            bufferTemp[conta] = Arrays.copyOfRange(data, 1, data.length);
            //System.out.println("He recibido el paquete: " + position.get(conta) + ". \tMe faltan: " + (10-conta));
            StringBuilder barra = new StringBuilder("[");

            conta += 1;

            for (int i = 0; i < 10; i++) {
                if (i < conta) {
                    barra.append("=");
                } else {
                    barra.append(" ");
                }
            }
            barra.append("]");
            System.out.print("\r" + barra.toString() + " " + conta + "/10." + " Recibi el paquete: " + position.get(conta-1));
                
            // Cuando el contador llega a 10, ya se recibieron todos los paquetes de forma desordenada. Por lo que
            // con ayuda de la lista "position", se busca la posicion de los paquetes del 0 al 9 y se escriben en 
            // el archivo
            if (conta == 10){
                barra.append("]");
                for (int j = 0; j < 10; j += 1)
                    fos.write(bufferTemp[position.indexOf(j)]);                 
                conta = 0;
                System.out.println("\nArchivo recibido con exito. c:");
            }
        } 
        catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
 
    public static void main(String[] args) throws RemoteException {
        try {
            FileTransferClient server = new FileTransferClient();
            Registry registry = LocateRegistry.createRegistry(1097);
            registry.bind("FileTransfer", server);
            System.out.println("Client ready 3. Waiting for server");
        } 
        catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}