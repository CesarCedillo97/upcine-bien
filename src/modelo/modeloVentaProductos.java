/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
    
        public boolean insertarProductos(String cantidad,String costo,String precio,String prov,String descripcion){
        try
        {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            s.executeUpdate("insert into producto(Cantidad, Costo, Precio_venta, proveedor_idProveedor, Descripcion) values('"+cantidad+"', '"+costo+"', '"+precio+"', '"+prov+"', '"+descripcion+"')");
            conexion.cerrarConexion(con);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
