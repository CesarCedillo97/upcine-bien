/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import vista.IF_Combos;
import modelo.modeloCombos;
import controlador.conAlerts.controladorSucces;
import vista.alerts.alertSuccess;

/**
 *
 * @author Cesar Cedillo
 */
public class controladorCombos extends ControladorPrincipal implements MouseListener{
    
    IF_Combos vista = new IF_Combos();
    modeloCombos modelo = new modeloCombos();
    
    

    public controladorCombos(IF_Combos vistaP, modeloCombos modeloP) {
        this.modelo= modeloP;
        this.vista = vistaP;
    }

    
    @Override
    public void iniciarVista() {
        vista.panelAgregar.addMouseListener(this);
        vista.panelEditar.addMouseListener(this);
        vista. panelEliminar.addMouseListener(this);
        vista.panelLimpiar.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (true) {
            if (vista.panelAgregar == e.getSource()) {
            alertSuccess vistaSucces = new alertSuccess();
            controladorSucces conSucces = new controladorSucces(vistaSucces, "Se metio al Agregar");
            conSucces.iniciarVista();
        }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
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
    public void mouseExited(MouseEvent e) {
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
