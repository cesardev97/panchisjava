/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.Crud_Facturacion;
import DAO.Crud_RegistrarVenta;
import DAO.Crud_VentaPDF;
import Vistas.Dashboard;
import Vistas.FrmFacturacion;
import Vistas.FrmGestionarVentas;
import Vistas.FrmPago;
import conexion.Conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.CabeceraVenta;
import modelo.DetalleVenta;

/**
 *
 * @author Jean
 */
public class Ctrl_Facturacion implements ActionListener {

    FrmFacturacion vista;
    Crud_Facturacion crud;

    public Ctrl_Facturacion(FrmFacturacion vista) {
        this.vista = vista;
        this.crud = new Crud_Facturacion();
        // Agregar el ActionListener A los botones
        Crud_Facturacion.CargarTablaProducto();
        this.vista.btn_buscar.addActionListener(this);
        this.vista.btn_agregar_producto.addActionListener(this);
        this.vista.btn_calcular_vuelto.addActionListener(this);
        this.vista.btn_registrar_venta.addActionListener(this);
        this.vista.btn_registro.addActionListener(this);
        this.vista.btn_pago.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btn_buscar) {
            //BUSCA EL CLIENTE SEGUN EL DNI
            String clienteBuscar = FrmFacturacion.txt_buscar_dni.getText().trim();
            Connection cn = DAO.Conexion.conectar();
            String sql = "select nombre, apellido from tb_cliente where DNI = '" + clienteBuscar + "'";
            Statement st;
            try {
                st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                if (rs.next()) {
                    FrmFacturacion.jComboCliente.setSelectedItem(rs.getString("nombre") + " " + rs.getString("apellido"));
                } else {
                    FrmFacturacion.jComboCliente.setSelectedItem("Seleccione cliente:");
                    JOptionPane.showMessageDialog(null, "¡Dni de cliente incorrecta o no encontrada!");
                }
                FrmFacturacion.txt_buscar_dni.setText("");
                cn.close();
            } catch (SQLException a) { // como el "e" está ocupado se coloca "a2
                System.out.println("¡Error al buscar cliente!, " + a);
            }

        }
        if (e.getSource() == vista.btn_agregar_producto) {
            // TODO add your handling code here:
            String combo = FrmFacturacion.jComboProducto.getSelectedItem().toString();
            //validar que seleccione un producto
            if (combo.equalsIgnoreCase("Seleccione producto:")) {
                JOptionPane.showMessageDialog(null, "Seleccione un producto");
            } else {
                //validar que ingrese una cantidad
                if (!FrmFacturacion.txt_cantidad.getText().isEmpty()) {
                    //validar que el usuario no ingrese caracteres no numericos
                    boolean validacion = Crud_Facturacion.validar(FrmFacturacion.txt_cantidad.getText());
                    if (validacion == true) {
                        //validar que la cantidad sea mayor a cero
                        if (Integer.parseInt(FrmFacturacion.txt_cantidad.getText()) > 0) {
                            Crud_Facturacion.cantidad = Integer.parseInt(FrmFacturacion.txt_cantidad.getText());
                            //ejecutar metodo para obtener datos del producto
                            Crud_Facturacion.DatosDelProducto();
                            //validar que la cantidad de productos seleccionado no sea mayor al stock de la base de datos
                            if (Crud_Facturacion.cantidad <= Crud_Facturacion.cantidadProductoBBDD) {

                                Crud_Facturacion.subtotal = Crud_Facturacion.precioUnitario * Crud_Facturacion.cantidad;
                                Crud_Facturacion.totalPagar = Crud_Facturacion.subtotal + Crud_Facturacion.igv + Crud_Facturacion.descuento;

                                //redondear decimales
                                Crud_Facturacion.subtotal = (double) Math.round(Crud_Facturacion.subtotal * 100) / 100;
                                Crud_Facturacion.igv = (double) Math.round(Crud_Facturacion.igv * 100) / 100;
                                Crud_Facturacion.descuento = (double) Math.round(Crud_Facturacion.descuento * 100) / 100;
                                Crud_Facturacion.totalPagar = (double) Math.round(Crud_Facturacion.totalPagar * 100) / 100;

                                //se crea un nuevo producto (ya almacenamos un nuevo objeto en el producto
                                Crud_Facturacion.producto = new DetalleVenta(Crud_Facturacion.auxIdDetalle,//idDetalleVenta
                                        1, //idCabecera
                                        Crud_Facturacion.idProducto,
                                        Crud_Facturacion.nombre,
                                        Integer.parseInt(FrmFacturacion.txt_cantidad.getText()),
                                        Crud_Facturacion.precioUnitario,
                                        Crud_Facturacion.subtotal,
                                        Crud_Facturacion.descuento,
                                        Crud_Facturacion.igv,
                                        Crud_Facturacion.totalPagar,
                                        1 //estado
                                );
                                //añadir a la lista (arryList)
                                Crud_Facturacion.listaProductos.add(Crud_Facturacion.producto);
                                JOptionPane.showMessageDialog(null, "Producto Agregado");
                                Crud_Facturacion.auxIdDetalle++;
                                FrmFacturacion.txt_cantidad.setText("");//limpiar el campo
                                //volver a cargar combo productos
                                Crud_Facturacion.CargarComboProducto();
                                Crud_Facturacion.CalcularTotalPagar();// metodo para calcular el totoal apagar 
                                FrmFacturacion.txt_efectivo.setEnabled(true);
                                FrmFacturacion.btn_calcular_vuelto.setEnabled(true);

                            } else {
                                JOptionPane.showMessageDialog(null, "La cantidad supera el Stock");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "La cantidad no puede ser cero (0), ni negativa");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "En la cantidad no se admiten caracteres no numericos");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingresa la cantidad de productos");
                }
            }
            //llamar al metodo
            Crud_Facturacion.listaTablaProductos();

        }
        if (e.getSource() == vista.btn_calcular_vuelto) {

            if (!FrmFacturacion.txt_efectivo.getText().isEmpty()) {
                //validamos que el usuario no ingrese otros caracteres no numericos 
                boolean validacion = Crud_Facturacion.validarDouble(FrmFacturacion.txt_efectivo.getText());
                if (validacion == true) {
                    //validar que el efectivo sea mayor a cero
                    double efc = Double.parseDouble(FrmFacturacion.txt_efectivo.getText().trim());
                    double top = Double.parseDouble(FrmFacturacion.txt_total_pagar.getText().trim());

                    if (efc < top) {
                        JOptionPane.showMessageDialog(null, "El Dinero en efectivo no es suficiente");
                    } else {
                        double cambio = (efc - top);
                        double cambi = (double) Math.round(cambio * 100d) / 100;
                        String camb = String.valueOf(cambi);
                        FrmFacturacion.txt_vuelto.setText(camb);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No de admiten caracteres no numericos");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese dinero en efectivo para calcular cambio");
            }
        }

        if (e.getSource() == vista.btn_registrar_venta) {
            // TODO add your handling code here:
            // TODO add your handling code here:
            CabeceraVenta cabeceraVenta = new CabeceraVenta();
            DetalleVenta detalleVenta = new DetalleVenta();
            Crud_RegistrarVenta controlVenta = new Crud_RegistrarVenta();

            String fechaActual = "";
            Date date = new Date();
            fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);

            if (!FrmFacturacion.jComboCliente.getSelectedItem().equals("Seleccione cliente:")) {
                if (Crud_Facturacion.listaProductos.size() > 0) {

                    //metodo para obtener el id del cliente
                    Crud_Facturacion.ObtenerIdCliente();
                    //registrar cabecera
                    cabeceraVenta.setIdCabeceraVenta(0);
                    cabeceraVenta.setIdCliente(Crud_Facturacion.idCliente);
                    cabeceraVenta.setValorPagar(Double.parseDouble(FrmFacturacion.txt_total_pagar.getText()));
                    cabeceraVenta.setFechaVenta(fechaActual);
                    cabeceraVenta.setEstado(1);

                    if (controlVenta.guardar(cabeceraVenta)) {
                        JOptionPane.showMessageDialog(null, "¡Venta Registrada!");

                        //Generar la factura de venta
                        Crud_VentaPDF pdf = new Crud_VentaPDF();
                        pdf.DatosCliente(Crud_Facturacion.idCliente);
                        pdf.generarFacturaPDF();

                        //guardar detalle
                        for (DetalleVenta elemento : Crud_Facturacion.listaProductos) {
                            detalleVenta.setIdDetalleVenta(0);
                            detalleVenta.setIdCabeceraVenta(0);
                            detalleVenta.setIdProducto(elemento.getIdProducto());
                            detalleVenta.setCantidad(elemento.getCantidad());
                            detalleVenta.setPrecioUnitario(elemento.getPrecioUnitario());
                            detalleVenta.setSubtotal(elemento.getSubtotal());
                            detalleVenta.setDescuento(elemento.getDescuento());
                            detalleVenta.setIGV(elemento.getIGV());
                            detalleVenta.setTotalPagar(elemento.getTotalPagar());
                            detalleVenta.setEstado(1);

                            if (controlVenta.guardarDetalle(detalleVenta)) {
                                //System.out.println("Detalle de Venta Registrado");

                                FrmFacturacion.txt_subtotal.setText("0.0");
                                FrmFacturacion.txt_igv.setText("0.0");
                                FrmFacturacion.txt_descuento.setText("0.0");
                                FrmFacturacion.txt_total_pagar.setText("0.0");
                                FrmFacturacion.txt_efectivo.setText("");
                                FrmFacturacion.txt_vuelto.setText("0.0");
                                Crud_Facturacion.auxIdDetalle = 1;
                                //METODO ABAJO
                                Crud_Facturacion.CargarComboCliente();
                                //METODO para que se actualice el sttock
                                Crud_Facturacion.RestarStockProductos(elemento.getIdProducto(), elemento.getCantidad());

                            } else {
                                JOptionPane.showMessageDialog(null, "¡Error al guardar detalle de venta!");
                            }
                        }
                        //vaciamos la lista
                        Crud_Facturacion.listaProductos.clear();
                        Crud_Facturacion.listaTablaProductos();

                    } else {
                        JOptionPane.showMessageDialog(null, "¡Error al guardar cabecera de venta!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "¡Seleccione un producto!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "¡Seleccione un cliente!");
            }

        }
//PARA GESTIONAR BOTONES PARA EDITAR VENTAS Y QR DE PAGO
        if (e.getSource() == vista.btn_pago) {
            FrmPago p9 = new FrmPago();
            Dashboard.ShowPanel(p9);
        }
        if (e.getSource() == vista.btn_registro) {
            FrmGestionarVentas p10 = new FrmGestionarVentas();
            Dashboard.ShowPanel(p10);

        }
    }

}
