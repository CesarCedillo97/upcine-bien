/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesus
 */
public class modeloReportes extends modeloPrincipal {
    private Conexion conexion = new Conexion();

    public void llenarComboEmpleado(JComboBox<EmpleadoComboBox> comboEmpleado)
    {
        try
        {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         ResultSet rs=s.executeQuery("SELECT idEmpleado,Nombre FROM empleado ORDER BY Nombre");
         while(rs.next())
         {
             comboEmpleado.addItem(new EmpleadoComboBox(rs.getInt("idEmpleado"),rs.getString("Nombre")));
         }
         conexion.cerrarConexion(con);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public String[][] callObtenerDatos(){
        //txtQuery devuelve TODOS los campos que se van a mostrar en la parte de datos
        String txtQuery = "SELECT DISTINCT venta.idventa AS ID,venta.TipoVenta,venta.Fecha,venta.Subtotal,venta.Descuento,venta.IVA,venta.Total, empleado.Nombre AS NOMBRE_EMPLEADO,\n" +
"                            CASE WHEN venta.cliente_idCliente IS NOT NULL THEN cliente.Nombre\n" +
"                                 WHEN venta.cliente_idCliente IS NULL THEN \"\"\n" +
"				END\n" +
"                            AS NOMBRE_CLIENTE FROM venta, empleado, cliente WHERE venta.empleado_IdEmpleado = empleado.IdEmpleado AND (venta.cliente_idCliente = cliente.idCliente OR venta.cliente_idCliente IS NULL);";
        return super.obtenerDatos(txtQuery);
    }

    public class EmpleadoComboBox
    {
        private int id_emp;
        private String nombre_emp;

        public EmpleadoComboBox(int id_emp, String nombre_emp) {
            this.id_emp = id_emp;
            this.nombre_emp = nombre_emp;
        }

        public int getId_emp() {
            return id_emp;
        }

 
        public void setId_emp(int id_emp) {
            this.id_emp = id_emp;
        }

        
        public String getNombre_emp() {
            return nombre_emp;
        }

        
        public void setNombre_emp(String nombre_emp) {
            this.nombre_emp = nombre_emp;
        }
        
        @Override
        public String toString()
        {
            return nombre_emp;
        }
    }
}
