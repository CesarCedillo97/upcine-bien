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
import vista.IF_funciones;
import modelo.modeloFunciones;
import vista.alerts.alertAccept;
import vista.alerts.alertError;
import vista.alerts.alertMessage;
import vista.alerts.alertSuccess;
/**
 *
 * @author Adrián Scott
 */
public class controladorFunciones extends ControladorPrincipal{
    IF_funciones vista;
    modeloFunciones modelo;

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
    
    public controladorFunciones(IF_funciones vista, modeloFunciones modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
    
    @Override
    public void iniciarVista() {
        
    }
    
}
