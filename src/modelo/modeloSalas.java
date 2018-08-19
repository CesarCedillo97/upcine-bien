/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Adrián Scott
 */
public class modeloSalas extends modeloPrincipal{
     public String[][] callObtenerDatos(){
        //txtQuery devuelve TODOS los campos que se van a mostrar en la parte de datos
        String txtQuery = "SELECT * from sala;";
        return super.obtenerDatos(txtQuery);
    }
   
    public DefaultTableModel callObtenerDatosTabla(){
        //txtQueryTabla es la consulta que jalará los datos que irán en la tabla solamente
        String txtQueryTabla = "SELECT * from sala;";
        //Se obtienen los datos de la consulta de la tabla
        String[][] datosTabla = super.obtenerDatos(txtQueryTabla);
        //Se declaran los nombres de las columnas que llevará la table (Esta madre no tiene nada que ver con la base de datos si no con JTable)
        String[] columnasTabla = new String[]{"ID","NumSala","Filas","Columnas","Num. Asientos","Tipo","Estatus"};
        return obtenerDatosTabla(datosTabla, columnasTabla);
    }
    
    public DefaultTableModel callFiltrarTabla(String dato){
        String[] columnas = {"ID","NumSala","Filas","Columnas","Num. Asientos","Tipo","Estatus"};
        String Query = "select * from sala";
        return super.filtrarTabla(Query, columnas);
    }
    
    public boolean[][] consultarAsientos(int idSala,int filas, int cols){
        int id = -1;
        int fila = 0, columna = 0;
        boolean[][] asientos = new boolean[filas][cols];
        try{
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select Fila, Columna from asiento where sala_IdSala = "+idSala+" ");
            while(rs.next()){
                fila = rs.getInt("Fila");
                columna = rs.getInt(("Columna"));
                asientos[fila][columna] = true;
                System.out.println("Fila: "+fila+"Col: "+columna);
            }
            conexion.cerrarConexion(con);
            return asientos;
        }
        catch(SQLException e){
            System.out.println("no se pudo realizar la consulta");
            return null;
        }
    } 
    public boolean eliminarAsientos(int idSala){
        try
        {
            Connection con = conexion .abrirConexion();
            Statement s = con.createStatement();
            s.executeUpdate("delete from asiento where sala_IdSala = "+idSala+"");
            conexion.cerrarConexion(con);
            return true;
        
        } catch (SQLException e) {
            return false;
        }
    }
}
