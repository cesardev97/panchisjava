/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.*;
public class ConectarBD implements Parametros{
    Connection conexion;
    Statement st;
    ResultSet rs;
    PreparedStatement ps;
    public ConectarBD(){
     try{
            Class.forName(DRIVE);
            conexion = DriverManager.getConnection(RUTA,USUARIO,CLAVE);
            st = conexion.createStatement();
        }catch(Exception ex){
            System.out.println("error al coenctar en la base de datos en conectarBD");
        }
    
    }
    
}
