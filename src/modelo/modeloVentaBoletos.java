/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.conAlerts.controladorError;
import controlador.conAlerts.controladorSucces;
import controlador.controladorEmpleados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrián Scott
 */
public class modeloVentaBoletos extends modeloPrincipal{
    public String[][] obtenerTiposBoletos(){
        //txtQuery devuelve TODOS los campos que se van a mostrar en la parte de datos
        String txtQuery = "SELECT Descripcion, Precio FROM mantenimiento WHERE Descripcion != 'IVA';";
        return super.obtenerDatos(txtQuery);
    }
    
    public String[][] obtenerPeliculas(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String date = dateFormat.format(date1);
        String txtQuery = "SELECT pelicula.IdPelicula, pelicula.Nombre, pelicula.Idioma, pelicula.Subtitulos, pelicula.Formato, funcion.Hora_inicio, pelicula.Imagen, funcion.Fecha_inicio, funcion.Fecha_fin,funcion.sala_IdSala, funcion.IdFuncion \n" +
                            "FROM funcion,pelicula \n" +
                            "WHERE pelicula.IdPelicula = funcion.pelicula_IdPelicula AND Estatus = 1 \n"+
                            "AND STR_TO_DATE('"+date+"', '%Y-%m-%d %H:%i:%s') >= funcion.InicioVenta  AND STR_TO_DATE('"+date+"', '%Y-%m-%d %H:%i:%s') <= funcion.Fecha_fin ORDER BY funcion.Hora_inicio";
        return super.obtenerDatos(txtQuery);
    }
    
    public String[] obtenerCliente(int idCliente){
        //txtQuery devuelve TODOS los campos que se van a mostrar en la parte de datos
        String txtQuery = "SELECT idCliente, Nombre, Puntos FROM cliente WHERE idCliente="+idCliente+";";
        String[][] datos = super.obtenerDatos(txtQuery);
        if(datos.length > 0){
            return datos[0];
        }
        else return null;
    }
    
    public int getNumAsientos(int idSala){
        try{
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT Num_Asientos FROM sala WHERE IdSala = "+idSala+" AND Estatus = 1");
            rs.next();
            int num = rs.getInt("Num_Asientos");
            conexion.cerrarConexion(con);
            return num;
        }
        catch(SQLException e){
            return -1;
        }
    }
    public int[][] obtenerAsientos(int idSala){
        int id = -1;
        int fila = 0, columna = 0;
        int filas = 0, cols =0;
        try{
            Connection con = conexion.abrirConexion();
            Statement q = con.createStatement();
            //obtiene filas y columnas de la sala
            ResultSet rs2 = q.executeQuery("select Filas, Columnas from sala where IdSala = "+idSala+"");
            rs2.next();
            filas = rs2.getInt("Filas");
            cols = rs2.getInt("Columnas");
            int[][] asientos = new int[filas][cols];
            for (int[] asiento : asientos)
            {
                for (int j = 0; j < cols; j++)
                {
                    asiento[j] = -1;
                }                
            }
            
            Statement w = con.createStatement();
            ResultSet rs3 = w.executeQuery("select Fila, Columna from asiento where sala_IdSala = "+idSala+"");
            while(rs3.next()){
                fila = rs3.getInt("Fila");
                columna = rs3.getInt(("Columna"));
                asientos[fila][columna] = 0;
            }
            //obtiene los asientos
            Statement s = con.createStatement();
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            ResultSet rs = s.executeQuery("select Fila, Columna from asiento, venta, boleto where asiento.sala_IdSala = "+idSala+" and boleto.venta_idventa = venta.idventa and venta.Fecha = '"+df.format(date)+"' and boleto.asiento_idAsiento = asiento.idAsiento");
            while(rs.next()){
                fila = rs.getInt("Fila");
                columna = rs.getInt(("Columna"));
                asientos[fila][columna] = 1;
            }
            conexion.cerrarConexion(con);
            return asientos;
        }
        catch(SQLException e){
            return null;
        }
    }
    public boolean insertarVenta2(String subtotal, String iva, String total, int idEmp, int idCliente, int[][] selectedSeats, int idSala, int idFuncion) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Connection con = conexion.abrirConexion();
            System.out.println("INSERT INTO venta(TipoVenta, Fecha, Subtotal, IVA, Total, empleado_IdEmpleado,cliente_idCliente)VALUES (1,'"+df.format(date)+"', "+subtotal+", "+iva+", "+total+", "+idEmp+","+idCliente+");");
            PreparedStatement s = con.prepareStatement("INSERT INTO venta(TipoVenta, Fecha, Subtotal, IVA, Total, empleado_IdEmpleado,cliente_idCliente)VALUES (1,'"+df.format(date)+"', "+subtotal+", "+iva+", "+total+", "+idEmp+","+idCliente+")",Statement.RETURN_GENERATED_KEYS);
            s.executeUpdate();
            ResultSet rs = s.getGeneratedKeys();
            if(rs.next())System.out.println("si hay next");
            int idVenta = rs.getInt(1);
            Statement q = con.createStatement();
            for (int[] selectedSeat : selectedSeats)
            {
                q.executeUpdate("INSERT INTO boleto(funcion_IdFuncion, asiento_idAsiento, venta_idventa) VALUES ("+idFuncion+", (SELECT idAsiento FROM asiento where Fila = " + selectedSeat[0] + " and Columna = " + selectedSeat[1] + " and sala_IdSala = " + idSala + "), " + idVenta + ")");
            }
            conexion.cerrarConexion(con);
            return true;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            System.out.println("Se ha terminado");
        }
    }
    public boolean modificarPuntos(String id,String puntos){
        String[] table_columns={"idCliente","Puntos"};
        String[] table_values={id,puntos};
        Connection con;
        try {
            con = conexion.abrirConexion();
            return super.modificar("cliente", table_columns, table_values, con);
        } catch (SQLException ex) {
            return false;
        }
    }
    public boolean insertarVenta(String subtotal, String iva, String total, int idEmp, int idCliente, int[][] selectedSeats, int idSala, int idFuncion) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        String[] table_columns = {"TipoVenta","Fecha","Subtotal","IVA","Total","Empleado_idEmpleado","cliente_idCliente"};
        String[] table_values = {"1",df.format(date), subtotal, iva, total, String.valueOf(idEmp), ""};
        
        String[] table_columns2 = {"funcion_IdFuncion","asiento_idAsiento","venta_idVenta"};
        try{
            Connection con = conexion.abrirConexion();
            if(con!=null){
                con.setAutoCommit(false);
            }
            int idVenta;
            if((idVenta = insertar("venta", table_columns, table_values, con))!= -1){
                for (int[] selectedSeat : selectedSeats)
                {
                    String asiento = "(SELECT idAsiento FROM asiento where Fila = " + selectedSeat[0] + " and Columna = " + selectedSeat[1] + " and sala_IdSala = " + idSala +")";
                    String[] table_values2 = {String.valueOf(idFuncion),asiento,String.valueOf(idVenta)};
                    insertar("boleto", table_columns2, table_values2, con);
                }
            }
            else{
                return false;
            }
            con.commit();
            conexion.cerrarConexion(con);
            return true;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
