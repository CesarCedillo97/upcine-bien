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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import vista.IF_funciones;
import modelo.modeloFunciones;
import vista.alerts.alertAccept;
import vista.alerts.alertError;
import vista.alerts.alertMessage;
import vista.alerts.alertSuccess;
import vista.forms.AdmFormFunciones;
/**
 *
 * @author Adrián Scott
 */
public class controladorFunciones extends ControladorPrincipal implements MouseListener, KeyListener{
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
        vista.bucar_txtPro.addKeyListener(this);
        vista.panelAgregar.addMouseListener(this);
        vista.panelEditar.addMouseListener(this);
        vista.panelEliminar.addMouseListener(this);
        vista.JTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            fila = vista.JTable.getSelectedRow();
            llenarDatos();
        });
        fila=-1;
        datos = modelo.callObtenerDatos();
        vista.JTable.setModel(modelo.callObtenerDatosTabla());
    }
    
    public void llenarDatos(){
        if(fila!=-1){
            vista.lblID.setText(datos[fila][0]);
            vista.lblFechaInicio.setText(datos[fila][2]);
            vista.lblFechaFin.setText(datos[fila][3]);
            vista.lblHoraInicio.setText(datos[fila][4]);
            vista.lblHoraFin.setText(datos[fila][5]);
            vista.lblEstado.setText(datos[fila][6]);
            vista.lblPelicula.setText(datos[fila][8]);
            vista.lblSalas.setText(datos[fila][10]);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(vista.panelAgregar == e.getSource()){
            formFunciones form = new formFunciones();
            form.iniciarFormFunciones();
        }
        else if(vista.panelEditar == e.getSource()){
            datos = modelo.callObtenerDatos();
            formFunciones form = new formFunciones(datos[fila][0], datos[fila][1], datos[fila][2], datos[fila][3], datos[fila][4], datos[fila][5], datos[fila][6], datos[fila][7], datos[fila][8], datos[fila][9], datos[fila][10]);
            form.iniciarFormFunciones();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    public void mouseReleased(MouseEvent e){
        
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
    
    public class formFunciones implements MouseListener{
        String id,inicioVenta, fechaInicio, fechaFin, horaInicio, horaFin, estado, idPelicula, pelicula, idSala, sala;
        boolean opcion = false;
        AdmFormFunciones form = new AdmFormFunciones();

        public formFunciones(String id, String inicioVenta, String fechaInicio, String fechaFin, String horaInicio, String horaFin, String estado, String idPelicula, String pelicula, String idSala, String sala) {
            this.id = id;
            this.inicioVenta = inicioVenta;
            this.fechaInicio = fechaInicio;
            this.fechaFin = fechaFin;
            this.horaInicio = horaInicio;
            this.horaFin = horaFin;
            this.estado = estado;
            this.idPelicula = idPelicula;
            this.pelicula = pelicula;
            this.idSala = idSala;
            this.sala = sala;
            opcion = true;
        }
        
        public formFunciones() {
            
        }
        
        public void iniciarFormFunciones(){
            form.setLocationRelativeTo(null);
            form.panelAdd.addMouseListener(this);
            form.panelBack.addMouseListener(this);            
            form.setVisible(true);
            form.title.setText((opcion?"Modificar ":"Agregar ")+"función");
            if(this.opcion == true)
                llenarInputs();
        }
        
        private void llenarInputs(){
            form.txtId.setText(datos[fila][0]);
            try {
                modelo.callObtenerDatos();
                form.txtInicioVenta.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(datos[fila][2]));
                form.txtFechaFin.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(datos[fila][3]));
                form.txtHoraInicio.setValue(new SimpleDateFormat("hh:mm:ss").parse(datos[fila][4]));
                form.txtHorafin.setValue(new SimpleDateFormat("hh:mm:ss").parse(datos[fila][5]));
            } catch (ParseException ex) {
                
            }
            //form.txtFechaIni.setText(datos[fila][2]);
            //form.txtFechaFin.setText(datos[fila][3]);
            //form.txtHoraInicio.setText(datos[fila][4]);
            //form.txtHorafin.setText(datos[fila][5]);
            form.txtEstatus.setSelectedItem(datos[fila][6]);
            //form.txtComboPeli.setText(datos[fila][8]);
            //form.txtComboSala.setText(datos[fila][10]);
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

}
