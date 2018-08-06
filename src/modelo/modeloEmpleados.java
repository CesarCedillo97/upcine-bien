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
public class modeloEmpleados extends modeloPrincipal{
    public String[][] callObtenerDatos(){
        //txtQuery devuelve TODOS los campos que se van a mostrar en la parte de datos
        String txtQuery = "SELECT IdEmpleado, Usuario, Contraseña, Nombre, Telefono, Direccion, Edad, Fecha_Inicio, \n" +
                                "case when Tipo = 1 then 'Administrador'\n" +
                                "     when Tipo = 2 then 'Empleado' \n" +
                                "     end as 'Tipo'\n" +
                                " FROM empleado, login WHERE empleado.IdEmpleado = login.empleado_IdEmpleado order by Nombre;";
        return super.obtenerDatos(txtQuery);
    }
   
    public DefaultTableModel callObtenerDatosTabla(){
        //txtQueryTabla es la consulta que jalará los datos que irán en la tabla solamente
        String txtQueryTabla = "SELECT Nombre, Telefono, Direccion, Edad FROM empleado, login WHERE empleado.IdEmpleado = login.empleado_IdEmpleado order by Nombre;";
        //Se obtienen los datos de la consulta de la tabla
        String[][] datosTabla = super.obtenerDatos(txtQueryTabla);
        //Se declaran los nombres de las columnas que llevará la table (Esta madre no tiene nada que ver con la base de datos si no con JTable)
        String[] columnasTabla = new String[]{"Nombre","Telefono","Direccion","Edad"};
        return obtenerDatosTabla(datosTabla, columnasTabla);
    }
    
    public DefaultTableModel callFiltrarTabla(String dato){
        String[] columnas = {"Nombre","Teléfono","Dirección","Edad"};
        String Query = "select Nombre, Telefono, Direccion, Edad from upcine.empleado where Nombre LIKE '"+ dato +"%'";
        return super.filtrarTabla(Query, columnas);
    }
            
}
