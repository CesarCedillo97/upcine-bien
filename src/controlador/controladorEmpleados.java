/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import vista.IF_empleados;
/**
 *
 * @author Cesar Cedillo
 */
public class controladorEmpleados extends ControladorPrincipal{
    IF_empleados vista = new IF_empleados();

    public controladorEmpleados( IF_empleados vista) {
    this.vista= vista;
    
    //declara lo que contiene el internal frame (es como el iniciar vista de las pantallas)
    
    
    }

    
    
    
}
