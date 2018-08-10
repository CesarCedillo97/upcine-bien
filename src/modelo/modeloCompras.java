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
public class modeloCompras extends modeloPrincipal{
    public String[][] callObtenerDatos(){
        //txtQuery devuelve TODOS los campos que se van a mostrar en la parte de datos
        String txtQuery = "SELECT * from compra;";
        return super.obtenerDatos(txtQuery);
    }
   
    public DefaultTableModel callObtenerDatosTabla(){
        //txtQueryTabla es la consulta que jalará los datos que irán en la tabla solamente
        String txtQueryTabla = "SELECT * from compra;";
        //Se obtienen los datos de la consulta de la tabla
        String[][] datosTabla = super.obtenerDatos(txtQueryTabla);
        //Se declaran los nombres de las columnas que llevará la table (Esta madre no tiene nada que ver con la base de datos si no con JTable)
        String[] columnasTabla = new String[]{"Id","Subtotal","IVA","Total","Fecha","Estado","Empleado"};
        return obtenerDatosTabla(datosTabla, columnasTabla);
    }
    
    public DefaultTableModel callFiltrarTabla(String dato){
        String[] columnas = {"Id","Subtotal","IVA","Total","Fecha","Estado","Empleado"};
        String Query = "select * from compra;";
        return super.filtrarTabla(Query, columnas);
    }
            
}
