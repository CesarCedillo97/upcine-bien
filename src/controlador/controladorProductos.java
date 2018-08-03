/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import vista.IF_productos;
import modelo.modeloProductos;
/**
 *
 * @author Cesar Cedillo
 */
public class controladorProductos extends ControladorPrincipal{
    IF_productos vista = new IF_productos();
    modeloProductos modelo = new modeloProductos();

    public controladorProductos(IF_productos vista, modeloProductos modelo) {
        this.modelo = modelo;
        this.vista = vista;
    }

    @Override
    public void iniciarVista() {
        
    }
    
    
}
