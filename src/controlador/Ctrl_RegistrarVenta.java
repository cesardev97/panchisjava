/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

//import DAO.Crud_GestionarVentas;

import DAO.Crud_RegistrarVenta;
import Vistas.FrmGestionarVentas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.CabeceraVenta;

/**
 *
 * @author Jean
 */
public class Ctrl_RegistrarVenta implements ActionListener{
    FrmGestionarVentas vista;
    Crud_RegistrarVenta crud;
        public Ctrl_RegistrarVenta(FrmGestionarVentas vista) {
        this.vista = vista;
        this.crud = new Crud_RegistrarVenta();
        // Agregar el ActionListener A los botones
//        Crud_RegistrarVenta.CargarTablaVentas();
//        this.vista.btn_actualizar.addActionListener(this);

    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btn_actualizar) {
            
        }
        
        
    }
    
            
            
}
