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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import vista.IF_Proveedores;
import modelo.modeloProveedor;
import vista.alerts.alertAccept;
import vista.alerts.alertError;
import vista.alerts.alertMessage;
import vista.alerts.alertSuccess;
import vista.forms.AdmFormProveedores;
import vista.forms.vistaFormEmpleados;
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
        vista.panelAgregarProv.addMouseListener(this);
        vista.panelEditProv.addMouseListener(this);
        vista.panelEliminarProv.addMouseListener(this);
        cargarTabla();
        vista.JTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            fila = vista.JTable.getSelectedRow();
            llenarDatos();
        });
        vista.bucar_txt.addKeyListener(this);
    }
    public void cargarTabla(){
        String[] columnas = new String[]{"ID","Empresa","Responsable","Dirección","Teléfono"};
        String txtQuery = "SELECT * FROM proveedor";
        datos = modelo.obtenerDatos(txtQuery);
        vista.JTable.setModel(modelo.obtenerDatosTabla(datos,columnas));
    }
    public void llenarDatos(){
        if(fila!=-1){
            vista.lblId.setText(datos[fila][0]);
            vista.lblEmpresa.setText(datos[fila][1]);
            vista.lblResponsable.setText(datos[fila][2]);
            vista.lblDireccion.setText(datos[fila][3]);
            vista.lblTelefono.setText(datos[fila][4]);
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
        if(e.getSource() == vista.panelAgregarProv){
            formProveedor form = new formProveedor();
            form.iniciarVistaForm();
        }
        else if(e.getSource() == vista.panelEditProv){
            formProveedor form = new formProveedor(vista.lblEmpresa.getText(),
                                                   vista.lblResponsable.getText(),
                                                   vista.lblTelefono.getText(),
                                                   vista.lblDireccion.getText(),
                                                   vista.lblId.getText());
            form.iniciarVistaForm();
        }
        else if(e.getSource() == vista.panelEliminarProv){
            conAcept = new controladorAceptar(alertAccept, "¿Seguro que desea eliminar el registro?");
            conAcept.iniciarVista();
            conAcept.vista.panelAceptar.addMouseListener(this);
        }
        else if(conAcept.vista.panelAceptar == e.getSource()){
            conAcept.vista.dispose();
            if(modelo.eliminar("proveedor", "idProveedor", Integer.parseInt(vista.lblId.getText()))){
                conSuccess = new controladorSucces(alertSuccess, "Se ha eliminado exitosamente");
                cargarTabla();
            }
            fila = -1;
        }
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

    private class formProveedor implements MouseListener, WindowListener {
        private String empresa,responsable,telefono,direccion,id;        
        private boolean opcion = false;//Esta variable es para saber como se utilizará si para agregar o modificar, Modificar = true; Agregar = false; 
        AdmFormProveedores form = new AdmFormProveedores();

        public formProveedor(String empresa, String responsable, String telefono, String direccion, String id) {
            this.empresa = empresa;
            this.responsable = responsable;
            this.telefono = telefono;
            this.direccion = direccion;
            this.id = id;
            opcion = true;
        }
        
        
        private formProveedor() {
            //No lo borren, se ocupa un constructor 
        }
        
        private void iniciarVistaForm(){
            form.setLocationRelativeTo(null);
            form.panelAdd.addMouseListener(this);
            form.panelBack.addMouseListener(this);
            form.setVisible(true);
            form.title.setText((opcion?"Modificar ":"Agregar ")+"Empleado");
            if(this.opcion == true)
                llenarInputs();
        }

        private void llenarInputs(){
            form.txtId.setText(this.id);
            form.txtEmpresa.setText(this.empresa);
            form.txtResponsable.setText(this.responsable);
            form.txtTelefono.setText(this.telefono);
            form.txtDireccion.setText(this.direccion);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() == form.panelAdd && !opcion){
                String[] columnas = {"Nombre_empresa","Nombre_responsable","Direccion","Telefono"};
                String[] valores = {form.txtEmpresa.getText(),
                                    form.txtResponsable.getText(),
                                    form.txtDireccion.getText(),
                                    form.txtTelefono.getText()};
                boolean notEmpty = checkIsNotEmpty(valores);
                Connection con = modelo.abrirConexion();
                try {
                    con.setAutoCommit(false);
                } catch (SQLException ex) {
                    Logger.getLogger(controladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(notEmpty && con!= null && modelo.insertar("proveedor", columnas, valores, con) != -1){
                    try {
                        conSuccess = new controladorSucces(alertSuccess, "¡Se ha agregado con éxito!");
                        conSuccess.iniciarVista();
                        form.dispose();
                        //si todo se inserta se realiza el commit
                        con.commit();
                    } catch (SQLException ex) {
                        conError = new controladorError(alertError, "Algo ha sucedido, no se pudo realizar commit");
                        conSuccess.iniciarVista();
                        form.dispose();
                    }
                    modelo.cerrarConexion(con);
                }
                else{
                    if(!notEmpty){
                        conError = new controladorError(alertError, "Por favor llene todos los campos para proseguir");
                        conError.iniciarVista();
                    }
                }
            }
            else if(e.getSource() == form.panelAdd && opcion){
                String[] columnas = {"idProveedor","Nombre_empresa","Nombre_responsable","Direccion","Telefono"};
                String[] valores = {form.txtId.getText(),
                                    form.txtEmpresa.getText(),
                                    form.txtResponsable.getText(),
                                    form.txtDireccion.getText(),
                                    form.txtTelefono.getText()};
                boolean notEmpty = checkIsNotEmpty(valores);
                Connection con = modelo.abrirConexion();
                try {
                    con.setAutoCommit(false);
                } catch (SQLException ex) {
                    Logger.getLogger(controladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(notEmpty && modelo.modificar("proveedor", columnas, valores, con)){
                    try {
                        conSuccess = new controladorSucces(alertSuccess, "¡Se ha modificado con éxito!");
                        conSuccess.iniciarVista();
                        form.dispose();
                        //si todo se inserta se realiza el commit
                        con.commit();
                    } catch (SQLException ex) {
                        conError = new controladorError(alertError, "Algo ha sucedido, no se pudo realizar commit");
                        conSuccess.iniciarVista();
                        form.dispose();
                    }
                    modelo.cerrarConexion(con);
                }
                else{
                    if(!notEmpty){
                        conError = new controladorError(alertError, "Por favor llene todos los campos para proseguir");
                        conError.iniciarVista();
                    }
                }
            }
            else if(e.getSource() == form.panelBack){
                form.dispose();
            }
            cargarTabla();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if(e.getSource() == form.panelAdd){
                setColorAceptar(form.panelAdd);
            }
            else if(e.getSource() == form.panelBack){
                setColorCancelar(form.panelBack);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(e.getSource() == form.panelAdd){
                resetColorGrey(form.panelAdd);
            }
            else if(e.getSource() == form.panelBack){
                resetColorGrey(form.panelBack);
            }
        }

        @Override
        public void windowOpened(WindowEvent e) {
            
        }

        @Override
        public void windowClosing(WindowEvent e) {
            
        }

        @Override
        public void windowClosed(WindowEvent e) {
            
        }

        @Override
        public void windowIconified(WindowEvent e) {
            
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
           
        }

        @Override
        public void windowActivated(WindowEvent e) {
          
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            
        }
    }
}
