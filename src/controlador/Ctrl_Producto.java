/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.Conexion;
import DAO.Crud_Producto;
//import static DAO.Crud_Producto.obtenerIdCategoriaCombo;
import Vistas.FrmProducto;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.Producto;

/**
 *
 * @author Jean
 */
public class Ctrl_Producto implements ActionListener {

//    int obtenerIdCategoriaCombo = Crud_Producto.obtenerIdCategoriaCombo;
    static int obtenerIdCategoriaCombo = 0;

    FrmProducto vista;
    Crud_Producto crud;

    public Ctrl_Producto(FrmProducto vista) {
        this.vista = vista;
        this.crud = new Crud_Producto();
        // Agregar el ActionListener A los botones
        Crud_Producto.CargarTablaProductos();
        this.vista.btn_guardar.addActionListener(this);
        this.vista.btn_actualizar.addActionListener(this);
        this.vista.btn_eliminar.addActionListener(this);
        this.vista.btn_buscar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btn_buscar) {
            //BUSCA PRODUCTO SEGUN SU ID
            if (FrmProducto.txt_buscar_idProducto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Escribe el ID del producto a buscar");
            } else {
                String productoBuscar = FrmProducto.txt_buscar_idProducto.getText().trim();
                Connection cn = Conexion.conectar();
                String sql = "SELECT * FROM tb_producto where idProducto  = '" + productoBuscar + "'";
                Statement st;
                try {
                    st = cn.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    if (rs.next()) {

                        FrmProducto.txt_gestionar_nombre.setText(rs.getString("nombre")); //"
                        FrmProducto.txt_gestionar_cantidad.setText(rs.getString("cantidad")); //"
                        FrmProducto.txt_gestionar_discripcion.setText(rs.getString("discripcion")); //"
                        FrmProducto.txt_gestionar_precio.setText(rs.getString("precio")); //"
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
                         int idCate = rs.getInt("idCategoria");
                FrmProducto.jComboBox_gestionarCategoria.setSelectedItem(Crud_Producto.relacionarCategoria(idCate));

                    } else {
                        JOptionPane.showMessageDialog(null, "¡Id de producto incorrecta o no encontrada!");
                    }
                    FrmProducto.txt_buscar_idProducto.setText("");
                    cn.close();
                } catch (SQLException a) { // como el "e" está ocupado se coloca "a
                    System.out.println("¡Error al buscar producto!, " + a);
                }
            }
        }
        if (e.getSource() == vista.btn_guardar) {
//            int obtenerIdCategoriaCombo = Crud_Producto.obtenerIdCategoriaCombo;
            Producto producto = new Producto();
            Crud_Producto controlProducto = new Crud_Producto();
            String iva = "";
            String categoria = "";
            iva = FrmProducto.jComboBoxIGV.getSelectedItem().toString().trim();
            categoria = FrmProducto.jComboBoxCategoria.getSelectedItem().toString().trim();

            //validar campos no nulos
            if (FrmProducto.txt_nombre.getText().equals("") || FrmProducto.txt_cantidad.getText().equals("") || FrmProducto.txt_precio.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Complete todos los campos");
//                FrmProducto.txt_nombre.setBackground(Color.red);
//                FrmProducto.txt_cantidad.setBackground(Color.red);
//                FrmProducto.txt_precio.setBackground(Color.red);
            } else {
                //consulta para ver si el producto ya existe
                if (!controlProducto.existeProducto(FrmProducto.txt_nombre.getText().trim())) {
                    if (iva.equalsIgnoreCase("Seleccione IGV:")) {
                        JOptionPane.showMessageDialog(null, "Seleccione iva.");
                    } else {
                        if (categoria.equalsIgnoreCase("Seleccione categoria:")) {
                            JOptionPane.showMessageDialog(null, "Seleccione categoria");
                        } else {
                            try {
                                producto.setNombre(FrmProducto.txt_nombre.getText().trim());
                                producto.setCantidad(Integer.parseInt(FrmProducto.txt_cantidad.getText().trim()));
                                String precioTXT = "";
                                double Precio = 0.0;
                                precioTXT = FrmProducto.txt_precio.getText().trim();
                                boolean aux = false;
                                /*
                            *Si el usuario ingresa , (coma) como punto decimal,
                            lo transformamos a punto (.)
                                 */
                                for (int i = 0; i < precioTXT.length(); i++) {
                                    if (precioTXT.charAt(i) == ',') {
                                        String precioNuevo = precioTXT.replace(",", ".");
                                        Precio = Double.parseDouble(precioNuevo);
                                        aux = true;
                                    }
                                }
                                //evaluar la condicion
                                if (aux == true) {
                                    producto.setPrecio(Precio);
                                } else {
                                    Precio = Double.parseDouble(precioTXT);
                                    producto.setPrecio(Precio);
                                }

                                producto.setDiscripcion(FrmProducto.txt_discripcion.getText().trim());
                                //Porcentaje IVA
                                if (iva.equalsIgnoreCase("Sin IGV:")) {
                                    producto.setIGV(0);
                                } else if (iva.equalsIgnoreCase("16%")) {
                                    producto.setIGV(16);
                                } else if (iva.equalsIgnoreCase("18%")) {
                                    producto.setIGV(18);
                                }

                                //idcategoria - cargar metodo que obtiene el id de categoria
                                this.IdCategoria();
                                producto.setIdCategoria(obtenerIdCategoriaCombo);
                                producto.setEstado(1);

                                if (controlProducto.guardar(producto)) {
                                    JOptionPane.showMessageDialog(null, "Producto Guardado");
//                                txt_nombre.setBackground(Color.green);
//                                txt_cantidad.setBackground(Color.green);
//                                txt_precio.setBackground(Color.green);
//                                txt_discripcion.setBackground(Color.green);
                                    Crud_Producto.CargarTablaProductos();
                                    Crud_Producto.CargarComboCategoria();
                                    FrmProducto.jComboBoxIGV.setSelectedItem("Seleccione IGV:");
                                    Crud_Producto.Limpiar();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                                }

                            } catch (HeadlessException | NumberFormatException a) { // era "e" en vez de "a"
                                System.out.println("Error en: " + a);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El producto ya existe en la Base de Datos");
                    FrmProducto.txt_nombre.setBackground(Color.red);
                }
            }
        }

        if (e.getSource() == vista.btn_actualizar) {
            int idProducto = Crud_Producto.idProducto;
            Producto producto = new Producto();
            Crud_Producto controlProducto = new Crud_Producto();
            String iva = "";
            String categoria = "";
            iva = FrmProducto.jComboBox_gestionarIGV.getSelectedItem().toString().trim();
            categoria = FrmProducto.jComboBox_gestionarCategoria.getSelectedItem().toString().trim();

            //validar campos
            if (FrmProducto.txt_gestionar_nombre.getText().isEmpty() || FrmProducto.txt_gestionar_cantidad.getText().isEmpty() || FrmProducto.txt_gestionar_precio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Complete todos los campos");
            } else {
                if (iva.equalsIgnoreCase("Seleccione IGV:")) {
                    JOptionPane.showMessageDialog(null, "Seleccione un IGV válida");
                } else {
                    if (categoria.equalsIgnoreCase("Seleccione categoria:")) {
                        JOptionPane.showMessageDialog(null, "Seleccione una categoria válida");
                    } else {
                        try {
                            producto.setNombre(FrmProducto.txt_gestionar_nombre.getText().trim());
                            producto.setCantidad(Integer.parseInt(FrmProducto.txt_gestionar_cantidad.getText().trim()));
                            String precioTXT = "";
                            double Precio = 0.0;
                            precioTXT = FrmProducto.txt_gestionar_precio.getText().trim();
                            boolean aux = false;
                            /*
                            *Si el usuario ingresa , (coma) como punto decimal,
                            lo transformamos a punto (.)
                             */
                            for (int i = 0; i < precioTXT.length(); i++) {
                                if (precioTXT.charAt(i) == ',') {
                                    String precioNuevo = precioTXT.replace(",", ".");
                                    Precio = Double.parseDouble(precioNuevo);
                                    aux = true;
                                }
                            }
                            //evaluar la condicion
                            if (aux == true) {
                                producto.setPrecio(Precio);
                            } else {
                                Precio = Double.parseDouble(precioTXT);
                                producto.setPrecio(Precio);
                            }
                            //actualiza el igv
                            producto.setDiscripcion(FrmProducto.txt_gestionar_discripcion.getText().trim());
                            //Porcentaje IGV
                            if (iva.equalsIgnoreCase("Seleccione IGV")) {
                                producto.setIGV(0);
                            } else if (iva.equalsIgnoreCase("Sin IGV")) {
                                producto.setIGV(0);
                            } else if (iva.equalsIgnoreCase("16%")) {
                                producto.setIGV(16);
                            } else if (iva.equalsIgnoreCase("18%")) {
                                producto.setIGV(18);
                            }

                            //idcategoria - cargar metodo que obtiene el id de categoria
                            this.IdCategoria2();
                            producto.setIdCategoria(obtenerIdCategoriaCombo);
                            producto.setEstado(1);

                            if (controlProducto.actualizar(producto, idProducto)) {
                                JOptionPane.showMessageDialog(null, "Registro Actualizado");
                                Crud_Producto.CargarTablaProductos();
                                Crud_Producto.CargarComboCategorias();
                                FrmProducto.jComboBox_gestionarIGV.setSelectedItem("Seleccione IGV::");
                                Crud_Producto.Limpiar2();
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al Actualizar desde el controlador");
                            }
                        } catch (HeadlessException | NumberFormatException a) {
                            System.out.println("Error en: " + a);
                        }
                    }
                }
            }
        }

        if (e.getSource() == vista.btn_eliminar) {
            int idProducto = Crud_Producto.idProducto;
            Crud_Producto controlProducto = new Crud_Producto();
            if (idProducto == 0) {
                JOptionPane.showMessageDialog(null, "¡Seleccione un Producto!");
            } else {
                if (!controlProducto.eliminar(idProducto)) {
                    JOptionPane.showMessageDialog(null, "¡Producto Eliminado!");
                    Crud_Producto.CargarTablaProductos();
                    Crud_Producto.CargarComboCategorias();
                    Crud_Producto.Limpiar2();
                } else {
                    JOptionPane.showMessageDialog(null, "¡Error al eliminar producto!");
                }
            }
        }

    }
//esto es para el combo de la izquierda

    public static int IdCategoria() {
        String sql = "select * from tb_categoria where discripcion = '" + FrmProducto.jComboBoxCategoria.getSelectedItem() + "'"; // 
        Statement st;
        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                obtenerIdCategoriaCombo = rs.getInt("idCategoria");
            }
        } catch (SQLException e) {
            System.out.println("Error al obener id categoria");
        }
        return obtenerIdCategoriaCombo;
    }

    //esto para obtner la id del combo de la derecha
    private int IdCategoria2() {
        String sql = "select * from tb_categoria where discripcion = '" + FrmProducto.jComboBox_gestionarCategoria.getSelectedItem() + "'";// se cambia jComboBox_gestionarCategoria por jComboBoxCategoria
        Statement st;
        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                obtenerIdCategoriaCombo = rs.getInt("idCategoria");
            }
        } catch (SQLException e) {
            System.out.println("Error al obener id categoria");
        }
        return obtenerIdCategoriaCombo;
    }
    /**
     *
     * metodos panel izquierdo
     */
    // Metodo para cargar las categorias en el combo (panel derecho)

    /**
     *
     * metodos panel derecho
     */
}
