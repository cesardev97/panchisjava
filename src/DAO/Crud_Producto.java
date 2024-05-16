/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Vistas.FrmProducto;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;

/**
 *
 * @author Jean
 */
public class Crud_Producto extends Conexion {

    public static int idProducto;

// no se necesita ya que el metodo IdCategoria esta en el ctrl y aqui se dejó comentado
//   public static int obtenerIdCategoriaCombo=0 ;
//
    public Crud_Producto() {

    }

    /**
     * todo izquierda
     */
    public static void Limpiar() {
        FrmProducto.txt_nombre.setText("");
        FrmProducto.txt_cantidad.setText("");
        FrmProducto.txt_precio.setText("");
        FrmProducto.txt_discripcion.setText("");

    }
    //metodo para cargar categorias en el combo

    public static void CargarComboCategoria() {
        Connection cn = Conexion.conectar();
        String sql = "select * from tb_categoria";
        Statement st;
        try {

            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            FrmProducto.jComboBoxCategoria.removeAllItems();
            FrmProducto.jComboBoxCategoria.addItem("Seleccione categoria:");
            while (rs.next()) {
                FrmProducto.jComboBoxCategoria.addItem(rs.getString("discripcion"));
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("¡Error al cargar categorias!");
        }
    }

    /**
     * este método se colocara en el ctrl ya que no se extraer correctamente el
     * id para poder guardarlo en la base de datos(pendeine encontrar como
     * extraerlo correctamente)
     */
//    public static int IdCategoria() {
//        String sql = "select * from tb_categoria where discripcion = '" + FrmProducto.jComboBoxCategoria.getSelectedItem() + "'"; // 
//        Statement st;
//        try {
//            Connection cn = Conexion.conectar();
//            st = cn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//                obtenerIdCategoriaCombo = rs.getInt("idCategoria");
//            }
//        } catch (SQLException e) {
//            System.out.println("Error al obener id categoria");
//        }
//        return obtenerIdCategoriaCombo;
//    }
    
    
    /**
     * todo derecha
     */
    //metodo limpiar 
    public static void Limpiar2() {
        FrmProducto.txt_gestionar_nombre.setText("");
        FrmProducto.txt_gestionar_cantidad.setText("");
        FrmProducto.txt_gestionar_precio.setText("");
        FrmProducto.txt_gestionar_discripcion.setText("");
        FrmProducto.jComboBox_gestionarIGV.setSelectedItem("Seleccione IGV:");
        FrmProducto.jComboBox_gestionarCategoria.setSelectedItem("Seleccione categoria:");
    } //metodo para cargar categorias en el combo

    public static void CargarComboCategorias() {
        Connection cn = Conexion.conectar();
        String sql = "select * from tb_categoria";
        Statement st;

        try {

            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            FrmProducto.jComboBox_gestionarCategoria.removeAllItems();
            FrmProducto.jComboBox_gestionarCategoria.addItem("Seleccione categoria:");
            while (rs.next()) {
                FrmProducto.jComboBox_gestionarCategoria.addItem(rs.getString("discripcion"));//creado con error ortografico en la base de datos 
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al cargar categorias");
        }
    }
    //metodo para mostrar todas los productos registradas
    static String descripcionCategoria = "";
    static double precio = 0.0;
    static int porcentajeIgv = 0;
    static double IVAA = 0;

    public static void CargarTablaProductos() {

        Connection con = Conexion.conectar();
        DefaultTableModel model = new DefaultTableModel(); //verificar
        String sql = "select p.idProducto, p.nombre, p.cantidad, p.precio, p.discripcion, p.IGV, c.discripcion, p.estado from tb_producto As p, tb_categoria As c where p.idCategoria = c.idCategoria;";
        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            FrmProducto.jTable_producto = new JTable(model);
            FrmProducto.jScrollPane1.setViewportView(FrmProducto.jTable_producto);

            model.addColumn("N°");
            model.addColumn("nombre");
            model.addColumn("Cantidad");
            model.addColumn("precio");
            model.addColumn("descripción");
            model.addColumn("IGV");
            model.addColumn("Categoria");
            model.addColumn("estado");
// calculara el igv en relacion al precio y al porcentaje (solucionado)
            while (rs.next()) {
                precio = rs.getDouble("precio");
                porcentajeIgv = rs.getInt("IGV");

                Object fila[] = new Object[8];
                for (int i = 0; i < 8; i++) {

                    if (i == 5) {
                        Crud_Producto.calcularIgv(precio, porcentajeIgv);//metodo
                        fila[i] = IVAA;
                        rs.getObject(i + 1);
                    } else {
                        fila[i] = rs.getObject(i + 1);
                    }
                }
                model.addRow(fila);
            }
            con.close();// agrefa despues 26/06/2023
        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla producto:" + e);
        }
        //evento para obtener campo al cual el usuario da click
        //y obtener la interfaz que mostrara la informacion general
        FrmProducto.jTable_producto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila_point = FrmProducto.jTable_producto.rowAtPoint(e.getPoint());
                int columna_point = 0;
                if (fila_point > -1) {
                    idProducto = (int) model.getValueAt(fila_point, columna_point);
                    EnviarDatosProductosSeleccionados(idProducto);
                }
            }
        });
    }

    //metodo calcular igv
    public static double calcularIgv(double precio, int iva) {
        int p_iva = iva;

        switch (p_iva) {
            case 0:
                IVAA = 0.0;
                break;
            case 16:
                IVAA = precio * 0.16;
                break;
            case 18:
                IVAA = precio * 0.18;
                break;
            default:
                break;
        }
        //redondear decimales
        IVAA = (double) Math.round(IVAA * 100) / 100;
        return IVAA;
    }

    //metodo enviar los datos seleccionados
//discripción error ortográfico, despues se deberá corregir en la BBDD
    public static void EnviarDatosProductosSeleccionados(int idProducto) {
        try {
            Connection con = Conexion.conectar();
            PreparedStatement pst = con.prepareStatement(
                    "SELECT * FROM tb_producto WHERE idProducto ='" + idProducto + "'");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                FrmProducto.txt_gestionar_nombre.setText(rs.getString("nombre")); //"
                FrmProducto.txt_gestionar_cantidad.setText(rs.getString("cantidad")); //"
                FrmProducto.txt_gestionar_precio.setText(rs.getString("precio")); //"
                FrmProducto.txt_gestionar_discripcion.setText(rs.getString("discripcion")); //"

                //deberia devolverme en función al valor de la base de datos
                int iva = rs.getInt("IGV");
                switch (iva) {
                    case 0:
                        FrmProducto.jComboBox_gestionarIGV.setSelectedItem("Sin IGV");
                        break;
                    case 16:
                        FrmProducto.jComboBox_gestionarIGV.setSelectedItem("16%");
                        break;
                    case 18:
                        FrmProducto.jComboBox_gestionarIGV.setSelectedItem("18%");
                        break;
                    default:
                        FrmProducto.jComboBox_gestionarIGV.setSelectedItem("Seleccione igv:");
                        break;
                }
//             
                int idCate = rs.getInt("idCategoria");
                FrmProducto.jComboBox_gestionarCategoria.setSelectedItem(relacionarCategoria(idCate));
            }
            con.close();

        } catch (SQLException e) {
            System.out.println("Error al seleccionar categoria" + e);
        }
    }
    // Metodo para relacionar categorias

    public static String relacionarCategoria(int idCategoria) {

        String sql = "select discripcion from tb_categoria where idCategoria = '" + idCategoria + "'";
        Statement st;
        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                descripcionCategoria = rs.getString("discripcion");
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("¡Error al obtener el id de la categoria!");
        }
        return descripcionCategoria;
    }

    //metodo calcular igv
/////////////////////////////////////////////////////////
    public boolean guardar(Producto objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("INSERT INTO tb_producto values(?,?,?,?,?,?,?,?)");
            consulta.setInt(1, 0); // id
            consulta.setString(2, objeto.getNombre());
            consulta.setInt(3, objeto.getCantidad());
            consulta.setDouble(4, objeto.getPrecio());
            consulta.setString(5, objeto.getDiscripcion());
            consulta.setInt(6, objeto.getIGV());
            consulta.setInt(7, objeto.getIdCategoria());
            consulta.setInt(8, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al guardar producto desde el controlador" + e);
        }
        return respuesta;
    }

    //metodo para consulta si el producto ya está guardado
    public boolean existeProducto(String producto) {
        boolean respuesta = false;
        String sql = "SELECT nombre FROM tb_producto WHERE nombre='" + producto + "';";
        Statement st;

        try {
            Connection cn = DAO.Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql); //se ejecuta
            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar Producto" + e);

        }
        return respuesta;
    }

    /**
     * **************************************************
     * metodo para actualizar un producto
     * **************************************************
     */
    public boolean actualizar(Producto objeto, int idProducto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("update tb_producto set nombre=?, cantidad = ?, precio = ?, discripcion= ?, IGV = ?, idCategoria = ?, estado = ? where idProducto ='" + idProducto + "'");
            consulta.setString(1, objeto.getNombre());
            consulta.setInt(2, objeto.getCantidad());
            consulta.setDouble(3, objeto.getPrecio());
            consulta.setString(4, objeto.getDiscripcion());
            consulta.setInt(5, objeto.getIGV());
            consulta.setInt(6, objeto.getIdCategoria());
            consulta.setInt(7, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto desde el controlador crud_Prodcuto: " + e);
        }
        return respuesta;
    }

    /**
     * **************************************************
     * metodo para eliminar un producto
     * **************************************************
     */
    public boolean eliminar(int idProducto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "delete from tb_producto where idProducto ='" + idProducto + "'");
            consulta.executeUpdate();

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e);
        }
        return respuesta;
    }

}
