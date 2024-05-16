/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Vistas.FrmGestionarVentas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jean
 */
public class Crud_GestionarVentas {
     public static int idCliente;
     public static int idVenta;
    
    /**
     *
     * metodo panel derecho
     */
//metodo para limpiar campos
    private void Limpiar() {
        FrmGestionarVentas.txt_total_pagar.setText("");
        FrmGestionarVentas.txt_fecha.setText("");
        FrmGestionarVentas.jComboBoxCliente.setSelectedItem("Seleccione cliente:");
        FrmGestionarVentas.jComboBoxEstado.setSelectedItem("Activo");
        idCliente = 0;
    }
    /**
     *
     * metodo panel izquierdo
     */
    //metodo para limpiar campos

//metodo para mostrar todos los clientes registrados
    public static void CargarTablaVentas() { // funcionañ
        Connection con = Conexion.conectar();
        DefaultTableModel model = new DefaultTableModel();
        String sql = "select cv.idCabeceraVenta as id, concat(c.nombre, ' ', c.apellido) as cliente, "
                + "cv.valorPagar as total, cv.fechaVenta as fecha, cv.estado "
                + "from tb_cabecera_venta as cv, tb_cliente as c where cv.idCliente = c.idCliente;";
        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            FrmGestionarVentas.jTable_ventas = new JTable(model);
            FrmGestionarVentas.jScrollPane1.setViewportView(FrmGestionarVentas.jTable_ventas);

            model.addColumn("N°");//ID
            model.addColumn("Cliente");
            model.addColumn("Total Pagar");
            model.addColumn("Fecha Venta");
            model.addColumn("estado");

            while (rs.next()) {
                Object fila[] = new Object[5];
                for (int i = 0; i < 5; i++) {
                    if (i == 4) {
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
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla de ventas: " + e);
        }
        //evento para obtener campo al cual el usuario da click
        //y obtener la interfaz que mostrara la informacion general
        FrmGestionarVentas.jTable_ventas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila_point =  FrmGestionarVentas.jTable_ventas.rowAtPoint(e.getPoint());
                int columna_point = 0;

                if (fila_point > -1) {
                    idCliente = (int) model.getValueAt(fila_point, columna_point);
                    EnviarDatosVentaSeleccionada(idCliente);//metodo
                }
            }
        });
    }
    
    
    //metodo enviar los datos seleccionados
    public static void EnviarDatosVentaSeleccionada(int idVenta) { //copiado y modificado del Frmcliente, 

        try {
            Connection con = DAO.Conexion.conectar();
            PreparedStatement pst = con.prepareStatement(
                    "    select cv.idCabeceraVenta, cv.idCliente, concat(c.nombre, ' ', c.apellido) as cliente, \n"
                    + "    cv.valorPagar, cv.fechaVenta, cv.estado \n"
                    + "   from tb_cabecera_venta as cv,   tb_cliente as c \n"
                    + "   where  cv.idCabeceraVenta = '" + idVenta + "' and cv.idCliente = c.idCliente;;");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                 FrmGestionarVentas.jComboBoxCliente.setSelectedItem(rs.getString("cliente"));
                 FrmGestionarVentas.txt_total_pagar.setText(rs.getString("valorPagar")); //"
                 FrmGestionarVentas.txt_fecha.setText(rs.getString("fechaVenta")); //"
                int estado = rs.getInt("estado");
                if (estado == 1) {
                     FrmGestionarVentas.jComboBoxCliente.setSelectedItem("Activo");
                } else {
                     FrmGestionarVentas.jComboBoxEstado.setSelectedItem("Inactivo");
                }

            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al seleccionar venta desde el Frm" + e);
        }
    }

    /*
    Metodo para cargar los clientes en el jComboBox
     */
    public static void CargarComboClientes() {
        Connection cn = DAO.Conexion.conectar();
        String sql = "select * from tb_cliente";
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            FrmGestionarVentas.jComboBoxCliente.removeAllItems();
             FrmGestionarVentas.jComboBoxCliente.addItem("Seleccione cliente:");
            while (rs.next()) {
                 FrmGestionarVentas.jComboBoxCliente.addItem(rs.getString("nombre") + " " + rs.getString("apellido"));
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("¡Error al cargar clientes, !" + e);
        }
    }
    
    
}
