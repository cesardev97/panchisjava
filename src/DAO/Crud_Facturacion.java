/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Vistas.FrmFacturacion;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelo.DetalleVenta;

public class Crud_Facturacion extends Conexion {
    //modelo de datos

    public static DefaultTableModel modeloDatosProductos;

    //lista para el detalle de venta de los productos
    public static ArrayList<DetalleVenta> listaProductos = new ArrayList<>();
    public static DetalleVenta producto;

    public static int idCliente = 0;//id del cliente sleccionado

    public static int idProducto = 0;
    public static String nombre = "";
    public static int cantidadProductoBBDD = 0;
    public static double precioUnitario = 0.0;
    public static int porcentajeIgv = 0;

    public static int cantidad = 0;//cantidad de productos a comprar
    public static double subtotal = 0.0;//cantidad por precio
    public static double descuento = 0.0;
    public static double igv = 0.0;
    public static double totalPagar = 0.0;

    //variables para calculos globales
    public static double subtotalGeneral = 0.0;
    public static double descuentoGeneral = 0.0;
    public static double igvGeneral = 0.0;
    public static double totalPagarGeneral = 0.0;
    //fin de variables de calculos globales
    public static int auxIdDetalle = 1;//id del detalle de venta

    //metodo para inicializar tabla de productos
    public static void CargarTablaProducto() {
        modeloDatosProductos = new DefaultTableModel();
        //añadir columnas a ala tabla
        modeloDatosProductos.addColumn("N°");
        modeloDatosProductos.addColumn("Nombre°");
        modeloDatosProductos.addColumn("Cantidad°");
        modeloDatosProductos.addColumn("P.unitario");
        modeloDatosProductos.addColumn("Subtotal");
        modeloDatosProductos.addColumn("Descuento");
        modeloDatosProductos.addColumn("IGV");
        modeloDatosProductos.addColumn("Pago Total");
        modeloDatosProductos.addColumn("acción");
        //agregar los datoa a la tabla
        FrmFacturacion.jTable_productos.setModel(modeloDatosProductos);
    }
    //metodo para presentar la informacion de la tabla DetalleVenta

    public static void listaTablaProductos() {
        Crud_Facturacion.modeloDatosProductos.setRowCount(listaProductos.size());
        for (int i = 0; i < listaProductos.size(); i++) {
            Crud_Facturacion.modeloDatosProductos.setValueAt(i + 1, i, 0);
            Crud_Facturacion.modeloDatosProductos.setValueAt(listaProductos.get(i).getNombre(), i, 1);
            Crud_Facturacion.modeloDatosProductos.setValueAt(listaProductos.get(i).getCantidad(), i, 2);
            Crud_Facturacion.modeloDatosProductos.setValueAt(listaProductos.get(i).getPrecioUnitario(), i, 3);
            Crud_Facturacion.modeloDatosProductos.setValueAt(listaProductos.get(i).getSubtotal(), i, 4);
            Crud_Facturacion.modeloDatosProductos.setValueAt(listaProductos.get(i).getDescuento(), i, 5);
            Crud_Facturacion.modeloDatosProductos.setValueAt(listaProductos.get(i).getIGV(), i, 6);
            Crud_Facturacion.modeloDatosProductos.setValueAt(listaProductos.get(i).getTotalPagar(), i, 7);
            Crud_Facturacion.modeloDatosProductos.setValueAt("Eliminar", i, 8);//FALTA IMPLEMENTAR UN BOTON ELIMINAR
        }
        //añadir al Jtable
        FrmFacturacion.jTable_productos.setModel(modeloDatosProductos);
    }

    ///////////////////////////////////////////////////////////
    /*
     * *****************************************************
     * panel izquierda
     * *****************************************************
     */
    public static void CargarComboCliente() {
        Connection cn = DAO.Conexion.conectar();
        String sql = "select * from tb_cliente";
        Statement st;
        try {

            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            FrmFacturacion.jComboCliente.removeAllItems();
            FrmFacturacion.jComboCliente.addItem("Seleccione cliente:");
            while (rs.next()) {
                FrmFacturacion.jComboCliente.addItem(rs.getString("nombre") + " " + rs.getString("apellido"));
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("¡Error al cargar cliente!");
        }
    }

    public static void CargarComboProducto() {
        Connection cn = DAO.Conexion.conectar();
        String sql = "select * from tb_producto";
        Statement st;
        try {

            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            FrmFacturacion.jComboProducto.removeAllItems();
            FrmFacturacion.jComboProducto.addItem("Seleccione producto:"); //hara que desaparezzaca
            while (rs.next()) {
                FrmFacturacion.jComboProducto.addItem(rs.getString("nombre"));
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("¡Error al cargar productos!");
        }
    }

    /*
        Metodo para validar que el usuario no ingrese caracteres no numericos
     */
    public static boolean validar(String valor) {
        try {
            int num = Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /*
        Metodo para validar que el usuario no ingrese caracteres no numericos
     */
    public static boolean validarDouble(String valor) {
        try {
            double num = Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /*
        Metodo para mostrar los datos del producto seleccionado
     */
    public static void DatosDelProducto() {
        try {
            String sql = "select * from tb_producto where nombre = '" + FrmFacturacion.jComboProducto.getSelectedItem() + "'";
           Connection cn = DAO.Conexion.conectar();
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                idProducto = rs.getInt("idProducto");
                nombre = rs.getString("nombre");
                cantidadProductoBBDD = rs.getInt("cantidad");
                precioUnitario = rs.getDouble("precio");
                porcentajeIgv = rs.getInt("IGV");
                Crud_Facturacion.CalcularIgv(precioUnitario, porcentajeIgv);//calcula y retorna el igv
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener datos del producto, " + e);
        }
    }

    /*
        Metodo para calcular el igv al agregar producto
     */
    public static double CalcularIgv(double precio, int porcentajeIgv) {
        int p_igv = porcentajeIgv;

        switch (p_igv) {
            case 0:
                //declarado al inicio
                igv = 0.0;
                break;
            case 16:
                igv = (precio * cantidad) * 0.16;
                break;
            case 18:
                igv = (precio * cantidad) * 0.18;
                break;
            default:
                break;
        }

        return igv;
    }

    /*
    Metodo para calcular el total a pagar de todos los productos agregados
     */
    public static void CalcularTotalPagar() {
        subtotalGeneral = 0;
        descuentoGeneral = 0;
        igvGeneral = 0;
        totalPagarGeneral = 0;

        for (DetalleVenta elemento : listaProductos) {
            subtotalGeneral += elemento.getSubtotal();
            descuentoGeneral += elemento.getDescuento();
            igvGeneral += elemento.getIGV();
            totalPagarGeneral += elemento.getTotalPagar();//total a pagar
        }
        //redondear decimales
        subtotalGeneral = (double) Math.round(subtotalGeneral * 100) / 100;
        igvGeneral = (double) Math.round(igvGeneral * 100) / 100;
        descuentoGeneral = (double) Math.round(descuentoGeneral * 100) / 100;
        totalPagarGeneral = (double) Math.round(totalPagarGeneral * 100) / 100;

        //enviar datos a la vista
        FrmFacturacion.txt_subtotal.setText(String.valueOf(subtotalGeneral));
        FrmFacturacion.txt_igv.setText(String.valueOf(igvGeneral));
        FrmFacturacion.txt_descuento.setText(String.valueOf(descuentoGeneral));
        FrmFacturacion.txt_total_pagar.setText(String.valueOf(totalPagarGeneral));
    }

    /*
     * *****************************************************
     * panel derecha
     * *****************************************************
     */
 /*
    Metodo para obtener id del cliente
     */
    public static void ObtenerIdCliente() {
        try {
            String sql = "select * from tb_cliente where concat(nombre,' ',apellido) = '" + FrmFacturacion.jComboCliente.getSelectedItem() + "'";
            Connection cn = DAO.Conexion.conectar();
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                idCliente = rs.getInt("idCliente");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener id del cliente, " + e);
        }
    }

    //metodo para restar la cantidad (stock) de los productos vendidos
    public static void RestarStockProductos(int idProducto, int cantidad) {
        int cantidadProductosBaseDeDatos = 0;
        try {
            Connection cn = DAO.Conexion.conectar();
            String sql = "select idProducto, cantidad from tb_producto where idProducto = '" + idProducto + "'";
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cantidadProductosBaseDeDatos = rs.getInt("cantidad");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al restar cantidad 1, " + e);
        }

        try {
           Connection cn = DAO.Conexion.conectar();
            PreparedStatement consulta = cn.prepareStatement("update tb_producto set cantidad=? where idProducto = '" + idProducto + "'");
            int cantidadNueva = cantidadProductosBaseDeDatos - cantidad;
            consulta.setInt(1, cantidadNueva);
            if (consulta.executeUpdate() > 0) {
                //System.out.println("Todo bien");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al restar cantidad 2, " + e);
        }
    }

}
