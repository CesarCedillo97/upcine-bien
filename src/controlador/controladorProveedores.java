/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import controlador.conAlerts.controladorAceptar;
import controlador.conAlerts.controladorError;
import controlador.conAlerts.controladorMessage;
import controlador.conAlerts.controladorSucces;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import vista.IF_Proveedores;
import modelo.modeloProveedor;
import vista.alerts.alertAccept;
import vista.alerts.alertError;
import vista.alerts.alertMessage;
import vista.alerts.alertSuccess;
/**
 *
 * @author Jesus
 */
public class controladorProveedores extends ControladorPrincipal implements KeyListener,MouseListener {
    IF_Proveedores vista = new  IF_Proveedores();
    modeloProveedor modelo = new modeloProveedor();
    
    
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
    
    
    
    public controladorProveedores( IF_Proveedores vista, modeloProveedor modProv ) {
    this.vista = vista;
    this.modelo= modProv;
    //declara lo que contiene el internal frame (es como el iniciar vista de las pantallas)
    
    }
    
    
    @Override
    public void iniciarVista() {
        String[] columnas = new String[]{"ID","Empresa","Responsable","Dirección","Teléfono"};
        String txtQuery = "SELECT * FROM proveedor";
        datos = modelo.obtenerDatos(txtQuery);
        vista.JTable.setModel(modelo.obtenerDatosTabla(datos,columnas));
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
          String[] columnas = {"ID","Empresa","Responsable","Dirección","Teléfono"};
          String query = "select * from upcine.proveedor where Nombre_empresa LIKE '"+ vista.bucar_txt.getText() +"%'";
          vista.JTable.setModel(modelo.filtrarTabla(query,columnas));  
            //modelo.buscarDatos();
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

    
}
