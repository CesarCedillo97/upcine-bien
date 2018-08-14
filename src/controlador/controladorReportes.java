/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.toedter.calendar.JCalendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


import modelo.Conexion;
import modelo.modeloReportes;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import vista.IF_Reportes;
/**
 *
 * @author Jesus
 */
public class controladorReportes extends ControladorPrincipal implements ActionListener{
    
    int report=1;
    String Mes="VentasMonth.jasper";
    String Dia="Ventas.jasper";
    IF_Reportes vista = new IF_Reportes();
    
    modeloReportes modelo = new modeloReportes();
    String[][] datos;
    public controladorReportes(IF_Reportes vista)
    {
        this.vista=vista;
    }
    @Override
    public void iniciarVista() {
        String[] columnas = new String[]{"ID","Tipo Venta","Fecha","Subtotal","Descuento","IVA","Total","Empleado","Cliente"};
        String txtQuery = "SELECT DISTINCT venta.idventa AS ID,venta.TipoVenta,venta.Fecha,venta.Subtotal,venta.Descuento,venta.IVA,venta.Total, empleado.Nombre AS NOMBRE_EMPLEADO,\n" +
"                            CASE WHEN venta.cliente_idCliente IS NOT NULL THEN cliente.Nombre\n" +
"                                 WHEN venta.cliente_idCliente IS NULL THEN \"\"\n" +
"				END\n" +
"                            AS NOMBRE_CLIENTE FROM venta, empleado, cliente WHERE venta.empleado_IdEmpleado = empleado.IdEmpleado AND (venta.cliente_idCliente = cliente.idCliente OR venta.cliente_idCliente IS NULL)";
        datos = modelo.obtenerDatos(txtQuery);
        vista.JTable.setModel(modelo.obtenerDatosTabla(datos,columnas));
        vista.btnGenerar.addActionListener(this);
        vista.cmbOpc.addActionListener(this);
        vista.JMes.setEnabled(true);
        vista.jDate.setEnabled(false);
        vista.cmbEmpleados.setEnabled(false);
        modelo.llenarComboEmpleado(vista.cmbEmpleados);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.cmbOpc)
        {
         if(vista.cmbOpc.getItemAt(vista.cmbOpc.getSelectedIndex()) == "Mes")
         {
             vista.JMes.setEnabled(true);
             vista.jDate.setEnabled(false);
             vista.cmbEmpleados.setEnabled(false);
             report=1;
         }
         else if(vista.cmbOpc.getItemAt(vista.cmbOpc.getSelectedIndex()) == "Dia")
         {
             vista.JMes.setEnabled(false);
             vista.jDate.setEnabled(true);
             vista.cmbEmpleados.setEnabled(false);
             report=2;
         }
         else if(vista.cmbOpc.getItemAt(vista.cmbOpc.getSelectedIndex()) == "Empleado")
         {
             vista.cmbEmpleados.setEnabled(true);
             vista.JMes.setEnabled(false);
             vista.jDate.setEnabled(false);
             report=3;
         }
        }
        else if(e.getSource() == vista.btnGenerar && report == 2)
        {
            ReporteDia();
        }
        else if(e.getSource() == vista.btnGenerar && report== 1)
        {
            ReporteMes();
        }
        else if(e.getSource() == vista.btnGenerar && report == 3)
        {
            ReporteEmpleado();
        }
    }
    public void ReporteDia(){
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String fecha= df.format(vista.jDate.getDate());
            System.out.println(fecha);
            try {
                Conexion con = new Conexion();
                Connection conn= con.abrirConexion();
                JasperReport reporte = null;
                String path = "src\\reportes\\Ventas.jasper";
                
                Map parametro = new HashMap();
                parametro.put("FechaB", fecha);
                
                reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                
                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro,conn);
                
                JasperViewer view = new JasperViewer(jprint, false);
                
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                view.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(controladorReportes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JRException ex) {
                Logger.getLogger(controladorReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void ReporteMes()
    {       
           
            String fecha= String.valueOf(vista.JMes.getMonth()+1);
            //System.out.println(fecha);
            try {
                Conexion con = new Conexion();
                Connection conn= con.abrirConexion();
                JasperReport reporte = null;
                String path = "src\\reportes\\VentasMonth.jasper";
                
                Map parametro = new HashMap();
                parametro.put("Mes", fecha);
                
                reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                
                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro,conn);
                
                JasperViewer view = new JasperViewer(jprint, false);
                
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                view.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(controladorReportes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JRException ex) {
                Logger.getLogger(controladorReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void ReporteEmpleado()
    {
        int id_emp= vista.cmbEmpleados.getItemAt(vista.cmbEmpleados.getSelectedIndex()).getId_emp();
        System.out.println(id_emp);
        try {
                Conexion con = new Conexion();
                Connection conn= con.abrirConexion();
                JasperReport reporte = null;
                String path = "src\\reportes\\VentasEmpleado.jasper";
                
                Map parametro = new HashMap();
                parametro.put("Empleado", id_emp);
                
                reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                
                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro,conn);
                
                JasperViewer view = new JasperViewer(jprint, false);
                
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                view.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(controladorReportes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JRException ex) {
                Logger.getLogger(controladorReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
 