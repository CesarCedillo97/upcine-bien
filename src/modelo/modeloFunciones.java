/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.swing.table.DefaultTableModel;

/**
 * @author Adrián Scott
 */
public class modeloFunciones extends modeloPrincipal{
    /**
     * Datos retornado
     * <ul>
     * <li>0 IdFuncion</li>
     * <li>1 InicioVenta</li>
     * <li>2 Fecha_inicio</li>
     * <li>3 Fecha_fin</li>
     * <li>4 Hora_inicio</li>
     * <li>5 Hora_fin</li>
     * <li>6 Estatus</li>
     * <li>7 IdPelicula</li>
     * <li>8 pelicula.Nombre</li>
     * <li>9 IdSala</li>
     * <li>10 NumSala</li>
     * </ul>
     */
    public String[][] callObtenerDatos(){
        //txtQuery devuelve TODOS los campos que se van a mostrar en la parte de datos
        String txtQuery = "SELECT funcion.IdFuncion, funcion.InicioVenta, funcion.Fecha_inicio, funcion.Fecha_fin, funcion.Hora_inicio, funcion.Hora_fin, funcion.Estatus, pelicula.IdPelicula, pelicula.Nombre, sala.IdSala, sala.NumSala FROM sala, funcion, pelicula WHERE funcion.pelicula_IdPelicula = pelicula.IdPelicula AND funcion.sala_IdSala = sala.IdSala;";
        return super.obtenerDatos(txtQuery);
    }
    public DefaultTableModel callObtenerDatosTabla(){
        //txtQueryTabla es la consulta que jalará los datos que irán en la tabla solamente
        String txtQueryTabla = "SELECT funcion.IdFuncion, pelicula.Nombre, sala.NumSala, funcion.Estatus, funcion.InicioVenta FROM sala, funcion, pelicula WHERE funcion.pelicula_IdPelicula = pelicula.IdPelicula AND funcion.sala_IdSala = sala.IdSala;";
        //Se obtienen los datos de la consulta de la tabla
        String[][] datosTabla = super.obtenerDatos(txtQueryTabla);
        //Se declaran los nombres de las columnas que llevará la table (Esta madre no tiene nada que ver con la base de datos si no con JTable)
        String[] columnasTabla = new String[]{"ID","Pelicula","Sala","Estado","Inicio venta"};
        return obtenerDatosTabla(datosTabla, columnasTabla);
    }
    
    public DefaultTableModel callFiltrarTabla(String dato){
        String[] columnas = new String[]{"ID","Pelicula","Sala","Estado","Inicio venta"};
        String Query = "select * from salaSELECT funcion.IdFuncion, pelicula.Nombre, sala.NumSala, funcion.Estatus, funcion.InicioVenta FROM sala, funcion, pelicula WHERE funcion.pelicula_IdPelicula = pelicula.IdPelicula AND funcion.sala_IdSala = sala.IdSala;";
        return super.filtrarTabla(Query, columnas);
    }
    
}
