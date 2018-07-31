/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import vista.IF_empleados;
import modelo.modeloEmpleados;
/**
 *
 * @author Cesar Cedillo
 */
public class controladorEmpleados extends ControladorPrincipal implements KeyListener{
    IF_empleados vista = new IF_empleados();
    modeloEmpleados modelo = new modeloEmpleados();

    public controladorEmpleados( IF_empleados vista, modeloEmpleados modelo) {
    this.vista= vista;
    this.modelo= modelo;
    }

    @Override
    public void iniciarVista() {
        vista.bucar_txt.addKeyListener(this);
        vista.JTable.setModel(modelo.consultarPeliculas());
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (vista.bucar_txt == e.getSource()) {
            vista.JTable.setModel(modelo.buscarDatos(vista.bucar_txt.getText()));
        }
    }

    
    
    
}
