/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
//import Vistas.FrmCategoria;
import Vistas.FrmCategoria;
import static Vistas.FrmCategoria.jTable_categoria;
import conexion.Conexion;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Categoria;

public class Crud_Categoria extends Conexion {
  public static int idCategoria;
    public Crud_Categoria() {
        
    }
   
     
     //metodo que muestra en un JTable los registros dela tabla categoria
    public static void CargarTablaCategorias() {
        
        Connection con = DAO.Conexion.conectar();
        DefaultTableModel model = new DefaultTableModel(); //verificar
        String sql = "SELECT idCategoria, discripcion, estado FROM tb_categoria";
        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            FrmCategoria.jTable_categoria = new JTable(model);
            FrmCategoria.jScrollPane1.setViewportView(FrmCategoria.jTable_categoria);

           
            model.addColumn("id");
            model.addColumn("nombre");
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
            // Setear el modelo a la tabla jTable_categoria
//          FrmCategoria.jTable_categoria.setModel(model);
        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla categoria:" + e);
        }
        jTable_categoria.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila_point = jTable_categoria.rowAtPoint(e.getPoint());
                int columna_point = 0;
                if (fila_point > -1) {
                    idCategoria = (int) model.getValueAt(fila_point, columna_point);
                    EnviarDatosCategoriasSeleccionadas(idCategoria);
                }
            }
        });

    }
    //a comparación con el código orifgianl se arregla usanso static
        private static  void EnviarDatosCategoriasSeleccionadas(int idCategoria) {
        try {
           Connection con = DAO.Conexion.conectar();
            PreparedStatement pst = con.prepareStatement(
                    "SELECT * FROM tb_categoria WHERE idCategoria ='" + idCategoria + "'");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                FrmCategoria.txt_gestionar_discripcion.setText(rs.getString("discripcion")); //"discripcion" campo registrado con ese error ortografico en la BBDD
            }
            con.close();

        } catch (SQLException e) {
            System.out.println("Error al seleccionar categoria" + e);
        }
    }
        
        ////////////////////////////////////////////////////////////////////////

   public boolean guardar (Categoria objeto){
        boolean respuesta=false;
        Connection cn = DAO.Conexion.conectar();
        try {
            PreparedStatement consulta=cn.prepareStatement( "INSERT INTO tb_categoria values(?,?,?)");
            consulta.setInt(1, 0); // id
            consulta.setString(2, objeto.getDiscripcion());
            consulta.setInt(3, objeto.getEstado());
  
            
            if (consulta.executeUpdate()>0) {
                respuesta=true;
            }
            cn.close();
     
            } catch (SQLException e){
            System.out.println("Error al guardar categoria"+e);
        }
      
        return respuesta;
    }

//metodo para validar la existencia de la  categoria
   public boolean existeCategoria(String categoria) {
        boolean respuesta = false;
        String sql = "SELECT discripcion FROM tb_categoria where discripcion = '" + categoria + "';";
        Statement st;

        try {
           Connection cn = DAO.Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar categoria: " + e);
        }
        return respuesta;
    }
         //metodo para actualizar categoria
    public boolean actualizar (Categoria objeto, int idCategoria){
        
        boolean respuesta=false;
        Connection cn = DAO.Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement( "UPDATE tb_categoria SET discripcion=? WHERE idCategoria = '"+idCategoria+"'");
            consulta.setString(1, objeto.getDiscripcion()); // id

  
            if (consulta.executeUpdate()>0) {
                respuesta=true;
            }
            cn.close();
     
            } catch (SQLException e){
            System.out.println("Error al actualizar categoria desde el crud_categoria"+e);
        }
      
        return respuesta;
    }
    
    //metodo para eliminar categoria
    public boolean eliminar (int idCategoria){
        boolean respuesta=false;
        Connection cn = DAO.Conexion.conectar();
        try {
            PreparedStatement consulta=cn.prepareStatement( "DELETE FROM tb_categoria WHERE idCategoria = '"+idCategoria+"'");
            consulta.executeUpdate(); //
  
            if (consulta.executeUpdate()>0) {
                respuesta=true;
            }
            cn.close();
     
            } catch (SQLException e){
            System.out.println("Error al eliminar categoria"+e);
        }
      
        return respuesta;
//     try {
//            PreparedStatement consulta=cn.prepareStatement( "UPDATE tb_categoria set estado='0' WHERE idCategoria = '"+idCategoria+"'");
//            consulta.executeUpdate(); //
//  
//            if (consulta.executeUpdate()>0) {
//                respuesta=true;
//            }
//            cn.close();
//     
//            } catch (SQLException e){
//            System.out.println("Error al eliminar categoria"+e);
//        }
//      
//        return respuesta;
    }
}
