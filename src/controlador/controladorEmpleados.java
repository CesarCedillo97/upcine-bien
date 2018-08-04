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
import javax.swing.table.TableModel;
import vista.IF_empleados;
import modelo.modeloEmpleados;
import vista.forms.vistaFormEmpleados;
/**
 *
 * @author Cesar Cedillo
 */
public class controladorEmpleados extends ControladorPrincipal implements KeyListener,MouseListener{
    IF_empleados vista = new IF_empleados();
    modeloEmpleados modelo = new modeloEmpleados();
    
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
        vista.JTable.addMouseListener(this);
    }
    
    public void llenarDatos(int fila){
        TableModel tm = vista.JTable.getModel();
        vista.lblId.setText(String.valueOf(tm.getValueAt(fila, 0)));
        //vista.lblUser.setText(String.valueOf(tm.getValueAt(fila, 0)));
        //vista.lblPass.setText(String.valueOf(tm.getValueAt(fila, 0)));
        vista.lblNombre.setText(String.valueOf(tm.getValueAt(fila, 1)));
        vista.lblPhone.setText(String.valueOf(tm.getValueAt(fila, 2)));
        vista.lblDireccion.setText(String.valueOf(tm.getValueAt(fila, 3)));
        vista.lblEdad.setText(String.valueOf(tm.getValueAt(fila, 4)));
        vista.lblInitDate.setText(String.valueOf(tm.getValueAt(fila, 5)));
        vista.lblType.setText(String.valueOf(tm.getValueAt(fila, 6)));
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
            String[] columnas = {"ID","Nombre","Teléfono","Dirección","Edad","Fecha inicio","Tipo"};
            String Query = "select * from upcine.empleado where Nombre LIKE '"+ vista.bucar_txt.getText() +"%'";
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
        if(e.getSource() == vista.JTable){
            llenarDatos(vista.JTable.rowAtPoint(e.getPoint()));
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
        private String user,password,name,age,phone,address,type, status;        
        private Date initDate;
        private boolean opcion = false;//Esta variable es para saber como se utilizará si para agregar o modificar, Modificar = true; Agregar = false; 
        vistaFormEmpleados form = new vistaFormEmpleados();
        private formEmpleado(String user, String password, String name, String age, String phone, String address, Date initDate, String type, String status) {
            this.user = user;
            this.password = password;
            this.name = name;
            this.age = age;
            this.phone = phone;
            this.address = address;
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
            form.setVisible(true);
            if(this.opcion == true)
                llenarInputs();
        }

        private void llenarInputs(){
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
            if(e.getSource() == form.panelAdd){
                int last_id = -1;
                //Para la tabla empleado
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String fechaBD = df.format(form.txtFecha_Inicio.getDate()); //le da formato correcto a la fecha 
                String[] table_columns = {"Nombre","Teléfono","Dirección","Edad","Fecha_Inicio","Tipo"};
                String[] table_values = {form.txtNombre.getText(),
                                         form.txtTelefono.getText(),
                                         form.txtDireccion.getText(),
                                         form.txtEdad.getText(),
                                         fechaBD,
                                         String.valueOf(form.txtTipo.getSelectedIndex()+1)};
                //Para la tabla de login
                String[] table_columns2 = {"Usuario","Contraseña","empleado_idEmpleado"};
                String[] table_values2 = {form.txtUsuario.getText(),
                                          form.txtContra.getText(),
                                          String.valueOf(last_id)};
                boolean areNotEmpty = checkIsNotEmpty(table_values) && checkIsNotEmpty(table_values2);
                Connection con = modelo.abrirConexion();
                if(areNotEmpty && con != null &&(last_id = modelo.insertar("empleado", table_columns, table_values, con)) != -1){
                    table_values2[2]=String.valueOf(last_id);
                    System.out.println(last_id);
                    if(modelo.insertar("login", table_columns2, table_values2, con) != -1){
                        try {
                            System.out.println("Se ha insertado prrron alv");
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

    
    
    
}
