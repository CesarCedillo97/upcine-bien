/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import vista.IF_Proveedores;
import modelo.modeloProveedor;
/**
 *
 * @author Jesus
 */
public class controladorProveedores extends ControladorPrincipal implements KeyListener {
    IF_Proveedores vista = new  IF_Proveedores();
    modeloProveedor modelo = new modeloProveedor();
    
    public controladorProveedores( IF_Proveedores vista, modeloProveedor modProv ) {
    this.vista = vista;
    this.modelo= modProv;
    //declara lo que contiene el internal frame (es como el iniciar vista de las pantallas)
    
    }
    
    @Override
    public void iniciarVista() {
        vista.JTable.setModel(modelo.cargarDatos());
        vista.bucar_txt.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource() == vista.bucar_txt)
        {
          vista.JTable.setModel(modelo.buscarDatos(vista.bucar_txt.getText()));  
//modelo.buscarDatos();
        }
    }

    
}
