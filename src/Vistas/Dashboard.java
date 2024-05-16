/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import DAO.Conexion;
import DAO.Crud_Usuario;
import static Vistas.Dashboard.Contenido;
import static Vistas.Dashboard.ShowPanel;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.JPanel;

/**
 *
 * @author Jean
 */
//<a href="https://www.flaticon.es/iconos-gratis/pizza" title="pizza iconos">Pizza iconos creados por Freepik - Flaticon</a>
public class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
    //singleton
    private static Dashboard instance;
    private int idRol;

    private Dashboard() {
        initComponents();
        this.idRol = 0; // Valor predeterminado del rol
        FrmMenu p0 = new FrmMenu();
        ShowPanel(p0);
        Contenido.removeAll();
        Contenido.add(p0, BorderLayout.CENTER);
        Contenido.revalidate();
        Contenido.repaint();
        this.setTitle("Menú Panchi's Pizza");
        
    }

    public static Dashboard getInstance() {
        if (instance == null) {
            instance = new Dashboard();
        }
        return instance;
    }

    public static void setIdRol(int idRol) {
        getInstance().idRol = idRol;
        getInstance().habilitarBotonesSegunRol();
    }

    private void habilitarBotonesSegunRol() {
        // Si el ID del rol es 1 (ADMIN), habilitar todos los botones
        // Si el ID del rol es 2 (CAJERO), deshabilitar botones de configuración, etc.
        if (idRol == 1) {

        } else if (idRol == 2) {
            jbtnUsuario.setEnabled(false);
            jbtnCategoria.setEnabled(false);
        } else if (idRol == 3) {
            jbtnUsuario.setEnabled(false);
            jbtnCategoria.setEnabled(false);
            jbtnCliente.setEnabled(false);
            jbtnFacturar.setEnabled(false);
            jbtnHistorial.setEnabled(false);
            jbtnProducto.setEnabled(false);
            jbtnReportes.setEnabled(false);
        }
    }

    //ICONO DEL SISTEMA
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imagenes/logo panchis 50 x50 px.png"));
        return retValue;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel();
        Header = new javax.swing.JPanel();
        Titulo_header = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Menu = new javax.swing.JPanel();
        jbtnUsuario = new javax.swing.JButton();
        jbtnSalir = new javax.swing.JButton();
        Separator2 = new javax.swing.JSeparator();
        Separator1 = new javax.swing.JSeparator();
        jbtnProducto = new javax.swing.JButton();
        Separator3 = new javax.swing.JSeparator();
        Separator4 = new javax.swing.JSeparator();
        jbtnCategoria = new javax.swing.JButton();
        Separator5 = new javax.swing.JSeparator();
        jbtnFacturar = new javax.swing.JButton();
        Separator6 = new javax.swing.JSeparator();
        jbtnReportes = new javax.swing.JButton();
        Separator7 = new javax.swing.JSeparator();
        Separator8 = new javax.swing.JSeparator();
        jbtnHistorial = new javax.swing.JButton();
        jbtnMenu = new javax.swing.JButton();
        Separator9 = new javax.swing.JSeparator();
        jbtnCliente = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Contenido = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Background.setBackground(new java.awt.Color(255, 255, 255));
        Background.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        Background.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Header.setBackground(new java.awt.Color(255, 204, 0));
        Header.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        Titulo_header.setBackground(new java.awt.Color(0, 0, 0));
        Titulo_header.setFont(new java.awt.Font("Segoe UI Black", 3, 24)); // NOI18N
        Titulo_header.setText("SISTEMA DE VENTA ");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo panchis 80x80 px.png"))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Pig (1).png"))); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(309, 309, 309)
                .addComponent(Titulo_header)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(Titulo_header)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderLayout.createSequentialGroup()
                .addGap(0, 9, Short.MAX_VALUE)
                .addComponent(jLabel1))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Menu.setBackground(new java.awt.Color(255, 204, 0));
        Menu.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        Menu.setForeground(new java.awt.Color(51, 255, 255));
        Menu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jbtnUsuario.setBackground(new java.awt.Color(0, 0, 0));
        jbtnUsuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbtnUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/profile-user.png"))); // NOI18N
        jbtnUsuario.setText("USUARIO");
        jbtnUsuario.setBorder(null);
        jbtnUsuario.setBorderPainted(false);
        jbtnUsuario.setContentAreaFilled(false);
        jbtnUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnUsuario.setFocusPainted(false);
        jbtnUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnUsuarioActionPerformed(evt);
            }
        });

        jbtnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbtnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/power.png"))); // NOI18N
        jbtnSalir.setText("CERRAR SESION");
        jbtnSalir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jbtnSalir.setContentAreaFilled(false);
        jbtnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnSalir.setFocusPainted(false);
        jbtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSalirActionPerformed(evt);
            }
        });

        Separator2.setBackground(new java.awt.Color(255, 204, 0));
        Separator2.setForeground(new java.awt.Color(0, 0, 0));

        Separator1.setBackground(new java.awt.Color(255, 204, 0));
        Separator1.setForeground(new java.awt.Color(0, 0, 0));

        jbtnProducto.setBackground(new java.awt.Color(0, 0, 0));
        jbtnProducto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbtnProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/box.png"))); // NOI18N
        jbtnProducto.setText("PRODUCTO");
        jbtnProducto.setBorder(null);
        jbtnProducto.setBorderPainted(false);
        jbtnProducto.setContentAreaFilled(false);
        jbtnProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnProducto.setFocusPainted(false);
        jbtnProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnProductoActionPerformed(evt);
            }
        });

        Separator3.setBackground(new java.awt.Color(255, 204, 0));
        Separator3.setForeground(new java.awt.Color(0, 0, 0));

        Separator4.setBackground(new java.awt.Color(255, 204, 0));
        Separator4.setForeground(new java.awt.Color(0, 0, 0));

        jbtnCategoria.setBackground(new java.awt.Color(0, 0, 0));
        jbtnCategoria.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbtnCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/categorization.png"))); // NOI18N
        jbtnCategoria.setText("CATEGORIA");
        jbtnCategoria.setBorder(null);
        jbtnCategoria.setBorderPainted(false);
        jbtnCategoria.setContentAreaFilled(false);
        jbtnCategoria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnCategoria.setFocusPainted(false);
        jbtnCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCategoriaActionPerformed(evt);
            }
        });

        Separator5.setBackground(new java.awt.Color(255, 204, 0));
        Separator5.setForeground(new java.awt.Color(0, 0, 0));

        jbtnFacturar.setBackground(new java.awt.Color(0, 0, 0));
        jbtnFacturar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbtnFacturar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buy-button.png"))); // NOI18N
        jbtnFacturar.setText("FACTURAR");
        jbtnFacturar.setBorder(null);
        jbtnFacturar.setBorderPainted(false);
        jbtnFacturar.setContentAreaFilled(false);
        jbtnFacturar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnFacturar.setFocusPainted(false);
        jbtnFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnFacturarActionPerformed(evt);
            }
        });

        Separator6.setBackground(new java.awt.Color(255, 204, 0));
        Separator6.setForeground(new java.awt.Color(0, 0, 0));

        jbtnReportes.setBackground(new java.awt.Color(0, 0, 0));
        jbtnReportes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbtnReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/analytics.png"))); // NOI18N
        jbtnReportes.setText("REPORTES");
        jbtnReportes.setBorder(null);
        jbtnReportes.setBorderPainted(false);
        jbtnReportes.setContentAreaFilled(false);
        jbtnReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnReportes.setFocusPainted(false);
        jbtnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReportesActionPerformed(evt);
            }
        });

        Separator7.setBackground(new java.awt.Color(255, 204, 0));
        Separator7.setForeground(new java.awt.Color(0, 0, 0));

        Separator8.setBackground(new java.awt.Color(255, 204, 0));
        Separator8.setForeground(new java.awt.Color(0, 0, 0));

        jbtnHistorial.setBackground(new java.awt.Color(0, 0, 0));
        jbtnHistorial.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbtnHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/refresh.png"))); // NOI18N
        jbtnHistorial.setText("HISTORIAL");
        jbtnHistorial.setBorder(null);
        jbtnHistorial.setBorderPainted(false);
        jbtnHistorial.setContentAreaFilled(false);
        jbtnHistorial.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnHistorial.setFocusPainted(false);
        jbtnHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnHistorialActionPerformed(evt);
            }
        });

        jbtnMenu.setBackground(new java.awt.Color(0, 0, 0));
        jbtnMenu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbtnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/menu.png"))); // NOI18N
        jbtnMenu.setText("MENU DEL DÍA");
        jbtnMenu.setBorder(null);
        jbtnMenu.setBorderPainted(false);
        jbtnMenu.setContentAreaFilled(false);
        jbtnMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnMenu.setFocusPainted(false);
        jbtnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnMenuActionPerformed(evt);
            }
        });

        Separator9.setBackground(new java.awt.Color(255, 204, 0));
        Separator9.setForeground(new java.awt.Color(0, 0, 0));
        Separator9.setEnabled(false);

        jbtnCliente.setBackground(new java.awt.Color(0, 0, 0));
        jbtnCliente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbtnCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/costumer.png"))); // NOI18N
        jbtnCliente.setText("CLIENTE");
        jbtnCliente.setBorder(null);
        jbtnCliente.setBorderPainted(false);
        jbtnCliente.setContentAreaFilled(false);
        jbtnCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnCliente.setFocusPainted(false);
        jbtnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnClienteActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 8)); // NOI18N
        jLabel3.setText("Versión 2.0.17.04.24");

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jbtnProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jbtnCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MenuLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Separator1)
                                    .addComponent(Separator2)
                                    .addComponent(jbtnUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))
                            .addGroup(MenuLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(MenuLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jbtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(Separator9)
                                        .addComponent(Separator8, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Separator7, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Separator6, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Separator5, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Separator4, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Separator3, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jbtnCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbtnFacturar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbtnReportes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbtnHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbtnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)))))))
                .addContainerGap(11, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Separator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(Separator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Separator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Separator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Separator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnFacturar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Separator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Separator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Separator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Separator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        Contenido.setBackground(new java.awt.Color(204, 204, 204));
        Contenido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Contenido.setPreferredSize(new java.awt.Dimension(1010, 500));

        javax.swing.GroupLayout ContenidoLayout = new javax.swing.GroupLayout(Contenido);
        Contenido.setLayout(ContenidoLayout);
        ContenidoLayout.setHorizontalGroup(
            ContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1008, Short.MAX_VALUE)
        );
        ContenidoLayout.setVerticalGroup(
            ContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(Header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackgroundLayout.createSequentialGroup()
                .addGap(0, 200, Short.MAX_VALUE)
                .addComponent(Contenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Contenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUsuarioActionPerformed
        FrmUsuario p1 = new FrmUsuario();
        ShowPanel(p1);
        registrarAccion("Botón de Usuario presionado");

    }//GEN-LAST:event_jbtnUsuarioActionPerformed

    private void jbtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSalirActionPerformed
        // TODO add your handling code here:
        registrarAccion("Botón de cerrar sesión presionado");
        System.exit(0);

// Registrar la acción de presionar el botón Salir
//regresa al login pero ESTO PROVOCA QUE AL LOGEARSE nuevamente CON OTRO ROL SE QUEDE CON LAS RESTRICIONES DEL ULTIMO USSUARIO
//        registrarAccion("Botón Salir presionado");
//
//        // Cerrar la ventana actual
//        this.dispose();
//
//        // Abrir una nueva ventana de inicio de sesión
//        Login login = new Login();
//        login.setVisible(true);


    }//GEN-LAST:event_jbtnSalirActionPerformed

    private void jbtnProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnProductoActionPerformed
        // TODO add your handling code here:
        FrmProducto p2 = new FrmProducto();
        ShowPanel(p2);
        registrarAccion("Botón de Producto presionado");
    }//GEN-LAST:event_jbtnProductoActionPerformed

    private void jbtnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnClienteActionPerformed
        FrmCliente p3 = new FrmCliente();
        ShowPanel(p3);
        registrarAccion("Botón de Cliente presionado");
    }//GEN-LAST:event_jbtnClienteActionPerformed

    private void jbtnCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCategoriaActionPerformed
        // TODO add your handling code here:
        FrmCategoria p4 = new FrmCategoria();
        ShowPanel(p4);
        registrarAccion("Botón de Categoria presionado");
    }//GEN-LAST:event_jbtnCategoriaActionPerformed

    private void jbtnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnFacturarActionPerformed
        // TODO add your handling code here:
        FrmFacturacion p5 = new FrmFacturacion();
        ShowPanel(p5);
        registrarAccion("Botón de Facturación presionado");
    }//GEN-LAST:event_jbtnFacturarActionPerformed

    private void jbtnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReportesActionPerformed
        // TODO add your handling code here:
        FrmReportes p6 = new FrmReportes();
        ShowPanel(p6);
        registrarAccion("Botón de Reportes presionado");
    }//GEN-LAST:event_jbtnReportesActionPerformed

    private void jbtnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnHistorialActionPerformed
        // TODO add your handling code here:}}
        FrmHistorialVentas p7 = new FrmHistorialVentas();
        ShowPanel(p7);
        registrarAccion("Botón Historial presionado");
    }//GEN-LAST:event_jbtnHistorialActionPerformed

    private void jbtnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnMenuActionPerformed
        // TODO add your handling code here:
        FrmMenu p8 = new FrmMenu();
        ShowPanel(p8);
        registrarAccion("Botón Menú presionado");
    }//GEN-LAST:event_jbtnMenuActionPerformed

    /**
     * @param args the command line arguments
     */
//    esto se reemplazará con el de abajo 
    public static void main(String args[]) {
        // Invocar la ventana del panel de control
        java.awt.EventQueue.invokeLater(() -> {
            new Dashboard().setVisible(true);
        });
    }
//    public static void main(String args[]) {
//        // Invocar la ventana del panel de control
//        java.awt.EventQueue.invokeLater(() -> {
//            new Dashboard(1).setVisible(true); // Establecer un ID de rol predeterminado
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    public static javax.swing.JPanel Contenido;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel Menu;
    private javax.swing.JSeparator Separator1;
    private javax.swing.JSeparator Separator2;
    private javax.swing.JSeparator Separator3;
    private javax.swing.JSeparator Separator4;
    private javax.swing.JSeparator Separator5;
    private javax.swing.JSeparator Separator6;
    private javax.swing.JSeparator Separator7;
    private javax.swing.JSeparator Separator8;
    private javax.swing.JSeparator Separator9;
    private javax.swing.JLabel Titulo_header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jbtnCategoria;
    private javax.swing.JButton jbtnCliente;
    private javax.swing.JButton jbtnFacturar;
    private javax.swing.JButton jbtnHistorial;
    private javax.swing.JButton jbtnMenu;
    private javax.swing.JButton jbtnProducto;
    private javax.swing.JButton jbtnReportes;
    private javax.swing.JButton jbtnSalir;
    private javax.swing.JButton jbtnUsuario;
    // End of variables declaration//GEN-END:variables

    //nos sirve para cambiar entre jpanels cuando presioanmos un boton en el dashboardd
    public static void ShowPanel(JPanel p) { //borre el static

        p.setSize(1010, 500);// forzamos a darle el tamaño para evitar errores
        p.setLocation(0, 0); //centra
        Contenido.removeAll();
        Contenido.add(p, BorderLayout.CENTER);
        Contenido.revalidate();
        Contenido.repaint();

    }
    // Método para registrar la acción en la tabla de auditoría

    private void registrarAccion(String accion) {
        try {
            Connection con = Conexion.conectar();
            String sql = "INSERT INTO tb_auditoria (idUsuario, fecha_conexion, hora_conexion, accion_realizada, ip_computadora) "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Crud_Usuario.idUsuario); // Suponiendo que ya tienes el idUsuario disponible
            ps.setTimestamp(2, new Timestamp(new Date().getTime())); // Fecha actual
            ps.setTimestamp(3, new Timestamp(new Date().getTime())); // Hora actual
            ps.setString(4, accion); // Acción realizada
            ps.setString(5, Crud_Usuario.obtenerDireccionIP()); // IP de la computadora
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al registrar la acción en la auditoría: " + e);
        }
    }

}
