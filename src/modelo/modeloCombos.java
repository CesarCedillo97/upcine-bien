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
    
    public String[][] obtenerProductos(String idCombo){
        //txtQuery devuelve TODOS los campos que se van a mostrar en la parte de datos
        String txtQuery = "SELECT * FROM detalles_combos where id_IdCombo = '"+idCombo+"';";
        return super.obtenerDatos(txtQuery);
    }
    
    public String[][] obtenerProductosFilter( String dato){
        //txtQuery devuelve TODOS los campos que se van a mostrar en la parte de datos
        String txtQuery = "SELECT * FROM combos where nombre LIKE '"+ dato +"%'";
        return super.obtenerDatos(txtQuery);
    }
       
    public DefaultTableModel callObtenerDatosTabla(){
        //txtQueryTabla es la consulta que jalará los datos que irán en la tabla solamente
        String txtQueryTabla = "SELECT nombre, precio FROM combos;";
        //Se obtienen los datos de la consulta de la tabla
        String[][] datosTabla = super.obtenerDatos(txtQueryTabla);
        //Se declaran los nombres de las columnas que llevará la table (Esta madre no tiene nada que ver con la base de datos si no con JTable)
        String[] columnasTabla = new String[]{"Nombre","Precio"};
        return obtenerDatosTabla(datosTabla, columnasTabla);
    }
    
    public DefaultTableModel callObtenerDatosTablaLista(String idCombo){
        String txtQueryTabla = "SELECT * FROM combos";
        //Se obtienen los datos de la consulta de la tabla
        String[][] datosTabla = super.obtenerDatos(txtQueryTabla);
        //Se declaran los nombres de las columnas que llevará la table (Esta madre no tiene nada que ver con la base de datos si no con JTable)
        String[] columnasTabla = new String[]{"ID","Precio","Nombre"};
        return obtenerDatosTabla(datosTabla, columnasTabla);
    }
    
    public DefaultTableModel callFiltrarTabla(String dato){
        String[] columnas = {"Nombre","Precio"};
        String Query = "SELECT nombre, precio FROM combos WHERE nombre LIKE '"+ dato +"%'";
        return super.filtrarTabla(Query, columnas);
    }
    
    public DefaultTableModel obtenerDatosTablaLista(String id){
        //txtQuery devuelve TODOS los campos que se van a mostrar en la parte de datos
        String[][] datos = super.obtenerDatos("SELECT producto.Descripcion, detalles_combos.cantidad FROM combos,detalles_combos, producto WHERE id_IdCombo = idCombos AND id_IdCombo="+id+" AND detalles_combos.id_IdProductos = IdProducto");
        String[] columna = {"Producto","Descripción"};
        return super.obtenerDatosTabla(datos,columna);
    }
    
}
