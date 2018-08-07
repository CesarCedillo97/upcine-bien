/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.conAlerts;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import vista.alerts.alertAccept;

/**
 *
 * @author Cesar Cedillo
 */
public class controladorAceptar extends controlador.ControladorPrincipal implements MouseListener{
    alertAccept vista = new alertAccept();
    private String message;

    public controladorAceptar(alertAccept vistaP,String message) {
        this.vista= vistaP;
        this.message = message;
    }
    
     @Override
    public void iniciarVista() {
        this.vista.lblMessage.setText(message);
        this.vista.panelAceptar.addMouseListener(this);
        this.vista.panelRechazar.addMouseListener(this);
        this.vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setTitle("¡Hecho!");
        vista.pack();
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (vista.panelRechazar == e.getSource()) {
            this.vista.dispose();
            resetColor(vista.panelRechazar);
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
            setColorAdd(vista.panelAceptar);
        }else if (vista.panelRechazar == e.getSource()) {
            setColorEliminar(vista.panelRechazar);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (vista.panelAceptar == e.getSource()) {
            resetColor(vista.panelAceptar);
        }else if (vista.panelRechazar == e.getSource()) {
            resetColor(vista.panelRechazar);
        }
    }
    
}
