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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        String[] columnas = new String[]{"ID","Empresa","Responsable","Dirección","Teléfono"};
        String txtQuery = "SELECT * FROM proveedor";
        datos = modelo.obtenerDatos(txtQuery);
        vista.panelAgregarProv.addMouseListener(this);
        vista.panelEditProv.addMouseListener(this);
        vista.panelEliminarProv.addMouseListener(this);
        vista.JTable.setModel(modelo.obtenerDatosTabla(datos,columnas));
        vista.JTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            fila = vista.JTable.getSelectedRow();
            llenarDatos();
        });
        vista.bucar_txt.addKeyListener(this);
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
