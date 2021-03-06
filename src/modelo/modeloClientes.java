/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Adrián Scott
 */
public class modeloClientes extends modeloPrincipal{
    public String[][] callObtenerDatos(){
        //txtQuery devuelve TODOS los campos que se van a mostrar en la parte de datos
        String txtQuery = "SELECT * FROM cliente;";
        return super.obtenerDatos(txtQuery);
    }
    public DefaultTableModel callObtenerDatosTabla(){
        //txtQueryTabla es la consulta que jalará los datos que irán en la tabla solamente
        String txtQueryTabla = "SELECT * FROM cliente;";
        //Se obtienen los datos de la consulta de la tabla
        String[][] datosTabla = super.obtenerDatos(txtQueryTabla);
        //Se declaran los nombres de las columnas que llevará la table (Esta madre no tiene nada que ver con la base de datos si no con JTable)
        String[] columnasTabla = new String[]{"ID","Nombre","Dirección","Correo","Puntos","Fecha de antigüedad"};
        return obtenerDatosTabla(datosTabla, columnasTabla);
    }
    
    public DefaultTableModel callFiltrarTabla(String dato){
        String[] columnas = new String[]{"ID","Nombre","Dirección","Correo","Puntos","Fecha de antigüedad"};
        String Query = "SELECT * FROM cliente;";
        return super.filtrarTabla(Query, columnas);
    }
}
