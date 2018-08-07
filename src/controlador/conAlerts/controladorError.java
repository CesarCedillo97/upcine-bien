/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.conAlerts;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import vista.alerts.alertError;

/**
 *
 * @author Cesar Cedillo
 */
public class controladorError extends controlador.ControladorPrincipal implements MouseListener {
    alertError vista = new alertError();
    private String message;

    public controladorError(alertError vistaP, String mensaje) {
        this.vista = vistaP;
        this.message= mensaje;
    }
    
     @Override
    public void iniciarVista() {
        this.vista.lblMessage.setText(message);
        this.vista.panelAceptar.addMouseListener(this);
        this.vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setTitle("¡Hecho!");
        vista.pack();
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (vista.panelAceptar == e.getSource()) {
            this.vista.dispose();
            resetColor(vista.panelAceptar);
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
        if (vista.panelAceptar == e.getSource()) {
            setColorEliminar(vista.panelAceptar);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (vista.panelAceptar == e.getSource()) {
            resetColor(vista.panelAceptar);
        }
    }
   
    
    
}
