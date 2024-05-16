/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import Vistas.FrmRol;
import conexion.Conexion;
import static Vistas.FrmRol.jTable_rol;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Rol;


public class Crud_Rol extends Conexion {
  public static int idRol;
    public Crud_Rol() {
        
    }
   
     
     //metodo que muestra en un JTable los registros de la tabla rol
    public static void CargarTablaRol() {
        
        Connection con = DAO.Conexion.conectar();
        DefaultTableModel model = new DefaultTableModel(); //verificar
        String sql = "SELECT idRol, rol, estado FROM tb_Rol";
        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            FrmRol.jTable_rol = new JTable(model);
            FrmRol.jScrollPane1.setViewportView(FrmRol.jTable_rol);

           
            model.addColumn("id");
            model.addColumn("rol");
            model.addColumn("estado");

            while (rs.next()) {
                Object fila[] = new Object[3];
                for (int i = 0; i < 3; i++) {
                   if (i == 2) {
                        String estado = String.valueOf(rs.getObject(i + 1));
                        if (estado.equalsIgnoreCase("1")) {
                            fila[i] = "Activo";
                        } else {
                            fila[i] = "Inactivo";
                        }
                    } else {
                        fila[i] = rs.getObject(i + 1);
                    }
                }
                model.addRow(fila);
            }
            // Setear el modelo a la tabla jTable_categoria //
//          FrmCategoria.jTable_categoria.setModel(model);

        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla rol:" + e);
        }
        FrmRol.jTable_rol.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila_point = FrmRol.jTable_rol.rowAtPoint(e.getPoint());
                int columna_point = 0;
                if (fila_point > -1) {
                    idRol = (int) model.getValueAt(fila_point, columna_point);
                    EnviarDatosRolesSeleccionados(idRol);
                }
            }
        });

    }
    //a comparación con el código orifgianl se arregla usanso static
        private static  void EnviarDatosRolesSeleccionados(int idRol) {
        try {
            Connection con = DAO.Conexion.conectar();
            PreparedStatement pst = con.prepareStatement(
                    "SELECT * FROM tb_rol WHERE idRol ='" + idRol + "'");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                FrmRol.txt_gestionar_nombre.setText(rs.getString("rol")); //"nombre" campo registrado con ese error ortografico en la BBDD
            }
            con.close();

        } catch (SQLException e) {
            System.out.println("Error al seleccionar rol" + e);
        }
    }
        
        ////////////////////////////////////////////////////////////////////////

   public boolean guardar (Rol objeto){
        boolean respuesta=false;
       Connection cn = DAO.Conexion.conectar();
        try {
            PreparedStatement consulta=cn.prepareStatement( "INSERT INTO tb_rol values(?,?,?)");
            consulta.setInt(1, 0); // id
            consulta.setString(2, objeto.getRol());
            consulta.setInt(3, objeto.getEstado());
  
            
            if (consulta.executeUpdate()>0) {
                respuesta=true;
            }
            cn.close();
     
            } catch (SQLException e){
            System.out.println("Error al guardar rol desde el CRUD"+e);
        }
      
        return respuesta;
    }

//metodo para validar la existencia del rol
   public boolean existeRol(String rol) {
        boolean respuesta = false;
        String sql = "SELECT discripcion FROM tb_rol where rol = '" + rol + "';";
        Statement st;

        try {
            Connection cn = DAO.Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar rol: " + e);
        }
        return respuesta;
    }
         //metodo para actualizar Rol
    public boolean actualizar (Rol objeto, int idRol){
        
        boolean respuesta=false;
       Connection cn = DAO.Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement( "UPDATE tb_rol SET rol=? WHERE idRol = '"+idRol+"'");
            consulta.setString(1, objeto.getRol()); // id

  
            if (consulta.executeUpdate()>0) {
                respuesta=true;
            }
            cn.close();
     
            } catch (SQLException e){
            System.out.println("Error al actualizar rol desde el crud_rol"+e);
        }
      
        return respuesta;
    }
    
    //metodo para eliminar Rol
    public boolean eliminar (int idRol){
        boolean respuesta=false;
        Connection cn = DAO.Conexion.conectar();
        try {
            PreparedStatement consulta=cn.prepareStatement( "DELETE FROM tb_rol WHERE idRol = '"+idRol+"'");
            consulta.executeUpdate(); //
  
            if (consulta.executeUpdate()>0) {
                respuesta=true;
            }
            cn.close();
     
            } catch (SQLException e){
            System.out.println("Error al eliminar Rol"+e);
        }
      
        return respuesta;
//     try {
//            PreparedStatement consulta=cn.prepareStatement( "UPDATE tb_rol set estado='0' WHERE idRol = '"+idRol+"'");
//            consulta.executeUpdate(); //
//  
//            if (consulta.executeUpdate()>0) {
//                respuesta=true;
//            }
//            cn.close();
//     
//            } catch (SQLException e){
//            System.out.println("Error al eliminar Rol"+e);
//        }
//      
//        return respuesta;
    }
}
