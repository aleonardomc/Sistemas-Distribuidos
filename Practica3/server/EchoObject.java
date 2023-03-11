
package server;
import java.net.*;
import java.io.*;
import java.text.*;
import java.util.*;
import rmi.EchoInt;
public class EchoObject implements EchoInt {
    String myURL="localhost";
    //Constructor de la clase EchoObject
    public EchoObject(){
        try {
// obtengo el nombre del equipo donde estoy ejecutando y lo guardo en la propiedad MyURL
            myURL=InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) 
        {
     // si no pude conocer el nombre del equipo, mantengo el nombre localhost para MyURL
            myURL="localhost";
        }
    }
// el Metodo Echo que es la implementacion de la interfaz EchoInt
    public String echo(String input) throws java.rmi.RemoteException {

        System.out.println("calculando: '" + input + "'");

        String res="Err0";
        res = OperacionesAritmeticas(input);
        System.out.println("Resultado: '" + res + "'");
        return res;

    }

    public String OperacionesAritmeticas(String cop){

        String resultado = "";
        double aux = 0;
        String operacion = cop;

        // Dividimos la cadena en sus componentes
        String[] componentes = operacion.split("[+-/*]");

        // Obtenemos los operandos y el operador
        double operando1 = Double.parseDouble(componentes[0]);
        double operando2 = Double.parseDouble(componentes[1]);
        char operador = operacion.charAt(componentes[0].length());

        // Realizamos la operación correspondiente
        switch (operador) {
            case '+':
                aux = (operando1 + operando2);
                resultado = Double.toString(aux);
                break;
            case '-':
            aux = (operando1 - operando2);
            resultado = Double.toString(aux);
                break;
            case '*':
            aux = (operando1 * operando2);
            resultado = Double.toString(aux);
                break;
            case '/':
            aux = (operando1 / operando2);
            resultado = Double.toString(aux);
                break;
            default:
                resultado="Operador inválido";
        
        }
        return resultado;
    }

}





