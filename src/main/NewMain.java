/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import vista.IF_empleados;
import vista.VistaMenuAdm;


import controlador.ConMenuAdm;
import controlador.controladorEmpleados;
/**
 *
 * @author Cesar Cedillo
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IF_empleados uno = new IF_empleados();
        controladorEmpleados conEmp = new controladorEmpleados(uno);
        VistaMenuAdm vistaMenu = new VistaMenuAdm();
        ConMenuAdm newCalis = new ConMenuAdm(vistaMenu, uno);
        newCalis.iniciarVista();
        

    }
    
}
