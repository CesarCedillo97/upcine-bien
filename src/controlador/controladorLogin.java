/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

import vista.Login;
import modelo.modeloLogin;
/**
 *
 * @author Cesar Cedillo
 */
public class controladorLogin/* extends ControladorPrincipal implements ActionListener, KeyListener*/{
 /*   Login vista = new Login();
    modeloLogin modelo = new modeloLogin();
    
    
       int falloInicio;
    
    public controladorLogin(modeloLogin modInicio, Login vista)
    {
        this.modelo = modInicio;
        this.vista = vista;
    }
    
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
/
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
            tipoEmpleado = modInicio.verificarTipoAcceso(idEmpleado); //checa si es admin o empleado comun
            vista.dispose(); 
            if(tipoEmpleado == 1){  //Si es admin
                ModAdmMenu modMenu = new ModAdmMenu();
                AdmMenu visMenu = new AdmMenu();
                ConAdmMenu conMenu = new ConAdmMenu(modMenu, visMenu, idEmpleado);
                conMenu.iniciarVista();
            }
            else if(tipoEmpleado==2){   //si es empleado
                ModEmpOpcVender modOpcVender = new ModEmpOpcVender();
                EmpOpcVender visOpcVender = new EmpOpcVender();
                ConEmpOpcVender ConOpcVende = new ConEmpOpcVender(modOpcVender, visOpcVender, nombre,idEmpleado);
                ConOpcVende.iniciarVista();
            }

        }
        else{   //si no encuentra una coincidencia
            falloInicio++; 
            if(falloInicio >=5){ //no permite iniciar sesión despues de 5 fallas en el inicio de sesión
                GenAlert visAlert = new GenAlert();
                ConAlert alert = new ConAlert(visAlert, "Sistema temporalmente bloqueado, ","favor de contactar al administrador.");
                alert.iniciarVista();
            }
            else{
                GenAlert visAlert = new GenAlert();
                ConAlert alert = new ConAlert(visAlert, "Usuario y/o contraseña incorrectos,","quedan "+(5-falloInicio)+" intentos");
                alert.iniciarVista();
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if(vista.btnLogin == e.getSource() && falloInicio < 5){ //mandar llamar la función verificarInicio al dar click en el boton iniciar sesion
            verificarInicio();
        }else if (vista.btnSalir == e.getSource()) {
            System.exit(0);
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        vista.setCursor(Cursor.HAND_CURSOR);
        if (vista.btnLogin == e.getSource()) {
            vista.btnLogin.setBackground(new java.awt.Color(115, 163, 239));
        }else if (vista.btnSalir == e.getSource()) {
            vista.btnSalir.setBackground(new java.awt.Color(115, 163, 239));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        vista.setCursor(Cursor.DEFAULT_CURSOR);
        if (vista.btnLogin == e.getSource()) {
            vista.btnLogin.setBackground(new java.awt.Color(240,240,240));
        }else if (vista.btnSalir == e.getSource()) {
            vista.btnSalir.setBackground(new java.awt.Color(240,240,240));
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

    */
}
