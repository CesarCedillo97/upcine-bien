/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jesus
 */
public class modeloProveedor extends modeloPrincipal{
    
    public String[][] callObtenerDatos(){
        //txtQuery devuelve TODOS los campos que se van a mostrar en la parte de datos
        String txtQuery = "SELECT * FROM proveedor;";
        return super.obtenerDatos(txtQuery);
    }
   
    public DefaultTableModel callObtenerDatosTabla(){
        //txtQueryTabla es la consulta que jalará los datos que irán en la tabla solamente
        String txtQueryTabla = "SELECT Nombre_empresa, Nombre_responsable, Direccion, Telefono FROM proveedor;";
        //Se obtienen los datos de la consulta de la tabla
        String[][] datosTabla = super.obtenerDatos(txtQueryTabla);
        //Se declaran los nombres de las columnas que llevará la table (Esta madre no tiene nada que ver con la base de datos si no con JTable)
        String[] columnasTabla = new String[]{"Empresa","Responsable","Direccion","Telefono"};
        return obtenerDatosTabla(datosTabla, columnasTabla);
    }
    
    public DefaultTableModel callFiltrarTabla(String dato){
        String[] columnas = {"Empresa","Responsable","Direccion","Telefono"};
        String Query = "Nombre_empresa, Nombre_responsable, Direccion, Telefono FROM proveedor where Nombre_empresa LIKE '"+ dato +"%'";
        return super.filtrarTabla(Query, columnas);
    }
}
