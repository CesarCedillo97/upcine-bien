/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.modeloClientes;
import vista.IF_clientes;
import vista.alerts.alertAccept;
import vista.alerts.alertError;
import vista.alerts.alertMessage;
import vista.alerts.alertSuccess;
import controlador.conAlerts.controladorAceptar;
import controlador.conAlerts.controladorError;
import controlador.conAlerts.controladorMessage;
import controlador.conAlerts.controladorSucces;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.event.ListSelectionEvent;
/**
 *
 * @author Adrián Scott
 */
public class controladorClientes extends ControladorPrincipal implements MouseListener, KeyListener{
    modeloClientes modelo;
    IF_clientes vista;

    alertAccept alertAccept = new alertAccept();
    alertError alertError = new alertError();
    alertSuccess alertSuccess = new alertSuccess();
    alertMessage alertMessage = new alertMessage();
    
    controladorAceptar conAcept;
    controladorError conError;
    controladorSucces conSuccess;
    controladorMessage conMessage;
    
    String[][] datosTabla;
    String[][] datos;
    String[] columnasTabla;
    int fila = -1;
    
    public controladorClientes(IF_clientes vista, modeloClientes modelo) {
        this.modelo = modelo;
        this.vista = vista;
    }
    
    @Override
    public void iniciarVista() {
        vista.panelAgregar.addMouseListener(this);
        vista.panelEditar.addMouseListener(this);
        vista.panelEliminar.addMouseListener(this);
        vista.bucar_txtPro.addKeyListener(this);
        fila=-1;
        
        vista.JTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            fila = vista.JTable.getSelectedRow();
            llenarDatos();
        });
        
        datos = modelo.callObtenerDatos();
        vista.JTable.setModel(modelo.callObtenerDatosTabla());
    }
    
    public void llenarDatos(){
        if(fila!=-1){
            vista.lblID.setText(datos[fila][0]);
            vista.lbNombre.setText(datos[fila][1]);
            vista.lblDireccion.setText(datos[fila][2]);
            vista.lblCorreo.setText(datos[fila][3]);
            vista.lblPuntos.setText(datos[fila][4]);
            vista.lblFechaAnt.setText(datos[fila][5]);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (vista.bucar_txtPro == e.getSource()) {
            vista.JTable.setModel(modelo.callFiltrarTabla(vista.bucar_txtPro.getText()));
        }
    }
}
