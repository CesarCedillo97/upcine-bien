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
public class modeloCombos extends modeloPrincipal{
    
    public String[][] callObtenerDatos(){
        //txtQuery devuelve TODOS los campos que se van a mostrar en la parte de datos
        String txtQuery = "SELECT * FROM combos;";
        return super.obtenerDatos(txtQuery);
    }
   
    public DefaultTableModel callObtenerDatosTabla(){
        //txtQueryTabla es la consulta que jalará los datos que irán en la tabla solamente
        String txtQueryTabla = "SELECT * FROM combos";
        //Se obtienen los datos de la consulta de la tabla
        String[][] datosTabla = super.obtenerDatos(txtQueryTabla);
        //Se declaran los nombres de las columnas que llevará la table (Esta madre no tiene nada que ver con la base de datos si no con JTable)
        String[] columnasTabla = new String[]{"ID","Precio","Nombre"};
        return obtenerDatosTabla(datosTabla, columnasTabla);
    }
    
    public DefaultTableModel callFiltrarTabla(String dato){
        String[] columnas = new String[]{"ID","Precio","Nombre"};
        String Query = "SELECT Descripcion, Precio_venta, Nombre_empresa, Cantidad FROM producto, proveedor WHERE producto.proveedor_idProveedor = proveedor.idProveedor and Descripcion LIKE '"+ dato +"%'";
        return super.filtrarTabla(Query, columnas);
    }
    
    public DefaultTableModel obtenerDatosTablaLista(String id){
        //txtQuery devuelve TODOS los campos que se van a mostrar en la parte de datos
        String[][] datos = super.obtenerDatos("SELECT producto.Descripcion, detalles_combos.cantidad FROM combos,detalles_combos, producto WHERE id_IdCombo = idCombos AND id_IdCombo="+id+" AND detalles_combos.id_IdProductos = IdProducto");
        String[] columna = {"Producto","Descripción"};
        return super.obtenerDatosTabla(datos,columna);
    }
    
}
