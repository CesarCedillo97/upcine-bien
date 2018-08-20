/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

//Los de aquí
import vista.VentaProductos;
import modelo.modeloVentaProductos;

//para los Alerts
import vista.alerts.alertAccept;
import vista.alerts.alertError;
import vista.alerts.alertMessage;
import vista.alerts.alertSuccess;

//para los Alerts
import controlador.conAlerts.controladorAceptar;
import controlador.conAlerts.controladorError;
import controlador.conAlerts.controladorMessage;
import controlador.conAlerts.controladorSucces;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import vista.Login;
import modelo.modeloLogin;
import controlador.controladorLogin;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.JFormattedTextField;
import vista.forms.EmpConfirmVentaProd;



/**
 *
 * @author Cesar Cedillo
 */
public class ControladorVentaProductos extends ControladorPrincipal implements MouseListener, ActionListener{
    VentaProductos vista = new VentaProductos();
    modeloVentaProductos modelo = new modeloVentaProductos();
    private int ID;
    
    private String[][] productos, combos;
    float IVA;
    
    
    JLabel[] products;
    JPanel[] panelesProductos;
    JSpinner[] spiners;
    String[] datosCliente;
    
    
    //PARA LOS CONTROLADORES DE LOS ALERTS
    controladorMessage cMess;
    controladorError cError;
    controladorSucces cSuccess;
    controladorAceptar cAceptar;
    
    //PARA LAS VISTAS DE LOS ALERTS
    alertMessage vistaMess = new alertMessage();
    alertError vistaError = new alertError();
    alertAccept vistaAccept = new alertAccept();
    alertSuccess vistaSucces = new alertSuccess();

    public ControladorVentaProductos(VentaProductos vistaP, modeloVentaProductos modeloP, int id) {
        this.vista=vistaP;
        this.modelo = modeloP;
        this.ID = id;
        
    }
    
    
    
    @Override
    public void iniciarVista() {
         vista.setLocationRelativeTo(null);
         vista.setVisible(true);
         productos=modelo.obtenerProductos();
         combos=modelo.obtenerCombos();
         vista.panelAceptarCompra.addMouseListener(this);
         vista.panelEliminarVenta.addMouseListener(this);
         
         vista.panelSalir.addMouseListener(this);
         IVA=modelo.obtenerIVA();
         vista.lblIva.setText(Float.toString(IVA));
         vista.btnAceptClient.addActionListener((ActionListener) this);
         
         llenarCombos();
         llenarProductos();
    }
    
    
    public void llenarProductos(){
        panelesProductos = new JPanel[productos.length];
        spiners = new JSpinner[productos.length];
        int vali = productos.length;
        int cont =0;
        for(String[] strings: productos){
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setSize(200, 150);
            panel.setVisible(true);
            //Se crea la Precio
            panel.setBackground(new Color(161,143,30));
            JLabel labelDesc = new JLabel();
            //Se crea el Descrip
            JLabel labelPrec = new JLabel();
            labelDesc.setText(strings[5]);
            //Se cambia l color
            labelDesc.setForeground(Color.white);
            labelDesc.setFont(new Font("Segue UI", Font.PLAIN, 18));
            panel.add(labelDesc);
            labelPrec.setText("$"+strings[2]);
            labelPrec.setForeground(Color.white);
            labelPrec.setFont(new Font("Segue UI", Font.PLAIN, 18));
            panel.add(labelPrec);
            //SPINNER
            //SPINNEr
            JSpinner spinner = new JSpinner();
            spinner=crearSpinner();
            panel.add(spinner);
            //AGREGar
            panel.add(spinner);
            panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            vista.panelProductos.add(panel);
            panelesProductos[cont] = panel;
            spiners[cont]=spinner;
            
            cont++;
        }
        
    }
    
    
    public void llenarCombos(){
        int cont =0;
        int vali = combos.length;

        for(String[] strings: combos){
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setSize(200, 150);
            panel.setVisible(true);
            //Se crea la Precio
            panel.setBackground(new Color(161,143,30));
            JLabel labelDesc = new JLabel();
            //Se crea el Descrip
            JLabel labelPrec = new JLabel();
            labelDesc.setText(strings[2]);
            //Se cambia l color
            labelDesc.setForeground(Color.white);
            labelDesc.setFont(new Font("Segue UI", Font.PLAIN, 20));
            panel.add(labelDesc);
            labelPrec.setText("$"+strings[1]);
            labelPrec.setForeground(Color.white);
            labelPrec.setFont(new Font("Segue UI", Font.PLAIN, 20));
            panel.add(labelPrec);
            //SPINNER
            //SPINNEr
            JSpinner spinner = new JSpinner();
            spinner=crearSpinner();
            panel.add(spinner);
            //AGREGar
            panel.add(spinner);
            panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            vista.panelCombos.add(panel);
            cont++;
        }
        if (vali<=9) {
            for (int val = vali; val == 10; val++) {
                JPanel panel = new JPanel(new FlowLayout());
                panel.setSize(200, 150);
                panel.setBackground(new Color(161,143,30));
                panel.setVisible(true);
                vista.panelCombos.add(panel);
            }
            
        }
    }
    
    public void limpiarDatos(){
        IVA=modelo.obtenerIVA();
         vista.lblIva.setText(Float.toString(IVA)); 
         vista.panelCombos.removeAll();
         vista.panelProductos.removeAll();
         llenarCombos();
         llenarProductos();
         vista.lblSubtotal.setText("");
         vista.lblTotal.setText("");
         vista.txtCliente.setText("");
         vista.txtPuntos.setText("");
         vista.txtNombre.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        if (vista.panelAceptarCompra == e.getSource()) {
            EmpConfirmVentaProd vistaForm = new EmpConfirmVentaProd();
            FormVentaProductos conForm = new FormVentaProductos(vistaForm, vista.lblTotal.getText());
            conForm.iniciarVista();
            vista.setEnabled(false);
            
        }else if(vista.panelEliminarVenta == e.getSource()){
            limpiarDatos();
            
        }else if (vista.panelSalir == e.getSource()) {
            Login vistaLogin = new Login();
            modeloLogin modelLog = new modeloLogin();
            controladorLogin conLog = new controladorLogin(modelLog, vistaLogin);
            conLog.iniciarVista();
            vista.dispose();
            
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (vista.panelAceptarCompra == e.getSource()) {
            setColorAdd(vista.panelAceptarCompra);
        }else if(vista.panelEliminarVenta == e.getSource()){
            setColorEliminar(vista.panelEliminarVenta);
        }else if (vista.panelSalir == e.getSource()) {
            setColorAdd(vista.panelSalir);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (vista.panelAceptarCompra == e.getSource()) {
            resetColorAdd(vista.panelAceptarCompra);
        }else if(vista.panelEliminarVenta == e.getSource()){
            resetColorEliminar(vista.panelEliminarVenta);
        }else if (vista.panelSalir == e.getSource()) {
            resetColorNormalAz(vista.panelSalir);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnAceptClient){
            if(!"".equals(vista.txtCliente.getText())){
                datosCliente = modelo.obtenerCliente(Integer.parseInt(vista.txtCliente.getText()));
                if(datosCliente != null){
                    
                    vista.txtNombre.setText(datosCliente[1]);
                    vista.txtPuntos.setText(datosCliente[2]);
                }
                else{
                    cMess = new controladorMessage(vistaMess, "No se encontró el cliente");
                    cMess.iniciarVista();
                    vista.txtCliente.setText("");
                    vista.txtNombre.setText("");
                    vista.txtPuntos.setText("");

                }
            }
            else{
                cMess = new controladorMessage(vistaMess, "Introduce el ID del cliente");
                float puntos = "".equals(vista.txtPuntos.getText())?0f:Float.parseFloat(vista.txtPuntos.getText());
                vista.txtCliente.setText("");
                vista.txtNombre.setText("");
                vista.txtPuntos.setText("");
                cMess.iniciarVista();
            }
        }
    }
    
    public JSpinner crearSpinner(){
        SpinnerNumberModel m = new SpinnerNumberModel(0, 0, 1000, 1);
        JSpinner spinner = new JSpinner(m) {
            @Override public void setLayout(LayoutManager mgr) {
              super.setLayout(new SpinnerLayout());
            }
        };
        JFormattedTextField ftf = getTextField(spinner);
        if(ftf != null){
            ftf.setFont(new Font("Tagoma", Font.PLAIN, 20));
            ftf.setHorizontalAlignment(JTextField.CENTER);
        }
        Dimension d = spinner.getComponent(0).getPreferredSize();
        d.height = 100;
        spinner.getComponent(0).setPreferredSize(d);
        spinner.addChangeListener((ChangeEvent e) -> {
            
        });
        
        return spinner;
    }

    private JFormattedTextField getTextField(JSpinner spinner) {
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            return ((JSpinner.DefaultEditor) editor).getTextField();

        } else {
            System.err.println("Unexpected editor type: " + spinner.getEditor().getClass() + " isn't a descendant of DefaultEditor");
            return null;
        }
    }
    //**********************************************************************************************************************************
        //**********************************************************************************************************************************
    
    public class FormVentaProductos extends ControladorPrincipal implements MouseListener, KeyListener{
        EmpConfirmVentaProd vistaF = new EmpConfirmVentaProd();
        String TotalP;
        
        public FormVentaProductos(EmpConfirmVentaProd vistaFP, String total) {
            this.TotalP= total;
            this.vistaF= vistaFP;
        }

        @Override
        public void iniciarVista() {
            vistaF.setVisible(true);
            vistaF.setLocationRelativeTo(null);
            
            vistaF.panelCanel.addMouseListener(this);
            vistaF.panelConfirm.addMouseListener(this);
            vistaF.panelConfirm.setEnabled(false);
            
            vistaF.lblTotal.setText(TotalP);
            
            vistaF.txtPago.addKeyListener(this);
        }

        @Override
        public void mouseClicked(MouseEvent me) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (vistaF.panelConfirm == e.getSource()) {
                setColorAdd(vistaF.panelConfirm);
            }else if (vistaF.panelCanel == e.getSource()) {
                vista.setEnabled(true);
                vistaF.dispose();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (vistaF.panelConfirm == e.getSource()) {
                setColorAdd(vistaF.panelConfirm);
            }else if (vistaF.panelCanel == e.getSource()) {
                setColorEliminar(vistaF.panelCanel);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (vistaF.panelConfirm == e.getSource()) {
                resetColorGrey(vistaF.panelConfirm);
            }else if (vistaF.panelCanel == e.getSource()) {
                resetColorGrey(vistaF.panelCanel);
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
            if (vistaF.txtPago == e.getSource()) {
                float recibido = Float.parseFloat(vistaF.txtPago.getText());
                float Ftotal = Float.parseFloat(this.TotalP);
                if (recibido >= Ftotal) {
                    float cambio = Ftotal-recibido;
                    vistaF.lblCambio.setText(String.valueOf(cambio));
                    vistaF.panelConfirm.setEnabled(true);
                }else{
                    vistaF.lblCambio.setText("0");
                    vistaF.panelConfirm.setEnabled(false);
                }
            }
        }
        
        
    }
    
}
