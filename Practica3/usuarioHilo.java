import java.io.*;;


public class usuarioHilo extends Thread {

    private DataInputStream in;
    private DataOutputStream out;

    public usuarioHilo (DataInputStream in, DataOutputStream out) {
        this.in = in;
        this.out= out;
    }

    @Override
    public void run(){

        

    }
}
