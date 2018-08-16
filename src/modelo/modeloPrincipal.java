
package modelo;

import controlador.conAlerts.controladorError;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;
import vista.alerts.alertError;
/**
 *
 * @author Adrián Scott
 */
public class modeloPrincipal {
    Conexion conexion = new Conexion();
    alertError alertError = new alertError();
    controladorError conError;
    //EJEMPLO DE USO
//    public static void main(String args[]){
//        String[] columns={"Nombre","Telefono","Direccion","Edad","Fecha_Inicio","Tipo"};
//        String[] values={"Perez Velazco","666777","Bicentenario","15","2018-07-31","2"};
//        modeloPrincipal mdl = new modeloPrincipal();
//        mdl.insertar("empleado",columns,values);
//        
//        String[] columnas={"IdEmpleado","Nombre","Telefono","Direccion","Edad","Fecha_Inicio","Tipo"};
//        String[] valores={"16","Papas Frescas","666777","Bicentenario","15","2018-07-31","2"};
//        mdl.modificar("empleado",columnas,valores);
//        
//        mdl.eliminar("empleado", "IdEmpleado", 16);
//    }
    public Connection abrirConexion(){
        try {
            Connection con = conexion.abrirConexion();
            return con;
        } catch (SQLException ex) {
            conError = new controladorError(alertError, "No se pudo conectar con el servidor");
            conError.iniciarVista();
            return null;
        }
    }
    
    public void cerrarConexion(Connection con){
        try {
            conexion.cerrarConexion(con);
        } catch (SQLException ex) {
            conError = new controladorError(alertError, "No se pudo conectar con el servidor");
            conError.iniciarVista();
        }
    }
    /**
     * Inserta valores a la tabla especificando las columnas y los valores
     * @param table_name Nombre de la tabla
     * @param table_columns Nombre de cada columna en la que se insertará 
     * @param table_values Los valores a insertar
     * @param con Recibe la conexión que se supone ya esta abierta
     * Retorna el id agregado porque hay muchas donde se hace doble inserción, si falla retorna -1
     * @return
     */
    public int insertar(String table_name,String[] table_columns, String[] table_values,  Connection con) {
        try {
            con.setAutoCommit(false);
            String columns="";
            String values="";
            //Se pasan los nombres de las columnas a cadena de texto
            for (String table_column : table_columns) {
                columns+=table_column+",";
            }
            columns = columns.substring(0, columns.length()-1);//Esta madre nomas quita la ultima coma para que no de error SQL
            //Se pasan los valores a insertar a cadena de texto
            for (String table_value : table_values) {
                values+="'"+table_value+"',";
            }
            values = values.substring(0, values.length()-1);//Lo de arriba pero ahora con values
            System.out.println("INSERT INTO "+table_name+"("+columns+")VALUES("+values+")");
            PreparedStatement s = con.prepareStatement("INSERT INTO "+table_name+"("+columns+")VALUES("+values+")",Statement.RETURN_GENERATED_KEYS);
            s.executeUpdate();
            ResultSet rs = s.getGeneratedKeys();
            int last_id = -1;
            if(rs.next()){
                last_id = rs.getInt(1);   
            }
            rs.close();
            return last_id;
        }catch (SQLException e) {
            String mensaje;
            if(e.getErrorCode() == 1062){
                mensaje = "Ya existe un usuario identico";
            }
            else{
                mensaje = "Algo ha sucedido";
            }
            System.out.println(e.getMessage());
            conError = new controladorError(alertError, mensaje);
            conError.iniciarVista();
            return -1;
        }
    }
    /**
     * Modifica los valores del campo escificando las columnas y sus valores<br>
     * <b>IMPORTANTE: table_columns y table_values deben recibir primero el campo y el valor del ID</b>
     * @param table_name Nombre de la tabla
     * @param table_columns Nombre de cada columna en la que se insertará 
     * @param table_values Los valores a insertar
     * @param con Recibe la conexión que se supone ya esta abierta
     * @return 
     */
    public boolean modificar(String table_name, String[] table_columns, String[] table_values, Connection con){
        try {
            String values="";
            //Se pasan los nombres de las columnas a cadena de texto
            for(int x = 1; x < table_columns.length ; x++){
                values+=table_columns[x]+"='"+table_values[x]+"',";
            }
            values = values.substring(0, values.length()-1);//Esta madre nomas quita la ultima coma para que no de error SQL
            Statement s = con.createStatement();
            System.out.println("UPDATE "+table_name+" SET("+values+")WHERE "+table_columns[0]+"="+table_values[0]+"");
            s.executeUpdate("UPDATE "+table_name+" SET "+values+" WHERE "+table_columns[0]+"="+table_values[0]+"");
            return true;
        }catch (SQLException e) {
            String mensaje;
            if(e.getErrorCode() == 1062){
                mensaje = "Ya existe un usuario identico";
            }
            else{
                mensaje = "Algo ha sucedido";
            }
            conError = new controladorError(alertError, mensaje);
            conError.iniciarVista();
            return false;
        }
    }
    /**
     * Sirve para eliminar registros 
     * @param table_name Nombre de la tabla
     * @param column_name Nombre del campo del ID
     * @param id Pos el ID mamon
     * @return 
     */
    public boolean eliminar(String table_name,String column_name, int id){
        try {
            Connection con = conexion.abrirConexion();
            if(con!=null){
                Statement s = con.createStatement();
                System.out.println("DELETE FROM "+table_name+" WHERE "+column_name+"="+id);
                s.executeUpdate("DELETE FROM "+table_name+" WHERE "+column_name+"="+id);
                conexion.cerrarConexion(con);
                return true;
            }
            else{
                conError = new controladorError(alertError, "No se pudo hacer la conexión");
                conError.iniciarVista();
                return false;
            }
        }catch (SQLException e) {
            String mensaje;
            if(e.getErrorCode() == 1451){
                mensaje = "Este campo esta siendo utilizado en otros registros";
            }
            else{
                mensaje = "Algo sucedió, no se pudo eliminar";
            }
            System.out.println("Algo sucedió: "+e.getMessage());
            conError = new controladorError(alertError, mensaje);
            conError.iniciarVista();
            return false;
        }
    }
    /**
     * Retorna el valor del iva ya dividido entre 100 por ejemplo 0.18
     * @return 
     */
    public float obtenerIVA(){
        float iva = -1;
        Connection con = null;
        try{
            con = conexion.abrirConexion();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT Precio FROM mantenimiento");
            if(rs.next()){
                iva = rs.getFloat("Precio")/100;
            }
            else{
                conError = new controladorError(alertError, "No se encontró ningun campo IVA");
                conError.iniciarVista();
            }
            return iva;
        }
        catch(SQLException e){
            conError = new controladorError(alertError, "Hubo un fallo en la conexión");
            conError.iniciarVista();
            return -1;
        }
        finally{
            if(con != null) cerrarConexion(con);
        }
    }
    /**
     * Sirve para buscar filtrar los datos de la tabla
     * @param SQLQuery Recibe la sentencia a ejecutar en mysql
     * @param nombresColumnas Recibe los nombres de las columnas que se agregarán en la tabla
     * @return 
     */
    public DefaultTableModel filtrarTabla(String SQLQuery, String[] nombresColumnas){
        try
       {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         DefaultTableModel modelo;
        
         try
        {
          ResultSet rs = s.executeQuery(SQLQuery);
          modelo = new DefaultTableModel();
          ResultSetMetaData rsMd = rs.getMetaData();
          int cantidadColumnas = rsMd.getColumnCount();
          for(int i = 0; i < cantidadColumnas; i++)
          {
            modelo.addColumn(nombresColumnas[i]);
          }
          while(rs.next())
          {
              Object[] fila = new Object[cantidadColumnas];
              for(int i = 0; i < cantidadColumnas; i++)
              {
                  fila[i] = rs.getObject(i+1);
              }
              modelo.addRow(fila);
          }return modelo;
        }finally
         {
             conexion.cerrarConexion(con);
         }
       }catch(SQLException e)
       {
           return null;
       }
    }
    
    /**
     * Esta función obtiene los datos de la tabla especificada
     * @param datos Recibe todo los datos de donde hará el filtrado para la tabla
     * @param nombresColumnas Recibe el nombre de las columnas que se agregarán en la tabla
     * @return 
     */
    public DefaultTableModel obtenerDatosTabla(String[][] datos, String[] nombresColumnas){
        DefaultTableModel modelo = new DefaultTableModel(); 
        int numCols = nombresColumnas.length;
        for(int i=0;i < numCols;i++){
            modelo.addColumn(nombresColumnas[i]);
        }
        Object[] fila = new Object[numCols];
        for (String[] dato : datos) {
            for (int j = 0; j < numCols; j++) {
                fila[j] = dato[j];
            }
            modelo.addRow(fila);
        }
        return modelo;
    }
    /**
     * Esta función retorna todos los datos de la consulta en un arreglo bidimensional
     * @param SQLQuery Recibe la conlsuta de SQL
     * @return 
     */
    public String[][] obtenerDatos(String SQLQuery){
        String[][] datos;
        int numCol;
        Connection con = null;
        try{
            con = conexion.abrirConexion();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(SQLQuery);
            numCol = rs.getMetaData().getColumnCount();
            rs.last();
            int numRows = rs.getRow();
            datos = new String[numRows][numCol];
            rs.beforeFirst();
            while(rs.next()){
                for (int j = 0; j < numCol; j++) {
                    datos[rs.getRow()-1][j] = rs.getString(j+1);
                } 
            }
            return datos;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
        finally{
            if(con != null) cerrarConexion(con);
        }
    }
    
    

 }   
    
