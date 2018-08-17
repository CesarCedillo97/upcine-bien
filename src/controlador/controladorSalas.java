/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.modeloSalas;
import vista.IF_salas;
import vista.forms.AdmFormSalas;
import vista.alerts.alertAccept;
import vista.alerts.alertError;
import vista.alerts.alertMessage;
import vista.alerts.alertSuccess;
import controlador.conAlerts.controladorAceptar;
import controlador.conAlerts.controladorError;
import controlador.conAlerts.controladorMessage;
import controlador.conAlerts.controladorSucces;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;
import vista.forms.AdmFormAsientos;
/**
 *
 * @author Adrián Scott
 */
public class controladorSalas extends ControladorPrincipal implements MouseListener, KeyListener{
    modeloSalas modelo;
    IF_salas vista;
    
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
    
    public controladorSalas(IF_salas vista, modeloSalas modelo){
        this.modelo = modelo;
        this.vista = vista;
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
            vista.lblSala.setText(datos[fila][1]);
            vista.lblFila.setText(datos[fila][2]);
            vista.lblCols.setText(datos[fila][3]);
            vista.lblNumAsiento.setText(datos[fila][5]);
            vista.lblTipo.setText(datos[fila][5]);
            vista.lblEstado.setText(datos[fila][6]);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(vista.panelAgregar == e.getSource()){
            formSalas form = new formSalas();
            form.iniciarFormSalas();
        }
        else if(vista.panelEditar == e.getSource()){
            formSalas form = new formSalas(datos[fila][0], datos[fila][1], datos[fila][2], datos[fila][3], datos[fila][4], datos[fila][5], datos[fila][6]);
            form.iniciarFormSalas();
        }
        else if(e.getSource() == vista.panelEliminar){
            conAcept = new controladorAceptar(alertAccept, "¿Seguro que desea eliminar el registro?");
            conAcept.iniciarVista();
            conAcept.vista.panelAceptar.addMouseListener(this);
        }
        else if(conAcept.vista.panelAceptar == e.getSource()){
            conAcept.vista.dispose();
            if(modelo.eliminarAsientos(Integer.parseInt(vista.lblID.getText())) && modelo.eliminar("sala", "IdSala", Integer.parseInt(vista.lblID.getText()))){
                conSuccess = new controladorSucces(alertSuccess, "Se ha eliminado exitosamente");
                conSuccess.iniciarVista();
                vista.JTable.setModel(modelo.callObtenerDatosTabla());
                datos=modelo.callObtenerDatos();
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
    
    
    public class formSalas implements MouseListener, KeyListener{
        private String id, numSala, filas, columnas, snumAsientos, tipo, estatus;
        private int numFilas, numCols;
        boolean opcion = false; //MODIFICAR: TRUE / AGREGAR: FALSE;
        JLabel[][] arregloAsientos;
        boolean[][] seatsBool;
        int numAsientos;
        private AdmFormSalas form = new AdmFormSalas();
        private AdmFormAsientos formAsientos = new AdmFormAsientos();
        
        
        public formSalas(String id, String numSala, String filas, String columnas, String numAsientos, String tipo, String estatus) {
            this.id = id;
            this.numSala = numSala;
            this.filas = filas;
            this.columnas = columnas;
            this.snumAsientos = numAsientos;
            this.tipo = tipo;
            this.estatus = estatus;
            this.opcion = true;
        }

        public formSalas() {
            
        }
        
        private void iniciarFormSalas(){
            form.setLocationRelativeTo(null);
            form.panelSig.addMouseListener(this);
            //form.txtEdad.addKeyListener(this);
            form.setVisible(true);
            form.title.setText((opcion?"Modificar ":"Agregar ")+"precios");
            if(this.opcion == true)
                llenarInputs();
        }
        
        private void iniciarFormAsientos(){
            formAsientos.panelAdd.addMouseListener((MouseListener) this);
            formAsientos.panelBack.addMouseListener((MouseListener) this);
            formAsientos.setSize(700, 700);
            formAsientos.setLocationRelativeTo(null);
            formAsientos.title.setText("Seleccionar asientos");
            numFilas = Integer.parseInt(form.txtFilas.getText());
            numCols = Integer.parseInt(form.txtCols.getText());
            arregloAsientos = new JLabel[numFilas][numCols]; //esta variable almacena todos los labels que se imprimiran
            seatsBool = new boolean[numFilas][numCols]; //esta variable funciona para saber si esta activo o no
            formAsientos.panelAsientos.setLayout(new GridLayout(numFilas,numCols));
            formAsientos.panelAsientos.setSize(300, 300);
            if(opcion){
                //se obtienen los asientos de la sala en caso de que se deseen modificar
                seatsBool = modelo.consultarAsientos(Integer.parseInt(id), Integer.parseInt(filas), Integer.parseInt(columnas));
                if(seatsBool != null){
                    pintarAsientos();//se pintan los asientos
                }
                else{
                    System.out.println("No se pudo realizar la consulta");
                }          
            }
            else if(!opcion){
                pintarAsientos(); 
                numAsientos = numFilas * numCols;
            }
            formAsientos.setVisible(true);
        }
        
        public void pintarAsientos(){
            //pinta los asientos en el panel
            for (int i = numFilas-1; i >= 0 ; i--)
            {
                for (int j = 0; j < numCols; j++)
                {
                    if(seatsBool[i][j] && opcion){
                        arregloAsientos[i][j] = new JLabel(getLetra(i+1)+"-"+j,new ImageIcon(getClass().getResource("/assets/icons/asientoAzul.png")),0);
                        numAsientos+=1;
                    }
                    else if(!seatsBool[i][j] && !opcion){
                        arregloAsientos[i][j] = new JLabel(getLetra(i+1)+"-"+j,new ImageIcon(getClass().getResource("/assets/icons/asientoAzul.png")),0);
                        seatsBool[i][j] = true;
                    }
                    else{
                        arregloAsientos[i][j] = new JLabel(getLetra(i+1)+"-"+j,new ImageIcon(getClass().getResource("/assets/icons/asientoRojo.png")),0);
                    }

                    arregloAsientos[i][j].setHorizontalTextPosition(JLabel.CENTER);
                    arregloAsientos[i][j].setVerticalTextPosition(JLabel.BOTTOM);
                    formAsientos.panelAsientos.add(arregloAsientos[i][j]);
                    arregloAsientos[i][j].addMouseListener((MouseListener)this);
                }
            }
        }
        
            //función que convierte de int a char 
        private String getLetra(int i) {
            return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
        }
        
        private void llenarInputs(){
            form.txtId.setText(this.id);
            form.txtFilas.setText(this.filas);
            form.txtCols.setText(this.columnas);
            form.txtNumSala.setText(this.numSala);
            form.txtEstatus.setSelectedItem(this.estatus);
            form.txtTipo.setSelectedItem(this.tipo);
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            if(form.panelSig == e.getSource() && !opcion){
                String[] values = { form.txtNumSala.getText(),
                                    form.txtFilas.getText(),
                                    form.txtCols.getText(),
                                    String.valueOf(form.txtTipo.getSelectedItem()),
                                    String.valueOf(form.txtEstatus.getSelectedItem())};
                boolean notEmpty = checkIsNotEmpty(values);
                if(notEmpty){
                    form.dispose();
                    iniciarFormAsientos();
                }
                else{
                    conError = new controladorError(alertError, "Por favor llene todos los campos para proseguir");
                    conError.iniciarVista();
                }
            }
            else if(form.panelSig == e.getSource() && opcion){
                String[] values = { form.txtId.getText(),
                                    form.txtNumSala.getText(),
                                    form.txtFilas.getText(),
                                    form.txtCols.getText(),
                                    String.valueOf(form.txtTipo.getSelectedItem()),
                                    String.valueOf(form.txtEstatus.getSelectedItem())};
                boolean notEmpty = checkIsNotEmpty(values);
                if(notEmpty){
                    form.dispose();
                    iniciarFormAsientos();
                }
                else{
                    conError = new controladorError(alertError, "Por favor llene todos los campos para proseguir");
                    conError.iniciarVista();
                }
            }
            else if(formAsientos.panelAdd == e.getSource() && !opcion){
                String[] cols = {"NumSala", "Filas","Columnas","Tipo","Estatus"};
                String[] values = { form.txtNumSala.getText(),
                                    form.txtFilas.getText(),
                                    form.txtCols.getText(),
                                    String.valueOf(form.txtTipo.getSelectedItem()),
                                    String.valueOf(form.txtEstatus.getSelectedItem())};
                boolean notEmpty = checkIsNotEmpty(values);
                int last_id=-1;
                Connection con = modelo.abrirConexion();
                try {
                    con.setAutoCommit(false);
                } catch (SQLException ex) {
                    Logger.getLogger(controladorPrecios.class.getName()).log(Level.SEVERE, null, ex);
                }
                if((last_id=modelo.insertar("sala",cols,values,con)) != -1){
                    String[] colAsientos = {"Fila", "Columna", "sala_IdSala"};
                    boolean fallo = false;
                    for (int i = 0; i < seatsBool.length; i++)
                    {
                        for (int j = 0; j < seatsBool[0].length; j++)
                        {
                            if(seatsBool[i][j] == true){
                                String[] valuesAsiento = {String.valueOf(i), String.valueOf(j), String.valueOf(last_id)};
                                if(modelo.insertar("asiento", colAsientos, valuesAsiento, con) == -1){
                                    fallo = true;
                                    break;
                                }
                            }
                        }
                    }
                    if(!fallo){
                        try {
                            con.commit();
                            modelo.cerrarConexion(con);
                            conSuccess = new controladorSucces(alertSuccess, "¡Se ha agregado con éxito!");
                            conSuccess.iniciarVista();
                            formAsientos.dispose();
                        } catch (SQLException ex) {
                            conError = new controladorError(alertError, "No se ha podido realizar el commit");
                        }
                        datos = modelo.callObtenerDatos();
                        vista.JTable.setModel(modelo.callObtenerDatosTabla());
                    }
                }
            }
            else if(formAsientos.panelAdd == e.getSource() && opcion){
                String[] cols = {"IdSala","NumSala", "Filas","Columnas","Tipo","Estatus"};
                String[] values = { form.txtId.getText(),
                                    form.txtNumSala.getText(),
                                    form.txtFilas.getText(),
                                    form.txtCols.getText(),
                                    String.valueOf(form.txtTipo.getSelectedItem()),
                                    String.valueOf(form.txtEstatus.getSelectedItem())};
                Connection con = modelo.abrirConexion();
                try {
                    con.setAutoCommit(false);
                } catch (SQLException ex) {
                    Logger.getLogger(controladorPrecios.class.getName()).log(Level.SEVERE, null, ex);
                }
                if((modelo.modificar("sala",cols,values,con))&& modelo.eliminarAsientos(Integer.parseInt(form.txtId.getText()))){
                    String[] colAsientos = {"Fila", "Columna", "sala_IdSala"};
                    boolean fallo = false;
                    for (int i = 0; i < seatsBool.length; i++)
                    {
                        for (int j = 0; j < seatsBool[0].length; j++)
                        {
                            if(seatsBool[i][j] == true){
                                String[] valuesAsiento = {String.valueOf(i), String.valueOf(j), form.txtId.getText()};
                                if(modelo.insertar("asiento", colAsientos, valuesAsiento, con) == -1){
                                    fallo = true;
                                    break;
                                }
                            }
                        }
                    }
                    if(!fallo){
                        try {
                            con.commit();
                            modelo.cerrarConexion(con);
                            conSuccess = new controladorSucces(alertSuccess, "¡Se ha modificado con éxito!");
                            conSuccess.iniciarVista();
                            formAsientos.dispose();
                            fila = -1;
                        } catch (SQLException ex) {
                            conError = new controladorError(alertError, "No se ha podido realizar el commit");
                        }
                        datos = modelo.callObtenerDatos();
                        vista.JTable.setModel(modelo.callObtenerDatosTabla());
                    }
                }
            }
            else if(formAsientos.panelBack == e.getSource()){
                formAsientos.dispose();
            }
            else{//para saber en que asiento se hizo click
                for (int i = 0; i < numFilas; i++)
                {
                    for (int j = 0; j < numCols; j++)
                    {
                        if(arregloAsientos[i][j] == e.getSource()){
                            if(seatsBool[i][j] == false){
                                arregloAsientos[i][j].setIcon(new ImageIcon(getClass().getResource("/assets/icons/asientoAzul.png")));
                                seatsBool[i][j] = true;
                                numAsientos++;
                            }
                            else{
                                arregloAsientos[i][j].setIcon(new ImageIcon(getClass().getResource("/assets/icons/asientoRojo.png")));
                                seatsBool[i][j] = false;
                                numAsientos--;
                            }
                        }
                    }
                }
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
            if(form.panelSig == e.getSource()){
                form.panelSig.setBackground(new Color(220,220,220));
            }
            else if(formAsientos.panelAdd == e.getSource()){
                setColorAceptar(formAsientos.panelAdd);
            }
            else if(formAsientos.panelBack == e.getSource()){
                setColorCancelar(formAsientos.panelBack);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(form.panelSig == e.getSource()){
                resetColorGrey(form.panelSig);
            }
            else if(formAsientos.panelAdd == e.getSource()){
                resetColorGrey(formAsientos.panelAdd);
            }
            else if(formAsientos.panelBack == e.getSource()){
                resetColorGrey(formAsientos.panelBack);
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
            
        }
        
    }
}
