/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

//Desktop
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
import controlador.controladorProveedores;
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
        IF_empleados emp = new IF_empleados();
        modeloEmpleados modEmp = new modeloEmpleados();
        controladorEmpleados conEmp = new controladorEmpleados(emp, modEmp);
        
        //Para las pelis
        IF_peliculas peli = new IF_peliculas();
        modeloPelicuas modPeli = new modeloPelicuas();
        controladorPeliculas conPeli = new controladorPeliculas(peli, modPeli);
        
        //Para los proveedores
        IF_Proveedores prove = new IF_Proveedores();
        modeloProveedor modProv = new modeloProveedor();
        controladorProveedores conProv = new controladorProveedores(prove,modProv);
        
        VistaMenuAdm vistaMenu = new VistaMenuAdm();
        ConMenuAdm newCalis = new ConMenuAdm(vistaMenu, emp, peli,prove);
        newCalis.iniciarVista();
        

    }
    
}
