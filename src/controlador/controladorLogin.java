package controlador;

import controlador.*;


import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.modeloClientes;
import modelo.modeloEmpleados;

import vista.Login;
import modelo.modeloLogin;
import modelo.modeloPeliculas;
import modelo.modeloProveedor;
import modelo.modeloProductos;
import modelo.modeloCombos;
import modelo.modeloCompras;
import modelo.modeloFunciones;
import modelo.modeloPrecios;
import modelo.modeloSalas;
import modelo.modeloVentaBoletos;
import modelo.modeloVentaProductos;



import vista.IF_Proveedores;
import vista.IF_empleados;
import vista.IF_peliculas;
import vista.VistaMenuAdm;
import vista.IF_productos;
import vista.IF_Combos;
import vista.IF_Reportes;
import vista.IF_clientes;
import vista.IF_compras;
import vista.IF_funciones;
import vista.IF_precios;
import vista.IF_salas;
import vista.VentaBoletos;
import vista.empleadoOpcionVender;
import vista.VentaProductos;

/**
 *
 * @author Cesar Cedillo
 */
public class controladorLogin extends ControladorPrincipal implements ActionListener, KeyListener{
  Login vista = new Login();
  int idEmpleado = -1;
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
                modeloPeliculas modPeli = new modeloPeliculas();
                controladorPeliculas conPeli = new controladorPeliculas(peli, modPeli);
                conPeli.iniciarVista();

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
                
                IF_Reportes vrep = new IF_Reportes();
                controladorReportes conRep = new controladorReportes(vrep);
                conRep.iniciarVista();
                //Precios
                IF_precios vpre = new IF_precios();
                modeloPrecios mpre = new modeloPrecios();
                controladorPrecios conPre = new controladorPrecios(vpre, mpre);
                conPre.iniciarVista();
                
                //Funciones
                IF_funciones vfun = new IF_funciones();
                modeloFunciones mfun = new modeloFunciones();
                controladorFunciones conFun = new controladorFunciones(vfun, mfun);
                conFun.iniciarVista();
                
                //Salas
                IF_salas vsal = new IF_salas();
                modeloSalas msal = new modeloSalas();
                controladorSalas conSal = new controladorSalas(vsal, msal);
                conSal.iniciarVista();
                
                //Clientes
                IF_clientes vcli = new IF_clientes();
                modeloClientes mcli = new modeloClientes();
                controladorClientes conCli = new controladorClientes(vcli, mcli);
                conCli.iniciarVista();
                
                //Compras
                IF_compras vcompr = new IF_compras();
                modeloCompras mcompr = new modeloCompras();
                controladorCompras conCompr = new controladorCompras(vcompr, mcompr);
                conCompr.iniciarVista();
                
                //Para la pantalla principal del desktop
                VistaMenuAdm vistaMenu = new VistaMenuAdm();
                ConMenuAdm newCalis = new ConMenuAdm(vistaMenu, emp, peli,prove,vprod, vCom, vrep, vpre, vfun, vsal, vcli, vcompr);
                newCalis.iniciarVista();
            }
            else if(tipoEmpleado==2){   //si es empleado
                if(!existeFichero()){
                    empleadoOpcionVender opcVender = new empleadoOpcionVender();
                    opcVender.setVisible(true);
                    opcVender.setLocationRelativeTo(null);
                    opcVender.panelProductos.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e){
                            try {
                                escribirFichero(new File("src/File/config"),"productos");
                                abrirVentanaVenta();
                                opcVender.dispose();
                            } catch (UnsupportedEncodingException ex) {}
                        }
                    });
                    opcVender.panelFunciones.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e){
                            try {
                                escribirFichero(new File("src/File/config"),"funciones");
                                abrirVentanaVenta();
                                opcVender.dispose();
                            } catch (UnsupportedEncodingException ex) {}
                        }
                    });
                    opcVender.panelBack.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e){
                            File file = new File("src/File/config");
                            file.delete();
                            opcVender.dispose();
                            vista.setVisible(true);
                        }
                    });
                    this.vista.dispose();
                    
                }
                else{
                    abrirVentanaVenta();
                }

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
    
    public void abrirVentanaVenta(){
      try {
          String tipoVenta = leerFichero();
          if("funciones".equals(tipoVenta)){
              VentaBoletos vBol = new VentaBoletos();
              modeloVentaBoletos mVBol = new modeloVentaBoletos();
              ControladorVentaBoletos conVenBol = new ControladorVentaBoletos(vBol,mVBol,idEmpleado);
              conVenBol.iniciarVista();
          }
          else if("productos".equals(tipoVenta)){
              VentaProductos vProd = new VentaProductos();
              modeloVentaProductos modVentProd = new modeloVentaProductos();
              ControladorVentaProductos conVProd = new ControladorVentaProductos(vProd, modVentProd, idEmpleado);
              conVProd.iniciarVista();
          }
      } catch (FileNotFoundException ex) {
          Logger.getLogger(controladorLogin.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    public boolean existeFichero(){
        File config = new File("src/File/config"); 
        boolean fileExist = config.exists();
        if(!fileExist){
            config.getParentFile().mkdirs();
            try {
                config.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(controladorLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fileExist;
    }
   
    public void escribirFichero(File conf, String tipoVenta) throws UnsupportedEncodingException{
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(conf), "utf-8"))) {
            writer.write(String.valueOf("TipoVenta:"+tipoVenta));
         }  catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public String leerFichero() throws FileNotFoundException{
        Properties prop = new Properties();
        InputStream is = new FileInputStream("src/File/config");
        String suc = null;
        try {
            prop.load(is);
            suc = prop.getProperty("TipoVenta");
            is.close();
        } catch (IOException ex) {
            System.out.println("No se pudo cargar el archivo");
        }
        if(suc == null)
        {
            File file = new File("src/File/config");
            file.delete();
        }
        return prop.getProperty("TipoVenta");
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
