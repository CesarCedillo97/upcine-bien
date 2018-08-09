/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.modeloEmpleados;

import vista.Login;
import modelo.modeloLogin;
import modelo.modeloPelicuas;
import modelo.modeloProveedor;
import modelo.modeloProductos;
import modelo.modeloCombos;
import modelo.modeloCompras;
import modelo.modeloPrecios;


import vista.IF_Proveedores;
import vista.IF_empleados;
import vista.IF_peliculas;
import vista.VistaMenuAdm;
import vista.IF_productos;
import vista.IF_Combos;
<<<<<<< HEAD
import vista.IF_compras;
import vista.IF_precios;
=======
import vista.VentaBoletos;
>>>>>>> fe7aeda81f2492143190a1c1d5174a0830813d42
/**
 *
 * @author Cesar Cedillo
 */
public class controladorLogin extends ControladorPrincipal implements ActionListener, KeyListener{
  Login vista = new Login();
    modeloLogin modelo = new modeloLogin();
    
    
       int falloInicio;
    
    public controladorLogin(modeloLogin modInicio, Login vista)
    {
        this.modelo = modInicio;
        this.vista = vista;
    }
    
  @Override
    public void iniciarVista()
    {
        vista.setTitle("Inicio de sesión");
        vista.pack();
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.btnLogin.addActionListener(this);
        vista.btnSalir.addActionListener(this);
        vista.txtContra.addKeyListener(this);
        vista.btnLogin.addKeyListener(this);
        vista.btnSalir.addKeyListener(this);
    }

    public void verificarInicio(){
        int idEmpleado = -1;
        int tipoEmpleado;
        String nombre = "";
        if(falloInicio < 5){ //verifica si no se ha fallado en el inicio de sesion más de 5 veces
            idEmpleado = modelo.consultarInicio(vista.txtUsuario.getText(), vista.txtContra.getText()); //checa si el inicio es correcto y asigna el id a IdEmpleado si lo encuentra, de lo contrario, asigna un -1
            nombre = modelo.VerificarNombre(idEmpleado); //obtiene el nombre del empleado a partir del ID 
        }
        if(idEmpleado != -1){   //si se encontró una coincidencia en el inicio de sesion
            falloInicio = 0;    
            tipoEmpleado = modelo.verificarTipoAcceso(idEmpleado); //checa si es admin o empleado comun
            vista.dispose(); 
            if(tipoEmpleado == 1){  //Si es admin   
                //PAra empleados
                IF_empleados emp = new IF_empleados();
                modeloEmpleados modEmp = new modeloEmpleados();
                controladorEmpleados conEmp = new controladorEmpleados(emp, modEmp);
                conEmp.iniciarVista();
            
                //Para las pelis
                IF_peliculas peli = new IF_peliculas();
                modeloPelicuas modPeli = new modeloPelicuas();
                controladorPeliculas conPeli = new controladorPeliculas(peli, modPeli);

                //Para los proveedores
                IF_Proveedores prove = new IF_Proveedores();
                modeloProveedor modProv = new modeloProveedor();
                controladorProveedores conProv = new controladorProveedores(prove,modProv);
                conProv.iniciarVista();
                
                //para los combos
                IF_Combos vCom = new IF_Combos();
                modeloCombos modCom = new modeloCombos();
                controladorCombos conCom = new controladorCombos(vCom,modCom);
                conCom.iniciarVista();
                
                //Para los productos
                IF_productos vprod = new IF_productos();
                modeloProductos modPro = new modeloProductos();
                controladorProductos conProd = new controladorProductos(vprod, modPro);
                conProd.iniciarVista();
                
                //para las compras
                IF_compras vCop = new IF_compras();
                modeloCompras modCo = new modeloCompras();
                controladorCompras conCo = new controladorCompras(vCop, modCo);
                conCo.iniciarVista();
                
                //para los precios
                IF_precios pre = new IF_precios();
                modeloPrecios modPre = new modeloPrecios();
                controladorPrecios conPre = new controladorPrecios(pre, modPre);
                conPre.iniciarVista();
                
                //Para la pantalla principal del desktop
                VistaMenuAdm vistaMenu = new VistaMenuAdm();
<<<<<<< HEAD
                ConMenuAdm newCalis = new ConMenuAdm(vistaMenu, emp, peli,prove,vprod, vCom, pre, vCop);
=======
                ConMenuAdm newCalis = new ConMenuAdm(vistaMenu, emp, peli,prove,vprod, vCom);
                System.out.println("w");
>>>>>>> fe7aeda81f2492143190a1c1d5174a0830813d42
                newCalis.iniciarVista();
            }
            else if(tipoEmpleado==2){   //si es empleado
                VentaBoletos vBol = new VentaBoletos();
                ControladorVentaBoletos conVenBol = new ControladorVentaBoletos(vBol);
                conVenBol.iniciarVista();
            }

        }
        else{   //si no encuentra una coincidencia
            falloInicio++; 
            if(falloInicio >=5){ //no permite iniciar sesión despues de 5 fallas en el inicio de sesión
               JOptionPane.showMessageDialog(null,"nimodo prro, ya petaste");
            }
            else{
                JOptionPane.showMessageDialog(null,"nimodo prro, te quedan "+(5-falloInicio)+" intentos");
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(vista.btnLogin == e.getSource() && falloInicio < 5){ //mandar llamar la función verificarInicio al dar click en el boton iniciar sesion
            verificarInicio();
        }else if (vista.btnSalir == e.getSource()) {
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(KeyEvent.VK_ENTER == e.getKeyCode() && (vista.btnLogin == e.getSource() || vista.txtContra == e.getSource())){
            verificarInicio();
        }
        else if(vista.btnSalir == e.getSource()){
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        
    }

}
