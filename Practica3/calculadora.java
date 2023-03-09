import java.io.*;


public class calculadora {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Debe ingresar dos numeros.");
            System.exit(1);
        }   

        int x = Integer.parseInt(args[0]);
        int y= Integer.parseInt(args[0]);

        int suma = suma(x,y);
        System.out.println("El resultado de la suma es: " + suma);

        hilo hilo1 = new hilo();
        hilo1.start();
    }


    public static int suma (int x, int y){
        return x + y;
    }

    public static int resta (int x, int y){
        return x - y;
    }

    public static int multiplicacion (int x, int y){
        return x * y;
    }

    public static int division (int x, int y){
        return x / y;
    }
}
