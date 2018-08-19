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
import javax.swing.DefaultComboBoxModel;

import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import vista.IF_productos;
import modelo.modeloProductos;
import vista.alerts.alertAccept;
import vista.alerts.alertError;
import vista.alerts.alertMessage;
import vista.alerts.alertSuccess;
import vista.forms.vistaFormProductos;

/**
 *
 * @author Cesar Cedillo
 */
public class controladorProductos extends ControladorPrincipal implements KeyListener,MouseListener{
    IF_productos vista = new IF_productos();
    modeloProductos modelo = new modeloProductos();
    
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
    int fila =0;

    public controladorProductos(IF_productos vista, modeloProductos modelo) {
        this.modelo = modelo;
        this.vista = vista;

    }

    
    @Override
    public void iniciarVista() {
        vista.panelAgregar.addMouseListener(this);
        vista.panelEditar.addMouseListener(this);
        vista.panelEliminar.addMouseListener(this);
        vista.panelLimpiar.addMouseListener(this);
        vista.bucar_txtPro.addKeyListener(this);
        fila=-1;
        
        vista.JTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            fila = vista.JTable.getSelectedRow();
            llenarDatos();
        });
        
        datos = modelo.callObtenerDatos();
        
        vista.JTable.setModel(modelo.callObtenerDatosTabla());
        
    }
    
    public void llenarDatos(){
        if(fila!=-1){
            vista.lblID.setText(datos[fila][0]);
            vista.lblCant.setText(datos[fila][1]);
            vista.lblCosto.setText(datos[fila][2]);
            vista.lblPrecioPro.setText(datos[fila][3]);
            vista.lblProveedor.setText(datos[fila][4]);
            vista.lblDescrip.setText(datos[fila][5]);

        }
    }
    
    public void limpiarDatos(){
        if(fila!=-1){
            vista.lblID.setText("");
            vista.lblCant.setText("");
            vista.lblCosto.setText("");
            vista.lblPrecioPro.setText("");
            vista.lblProveedor.setText("");
            vista.lblDescrip.setText("");
            vista.bucar_txtPro.setText("");
            fila = -1;
        }
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        if (vista.panelAgregar == e.getSource()) {
            if(fila==-1){
                conMessage = new controladorMessage(alertMessage, "Primero debes seleccionar un campo de la tabla");
                conMessage.iniciarVista();
            }else{
                this.vista.setEnabled(false);
                formProductos vistaForm = new formProductos();
                vistaForm.iniciarVista();
            }
            
        }
        else if (vista.panelEditar == e.getSource()) {
            if(fila==-1){
                conMessage = new controladorMessage(alertMessage, "Primero debes seleccionar un campo de la tabla");
                conMessage.iniciarVista();
            }else{
                formProductos form = new formProductos(datos[fila][0],datos[fila][1],datos[fila][2],datos[fila][3],datos[fila][4],datos[fila][5]);
                form.iniciarVista();
            }
           
        }
        else if (vista.panelLimpiar == e.getSource()) {
            limpiarDatos();
        }
        else if (vista.panelEliminar == e.getSource()) {
            if(fila==-1){
                conMessage = new controladorMessage(alertMessage, "Primero debes seleccionar un campo de la tabla");
                conMessage.iniciarVista();
            }else{
                conAcept = new controladorAceptar(alertAccept, "¿Seguro que desea Eliminar el producto?");
                conAcept.iniciarVista();
            }
                
        }
        else if (alertAccept.panelAceptar == e.getSource())  {
            if (modelo.eliminarProductos(datos[fila][0])) {
               controladorSucces hecho = new controladorSucces(alertSuccess, "Se ha eliminado Correctamente");
               hecho.iniciarVista();
               alertAccept.dispose();
            }else{
                controladorError nohecho = new controladorError(alertError, "Ha ocurrido un error!");
                nohecho.iniciarVista();
            }
            
        }
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent me) {
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
        else if (vista.panelLimpiar == e.getSource()) {
            setColorLimpiar(vista.panelLimpiar);
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
        else if (vista.panelLimpiar == e.getSource()) {
            resetColorLimpiar(vista.panelLimpiar);
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
        if (vista.bucar_txtPro == e.getSource()) {
            vista.JTable.setModel(modelo.callFiltrarTabla(vista.bucar_txtPro.getText()));
        }
    }
    
    
    /*********************************************************************************************************************************************************************/
    
    private class formProductos extends ControladorPrincipal implements MouseListener{
        private String id,descrip,idProv,costo,precio, cant;
        private boolean opc;
        controladorAceptar conAceptF;
        controladorError conErrorF;
        controladorSucces conSuccessF;
        controladorMessage conMessageF;
        
        String[][] combos;
        
        vistaFormProductos vistaF = new vistaFormProductos();

        public formProductos(String id, String cant, String costo, String precio, String idProv, String desc) {
            this.id = id;
            this.descrip = desc;
            this.idProv = idProv;
            this.cant = cant;
            this.costo = costo;
            this.precio = precio;
            this.opc=true;
        }
        
        public formProductos() {
            this.opc=false;
        }
        
        
        

        @Override
        public void iniciarVista() {
            this.vistaF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            vistaF.setLocationRelativeTo(null);
            vistaF.pack();
            vistaF.panelAdd.addMouseListener(this);
            vistaF.panelBack.addMouseListener(this);
            vistaF.setVisible(true);
            vistaF.txtTitle.setText((opc?"Modificar ":"Agregar ")+"Producto");
            vistaF.lblAcept.setText((opc?"Editar ":"Agregar "));
            if(this.opc == true)
                llenarInputs();
            
            combos = modelo.callObtenerDatosCombo();
            DefaultComboBoxModel modelito = new DefaultComboBoxModel();
            for (String[] combo : combos) {
                modelito.addElement(combo[1]);
            }
            vistaF.txtProv.setModel(modelito);
        }
        
        private void llenarInputs(){
            System.out.println("cantidad:"+this.cant+"");
            vistaF.lblID.setText(this.id);
            vistaF.txtDescripcion.setText(this.descrip);
            vistaF.txtPrecioVenta.setText(this.precio);
            vistaF.txtCantidad.setValue(Integer.parseInt(this.cant));
            vistaF.txtCosto.setText(this.costo);
            vistaF.txtProv.setSelectedIndex(Integer.parseInt(this.idProv));
            //prov = arrayprovs[formProduct.txtProv.getSelectedIndex()][0];
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (vistaF.panelAdd == e.getSource()) {
                
                this.cant=String.valueOf(vistaF.txtCantidad.getValue());
                this.costo=vistaF.txtCosto.getText();
                this.descrip=vistaF.txtDescripcion.getText();
                this.id=vistaF.lblID.getText();
                this.precio=vistaF.txtPrecioVenta.getText();
                this.idProv= combos[vistaF.txtProv.getSelectedIndex()][0];
                    
                if (this.opc == false) { //Este es para agregar
                    
                    
                    if (!"".equals(this.cant) && (!"".equals(this.costo) || !"0".equals(this.costo)) && !"".equals(this.descrip) && !"".equals(this.precio) && !"".equals(this.idProv) ) {
                        if (modelo.insertarProductos(cant, costo, precio, idProv, descrip)) {
                            controladorSucces hecho = new controladorSucces(alertSuccess, "Se ha insertado Correctamente");
                            hecho.iniciarVista();
                            
                        }else{
                            controladorError nohecho = new controladorError(alertError, "Ha ocurrido un error!");
                            nohecho.iniciarVista();
                        }
                    }else{
                        controladorMessage message = new controladorMessage(alertMessage, "Por favor, llene los campos");
                        message.iniciarVista();
                    }
                }
                else {
                    if (!"".equals(this.cant) && (!"".equals(this.costo) || !"0".equals(this.costo)) && !"".equals(this.descrip) && !"".equals(this.precio) && !"".equals(this.idProv) && !"".equals(this.id)) { // este es para modificar
                        if (modelo.modificarProductos(id, cant, costo, precio, idProv, descrip)) {
                            controladorSucces hecho = new controladorSucces(alertSuccess, "Se ha Modificado Correctamente");
                            hecho.iniciarVista();
                        }else{
                            controladorError nohecho = new controladorError(alertError, "Ha ocurrido un error!");
                            nohecho.iniciarVista();
                        }
                    }else{
                        controladorMessage message = new controladorMessage(alertMessage, "Por favor, llene los campos");
                        message.iniciarVista();
                    }
                }
            }
            else if (vistaF.panelBack == e.getSource()) {
                vistaF.dispose();
                resetColorGrey(vistaF.panelBack);
            }
        }

        @Override
        public void mouseReleased(MouseEvent me) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (vistaF.panelAdd == e.getSource()) {
                setColorAdd(vistaF.panelAdd);
            }
            else if (vistaF.panelBack == e.getSource()) {
                setColorCancelar(vistaF.panelBack);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (vistaF.panelAdd == e.getSource()) {
                resetColorGrey(vistaF.panelAdd);
            }
            else if (vistaF.panelBack == e.getSource()) {
                resetColorGrey(vistaF.panelBack);
            }
        }

    
    
    }
}
