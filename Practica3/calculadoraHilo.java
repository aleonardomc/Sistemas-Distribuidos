import java.io.*;

public class calculadoraHilo extends Thread{

    private DataInputStream sockin;
    private DataOutputStream sockout;
    private String Operacion;

    public calculadoraHilo (DataInputStream sockin, DataOutputStream sockout, String Operacion) {
        this.sockin = sockin;
        this.sockout= sockout;
        this.Operacion = Operacion;
    }

    @Override
    public void run(){

        double resultado;
        if (Operacion.length() == 0) {
            try {
                sockout.writeUTF("Debe ingresar por lo menos dos numeros y un operador.");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.exit(1);
        }   
        else{
            String[] op = Operacion.split(" ");
            resultado = 0;
            switch (op[1].charAt(0)) {
                case '+':
                    resultado = Double.valueOf(op[0])+Double.valueOf(op[2]);
                    break;
                case '-':
                    resultado = Double.valueOf(op[0])-Double.valueOf(op[2]);
                    break;
                case '*':
                    resultado = Double.valueOf(op[0])*Double.valueOf(op[2]);
                    break;
                case '/':
                resultado = Double.valueOf(op[0])/Double.valueOf(op[2]);
                    break;
                default: 
                try {
                        sockout.writeUTF("Operacion incorrecta");
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                String str = Double.toString(resultado);
                    try {
                        sockout.writeUTF(str);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
            }
        }

    }

}