
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrián Scott
 */
public class modeloPrincipal {
    Conexion conexion = new Conexion();
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
    /**
     * Inserta valores a la tabla especificando las columnas y los valores
     * @param table_name Nombre de la tabla
     * @param table_columns Nombre de cada columna en la que se insertará 
     * @param table_values Los valores a insertar
     * Retorna el id agregado porque hay muchas donde se hace doble inserción, si falla retorna -1
     * @return
     */
    public int insertar(String table_name,String[] table_columns, String[] table_values) {
        try {
            Connection con = conexion.abrirConexion();
            if(con!=null){
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
                conexion.cerrarConexion(con);
                return last_id;
            }
            else{
                return -1;
            }
        }catch (SQLException e) {
            System.out.println("Algo sucedió: "+e.getMessage());
            return -1;
        }
    }
    /**
     * Modifica los valores del campo escificando las columnas y sus valores<br>
     * <b>IMPORTANTE: table_columns y table_values deben recibir primero el campo y el valor del ID</b>
     * @param table_name Nombre de la tabla
     * @param table_columns Nombre de cada columna en la que se insertará 
     * @param table_values Los valores a insertar
     * @return 
     */
    public boolean modificar(String table_name, String[] table_columns, String[] table_values){
        try {
            Connection con = conexion.abrirConexion();
            if(con!=null){
                String values="";
                //Se pasan los nombres de las columnas a cadena de texto
                for(int x = 1; x < table_columns.length ; x++){
                    values+=table_columns[x]+"='"+table_values[x]+"',";
                }
                values = values.substring(0, values.length()-1);//Esta madre nomas quita la ultima coma para que no de error SQL
                Statement s = con.createStatement();
                System.out.println("UPDATE "+table_name+" SET("+values+")WHERE "+table_columns[0]+"="+table_values[0]+"");
                s.executeUpdate("UPDATE "+table_name+" SET "+values+" WHERE "+table_columns[0]+"="+table_values[0]+"");
                conexion.cerrarConexion(con);
                return true;
            }
            else{
                return false;
            }
        }catch (SQLException e) {
            System.out.println("Algo sucedió: "+e.getMessage());
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
                s.executeUpdate("DELETE FROM "+table_name+" WHERE "+column_name+"="+id);
                conexion.cerrarConexion(con);
                return true;
            }
            else{
                return false;
            }
        }catch (SQLException e) {
            System.out.println("Algo sucedió: "+e.getMessage());
            return false;
        }
    }
}   
    
