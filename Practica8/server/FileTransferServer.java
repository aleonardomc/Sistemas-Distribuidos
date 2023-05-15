import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileTransferServer {
    public static void main(String[] args) throws RemoteException, NotBoundException, IOException, InterruptedException {
        //String filename = "archivotransferir.txt";
        String filename = "video.mp4";
        File file = new File(filename);
        long fileSize = file.length();
        long chunkSize = fileSize / 10;
        
        // Bits de relleno para que el tamaño sea multiplo de 10, de esta forma evitamos un "null" al momento de
        // enviar los datos.
        if (fileSize % 10 != 0){
            int numRelleno = 10 - ((int)fileSize - (int)chunkSize*10);
            byte [] relleno = new byte[numRelleno];
            for(int i = 0; i < numRelleno; i ++){
                relleno[i] = (byte) ' ';
            }
            try (FileOutputStream fos = new FileOutputStream(filename, true)) {
                fos.write(relleno);
            } catch (IOException e) {
                System.err.println("Error writing file: " + e.getMessage());
            }
        }
        fileSize = file.length();
        chunkSize = fileSize / 10;

        Registry registry1 = LocateRegistry.getRegistry("localhost", 1099);
        Registry registry2 = LocateRegistry.getRegistry("localhost", 1098);
        Registry registry3 = LocateRegistry.getRegistry("localhost", 1097);
        Registry registry4 = LocateRegistry.getRegistry("localhost", 1096);
        Registry registry5 = LocateRegistry.getRegistry("localhost", 1095);

        FileTransfer stub1 = (FileTransfer) registry1.lookup("FileTransfer");
        FileTransfer stub2 = (FileTransfer) registry2.lookup("FileTransfer");
        FileTransfer stub3 = (FileTransfer) registry3.lookup("FileTransfer");
        FileTransfer stub4 = (FileTransfer) registry4.lookup("FileTransfer");
        FileTransfer stub5 = (FileTransfer) registry5.lookup("FileTransfer");


        // Generacion de los numeros aleatorios
        List<Integer> fragmentNumbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) 
            fragmentNumbers.add(i);
        Collections.shuffle(fragmentNumbers);
        System.out.println(fragmentNumbers);

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            byte[][] bufferTemp = new byte[11][(int) chunkSize];
            int bytesRead = 0;
            int conta = 0;
            
            // Lectura de todos los paquetes del archivo (En este caso 10). Y se almacenan en bufferTemp, para que por
            // medio de la lista "position", se desordenen.
            while ((bytesRead = bis.read(bufferTemp[conta])) != -1)
                conta += 1;

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int header;
            for (int i = 0; i < 10; i++){ 
                
                // Se toma el primero numero de la lista aleatoria para enviar ese paquete y agregarle es numero como cabecera.
                // Guardandose el paquete con su cabecera en "newbytes"
                header = fragmentNumbers.get(i);
                outputStream.write(header);
                outputStream.write(bufferTemp[fragmentNumbers.get(i)]);
                byte[] newbytes = outputStream.toByteArray();
                outputStream.reset();
                Thread.sleep(1000);
                stub1.transferFile(newbytes, file.getName());
                stub2.transferFile(newbytes, file.getName());
                stub3.transferFile(newbytes, file.getName());
                stub4.transferFile(newbytes, file.getName());
                stub5.transferFile(newbytes, file.getName());
            }
        }
    }
}