/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cesar Cedillo
 */
public class modeloProductos extends modeloPrincipal{
    
    public String[][] callObtenerDatos(){
        //txtQuery devuelve TODOS los campos que se van a mostrar en la parte de datos
        String txtQuery = "SELECT IdProducto, Cantidad, Costo, Precio_venta, proveedor_idProveedor, Descripcion  \n" +
                                " FROM producto order by Descripcion;";
        return super.obtenerDatos(txtQuery);
    }
   
    public DefaultTableModel callObtenerDatosTabla(){
        //txtQueryTabla es la consulta que jalará los datos que irán en la tabla solamente
        String txtQueryTabla = "SELECT Descripcion, Precio_venta, Nombre_empresa, Cantidad FROM producto, proveedor WHERE producto.proveedor_idProveedor = proveedor.idProveedor order by Descripcion;";
        //Se obtienen los datos de la consulta de la tabla
        String[][] datosTabla = super.obtenerDatos(txtQueryTabla);
        //Se declaran los nombres de las columnas que llevará la table (Esta madre no tiene nada que ver con la base de datos si no con JTable)
        String[] columnasTabla = new String[]{"Descripcion","Precio","Proveedor","Cantidad"};
        return obtenerDatosTabla(datosTabla, columnasTabla);
    }
    
    public DefaultTableModel callFiltrarTabla(String dato){
        String[] columnas = {"Descripcion","Precio","Proveedor","Cantidad"};
        String Query = "SELECT Descripcion, Precio_venta, Nombre_empresa, Cantidad FROM producto, proveedor WHERE producto.proveedor_idProveedor = proveedor.idProveedor and Descripcion LIKE '"+ dato +"%' order by Descripcion";
        return super.filtrarTabla(Query, columnas);
    }
    
    public String[][] callObtenerDatosCombo(){
        //txtQuery devuelve TODOS los campos que se van a mostrar en la parte de datos
        String txtQuery = "SELECT idProveedor, Nombre_empresa \n" +
                                " FROM proveedor;";
        return super.obtenerDatos(txtQuery);
    }
    
}
