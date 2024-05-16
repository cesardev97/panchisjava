/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Vistas.FrmFacturacion;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import conexion.Conexion;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Crud_VentaPDF {

    private String nombreCliente;
    private String dniCliente;
    private String telefonoCliente;
    private String direccionCliente;

    private String fechaActual = "";
    private String nombreArchivoPDFVenta = "";

    public void DatosCliente(int idCliente) {
        Connection cn = DAO.Conexion.conectar();
        String sql = "select * from tb_cliente where idCliente = '" + idCliente + "'";
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                nombreCliente = rs.getString("nombre") + " " + rs.getString("apellido");
                dniCliente = rs.getString("dni");
                telefonoCliente = rs.getString("telefono");
                direccionCliente = rs.getString("direccion");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener datos de cliente desde controlador.VentPDF: " + e);
        }
    }

    public void generarFacturaPDF() {
        try {
            Date date = new Date();
            fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);
            String fechaNueva = fechaActual.replace("/", "_");

            nombreArchivoPDFVenta = "Venta_" + nombreCliente + "_" + fechaNueva + ".pdf";

            FileOutputStream archivo;
            File file = new File("src/pdf/" + nombreArchivoPDFVenta);
            archivo = new FileOutputStream(file);

            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();

            Image img = Image.getInstance("src/Imagenes/logo panchis 80x80 px.png");

            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.YELLOW); // SE CAMBIO DEL BLUE

            Paragraph fecha = new Paragraph();
            fecha.add(Chunk.NEWLINE);
            fecha.add("Factura: 001" + "\nFecha: " + fechaActual + "\n\n");

            PdfPTable encabezado = new PdfPTable(4);
            encabezado.setWidthPercentage(100);
            encabezado.getDefaultCell().setBorder(0);
            float[] columnasEncabezado = new float[]{20f, 30f, 70f, 40f};
            encabezado.setWidths(columnasEncabezado);
            encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            encabezado.addCell(img);
            String ruc = "0987654321999";
            String nombre = "Panchi's Pizza";
            String telefono = "0987654321";
            String direccion = "AV. Las Palmeras";
            String razon = "**************";
            PdfPCell datosEmpresa = new PdfPCell(new Phrase("RUC: " + ruc + "\nNOMBRE: " + nombre + "\nTELEFONO: " + telefono + "\nDIRECCION: " + direccion + "\nRAZON SOCIAL: " + razon));
            datosEmpresa.setBorder(0);
            encabezado.addCell("");
            encabezado.addCell(datosEmpresa);
            encabezado.addCell(fecha);
            doc.add(encabezado);

            Paragraph datosCliente = new Paragraph();
            datosCliente.add(Chunk.NEWLINE);
            datosCliente.add("Datos del cliente:\n\n");
            doc.add(datosCliente);

            PdfPTable tablaCliente = new PdfPTable(4);
            tablaCliente.setWidthPercentage(100);
            tablaCliente.getDefaultCell().setBorder(0);
            float[] columnasCliente = new float[]{25f, 45f, 30f, 40f};
            tablaCliente.setWidths(columnasCliente);
            tablaCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell celdaDniRuc = new PdfPCell(new Phrase("DNI/RUC: ", negrita));
            PdfPCell celdaNombre = new PdfPCell(new Phrase("Nombre: ", negrita));
            PdfPCell celdaTelefono = new PdfPCell(new Phrase("Telefono: ", negrita));
            PdfPCell celdaDireccion = new PdfPCell(new Phrase("Direccion: ", negrita));
            celdaDniRuc.setBorder(0);
            celdaNombre.setBorder(0);
            celdaTelefono.setBorder(0);
            celdaDireccion.setBorder(0);
            tablaCliente.addCell(celdaDniRuc);
            tablaCliente.addCell(celdaNombre);
            tablaCliente.addCell(celdaTelefono);
            tablaCliente.addCell(celdaDireccion);
            tablaCliente.addCell(dniCliente);
            tablaCliente.addCell(nombreCliente);
            tablaCliente.addCell(telefonoCliente);
            tablaCliente.addCell(direccionCliente);
            doc.add(tablaCliente);

            Paragraph espacio = new Paragraph();
            espacio.add(Chunk.NEWLINE);
            espacio.add("\n");
            espacio.setAlignment(Element.ALIGN_CENTER);
            doc.add(espacio);

            PdfPTable tablaProducto = new PdfPTable(4);
            tablaProducto.setWidthPercentage(100);
            tablaProducto.getDefaultCell().setBorder(0);
            float[] columnasProducto = new float[]{15f, 50f, 15f, 20f};
            tablaProducto.setWidths(columnasProducto);
            tablaProducto.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell celdaCantidad = new PdfPCell(new Phrase("Cantidad: ", negrita));
            PdfPCell celdaDescripcion = new PdfPCell(new Phrase("Descripcion: ", negrita));
            PdfPCell celdaPrecioUnitario = new PdfPCell(new Phrase("Precio Unitario: ", negrita));
            PdfPCell celdaPrecioTotal = new PdfPCell(new Phrase("Precio Total: ", negrita));
            celdaCantidad.setBorder(0);
            celdaDescripcion.setBorder(0);
            celdaPrecioUnitario.setBorder(0);
            celdaPrecioTotal.setBorder(0);
            celdaCantidad.setBackgroundColor(BaseColor.LIGHT_GRAY);
            celdaDescripcion.setBackgroundColor(BaseColor.LIGHT_GRAY);
            celdaPrecioUnitario.setBackgroundColor(BaseColor.LIGHT_GRAY);
            celdaPrecioTotal.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tablaProducto.addCell(celdaCantidad);
            tablaProducto.addCell(celdaDescripcion);
            tablaProducto.addCell(celdaPrecioUnitario);
            tablaProducto.addCell(celdaPrecioTotal);

            for (int i = 0; i < FrmFacturacion.jTable_productos.getRowCount(); i++) {
                String producto = FrmFacturacion.jTable_productos.getValueAt(i, 1).toString();
                String cantidad = FrmFacturacion.jTable_productos.getValueAt(i, 2).toString();
                String precio = FrmFacturacion.jTable_productos.getValueAt(i, 3).toString();
                String total = FrmFacturacion.jTable_productos.getValueAt(i, 7).toString();

                tablaProducto.addCell(cantidad);
                tablaProducto.addCell(producto);
                tablaProducto.addCell(precio);
                tablaProducto.addCell(total);
            }

            doc.add(tablaProducto);

            Paragraph totalPagar = new Paragraph();
            totalPagar.add(Chunk.NEWLINE);
            totalPagar.add("Total a pagar: " + FrmFacturacion.txt_total_pagar.getText());
            totalPagar.setAlignment(Element.ALIGN_RIGHT);
            doc.add(totalPagar);

            Paragraph firma = new Paragraph();
            firma.add(Chunk.NEWLINE);
            firma.add(" Firma digital\n\n");
            firma.add("_______________________");
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);

            Paragraph mensaje = new Paragraph();
            mensaje.add(Chunk.NEWLINE);
            mensaje.add("¡Vuelva Pronto!");
            mensaje.setAlignment(Element.ALIGN_CENTER);
            doc.add(mensaje);

            doc.close();
            archivo.close();

            Desktop.getDesktop().open(file);

        } catch (DocumentException | IOException e) {
            System.out.println("Error en: " + e);
        }
    }
}

