/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Locale;
import javafx.scene.layout.Border;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import modelo.modeloLogin;
import vista.VistaMenuAdm;

import vista.IF_empleados;
import vista.IF_peliculas;
import vista.IF_Proveedores;
import vista.IF_productos;
import vista.IF_Combos;
import vista.IF_Reportes;

import vista.Login;


import modelo.*;
import vista.IF_clientes;
import vista.IF_compras;
import vista.IF_funciones;
import vista.IF_precios;
import vista.IF_salas;
/**
 *
 * @author Cesar Cedillo
 */
public class ConMenuAdm extends ControladorPrincipal implements MouseListener{
    
    VistaMenuAdm Desktop = new VistaMenuAdm();
    IF_empleados vEmp = new IF_empleados();
    IF_peliculas vPeli = new IF_peliculas();
    IF_Proveedores vProv= new IF_Proveedores();
    IF_productos vProd = new IF_productos();
    IF_Combos vCom = new IF_Combos();
    IF_Reportes vRep = new IF_Reportes();
    IF_precios vPre = new IF_precios();
    IF_funciones vFun = new IF_funciones();
    IF_salas vSal = new IF_salas();
    IF_clientes vCli = new IF_clientes();
    IF_compras vCompr = new IF_compras();
    
    //agancaso omiso a este comentrios
    
    public ConMenuAdm(VistaMenuAdm vista, IF_empleados vistaEmp, IF_peliculas vistaPeli, IF_Proveedores vistaProv, IF_productos productos ,IF_Combos combos,IF_Reportes reportes, IF_precios precios, IF_funciones funciones, IF_salas salas, IF_clientes clientes, IF_compras compras) { // se declaran todos los componentes que se van a mostrar dentro del constructor
        this.Desktop= vista;
        this.vEmp = vistaEmp;
        this.vPeli = vistaPeli;
        this.vProv = vistaProv;
        this.vProd = productos;
        this.vCom = combos;
        this.vRep = reportes;
        this.vPre = precios;
        this.vSal = salas;
        this.vCli = clientes;
        this.vCompr = compras;
        
        //Aquí va la declaracion de paneles (Internal frames)
        this.Desktop.panelEmpleados.addMouseListener((MouseListener)this); 
        this.Desktop.panelPeli.addMouseListener((MouseListener)this);
        this.Desktop.panelProv.addMouseListener((MouseListener)this);
        this.Desktop.panelAsientos.addMouseListener((MouseListener)this);
        this.Desktop.panelCompras.addMouseListener((MouseListener)this);
        this.Desktop.panelFunciones.addMouseListener((MouseListener)this);
        this.Desktop.panelPrecios.addMouseListener((MouseListener)this);
        this.Desktop.panelProduct.addMouseListener((MouseListener)this);
        this.Desktop.panelCombos.addMouseListener((MouseListener)this);
        this.Desktop.panelReportes.addMouseListener((MouseListener)this);
        this.Desktop.panelSalir.addMouseListener((MouseListener)this);
        this.Desktop.panelClientes.addMouseListener((MouseListener)this);
        this.Desktop.panelReportes.addMouseListener((MouseListener)this);
        
        //Aquí se agregan al desktop
        this.Desktop.Desktop.add(vEmp);
        this.Desktop.Desktop.add(vPeli);
        this.Desktop.Desktop.add(vPre);
        this.Desktop.Desktop.add(vProv);
        this.Desktop.Desktop.add(vProd);
        this.Desktop.Desktop.add(vCom);
        this.Desktop.Desktop.add(vRep);
        this.Desktop.Desktop.add(vFun);
        this.Desktop.Desktop.add(vSal);
        this.Desktop.Desktop.add(vCli);
        this.Desktop.Desktop.add(vCompr);
        
        //aquí se muestran
        this.vEmp.show();
        this.vPeli.show();
        this.vProv.show();
        this.vProd.show();
        this.vCom.show();
        this.vRep.show();
        this.vPre.show();
        this.vFun.show();
        this.vSal.show();
        this.vCli.show();
        this.vCompr.show();
        
        //Aqui se ponen en tal posicion para que se vean bien
        this.vEmp.setLocation(-1, -25);
        this.vPeli.setLocation(-1, -25);
        this.vProv.setLocation(-1, -25);
        this.vProd.setLocation(-1, -25);
        this.vCom.setLocation(-1, -25);
        this.vRep.setLocation(-1, -25);
        this.vPre.setLocation(-1, -25);
        this.vFun.setLocation(-1, -25);
        this.vSal.setLocation(-1, -25);
        this.vCli.setLocation(-1, -25);
        this.vCompr.setLocation(-1, -25);
        this.vEmp.toFront();
        
    }

    @Override
     public void iniciarVista(){
        Desktop.setTitle("UpCine");
        Desktop.pack();
        Desktop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Desktop.setLocationRelativeTo(null);
//        vD.Cual_sucursal.setText(this.IDSUC); solo si quieres que sea vea la sucursal al lado del salir
        Desktop.setVisible(true);
        this.vEmp.toFront();
        //algun setModel para la tabla de la primer pantalla (si es que tiene)... seria libros
        //dejo lo siguiente como ejemplo
        //vista.Administrador_Usuarios_TablaUsuarios_Table.setModel(modelo.usuariosUsuariosConsultar());

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (Desktop.panelEmpleados == e.getSource()) {
            this.vEmp.toFront();
        }
        else if (Desktop.panelPeli == e.getSource()) {
            //aqui iria el modelo de la tabla
            this.vPeli.toFront();
        }
        else if(Desktop.panelPrecios == e.getSource()){
            this.vPre.toFront();
        }
        else if (Desktop.panelProv == e.getSource()){
            this.vProv.toFront();
        } 
        else if (Desktop.panelFunciones == e.getSource()){
            this.vFun.toFront();
        } 
        else if (Desktop.panelAsientos == e.getSource()){
            this.vSal.toFront();
        } 
        else if (Desktop.panelClientes == e.getSource()){
            this.vCli.toFront();
        } 
        else if (Desktop.panelCompras == e.getSource()){
            this.vCompr.toFront();
        } 
        else if (Desktop.panelCombos == e.getSource()) {
            //aqui va el modleo de la tabla
            this.vCom.toFront();
        } 
        else if (Desktop.panelProduct == e.getSource()) {
            //aqui va el modleo de la tabla
            this.vProd.toFront();
        }
        else if (Desktop.panelReportes == e.getSource()){
            this.vRep.toFront();
        }
        
        else if (Desktop.panelSalir == e.getSource()) {
            Desktop.dispose();
            Login vistaL = new Login();
            modeloLogin modeloL = new modeloLogin();
            controladorLogin control = new controladorLogin(modeloL, vistaL);
            control.iniciarVista();
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
        else if (Desktop.panelFunciones == e.getSource()) {
            setColor(Desktop.panelFunciones);
        }
        else if (Desktop.panelPeli == e.getSource()) {
            setColor(Desktop.panelPeli);
        }
        else if (Desktop.panelProduct == e.getSource()) {
            setColor(Desktop.panelProduct);
        }
        else if (Desktop.panelCombos == e.getSource()) {
            setColor(Desktop.panelCombos);
        }
        else if (Desktop.panelPrecios == e.getSource()) {
            setColor(Desktop.panelPrecios);
        }
        else if (Desktop.panelAsientos == e.getSource()) {
            setColor(Desktop.panelAsientos);
        }
        else if (Desktop.panelProv == e.getSource()){
            setColor(Desktop.panelProv);
        }
        else if (Desktop.panelCompras == e.getSource()){
            setColor(Desktop.panelCompras);
        }
        else if (Desktop.panelReportes == e.getSource()){
            setColor(Desktop.panelReportes);
        }
        else if (Desktop.panelSalir == e.getSource()) {
            setColor(Desktop.panelSalir);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (Desktop.panelEmpleados == e.getSource()) {
            resetColor(Desktop.panelEmpleados);
        }
        else if (Desktop.panelFunciones == e.getSource()) {
            resetColor(Desktop.panelFunciones);
        }
        else if (Desktop.panelPeli == e.getSource()) {
            resetColor(Desktop.panelPeli);
        }
        else if (Desktop.panelProduct == e.getSource()) {
            resetColor(Desktop.panelProduct);
        }
        else if (Desktop.panelCombos == e.getSource()) {
            resetColor(Desktop.panelCombos);
        }
        else if (Desktop.panelPrecios == e.getSource()) {
            resetColor(Desktop.panelPrecios);
        }
        else if (Desktop.panelAsientos == e.getSource()) {
            resetColor(Desktop.panelAsientos);
        }
        else if (Desktop.panelProv == e.getSource()){
            resetColor(Desktop.panelProv);
        }
        else if (Desktop.panelCompras == e.getSource()){
            resetColor(Desktop.panelCompras);
        }
        else if (Desktop.panelReportes == e.getSource()){
            resetColor(Desktop.panelReportes);
        }
        else if (Desktop.panelSalir == e.getSource()) {
            resetColor(Desktop.panelSalir);
        }
    }
    
    
    
}
