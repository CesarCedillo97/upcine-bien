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
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import vista.IF_compras;
import modelo.modeloCompras;
import vista.forms.AdmAddProduct;
/**
 *
 * @author Cesar Cedillo
 */
public class controladorCompras extends ControladorPrincipal implements KeyListener,MouseListener{
    IF_compras vista = new IF_compras();
    modeloCompras modelo = new modeloCompras();
    String[][] datosTabla;
    String[][] datos;
    String[] columnasTabla;
    int fila;
    public controladorCompras( IF_compras vista, modeloCompras modelo) {
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
        //txtQueryTabla es la consulta que jalará los datos que irán en la tabla solamente
        String txtQueryTabla = "SELECT * FROM compra;";
        //txtQuery devuelve TODOS los campos que se van a mostrar en la parte de datos
        String txtQuery = "SELECT IdEmpleado, Usuario, Contraseña, Nombre, Telefono, Direccion, Edad, Fecha_Inicio, \n" +
                                "case when Tipo = 1 then 'Administrador'\n" +
                                "     when Tipo = 2 then 'Empleado' \n" +
                                "     end as 'Tipo'\n" +
                                " FROM empleado, login WHERE empleado.IdEmpleado = login.empleado_IdEmpleado order by Nombre;";
        //Se obtienen los datos de la consulta de la tabla
        datosTabla = modelo.obtenerDatos(txtQueryTabla);
        //Se obtienen los datos de la otra consulta (Para la parte de datos)
        datos = modelo.obtenerDatos(txtQuery);
        //Se declaran los nombres de las columnas que llevará la table (Esta madre no tiene nada que ver con la base de datos si no con JTable)
        columnasTabla = new String[]{"Nombre","Telefono","Direccion","Edad"};
        //Se asigna el modelo a la tabla de los datos de la tabla.
        vista.JTable.setModel(modelo.obtenerDatosTabla(datosTabla,columnasTabla));
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
            String[] columnas = {"Nombre","Teléfono","Dirección","Edad"};
            String Query = "select Nombre, Telefono, Direccion, Edad from upcine.empleado where Nombre LIKE '"+ vista.bucar_txt.getText() +"%'";
            vista.JTable.setModel(modelo.filtrarTabla(Query, columnas));
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
        else if(e.getSource() == vista.panelEditEmp){
            formEmpleado form = new formEmpleado(datos[fila][0],datos[fila][1],datos[fila][2],datos[fila][3],datos[fila][4],datos[fila][5],datos[fila][6],datos[fila][7],datos[fila][8],"1");
            form.iniciarVistaForm();
        }
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
    
    private class formEmpleado implements MouseListener, WindowListener {
        private String user,password,name,age,phone,address,type, status, initDate,id;        
        private boolean opcion = false;//Esta variable es para saber como se utilizará si para agregar o modificar, Modificar = true; Agregar = false; 
        AdmAddProduct form = new AdmAddProduct();
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
            //this.status = status;
            this.opcion = true;
        }

        private formEmpleado() {
            //No lo borren, se ocupa un constructor 
        }
        
        private void iniciarVistaForm(){
            form.setLocationRelativeTo(null);
            form.panelAceptar.addMouseListener(this);
            form.panelCancelar.addMouseListener(this);
            form.setVisible(true);
            if(this.opcion == true)
                llenarInputs();
        }

        private void llenarInputs(){
            //form.txtId.setText(this.id);
            form.txtUsuario.setText(this.user);
            form.txtContra.setText(this.password);
            form.txtNombre.setText(this.name);
            form.txtEdad.setText(this.age);
            form.txtTelefono.setText(this.phone);
            form.txtDireccion.setText(this.address);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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
            if(e.getSource() == form.panelAceptar && !opcion){
                int last_id = -1;
                //Se llenan los datos que se van a guardar en la tabla de EMPLEADOS
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String fechaBD = df.format(form.txtFecha_Inicio.getDate()); //le da formato correcto a la fecha 
                String[] table_columns = {"Nombre","Telefono","Direccion","Edad","Fecha_Inicio","Tipo"};
                String[] table_values = {form.txtNombre.getText(),
                                         form.txtTelefono.getText(),
                                         form.txtDireccion.getText(),
                                         form.txtEdad.getText(),
                                         fechaBD,
                                         String.valueOf(form.txtTipo.getSelectedIndex()+1)};
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
                            System.out.println("Se ha insertado prrron alv");
                            //si todo se inserta se realiza el commit
                            con.commit();
                        } catch (SQLException ex) {
                            System.out.println("No se pudo realizar el commit");
                        }
                    }
                    else{
                        System.out.println("Valió verga:c");
                    }
                    modelo.cerrarConexion(con);
                }
                else{
                    if(!areNotEmpty)
                        System.out.println("No mames, dejaste campos vacios");
                }
            }
            //para modificar
            else if(e.getSource() == form.panelAceptar && opcion){
                int last_id = -1;
                //Se llenan los datos que se van a guardar en la tabla de EMPLEADOS
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String fechaBD = df.format(form.txtFecha_Inicio.getDate()); //le da formato correcto a la fecha 
                String[] table_columns = {"IdEmpleado","Nombre","Telefono","Direccion","Edad","Fecha_Inicio","Tipo"};
                String[] table_values = {form.txtId.getText(),
                                         form.txtNombre.getText(),
                                         form.txtTelefono.getText(),
                                         form.txtDireccion.getText(),
                                         form.txtEdad.getText(),
                                         fechaBD,
                                         String.valueOf(form.txtTipo.getSelectedIndex()+1)};
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
                            System.out.println("Se ha modificado prrron alv");
                            //si todo se inserta se realiza el commit
                            con.commit();
                        } catch (SQLException ex) {
                            System.out.println("No se pudo realizar el commit");
                            System.out.println(ex.getMessage());
                        }
                    }
                    else{
                        System.out.println("Valió verga:c");
                    }
                    modelo.cerrarConexion(con);
                }
                else{
                    System.out.println("wha");
                    if(!areNotEmpty)
                        System.out.println("No mames, dejaste campos vacios");
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if(e.getSource() == form.panelAceptar){
                setColorAceptar(form.panelAceptar);
            }
            else if(e.getSource() == form.panelCancelar){
                setColorCancelar(form.panelCancelar);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(e.getSource() == form.panelAceptar){
                resetColorGrey(form.panelAceptar);
            }
            else if(e.getSource() == form.panelCancelar){
                resetColorGrey(form.panelCancelar);
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
            vista.setEnabled(true);
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
