/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;

import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import vista.forms.AdmFormBoleto;
import vista.IF_precios;
import vista.alerts.alertSuccess;
import vista.alerts.alertError;
import vista.alerts.alertMessage;
import vista.alerts.alertAccept;
import modelo.modeloPrecios;
import controlador.conAlerts.controladorError;
import controlador.conAlerts.controladorSucces;
import controlador.conAlerts.controladorMessage;
import controlador.conAlerts.controladorAceptar;
/**
 *
 * @author Cesar Cedillo
 */
public class controladorPrecios extends ControladorPrincipal implements KeyListener,MouseListener{
    IF_precios vista = new IF_precios();
    modeloPrecios modelo = new modeloPrecios();
    
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
    
    public controladorPrecios( IF_precios vista, modeloPrecios modelo) {
        this.vista= vista;
        this.modelo= modelo;
    }




    @Override
    public void iniciarVista() {
        vista.bucar_txt.addKeyListener(this);
        vista.panelAgregarBol.addMouseListener(this);
        vista.panelEditBol.addMouseListener(this);
        vista.panelEliminarBol.addMouseListener(this);
        vista.JTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            fila = vista.JTable.getSelectedRow();
            llenarDatos();
        });
        fila=-1;
        //Se obtienen los datos de la otra consulta (Para la parte de datos)
        datos = modelo.callObtenerDatos();
        //Se asigna el modelo a la tabla de los datos de la tabla.
        vista.JTable.setModel(modelo.callObtenerDatosTabla());
    }
    
    public void llenarDatos(){
        if(fila!=-1){
            vista.lblId.setText(datos[fila][0]);
            vista.lbTipo.setText(datos[fila][1]);
            vista.lbPrecio.setText(datos[fila][2]);
           
        }
    }
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (vista.bucar_txt == e.getSource()) {
            vista.JTable.setModel(modelo.callFiltrarTabla(vista.bucar_txt.getText()));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(vista.panelAgregarBol == e.getSource()){
            this.vista.setEnabled(false);
            formPrecio form = new formPrecio();
            form.iniciarVistaForm();
        }
        else if(fila==-1){
            conMessage = new controladorMessage(alertMessage, "Primero debes seleccionar un campo de la tabla");
            conMessage.iniciarVista();
        }
        else if(e.getSource() == vista.panelEditBol){
            formPrecio form = new formPrecio(datos[fila][0],datos[fila][1],datos[fila][2]);
            form.iniciarVistaForm();
            fila = -1;
        }
        else if(e.getSource() == vista.panelEliminarBol){
            conAcept = new controladorAceptar(alertAccept, "¿Seguro que desea eliminar el registro?");
            conAcept.iniciarVista();
            conAcept.vista.panelAceptar.addMouseListener(this);
        }
        else if(conAcept.vista.panelAceptar == e.getSource()){
            conAcept.vista.dispose();
            if(modelo.eliminar("mantenimiento", "id", Integer.parseInt(vista.lblId.getText()))){
                if(modelo.eliminar("mantenimiento", "id", Integer.parseInt(vista.lblId.getText()))){
                    conSuccess = new controladorSucces(alertSuccess, "Se ha eliminado exitosamente");
                }
            }
            fila = -1;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (vista.panelAgregarBol == e.getSource()) {
            setColorAdd(vista.panelAgregarBol);
        }
        else if (vista.panelEditBol == e.getSource()) {
            setColorEditar(vista.panelEditBol);
        }
        else if (vista.panelEliminarBol == e.getSource()) {
            setColorEliminar(vista.panelEliminarBol);
        }
        else if (vista.panelLimpiar == e.getSource()) {
            setColorLimpiar(vista.panelLimpiar);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (vista.panelAgregarBol == e.getSource()) {
            resetColorAdd(vista.panelAgregarBol);
        }
        else if (vista.panelEditBol == e.getSource()) {
            resetColorEditar(vista.panelEditBol);
        }
        else if (vista.panelEliminarBol == e.getSource()) {
            resetColorEliminar(vista.panelEliminarBol);
        }
        else if (vista.panelLimpiar == e.getSource()) {
            resetColorLimpiar(vista.panelLimpiar);
        }
    }
    
    private class formPrecio implements MouseListener, WindowListener, KeyListener {
        private String descripcion, precio, id;        
        private boolean opcion = false;//Esta variable es para saber como se utilizará si para agregar o modificar, Modificar = true; Agregar = false; 
        AdmFormBoleto form = new AdmFormBoleto();
        private formPrecio(String id, String descripcion, String precio) {
            this.id = id;
            this.descripcion = descripcion;
            this.precio = precio;
            this.opcion = true;
        }

        private formPrecio() {
            //No lo borren, se ocupa un constructor 
        }
        
        private void iniciarVistaForm(){
            form.setLocationRelativeTo(null);
            form.panelAdd.addMouseListener(this);
            form.panelBack.addMouseListener(this);
            //form.txtEdad.addKeyListener(this);
            form.setVisible(true);
            form.title.setText((opcion?"Modificar ":"Agregar ")+"Empleado");
            if(this.opcion == true)
                llenarInputs();
        }

        private void llenarInputs(){
            form.txtId.setText(this.id);
            form.txtDescripcion.setText(this.descripcion);
            form.txtPrecio.setText(this.precio);
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //Agregar
            if(e.getSource() == form.panelAdd && !opcion){
                int last_id = -1;
                //Se llenan los datos que se van a guardar en la tabla de MANTENIMIENTO
                
                String[] table_columns = {"Descripcion","Precio"};
                String[] table_values = {form.txtDescripcion.getText(),
                                         form.txtPrecio.getText()
                                        };
                //Se llenan los datos que se van a guardar en la tabla LOGIN
//                String[] table_columns2 = {"Usuario","Contraseña","empleado_idEmpleado"};
//                String[] table_values2 = {form.txtUsuario.getText(),
//                                          form.txtContra.getText(),
//                                          String.valueOf(last_id)};
                //verifica que los inputs no esten vaciós
                boolean areNotEmpty = checkIsNotEmpty(table_values);
                //Se abre la conexión, lo hacemos desde aquí para aplicar las transacciones
                Connection con = modelo.abrirConexion();
                try {
                    con.setAutoCommit(false);
                } catch (SQLException ex) {
                    Logger.getLogger(controladorPrecios.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(areNotEmpty && con != null &&(last_id = modelo.insertar("mantenimiento", table_columns, table_values, con)) != -1){
                    //table_values2[2]=String.valueOf(last_id);
                    System.out.println(last_id);
                    
//                    if(modelo.insertar("login", table_columns2, table_values2, con) != -1){
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
//                    }
                    modelo.cerrarConexion(con);
                }
                else{
                    if(!areNotEmpty){
                        conError = new controladorError(alertError, "Por favor llene todos los campos para proseguir");
                        conError.iniciarVista();
                    }
                }
            }
            //para modificar
            else if(e.getSource() == form.panelAdd && opcion){
                int last_id = -1;
                //Se llenan los datos que se van a guardar en la tabla de EMPLEADOS
               
                String[] table_columns = {"Descripcion","Precio"};
                String[] table_values = {form.txtDescripcion.getText(),
                                         form.txtPrecio.getText()
                                        };
                //Se llenan los datos que se van a guardar en la tabla LOGIN
//                String[] table_columns2 = {"Usuario","Contraseña","empleado_idEmpleado"};
//                String[] table_values2 = {form.txtUsuario.getText(),
//                                          form.txtContra.getText(),
//                                          String.valueOf(last_id)};
                //verifica que los inputs no esten vaciós
                boolean areNotEmpty = checkIsNotEmpty(table_values);
                //Se abre la conexión, lo hacemos desde aquí para aplicar las transacciones
                Connection con = modelo.abrirConexion();
                try {
                    con.setAutoCommit(false);
                } catch (SQLException ex) {
                    Logger.getLogger(controladorPrecios.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(areNotEmpty && con != null &&(modelo.modificar("mantenimiento", table_columns, table_values, con))){
       
//                    if(modelo.modificar("login", table_columns2, table_values2, con)){
                        try {
                            con.commit();
                            conSuccess = new controladorSucces(alertSuccess, "¡Se ha modificado con éxito!");
                            conSuccess.iniciarVista();
                            form.dispose();
                        } catch (SQLException ex) {
                            conError = new controladorError(alertError, "Algo ha sucedido, no se pudo realizar commit");
                            conSuccess.iniciarVista();
                            form.dispose();
                        }
//                    }
                    modelo.cerrarConexion(con);
                }
                else{
                    if(!areNotEmpty){
                        conError = new controladorError(alertError, "Por favor llene todos los campos para proseguir");
                        conError.iniciarVista();
                    }
                }
            }
            else if(e.getSource() == form.panelBack){
                form.dispose();
            }
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
            datos=modelo.callObtenerDatos();
            vista.JTable.setModel(modelo.callObtenerDatosTabla());
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

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    
    
    
}
