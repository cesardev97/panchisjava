/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.Crud_Categoria;
//import static Vistas.FrmCategoria.jTable_categoria;//tener en cuenta esto
import Vistas.FrmCategoria;
import conexion.Conexion;
//import static Vistas.FrmCategoria.jTable_categoria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
//import javax.swing.table.DefaultTableModel;
import modelo.Categoria;

/**
 *
 * @author Jean
 */
// AQUI DEBERA IR LAS ACCIONES DE LOS BOTONES
public class Ctrl_Categoria implements ActionListener {
// private static int idCategoria; //reemplazado por  -> int idCategoria = Crud_Categoria.idCategoria;

    FrmCategoria vista;
    Crud_Categoria crud;

    // Constructor que recibe la instancia de FrmCategoria
    public Ctrl_Categoria(FrmCategoria vista) {
        this.vista = vista;
        this.crud = new Crud_Categoria();
        // Agregar el ActionListener A los botones
        Crud_Categoria.CargarTablaCategorias();
        this.vista.btn_guardar.addActionListener(this);
        this.vista.btn_actualizar.addActionListener(this);
        this.vista.btn_eliminar.addActionListener(this);
        this.vista.btn_buscar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btn_buscar) {
            //BUSCA EL CLIENTE SEGUN EL DNI
            if (FrmCategoria.txt_buscar_idCategoria.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Escribe el ID de la categoria a buscar");

            } else {
                String categoriaBuscar = FrmCategoria.txt_buscar_idCategoria.getText().trim();
                Connection cn = DAO.Conexion.conectar();
                String sql = "SELECT * FROM tb_categoria where idCategoria = '" + categoriaBuscar + "'";
                Statement st;
                try {
                    st = cn.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    if (rs.next()) {
                        
                        FrmCategoria.txt_gestionar_discripcion.setText(rs.getString("discripcion")); //"


                    } else {
                        JOptionPane.showMessageDialog(null, "¡ID de categoria incorrecta o no encontrada!");
                    }
                    FrmCategoria.txt_buscar_idCategoria.setText("");
                    cn.close();
                } catch (SQLException a) { // como el "e" está ocupado se coloca "a
                    System.out.println("¡Error al buscar id!, " + a);
                }
            }
            
        }
        if (e.getSource() == vista.btn_guardar) {
            Categoria categoria = new Categoria();
            Crud_Categoria controlCategoria = new Crud_Categoria();
            categoria.setDiscripcion(FrmCategoria.txt_discripcion.getText().trim());
            //validar si el campo esta vacio
            if (FrmCategoria.txt_discripcion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "complete todos los campos");
            } else {
                if (!controlCategoria.existeCategoria(FrmCategoria.txt_discripcion.getText().trim())) {
                    categoria.setDiscripcion(FrmCategoria.txt_discripcion.getText().trim());
                    categoria.setEstado(1); //guarda el campo estado con 1
                    if (controlCategoria.guardar(categoria)) {
                        JOptionPane.showMessageDialog(null, "Registro guardado");

                        // Limpiar el modelo de la tabla(proceso inncesario y provoca error)
//                    DefaultTableModel model = (DefaultTableModel) jTable_categoria.getModel();
//                    model.setRowCount(0);
                        // Volver a cargar los datos en el modelo de la tabla
                        Crud_Categoria.CargarTablaCategorias();

                    } else {
                        JOptionPane.showMessageDialog(null, "Error al guardar");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "La categoria ya se encuentra en la base de datos");
                }

            }
            //limpiar campos
            FrmCategoria.txt_discripcion.setText("");
        }

        if (e.getSource() == vista.btn_actualizar) {
            int idCategoria = Crud_Categoria.idCategoria;
            if (!FrmCategoria.txt_gestionar_discripcion.getText().isEmpty()) {
                Categoria categoria = new Categoria();
                Crud_Categoria controlCategoria = new Crud_Categoria();
                categoria.setDiscripcion(FrmCategoria.txt_gestionar_discripcion.getText().trim());

                if (controlCategoria.actualizar(categoria, idCategoria)) {

                    JOptionPane.showMessageDialog(null, "Categoria Actualizada");
                    FrmCategoria.txt_gestionar_discripcion.setText("");
                    Crud_Categoria.CargarTablaCategorias();
                } else {
                    JOptionPane.showMessageDialog(null, "error al actualizar categoria desde el controlador ");
                }

            } else {
                JOptionPane.showMessageDialog(null, "seleccione una categoria");
            }

        }

        if (e.getSource() == vista.btn_eliminar) {
            if (!FrmCategoria.txt_gestionar_discripcion.getText().isEmpty()) {
                Categoria categoria = new Categoria();
                Crud_Categoria controlCategoria = new Crud_Categoria();
                categoria.setDiscripcion(FrmCategoria.txt_discripcion.getText().trim());
                int idCategoria = Crud_Categoria.idCategoria;
                if (!controlCategoria.eliminar(idCategoria)) {

                    JOptionPane.showMessageDialog(null, "Categoria Eliminada");
                    FrmCategoria.txt_gestionar_discripcion.setText("");
                    Crud_Categoria.CargarTablaCategorias();
                } else {
                    JOptionPane.showMessageDialog(null, "error al eliminar categoria");
                }
            } else {
                JOptionPane.showMessageDialog(null, "seleccione una categoria");
            }
        }


    }

}
