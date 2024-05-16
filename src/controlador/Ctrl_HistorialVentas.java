/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.Crud_GestionarVentas;
import DAO.Crud_HistorialVentas;
import Vistas.FrmHistorialVentas;
import conexion.Conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Jean
 */
public class Ctrl_HistorialVentas implements ActionListener {

    FrmHistorialVentas vista;
    Crud_HistorialVentas crud;

    public Ctrl_HistorialVentas(FrmHistorialVentas vista) {
        this.vista = vista;
        this.crud = new Crud_HistorialVentas();
        this.vista.btn_buscar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btn_buscar) {
                      if (FrmHistorialVentas.txt_buscar_idVenta.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Escribe el ID de la categoria a buscar");

            } else {
                String categoriaBuscar = FrmHistorialVentas.txt_buscar_idVenta.getText().trim();
                Connection cn = DAO.Conexion.conectar();
                String sql = "SELECT * FROM tb_cabecera_venta where idCabeceraVenta = '" + categoriaBuscar + "'";
                Statement st;
                try {
                    st = cn.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    if (rs.next()) {
                        
                        
//                      
                        FrmHistorialVentas.txt_total_pagar.setText(rs.getString("ValorPAgar"));
                        FrmHistorialVentas.txt_fecha.setText(rs.getString("fechaVenta"));
                        


                    } else {
                        JOptionPane.showMessageDialog(null, "¡ID de venta incorrecta o no encontrada!");
                    }
                    FrmHistorialVentas.txt_buscar_idVenta.setText("");
                    cn.close();
                } catch (SQLException a) { // como el "e" está ocupado se coloca "a
                    System.out.println("¡Error al buscar id!, " + a);
                }
            }
        }

    }
}
