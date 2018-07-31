/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;


/*Desktop
import vista.VistaMenuAdm;
import controlador.ConMenuAdm;

//empleado
import vista.IF_empleados;
import controlador.controladorEmpleados;
import modelo.modeloEmpleados;

//pelis
import vista.IF_peliculas;
import modelo.modeloPelicuas;
import controlador.controladorPeliculas;

//Proveedor
import vista.IF_Proveedores;
import modelo.modeloProveedor;
import controlador.controladorProveedores;*/

import vista.Login;
import modelo.modeloLogin;
import controlador.controladorLogin;
/**
 *
 * @author Cesar Cedillo
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //para el empleado
        
        Login vista = new Login();
        modeloLogin modelo = new modeloLogin();
        controladorLogin control = new controladorLogin(modelo, vista);
        control.iniciarVista();
        

    }
    
}
