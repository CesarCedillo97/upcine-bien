/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Cesar Cedillo
 */
public class modeloVentaProductos extends modeloPrincipal{
    
    
    public String[][] callObtenerDatosCombo(){
        //txtQuery devuelve TODOS los campos que se van a mostrar en la parte de datos
        String txtQuery = "SELECT idProveedor, Nombre_empresa \n" +
                                " FROM proveedor;";
        return super.obtenerDatos(txtQuery);
    }
    
        public boolean insertarVenta(String cantidad,String costo,String precio,String prov,String descripcion){
            try
            {
                Connection con = conexion.abrirConexion();
                Statement s = con.createStatement();
                s.executeUpdate("insert into producto(Cantidad, Costo, Precio_venta, proveedor_idProveedor, Descripcion) values('"+cantidad+"', '"+costo+"', '"+precio+"', '"+prov+"', '"+descripcion+"')");
                conexion.cerrarConexion(con);
                return true;
            } catch (SQLException e) 
            {
                return false;
            }
        }
        
        public String[][] obtenerCombos(){
        //txtQuery devuelve TODOS los campos que se van a mostrar en la parte de datos
        String txtQuery = "SELECT *FROM combos;";
        return super.obtenerDatos(txtQuery);
        }
        
        public String[][] obtenerProductos(){
        //txtQuery devuelve TODOS los campos que se van a mostrar en la parte de datos
        String txtQuery = "SELECT *FROM producto ;";
        return super.obtenerDatos(txtQuery);
        }
        
        public String[] obtenerCliente(int idCliente){
        //txtQuery devuelve TODOS los campos que se van a mostrar en la parte de datos
        String txtQuery = "SELECT idCliente, Nombre, Puntos FROM cliente WHERE idCliente="+idCliente+";";
        String[][] datos = super.obtenerDatos(txtQuery);
        if(datos.length > 0){
            return datos[0];
        }
        else return null;
        }
        
        
        public boolean insertarVenta(float subtotal, float iva, float total, int idEmp, int idCliente, ArrayList a){
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Connection con = conexion.abrirConexion();
            PreparedStatement s = con.prepareStatement("INSERT INTO venta(TipoVenta, Fecha, Subtotal, IVA, Total, empleado_IdEmpleado)VALUES (2,'"+df.format(date)+"', "+subtotal+", "+iva+", "+total+", "+idEmp+")",Statement.RETURN_GENERATED_KEYS);
            s.executeUpdate();
            ResultSet rs = s.getGeneratedKeys();
            rs.next();
            int idVenta = rs.getInt(1);
            Statement q = con.createStatement();

            conexion.cerrarConexion(con);
            return true;
        }
        catch(SQLException e){
            return false;
        }
    }
}
