/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clientepagosws;

import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class ClientePagosWS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int idProducto, precio, numeroProductos, monto, cvv, numeroTarjeta, select = 0;      
        String nombre = null;
        Scanner input = new Scanner(System.in);
        Scanner obj = new Scanner(System.in);

// take input from the user
        
        while(select != 3){
            System.out.println("1. | Pagar");
            System.out.println("2. | Comprar");
            System.out.print("Seleccione una de las opciones: ");
            select = input.nextInt();
            
            if (select == 1){
                System.out.println("\n\n------------------------------");
                System.out.println("Entrando al metodo de pago.");
                System.out.println("------------------------------");
                
                System.out.print("Ingrese el monto del pago: ");
                monto = input.nextInt();
                System.out.print("Ingrese el numero de tarjeta: ");
                numeroTarjeta = input.nextInt();
                System.out.print("Ingrese su nombre: ");
                nombre = obj.nextLine();
                System.out.print("Ingrese su el cvv: ");
                cvv = input.nextInt();
                
                System.out.println("\n------------------------------");
                if (pagar(numeroTarjeta, monto, nombre, cvv))
                    System.out.println("Compra realizada.");
                else
                    System.out.println("Compra rechazada. Verifique sus datos");
                System.out.println("------------------------------\n\n");
            }
            else if (select == 2){
             System.out.println("\n\n------------------------------");
                System.out.println("Entrando al metodo de compra.");
                System.out.println("------------------------------");
                
                System.out.print("Ingrese el id del producto: ");
                idProducto = input.nextInt();
                System.out.print("Ingrese el precio unitario: ");
                precio = input.nextInt();
                System.out.print("Ingrese el numero de productos: ");
                numeroProductos = input.nextInt();
                
                System.out.println("\n------------------------------");
                if (comprar(idProducto, precio, numeroProductos))
                    System.out.println("Compra realizada.");
                else
                    System.out.println("Compra rechazada. El total supera el saldo actual.");
                System.out.println("------------------------------\n\n");
            }
        }
        
    }

    private static Boolean comprar(int idProducto, int precio, int numeroProductos) {
        clientepagosws.Transacciones_Service service = new clientepagosws.Transacciones_Service();
        clientepagosws.Transacciones port = service.getTransaccionesPort();
        return port.comprar(idProducto, precio, numeroProductos);
    }

    private static Boolean pagar(long numeroTarjeta, int monto, java.lang.String nombre, int cvv) {
        clientepagosws.Transacciones_Service service = new clientepagosws.Transacciones_Service();
        clientepagosws.Transacciones port = service.getTransaccionesPort();
        return port.pagar(numeroTarjeta, monto, nombre, cvv);
    }
    
}
