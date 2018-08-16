/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

//Los de aquí
import vista.VentaProductos;
import modelo.modeloVentaProductos;

//para los Alerts
import vista.alerts.alertAccept;
import vista.alerts.alertError;
import vista.alerts.alertMessage;
import vista.alerts.alertSuccess;

//para los Alerts
import controlador.conAlerts.controladorAceptar;
import controlador.conAlerts.controladorError;
import controlador.conAlerts.controladorMessage;
import controlador.conAlerts.controladorSucces;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Cesar Cedillo
 */
public class ControladorVentaProductos extends ControladorPrincipal implements MouseListener{
    VentaProductos vista = new VentaProductos();
    modeloVentaProductos modelo = new modeloVentaProductos();
    private int ID;
    
    private String[][] productos, combos;

    public ControladorVentaProductos(VentaProductos vistaP, modeloVentaProductos modeloP, int id) {
        this.vista=vistaP;
        this.modelo = modeloP;
        this.ID = id;
    }
    
    
    
    @Override
    public void iniciarVista() {
         vista.setLocationRelativeTo(null);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
}
