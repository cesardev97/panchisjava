/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.Crud_Cliente;
import Vistas.FrmCliente;

import conexion.Conexion;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.Cliente;

/**
 *
 * @author Jean
 */
public class Ctrl_Cliente implements ActionListener {

    FrmCliente vista;
    Crud_Cliente crud;

    public Ctrl_Cliente(FrmCliente vista) {
        this.vista = vista;
        this.crud = new Crud_Cliente();
        // Agregar el ActionListener A los botones
        Crud_Cliente.CargarTablaCliente();
        this.vista.btn_guardar.addActionListener(this);
        this.vista.btn_actualizar.addActionListener(this);
        this.vista.btn_eliminar.addActionListener(this);
        this.vista.btn_buscar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btn_buscar) {
            //BUSCA EL CLIENTE SEGUN EL DNI
            if (FrmCliente.txt_buscar_dniCliente.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Escribe el DNI del usuario a buscar");

            } else {
                String clienteBuscar = FrmCliente.txt_buscar_dniCliente.getText().trim();
                Connection cn = DAO.Conexion.conectar();
                String sql = "SELECT * FROM tb_cliente where dni = '" + clienteBuscar + "'";
                Statement st;
                try {
                    st = cn.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    if (rs.next()) {
                        
                        FrmCliente.txt_gestionar_nombre.setText(rs.getString("nombre")); //"
                        FrmCliente.txt_gestionar_apellido.setText(rs.getString("apellido")); //"
                        FrmCliente.txt_gestionar_dni.setText(rs.getString("dni")); //"
                        FrmCliente.txt_gestionar_direccion.setText(rs.getString("direccion")); //"
                        FrmCliente.txt_gestionar_telefono.setText(rs.getString("telefono")); //"

                    } else {
                        JOptionPane.showMessageDialog(null, "¡Dni de cliente incorrecta o no encontrada!");
                    }
                    FrmCliente.txt_buscar_dniCliente.setText("");
                    cn.close();
                } catch (SQLException a) { // como el "e" está ocupado se coloca "a
                    System.out.println("¡Error al buscar cliente!, " + a);
                }
            }
            
        }
        
        if (e.getSource() == vista.btn_guardar) {
            Cliente cliente = new Cliente();
            Crud_Cliente controlCliente = new Crud_Cliente();

            if (!FrmCliente.txt_nombre.getText().isEmpty() && !FrmCliente.txt_apellido.getText().isEmpty() && !FrmCliente.txt_dni.getText().isEmpty()) {
                //JOptionPane.showMessageDialog(null, "registro correcto");
                if (!controlCliente.existeCliente(FrmCliente.txt_dni.getText().trim())) {
                    cliente.setNombre(FrmCliente.txt_nombre.getText().trim());
                    cliente.setApellido(FrmCliente.txt_apellido.getText().trim());
                    cliente.setDni(FrmCliente.txt_dni.getText().trim());
                    cliente.setDireccion(FrmCliente.txt_direccion.getText().trim());
                    cliente.setTelefono(FrmCliente.txt_telefono.getText().trim());
                    cliente.setEstado(1);
                    if (controlCliente.guardar(cliente)) {
                        JOptionPane.showMessageDialog(null, "Registro Guardado");
                        //los campos se pondran en verde se registró correctamente
//                    txt_nombre.setBackground(Color.GREEN);
//                    txt_apellido.setBackground(Color.GREEN);
//                    txt_dni.setBackground(Color.GREEN);
//                    txt_telefono.setBackground(Color.GREEN);
//                    txt_direccion.setBackground(Color.GREEN);
                        Crud_Cliente.Limpiar();
                        Crud_Cliente.CargarTablaCliente();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al guardar");

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "El Cliente ya se encuentra registrado en la Base de Datos.");

                    FrmCliente.txt_nombre.setBackground(Color.WHITE);
                    FrmCliente.txt_apellido.setBackground(Color.WHITE);
                    FrmCliente.txt_dni.setBackground(Color.WHITE);
                    FrmCliente.txt_telefono.setBackground(Color.WHITE);
                    FrmCliente.txt_direccion.setBackground(Color.WHITE);

                    FrmCliente.txt_dni.setText("");
                }

            } else {
                JOptionPane.showMessageDialog(null, "complete los campos faltantes");
//            los campos se colocaran en color rojos
//            FrmCliente.txt_nombre.setBackground(Color.red);
//            FrmCliente.txt_apellido.setBackground(Color.red);
//            FrmCliente.txt_dni.setBackground(Color.red);
//            FrmCliente.txt_telefono.setBackground(Color.red);
//            FrmCliente.txt_direccion.setBackground(Color.red);
            }
            //metodo limpiar

        }

        if (e.getSource() == vista.btn_actualizar) {
            int idCliente = Crud_Cliente.idCliente;
            
            if (idCliente == 0) {
                JOptionPane.showMessageDialog(null, "¡Seleccione un Usuario!");
            } else {
            if (FrmCliente.txt_gestionar_nombre.getText().isEmpty() && FrmCliente.txt_gestionar_apellido.getText().isEmpty()
                    && FrmCliente.txt_gestionar_dni.getText().isEmpty() && FrmCliente.txt_gestionar_telefono.getText().isEmpty() && FrmCliente.txt_gestionar_direccion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "¡Completa todos los campos!");
            } else {
                Cliente cliente = new Cliente();
                Crud_Cliente controlCliente = new Crud_Cliente();
                cliente.setNombre(FrmCliente.txt_gestionar_nombre.getText().trim());
                cliente.setApellido(FrmCliente.txt_gestionar_apellido.getText().trim());
                cliente.setDni(FrmCliente.txt_gestionar_dni.getText().trim());
                cliente.setTelefono(FrmCliente.txt_gestionar_telefono.getText().trim());
                cliente.setDireccion(FrmCliente.txt_gestionar_direccion.getText().trim());
                cliente.setEstado(1);

                if (controlCliente.actualizar(cliente, idCliente)) {
                    JOptionPane.showMessageDialog(null, "¡Datos del cliente actualizados!");
                    Crud_Cliente.CargarTablaCliente();
                    Crud_Cliente.Limpiar2();
                } else {
                    JOptionPane.showMessageDialog(null, "¡Error al actualizar desde el controlador!");
                }

            }
            }
        }
        if (e.getSource() == vista.btn_eliminar) {
            int idCliente = Crud_Cliente.idCliente;
            Crud_Cliente controlCliente = new Crud_Cliente();
            if (idCliente == 0) {
                JOptionPane.showMessageDialog(null, "¡Seleccione un cliente!");
            } else {
                if (!controlCliente.eliminar(idCliente)) {
                    JOptionPane.showMessageDialog(null, "¡Cliente Eliminado!");
                    Crud_Cliente.CargarTablaCliente();
                    Crud_Cliente.Limpiar2();
                } else {
                    JOptionPane.showMessageDialog(null, "¡Error al eliminar cliente!");
                    Crud_Cliente.Limpiar2();
                }
            }
        }
    }

}
