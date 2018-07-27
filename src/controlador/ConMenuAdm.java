/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import vista.VistaMenuAdm;

import vista.IF_empleados;
import vista.IF_peliculas;
import vista.IF_Proveedores;

import modelo.modeloProveedor;
/**
 *
 * @author Cesar Cedillo
 */
public class ConMenuAdm extends ControladorPrincipal implements ActionListener, MouseListener{
    
    VistaMenuAdm Desktop = new VistaMenuAdm();
    IF_empleados vEmp = new IF_empleados();
    IF_peliculas vPeli = new IF_peliculas();
    IF_Proveedores vProv= new IF_Proveedores();
    
    modeloProveedor modProv = new modeloProveedor();
    
    //agancaso omiso a este comentrios

    public ConMenuAdm(VistaMenuAdm vista, IF_empleados vistaEmp, IF_peliculas vistaPeli, IF_Proveedores vistaProv ) { // se declaran todos los componentes que se van a mostrar dentro del constructor
        this.Desktop= vista;
        this.vEmp = vistaEmp;
        this.vPeli = vistaPeli;
        this.vProv = vistaProv;
        
        this.Desktop.btnSalir.addActionListener((ActionListener) this);
        
        //Aquí va la declaracion de paneles (Internal frames)
        this.Desktop.panelEmpleados.addMouseListener((MouseListener)this); 
        this.Desktop.panelPeli.addMouseListener((MouseListener)this);
        this.Desktop.panelProv.addMouseListener((MouseListener)this);
        
        //Aquí se agregan al desktop
        this.Desktop.Desktop.add(vEmp);
        this.Desktop.Desktop.add(vPeli);
        this.Desktop.Desktop.add(vProv);
        
        //aquí se muestran
        this.vEmp.show();
        this.vPeli.show();
        this.vProv.show();
        
        //Aqui se ponen en tal posicion para que se vean bien
        this.vEmp.setLocation(-3, -25);
        this.vPeli.setLocation(-3, -25);
        this.vProv.setLocation(-3, -25);
        
        this.vEmp.toFront();
        
    }
    
     public void iniciarVista(){
        Desktop.setTitle("Biblioteca");
        Desktop.pack();
        Desktop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Desktop.setLocationRelativeTo(null);
//        vD.Cual_sucursal.setText(this.IDSUC); solo si quieres que sea vea la sucursal al lado del salir
        Desktop.setVisible(true);
        
        //algun setModel para la tabla de la primer pantalla (si es que tiene)... seria libros
        //dejo lo siguiente como ejemplo
        //vista.Administrador_Usuarios_TablaUsuarios_Table.setModel(modelo.usuariosUsuariosConsultar());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Desktop.btnSalir == e.getSource()) {
            Desktop.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (Desktop.panelEmpleados == e.getSource()) {
            this.vEmp.toFront();
        }
        else if (Desktop.panelPeli == e.getSource()) {
            this.vPeli.toFront();
        }
        else if (Desktop.panelProv == e.getSource()){
            vProv.JTable.setModel(modProv.cargarDatos());
            this.vProv.toFront();
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (Desktop.panelEmpleados == e.getSource()) {
            setColor(Desktop.panelEmpleados);
        }
        else if (Desktop.panelPeli == e.getSource()) {
            setColor(Desktop.panelPeli);
        }
        else if (Desktop.panelProv == e.getSource()){
            setColor(Desktop.panelProv);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (Desktop.panelEmpleados == e.getSource()) {
            resetColor(Desktop.panelEmpleados);
        }
        else if (Desktop.panelPeli == e.getSource()) {
            resetColor(Desktop.panelPeli);
        }
        else if (Desktop.panelProv == e.getSource()){
            resetColor(Desktop.panelProv);
        }
    }
    
    
    
}
