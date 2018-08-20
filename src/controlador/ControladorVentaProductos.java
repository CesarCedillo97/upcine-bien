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
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 *
 * @author Cesar Cedillo
 */
public class ControladorVentaProductos extends ControladorPrincipal implements MouseListener{
    VentaProductos vista = new VentaProductos();
    modeloVentaProductos modelo = new modeloVentaProductos();
    private int ID;
    
    private String[][] productos, combos;
    
    JLabel[] products;
    JPanel[] panelesProductos;
    JSpinner[] spiners;

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
         
         llenarCombos();
         llenarProductos();
    }
    
    
    public void llenarProductos(){
        panelesProductos = new JPanel[productos.length];
        spiners = new JSpinner[productos.length];
        int cont =0;
        for(String[] strings: productos){
            System.out.println(""+cont);
            JPanel panel = new JPanel();
            panel.setSize(200, 150);
            panel.setVisible(true);
            panel.setBackground(new Color(cont*15,cont*10,cont*20));
            JLabel labelDesc = new JLabel();
            JLabel labelPrec = new JLabel();
            labelDesc.setText(strings[5]);
            labelDesc.setForeground(Color.white);
            panel.add(labelDesc);
            labelPrec.setText("$"+strings[3]);
            labelPrec.setForeground(Color.white);
            panel.add(labelPrec);
            panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            vista.panelProductos.add(panel);
            cont++;
        }
        
    }
    
    
    public void llenarCombos(){
        int cont =0;
        for(String[] strings: combos){
            System.out.println(""+cont);
            JPanel panel = new JPanel();
            panel.setSize(200, 150);
            panel.setVisible(true);
            panel.setBackground(new Color(200,200,200));
            vista.panelCombos.add(panel);
            cont++;
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
}
