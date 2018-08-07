/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.awt.event.MouseListener;
import vista.IF_productos;
import modelo.modeloProductos;

/**
 *
 * @author Cesar Cedillo
 */
public class controladorProductos extends ControladorPrincipal implements MouseListener{
    IF_productos vista = new IF_productos();
    modeloProductos modelo = new modeloProductos();
    


    public controladorProductos(IF_productos vista, modeloProductos modelo) {
        this.modelo = modelo;
        this.vista = vista;

    }

    
    @Override
    public void iniciarVista() {
        vista.panelAgregar.addMouseListener(this);
        vista.panelEditar.addMouseListener(this);
        vista.panelEliminar.addMouseListener(this);
        vista.panelLimpiar.addMouseListener(this);
        
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        
        
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent me) {
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent me) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        if (vista.panelAgregar == e.getSource()) {
            setColorAdd(vista.panelAgregar);
        }
        else if (vista.panelEditar == e.getSource()) {
            setColorEditar(vista.panelEditar);
        }
        else if (vista.panelEliminar == e.getSource()) {
            setColorEliminar(vista.panelEliminar);
        }
        else if (vista.panelLimpiar == e.getSource()) {
            setColorLimpiar(vista.panelLimpiar);
        }
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        if (vista.panelAgregar == e.getSource()) {
            resetColorAdd(vista.panelAgregar);
        }
        else if (vista.panelEditar == e.getSource()) {
            resetColorEditar(vista.panelEditar);
        }
        else if (vista.panelEliminar == e.getSource()) {
            resetColorEliminar(vista.panelEliminar);
        }
        else if (vista.panelLimpiar == e.getSource()) {
            resetColorLimpiar(vista.panelLimpiar);
        }
    }
    
    
}
