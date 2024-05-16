/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author JEAN
 */
public class Rol {

    private int idRol;
    private String rol;
    private int estado;
    // constructo vacia

    public Rol() {
        this.idRol = 0;
        this.rol = "";
        this.estado = 0;
    }
    
    // constructor sobrecargado

    public Rol(int idRol, String rol, int estado) {
        this.idRol = idRol;
        this.rol = rol;
        this.estado = estado;
    }
       // geat and set

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
}
