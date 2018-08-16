/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.modeloSalas;
import vista.IF_salas;
import vista.alerts.alertAccept;
import vista.alerts.alertError;
import vista.alerts.alertMessage;
import vista.alerts.alertSuccess;
import controlador.conAlerts.controladorAceptar;
import controlador.conAlerts.controladorError;
import controlador.conAlerts.controladorMessage;
import controlador.conAlerts.controladorSucces;
/**
 *
 * @author Adrián Scott
 */
public class controladorSalas extends ControladorPrincipal{
    modeloSalas modelo;
    IF_salas salas;
    
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
    
    public controladorSalas(modeloSalas modelo, IF_salas salas) {
        this.modelo = modelo;
        this.salas = salas;
    }
    
    @Override
    public void iniciarVista() {
        
    }
    
}
