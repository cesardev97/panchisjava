/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.Crud_Producto.idProducto;
import Vistas.FrmMenu;
import static Vistas.FrmMenu.jTable_pizza;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jean
 */
public class Crud_Menu extends Conexion {
    //dar formato a los decimales
    static  DecimalFormat df = new DecimalFormat("#.##");

public static void CargarTablaPizza() {
    Connection con = Conexion.conectar();
    DefaultTableModel model = new DefaultTableModel();
    String sql = "SELECT idProducto, nombre, discripcion, precio, IGV FROM tb_producto WHERE idCategoria = 1"; // Filtrar por categoría 1
    try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        FrmMenu.jTable_pizza = new JTable(model);
        FrmMenu.jScrollPane6.setViewportView(FrmMenu.jTable_pizza);

        model.addColumn("N°"); // Columna oculta para almacenar el idProducto
        model.addColumn("Pizza");
        model.addColumn("Descripcion");
        model.addColumn("Precio");

        while (rs.next()) {
            Object fila[] = new Object[4];
            fila[0] = rs.getInt("idProducto"); // Obtener el idProducto como entero
            fila[1] = rs.getString("nombre"); // Obtener el nombre como String
            fila[2] = rs.getString("discripcion"); // Obtener la descripción como String
            
            // Obtener el precio como double
            double precio = rs.getDouble("precio");
            // Obtener el IGV como int
            int igv = rs.getInt("IGV");
            // Calcular el precio total incluyendo el IGV
            double totalPrecio = precio + (igv / 100.0) * precio;
            fila[3] = df.format(totalPrecio); // Formatear el precio total
            
            model.addRow(fila);
        }
        con.close();
    } catch (SQLException e) {
        System.out.println("Error al llenar la tabla menú pizza: " + e);
    }

    // Agregar el MouseListener al jTable_pizza
    FrmMenu.jTable_pizza.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int fila_point = FrmMenu.jTable_pizza.rowAtPoint(e.getPoint());
            int columna_point = 0;

            if (fila_point > -1) {
                int idProducto = (int) model.getValueAt(fila_point, columna_point);
                EnviarPizzasSeleccionados(idProducto);
            }
        }
    });
}


private static void EnviarPizzasSeleccionados(int idProducto) {
    try {
        Connection con = Conexion.conectar();
        PreparedStatement pst = con.prepareStatement(
                "SELECT * FROM tb_producto WHERE idproducto = ?");
        pst.setInt(1, idProducto);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            FrmMenu.txt_gestionar_nombre.setText(rs.getString("nombre"));
            FrmMenu.txt_gestionar_descripcion.setText(rs.getString("discripcion"));
            FrmMenu.txt_gestionar_precio.setText(String.valueOf(rs.getDouble("precio")));

        }
        con.close();

    } catch (SQLException e) {
        System.out.println("Error al seleccionar producto del menú" + e);
    }
}



public static void CargarTablaBebidas() {
    Connection con = Conexion.conectar();
    DefaultTableModel model = new DefaultTableModel();
    String sql = "SELECT idProducto, nombre, discripcion, precio, IGV FROM tb_producto WHERE idCategoria = 2"; // Filtrar por categoría 2
    try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        FrmMenu.jTable_bebidas = new JTable(model);
        FrmMenu.jScrollPane7.setViewportView(FrmMenu.jTable_bebidas);

        model.addColumn("N°"); // Columna oculta para almacenar el idProducto
        model.addColumn("Bebida");
        model.addColumn("Descripcion");
        model.addColumn("Precio");

        while (rs.next()) {
            Object fila[] = new Object[4];
            fila[0] = rs.getInt("idProducto"); // Obtener el idProducto como entero
            fila[1] = rs.getString("nombre"); // Obtener el nombre como String
            fila[2] = rs.getString("discripcion"); // Obtener la descripción como String
            
            // Obtener el precio como double
            double precio = rs.getDouble("precio");
            // Obtener el IGV como int
            int igv = rs.getInt("IGV");
            // Calcular el precio total incluyendo el IGV
            double totalPrecio = precio + (igv / 100.0) * precio;
            fila[3] = df.format(totalPrecio); // Formatear el precio total
            
            model.addRow(fila);
        }
        con.close();
    } catch (SQLException e) {
        System.out.println("Error al llenar la tabla menú bebida: " + e);
    }

    // Agregar el MouseListener al jTable_bebidas
    FrmMenu.jTable_bebidas.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int fila_point = FrmMenu.jTable_bebidas.rowAtPoint(e.getPoint());
            int columna_point = 0;

            if (fila_point > -1) {
                int idProducto = (int) model.getValueAt(fila_point, columna_point);
                EnviarBedidasSeleccionados(idProducto);
            }
        }
    });
}

private static void EnviarBedidasSeleccionados(int idProducto) {
    try {
        Connection con = Conexion.conectar();
        PreparedStatement pst = con.prepareStatement(
                "SELECT * FROM tb_producto WHERE idproducto = ?");
        pst.setInt(1, idProducto);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            FrmMenu.txt_gestionar_nombre.setText(rs.getString("nombre"));
            FrmMenu.txt_gestionar_descripcion.setText(rs.getString("discripcion"));
            FrmMenu.txt_gestionar_precio.setText(String.valueOf(rs.getDouble("precio")));

        }
        con.close();

    } catch (SQLException e) {
        System.out.println("Error al seleccionar producto del menú bebida" + e);
    }
}


public static void CargarTablaPiqueos() {
    Connection con = Conexion.conectar();
    DefaultTableModel model = new DefaultTableModel();
    String sql = "SELECT idProducto, nombre, discripcion, precio, IGV FROM tb_producto WHERE idCategoria = 3"; // Filtrar por categoría 3
    try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        FrmMenu.jTable_piqueos = new JTable(model);
        FrmMenu.jScrollPane8.setViewportView(FrmMenu.jTable_piqueos);

        model.addColumn("N°"); // Columna oculta para almacenar el idProducto
        model.addColumn("Piqueo");
        model.addColumn("Descripcion");
        model.addColumn("Precio");

        while (rs.next()) {
            Object fila[] = new Object[4];
            fila[0] = rs.getInt("idProducto"); // Obtener el idProducto como entero
            fila[1] = rs.getString("nombre"); // Obtener el nombre como String
            fila[2] = rs.getString("discripcion"); // Obtener la descripción como String
            
            // Obtener el precio como double
            double precio = rs.getDouble("precio");
            // Obtener el IGV como int
            int igv = rs.getInt("IGV");
            // Calcular el precio total incluyendo el IGV
            double totalPrecio = precio + (igv / 100.0) * precio;
            fila[3] = df.format(totalPrecio); // Formatear el precio total
            
            model.addRow(fila);
        }
        con.close();
        
        // Establecer el ancho de las columnas
        FrmMenu.jTable_piqueos.getColumnModel().getColumn(0).setPreferredWidth(80); // Columna del idProducto oculta
        FrmMenu.jTable_piqueos.getColumnModel().getColumn(1).setPreferredWidth(150); // Columna de Piqueo
        FrmMenu.jTable_piqueos.getColumnModel().getColumn(2).setPreferredWidth(300); // Columna de Descripción
        FrmMenu.jTable_piqueos.getColumnModel().getColumn(3).setPreferredWidth(100); // Columna de Precio
    } catch (SQLException e) {
        System.out.println("Error al llenar la tabla menú piqueos: " + e);
    }

    // Agregar el MouseListener al jTable_piqueos
    FrmMenu.jTable_piqueos.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int fila_point = FrmMenu.jTable_piqueos.rowAtPoint(e.getPoint());
            int columna_point = 0;

            if (fila_point > -1) {
                int idProducto = (int) model.getValueAt(fila_point, columna_point);
                EnviarDatosDeMenuSeleccionados(idProducto);
            }
        }
    });
}

private static void EnviarDatosDeMenuSeleccionados(int idProducto) {
    try {
        Connection con = Conexion.conectar();
        PreparedStatement pst = con.prepareStatement(
                "SELECT * FROM tb_producto WHERE idproducto = ?");
        pst.setInt(1, idProducto);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            FrmMenu.txt_gestionar_nombre.setText(rs.getString("nombre"));
            FrmMenu.txt_gestionar_descripcion.setText(rs.getString("discripcion"));
            FrmMenu.txt_gestionar_precio.setText(String.valueOf(rs.getDouble("precio")));

        }
        con.close();

    } catch (SQLException e) {
        System.out.println("Error al seleccionar producto del menú piqueos" + e);
    }
}



 

}
