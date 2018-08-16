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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import vista.forms.vistaFormEmpleados;
import vista.IF_empleados;
import vista.alerts.alertSuccess;
import vista.alerts.alertError;
import vista.alerts.alertMessage;
import vista.alerts.alertAccept;
import modelo.modeloEmpleados;
import controlador.conAlerts.controladorError;
import controlador.conAlerts.controladorSucces;
import controlador.conAlerts.controladorMessage;
import controlador.conAlerts.controladorAceptar;
/**
 *
 * @author Cesar Cedillo
 */
public class controladorEmpleados extends ControladorPrincipal implements KeyListener,MouseListener{
    IF_empleados vista = new IF_empleados();
    modeloEmpleados modelo = new modeloEmpleados();
    
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
    
    public controladorEmpleados( IF_empleados vista, modeloEmpleados modelo) {
        this.vista= vista;
        this.modelo= modelo;
    }
    
    @Override
    public void iniciarVista() {
        vista.bucar_txt.addKeyListener(this);
        vista.panelAgregarEmp.addMouseListener(this);
        vista.panelEditEmp.addMouseListener(this);
        vista.panelEliminarEmp.addMouseListener(this);
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
            vista.lblUser.setText(datos[fila][1]);
            vista.lblPass.setText(datos[fila][2]);
            vista.lblNombre.setText(datos[fila][3]);
            vista.lblPhone.setText(datos[fila][4]);
            vista.lblDireccion.setText(datos[fila][5]);
            vista.lblEdad.setText(datos[fila][6]);
            vista.lblInitDate.setText(datos[fila][7]);
            vista.lblType.setText(datos[fila][8]);
            vista.lblStatus.setText(datos[fila][9]);
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
        if(e.getSource() == vista.panelAgregarEmp){
            this.vista.setEnabled(false);
            formEmpleado form = new formEmpleado();
            form.iniciarVistaForm();
        }
        else if(fila==-1){
            conMessage = new controladorMessage(alertMessage, "Primero debes seleccionar un campo de la tabla");
            conMessage.iniciarVista();
        }
        else if(e.getSource() == vista.panelEditEmp){
            formEmpleado form = new formEmpleado(datos[fila][0],datos[fila][1],datos[fila][2],datos[fila][3],datos[fila][4],datos[fila][5],datos[fila][6],datos[fila][7],datos[fila][8],datos[fila][9]);
            form.iniciarVistaForm();
            fila = -1;
        }
        else if(e.getSource() == vista.panelEliminarEmp){
            conAcept = new controladorAceptar(alertAccept, "¿Seguro que desea eliminar el registro?");
            conAcept.iniciarVista();
            conAcept.vista.panelAceptar.addMouseListener(this);
        }
        else if(conAcept.vista.panelAceptar == e.getSource()){
            conAcept.vista.dispose();
            if(modelo.eliminar("login", "empleado_IdEmpleado", Integer.parseInt(vista.lblId.getText()))){
                if(modelo.eliminar("empleado", "IdEmpleado", Integer.parseInt(vista.lblId.getText()))){
                    conSuccess = new controladorSucces(alertSuccess, "Se ha eliminado exitosamente");
                    vista.JTable.setModel(modelo.callObtenerDatosTabla());
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
        if (vista.panelAgregarEmp == e.getSource()) {
            setColorAdd(vista.panelAgregarEmp);
        }
        else if (vista.panelEditEmp == e.getSource()) {
            setColorEditar(vista.panelEditEmp);
        }
        else if (vista.panelEliminarEmp == e.getSource()) {
            setColorEliminar(vista.panelEliminarEmp);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (vista.panelAgregarEmp == e.getSource()) {
            resetColorAdd(vista.panelAgregarEmp);
        }
        else if (vista.panelEditEmp == e.getSource()) {
            resetColorEditar(vista.panelEditEmp);
        }
        else if (vista.panelEliminarEmp == e.getSource()) {
            resetColorEliminar(vista.panelEliminarEmp);
        }
    }
    
    private class formEmpleado implements MouseListener, WindowListener, KeyListener {
        private String user,password,name,age,phone,address,type, status, initDate,id;        
        private boolean opcion = false;//Esta variable es para saber como se utilizará si para agregar o modificar, Modificar = true; Agregar = false; 
        vistaFormEmpleados form = new vistaFormEmpleados();
        private formEmpleado(String id, String user, String password, String name, String phone, String address, String age, String initDate, String type, String status) {
            this.id = id;
            this.user = user;
            this.password = password;
            this.name = name;
            this.phone = phone;
            this.address = address;
            this.age = age;
            this.initDate = initDate;
            this.type = type;
            this.status = status;
            this.opcion = true;
        }

        private formEmpleado() {
            //No lo borren, se ocupa un constructor 
        }
        
        private void iniciarVistaForm(){
            form.setLocationRelativeTo(null);
            form.panelAdd.addMouseListener(this);
            form.panelBack.addMouseListener(this);
            form.txtEdad.addKeyListener(this);
            form.setVisible(true);
            form.title.setText((opcion?"Modificar ":"Agregar ")+"Empleado");
            if(this.opcion == true)
                llenarInputs();
        }

        private void llenarInputs(){
            form.txtId.setText(this.id);
            form.txtUsuario.setText(this.user);
            form.txtContra.setText(this.password);
            form.txtNombre.setText(this.name);
            form.txtEdad.setText(this.age);
            form.txtTelefono.setText(this.phone);
            form.txtDireccion.setText(this.address);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            form.txtEstado.setSelectedItem(this.status);
            java.util.Date date = null;
            try{
                 date = df.parse(String.valueOf(initDate));
            } catch (ParseException ex){

            }   
            form.txtFecha_Inicio.setDate(date);
            form.txtTipo.setSelectedItem(this.type);
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //Agregar
            if(e.getSource() == form.panelAdd && !opcion){
                int last_id = -1;
                //Se llenan los datos que se van a guardar en la tabla de EMPLEADOS
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String fechaBD = df.format(form.txtFecha_Inicio.getDate()); //le da formato correcto a la fecha 
                String[] table_columns = {"Nombre","Telefono","Direccion","Edad","Fecha_Inicio","Tipo","Estatus"};
                String[] table_values = {form.txtNombre.getText(),
                                         form.txtTelefono.getText(),
                                         form.txtDireccion.getText(),
                                         form.txtEdad.getText(),
                                         fechaBD,
                                         "0".equals(String.valueOf(form.txtTipo.getSelectedIndex()))?"":String.valueOf(form.txtTipo.getSelectedIndex()),
                                         String.valueOf(form.txtEstado.getSelectedItem())
                                        };
                //Se llenan los datos que se van a guardar en la tabla LOGIN
                String[] table_columns2 = {"Usuario","Contraseña","empleado_idEmpleado"};
                String[] table_values2 = {form.txtUsuario.getText(),
                                          form.txtContra.getText(),
                                          String.valueOf(last_id)};
                //verifica que los inputs no esten vaciós
                boolean areNotEmpty = checkIsNotEmpty(table_values) && checkIsNotEmpty(table_values2);
                //Se abre la conexión, lo hacemos desde aquí para aplicar las transacciones
                Connection con = modelo.abrirConexion();
                try {
                    con.setAutoCommit(false);
                } catch (SQLException ex) {
                    Logger.getLogger(controladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(areNotEmpty && con != null &&(last_id = modelo.insertar("empleado", table_columns, table_values, con)) != -1){
                    table_values2[2]=String.valueOf(last_id);
                    System.out.println(last_id);
                    if(modelo.insertar("login", table_columns2, table_values2, con) != -1){
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
                    }
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
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String fechaBD = df.format(form.txtFecha_Inicio.getDate()); //le da formato correcto a la fecha s
                System.out.println(String.valueOf(form.txtTipo.getSelectedIndex()));
                String[] table_columns = {"IdEmpleado","Nombre","Telefono","Direccion","Edad","Fecha_Inicio","Tipo","Estatus"};
                String[] table_values = {form.txtId.getText(),
                                         form.txtNombre.getText(),
                                         form.txtTelefono.getText(),
                                         form.txtDireccion.getText(),
                                         form.txtEdad.getText(),
                                         fechaBD,
                                          "0".equals(String.valueOf(form.txtTipo.getSelectedIndex()))?"":String.valueOf(form.txtTipo.getSelectedIndex()),
                                         String.valueOf(form.txtEstado.getSelectedItem())
                                        };
                //Se llenan los datos que se van a guardar en la tabla LOGIN
                //Como se va a encotnrar por el campo idEmpleado, lo puse primero
                String[] table_columns2 = {"empleado_idEmpleado","Usuario","Contraseña"};
                String[] table_values2 = {form.txtId.getText(),
                                          form.txtUsuario.getText(),
                                          form.txtContra.getText()};
                //verifica que los inputs no esten vaciós
                boolean areNotEmpty = checkIsNotEmpty(table_values) && checkIsNotEmpty(table_values2);
                //Se abre la conexión, lo hacemos desde aquí para aplicar las transacciones
                Connection con = modelo.abrirConexion();
                try {
                    con.setAutoCommit(false);
                } catch (SQLException ex) {
                    Logger.getLogger(controladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(areNotEmpty && con != null &&(modelo.modificar("empleado", table_columns, table_values, con))){
                    if(modelo.modificar("login", table_columns2, table_values2, con)){
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
                    }
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
            vista.JTable.setModel(modelo.callObtenerDatosTabla());
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
            if(e.getSource() == form.txtEdad && !"".equals(form.txtEdad.getText())){
                validacionTexFields(form.txtEdad, "[0-9]+");
            }
        }
    }

    
    
    
}
