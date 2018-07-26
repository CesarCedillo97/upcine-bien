/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cesar Cedillo
 */
public class modeloPelicuas {
     private Conexion conexion = new Conexion();
    
    public boolean insertarPelicula(String nombre, String director, int duracion, String clasif, String generos, String actores, String idioma, String subti, String formato, String imagen){
        try
        {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            s.executeUpdate("insert into pelicula(Nombre, Director, Duracion, Clasificacion, Generos, Actores, Idioma, Subtitulos, Formato, Imagen) values('"+nombre+"', '"+director+"', "+duracion+", '"+clasif+"', '"+generos+"', '"+actores+"', '"+idioma+"', '"+subti+"', '"+formato+"', '"+imagen+"');");
            conexion.cerrarConexion(con);
            return true;
        } catch (SQLException e) {
            return false;
        }
        
    }
    
    public boolean modificarPeli(String id,String nombre, String director, int duracion, String clasif, String generos, String actores, String idioma, String subti, String formato, String imagen){
        try
        {
            Connection con = conexion .abrirConexion();
            Statement s = con.createStatement();
            System.out.println("update pelicula set Nombre = '"+nombre+"', Director = '"+director+"', Duracion = "+duracion+", Clasificacion = '"+clasif+"', Generos = '"+generos+"', Actores = '"+actores+"', Idioma = '"+idioma+"', Subtitulos  = '"+subti+"', Formato = '"+formato+"', Imagen = '"+imagen+"' where IdPelicula = "+id+"");
            s.executeUpdate("update pelicula set Nombre = '"+nombre+"', Director = '"+director+"', Duracion = "+duracion+", Clasificacion = '"+clasif+"', Generos = '"+generos+"', Actores = '"+actores+"', Idioma = '"+idioma+"', Subtitulos  = '"+subti+"', Formato = '"+formato+"', Imagen = '"+imagen+"' where IdPelicula = "+id+"");

            conexion.cerrarConexion(con);
            return true;
        
        } catch (SQLException e) {
            return false;
        } 
    }
    
    public boolean deletePelicula(int id){
        try
        {
            Connection con = conexion .abrirConexion();
            Statement s = con.createStatement();
            s.executeUpdate("delete from pelicula where IdPelicula = "+id+"");
            conexion.cerrarConexion(con);
            return true;
        
        } catch (SQLException e) {
            return false;
        } 
    }
    
    public DefaultTableModel consultarDisponibilidad(){
        try{
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            DefaultTableModel modelo;
            
            try{
                ResultSet rs = s.executeQuery("select titulo as Libro, autor as Autor, disponibles as Disponibles, nombre as Sucursal, telefono as Teléfono from biblioteca.inventario , biblioteca.sucursal , biblioteca.libro where libro_idlibro = idlibro and idsucursal = sucursal_idsucursal;");
                modelo = new DefaultTableModel();
                
                ResultSetMetaData rsMd = rs.getMetaData();
                int cantidadColumnas = rsMd.getColumnCount();
                
                for(int i=1;i <=cantidadColumnas;i++){
                    modelo.addColumn(rsMd.getColumnLabel(i));
                }
                while(rs.next()){
                    Object[] fila = new Object[cantidadColumnas];
                    for(int i = 0; i<cantidadColumnas; i++){
                        fila[i]=rs.getObject(i+1);
                        
                    }
                    modelo.addRow(fila);
                }
                return modelo;
            }finally{
                conexion.cerrarConexion(con);
            }
        }
        catch(SQLException e){
            return null;
        }
    }
}
