/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package org.me.transacciones;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Usuario
 */
@WebService(serviceName = "Transacciones")
public class Transacciones {

    /**
     * This is a sample web service operation
     */
    /*
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    */
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "Pagar")
    public Boolean Pagar(@WebParam(name = "numeroTarjeta") int numeroTarjeta, @WebParam(name = "monto") int monto, @WebParam(name = "nombre") String nombre, @WebParam(name = "cvv") int cvv) {
        if (numeroTarjeta == 123456789 && monto < 1200 && cvv == 456 && "Juan Perez".equals(nombre))
            return true;
        
        return false;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Comprar")
    public Boolean Comprar(@WebParam(name = "idProducto") int idProducto, @WebParam(name = "precio") int precio, @WebParam(name = "numeroProductos") int numeroProductos) {
        int total = precio * numeroProductos;
        if (total <= 1200)
            return true;
        
        return false;
    }
}
