/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import vista.empleadoOpcionVender;


/**
 *
 * @author Cesar Cedillo
 */
public class controladorOpcVender extends ControladorPrincipal implements MouseListener{
    empleadoOpcionVender vista = new empleadoOpcionVender();
    
    private String id,Nombre;

    public controladorOpcVender(empleadoOpcionVender vistaP, String id) {
        this.vista= vistaP;
        this.id = id;
    }

    @Override
    public void iniciarVista() {
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        
        vista.panelBack.addMouseListener(this);
        vista.panelFunciones.addMouseListener(this);
        vista.panelProductos.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    
    
    
}
