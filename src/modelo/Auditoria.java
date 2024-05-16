/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author JEAN
 */
public class Auditoria {
    private int id;
    private int idUsuario;
    private String fecha_conexion;
    private String hora_conexion;
    private String accion_realizada;
    private String ip_computadora;

    public Auditoria(int id, int idUsuario, String fecha_conexion, String hora_conexion, String accion_realizada, String ip_computadora) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.fecha_conexion = fecha_conexion;
        this.hora_conexion = hora_conexion;
        this.accion_realizada = accion_realizada;
        this.ip_computadora = ip_computadora;
    }
    
    
    
}
