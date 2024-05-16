/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Vistas.FrmCliente;
import static Vistas.FrmCliente.jTable_clientes;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;

/**
 *
 * @author Jean
 */
public class Crud_Cliente extends Conexion {

    public static int idCliente;

    public void Crud_Cliente() {

    }

    public static void Limpiar() {
        FrmCliente.txt_nombre.setText("");
        FrmCliente.txt_apellido.setText("");
        FrmCliente.txt_dni.setText("");
        FrmCliente.txt_telefono.setText("");
        FrmCliente.txt_direccion.setText("");

    }

    public static void Limpiar2() {
        FrmCliente.txt_gestionar_nombre.setText("");
        FrmCliente.txt_gestionar_apellido.setText("");
        FrmCliente.txt_gestionar_dni.setText("");
        FrmCliente.txt_gestionar_telefono.setText("");
        FrmCliente.txt_gestionar_direccion.setText("");

    }

    public static void CargarTablaCliente() {
        Connection con = Conexion.conectar();
        DefaultTableModel model = new DefaultTableModel();
        String sql = "select * from tb_cliente";
        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            FrmCliente.jTable_clientes = new JTable(model);
            FrmCliente.jScrollPane1.setViewportView(FrmCliente.jTable_clientes);

            model.addColumn("N°");//ID
            model.addColumn("nombre");
            model.addColumn("apellido");
            model.addColumn("dni");
            model.addColumn("direccion");
            model.addColumn("telefono");
            model.addColumn("estado");
// estado 1 o 0 lo cambia a activo o inactivo, posteriormente servirá para que al presionar eliminar cambie de estado a inactivo
            while (rs.next()) {
                Object fila[] = new Object[7];
                for (int i = 0; i < 7; i++) {
                    if (i == 6) {
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
            System.out.println("Error al llenar la tabla clientes: " + e);
        }
        //evento para obtener campo al cual el usuario da click
        //y obtener la interfaz que mostrara la informacion general
        jTable_clientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila_point = jTable_clientes.rowAtPoint(e.getPoint());
                int columna_point = 0;

                if (fila_point > -1) {
                    idCliente = (int) model.getValueAt(fila_point, columna_point);
                    EnviarDatosClienteSeleccionados(idCliente);//metodo
                }
            }
        });
    }
    //metodo enviar los datos seleccionados

    private static void EnviarDatosClienteSeleccionados(int idCliente) {
        try {
            Connection con = Conexion.conectar();
            PreparedStatement pst = con.prepareStatement(
                    "SELECT * FROM tb_cliente WHERE idcliente ='" + idCliente + "'");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                FrmCliente.txt_gestionar_nombre.setText(rs.getString("nombre")); //"
                FrmCliente.txt_gestionar_apellido.setText(rs.getString("apellido")); //"
                FrmCliente.txt_gestionar_dni.setText(rs.getString("dni")); //"
                FrmCliente.txt_gestionar_telefono.setText(rs.getString("telefono")); //"
                FrmCliente.txt_gestionar_direccion.setText(rs.getString("direccion")); //"

            }
            con.close();

        } catch (SQLException e) {
            System.out.println("Error al seleccionar cliente" + e);
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //metodo para guardar cliente
    public boolean guardar(Cliente objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("INSERT INTO tb_cliente values(?,?,?,?,?,?,?)");
            consulta.setInt(1, 0); // id
            consulta.setString(2, objeto.getNombre());
            consulta.setString(3, objeto.getApellido());
            consulta.setString(4, objeto.getDni());
            consulta.setString(5, objeto.getDireccion());
            consulta.setString(6, objeto.getTelefono());
            consulta.setInt(7, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al guardar cliente" + e);
        }
        return respuesta;
    }

    //metodo para consulta si el cliente ya está guardado
    public boolean existeCliente(String dni) {
        boolean respuesta = false;
        String sql = "SELECT dni FROM tb_cliente WHERE dni='" + dni + "';";
        Statement st;

        try {
            Connection cn = DAO.Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql); //se ejecuta
            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar Cliente" + e);

        }
        return respuesta;
    }

    /**
     * **************************************************
     * metodo para actualizar un cliente
     * **************************************************
     */
    public boolean actualizar(Cliente objeto, int idCliente) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("update tb_cliente set nombre=?, apellido = ?, dni = ?, telefono= ?, direccion = ?, estado = ? where idCliente ='" + idCliente + "'");
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getApellido());
            consulta.setString(3, objeto.getDni());
            consulta.setString(4, objeto.getTelefono());
            consulta.setString(5, objeto.getDireccion());
            consulta.setInt(6, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e);
        }
        return respuesta;
    }

    /**
     * **************************************************
     * metodo para eliminar un cliente
     * **************************************************
     */
    public boolean eliminar(int idCliente) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "delete from tb_cliente where idCliente ='" + idCliente + "'");
            consulta.executeUpdate();

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e);
        }
        return respuesta;
    }
}
