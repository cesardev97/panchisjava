package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 *
 * @author Jean
 */
public class Conexion {

    //conexion local
    public static Connection conectar() {
        try {
//            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_ventas_prueba", "root", "");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_pizza", "root", "");
            return cn;

        } catch (SQLException e) {
            System.out.println("Error en la conexion local" + e);

        }
        return null;
    }

}
