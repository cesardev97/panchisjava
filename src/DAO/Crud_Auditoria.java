/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Vistas.FrmAuditoria;
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
public class Crud_Auditoria {

    public static int idCliente;
    public static int idVenta;

    /**
     *
     * metodo panel derecho
     */
//metodo para limpiar campos
    private void Limpiar() {
//        FrmAuditoria.txt_total_pagar.setText("");
//        FrmAuditoria.txt_fecha.setText("");
//        FrmAuditoria.jComboBoxCliente.setSelectedItem("Seleccione cliente:");
//        FrmAuditoria.jComboBoxEstado.setSelectedItem("Activo");
        idCliente = 0;
    }

    /**
     *
     * metodo panel izquierdo
     */
    //metodo para limpiar campos
//     private int id;
//    private int idUsuario;
//    private String fecha_conexion;
//    private String hora_conexion;
//    private String accion_realizada;
//    private String ip_computadora;
//metodo para mostrar todos los clientes registrados
    public static void CargarTablaAuditoria() { // funcionañ
        Connection con = Conexion.conectar();
        DefaultTableModel model = new DefaultTableModel();
        String sql = "SELECT a.id, CONCAT(u.nombre, ' ', u.apellido) AS usuario, a.fecha_conexion, a.hora_conexion, a.accion_realizada, a.ip_computadora "
                + "FROM tb_auditoria a "
                + "INNER JOIN tb_usuario u ON a.idUsuario = u.idUsuario "
                + "ORDER BY a.id ASC";//se agregó esto porque se ordena por nombre y no por ID el jtable auditoria
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            // Agregar las columnas al modelo de tabla
            model.addColumn("N°");//ID
            model.addColumn("Usuario");
            model.addColumn("fecha");
            model.addColumn("hora");
            model.addColumn("acción");
            model.addColumn("ip");

            // Iterar sobre los resultados y agregarlos al modelo de tabla
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id"),
                    rs.getString("usuario"),//concatenado
                    rs.getString("fecha_conexion"),
                    rs.getString("hora_conexion"),
                    rs.getString("accion_realizada"),
                    rs.getString("ip_computadora")
                };
                model.addRow(fila);
            }
            // Configurar el modelo de tabla en la tabla visual
            FrmAuditoria.jTable_auditoria.setModel(model);

            // Cerrar la conexión
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla de auditoria desde el crud: " + e);
        }

    }

//    
//    
}
