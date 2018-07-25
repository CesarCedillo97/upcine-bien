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

/**
 *
 * @author Cesar Cedillo
 */
public class ConMenuAdm implements ActionListener, MouseListener {
    
    VistaMenuAdm Desktop = new VistaMenuAdm();
    IF_empleados vEmp = new IF_empleados();
    
    //agancaso omiso a este comentrios

    public ConMenuAdm(VistaMenuAdm vista, IF_empleados vistaEmp ) { // se declaran todos los componentes que se van a mostrar dentro del constructor
        this.Desktop= vista;
        this.vEmp = vistaEmp;
        
        this.Desktop.btnSalir.addActionListener((ActionListener) this);
        //Aquí va la declaracion de paneles (Internal frames)
        this.Desktop.panelEmpleados.addMouseListener((MouseListener)this); 
        
        //Aquí se agregan al desktop
        this.Desktop.Desktop.add(vEmp);
        
        //aquí se muestran
        this.vEmp.show();
        
        this.vEmp.setLocation(-3, -25);
        
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
        //AUS.Administrador_Usuarios_TablaUsuarios_Table.setModel(modelo.usuariosUsuariosConsultar());

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
