/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import conexion.Conexion;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Jean
 */
public class Reportes {
    
    /*
    metodo para crear reportes de los clientes registrado en la BBDD
    
    */
    public void ReporteClientes(){
     Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/OneDrive/Reporte_Clientes.pdf"));// hay que tener mucho cuidado con las rutas, depedne de cada PC
//            Image header = Image.getInstance("src/Imagenes/logo panchis 80x80 px.png");
//            header.scaleToFit(650, 1000);
//            header.setAlignment(Chunk.ALIGN_CENTER);
            //formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por \nVendedor Panchi´s\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Clientes \n\n");

            documento.open();
           //agregamos los datos
//            documento.add(header);
            documento.add(parrafo);

            PdfPTable tabla = new PdfPTable(5);
            tabla.addCell("Codigo");
            tabla.addCell("Nombres");
            tabla.addCell("dni");
            tabla.addCell("Telefono");
            tabla.addCell("Direccion");

            try {
                Connection cn = DAO.Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select idCliente, concat(nombre, ' ', apellido) as nombres, dni, telefono, direccion from tb_cliente");
                ResultSet rs = pst.executeQuery();
             if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                    } while (rs.next());
                    documento.add(tabla);
                }

            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();
            
            JOptionPane.showMessageDialog(null, "Reporte creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }
    
    
    /* 
    * metodo para crear reportes de los productos registrados en el sistema
     */
    public void ReportesProductos() {
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/OneDrive/Reporte_Productos.pdf"));
//            Image header = Image.getInstance("src/Imagenes/logo panchis 50x50 px.png");
//            header.scaleToFit(650, 1000);
//            header.setAlignment(Chunk.ALIGN_CENTER);
            //formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por \nVendedor(a) Panchi´sPizza\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Productos \n\n");

            documento.open();
            //agregamos los datos
//            documento.add(header);
            documento.add(parrafo);
            
            float[] columnsWidths = {3, 5, 4, 5, 7, 5, 6};

            PdfPTable tabla = new PdfPTable(columnsWidths);
            tabla.addCell("Codigo");
            tabla.addCell("Nombre");
            tabla.addCell("Cant.");
            tabla.addCell("Precio");
            tabla.addCell("Descripcion");
            tabla.addCell("Por. IGV"); //Iva
            tabla.addCell("Categoria");

            try {
                Connection cn = DAO.Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select p.idProducto, p.nombre, p.cantidad, p.precio, p.discripcion, "
                                + "p.IGV, c.discripcion as categoria, p.estado "
                                + "from tb_producto as p, tb_categoria as c "
                                + "where p.idCategoria = c.idCategoria;");
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                        tabla.addCell(rs.getString(6));
                        tabla.addCell(rs.getString(7));
                    } while (rs.next());
                    documento.add(tabla);
                }

            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();
            
            JOptionPane.showMessageDialog(null, "Reporte creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }
  
        /* 
    * metodo para crear reportes de los categorias registrados en la BBDD
     */
    public void ReportesCategorias() {
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/OneDrive/Reporte_Categotias.pdf"));
            //formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por \n Vendedor(a) Panchi´sPizza \n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Categorias \n\n");
            documento.open();
            //agregamos los datos
//            documento.add(header);
            documento.add(parrafo);

            PdfPTable tabla = new PdfPTable(3);
            tabla.addCell("Codigo");
            tabla.addCell("Descripcion");
            tabla.addCell("Estado");
            try {
               Connection cn = DAO.Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select * from tb_categoria");
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                    } while (rs.next());
                    documento.add(tabla);
                }
            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado");
        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }
    
    
        /* 
    * metodo para crear reportes de las ventas registrados en la BBDD
    */
    public void ReportesVentas() {
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta +"/OneDrive/Reporte_Ventas.pdf"));
            //formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por \nVendedor Panchi´s\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Ventas \n\n");
            documento.open();
            //agregamos los datos
//            documento.add(header);
            documento.add(parrafo);
            float[] columnsWidths = {3, 9, 4, 5, 3};
            PdfPTable tabla = new PdfPTable(columnsWidths);
            tabla.addCell("Codigo");
            tabla.addCell("Cliente");
            tabla.addCell("Tot. Pagar");
            tabla.addCell("Fecha Venta");
            tabla.addCell("Estado");
            try {
                Connection cn = DAO.Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select cv.idCabeceraVenta as id, concat(c.nombre, ' ', c.apellido) as cliente, "
                                + "cv.valorPagar as total, cv.fechaVenta as fecha, cv.estado "
                                + "from tb_cabecera_venta as cv, tb_cliente as c "
                                + "where cv.idCliente = c.idCliente;");
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                    } while (rs.next());
                    documento.add(tabla);
                }
            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado");
        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }
    
}
