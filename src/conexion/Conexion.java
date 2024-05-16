package conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Conexion {
// EL SISTEMA YA NO DEPENDE DE ESTACONEXIÓN, SUHJETO AL DAO ACTALMENTE
    //conexion local
//    public static Connection conectar() {
//        try {
//          
//            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_pizza", "root", "");
////            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_ventas_prueba", "root", "");//este no sirve ya que se trabaja con la conexion del paquete DAO
//            return cn; //la ruta aún sirve para las categorias, actualizar más tarde
//            
//
//        } catch (SQLException e) {
//            System.out.println("Error en la conexion local" + e);
//
//        }
//        return null;
//    }

}
