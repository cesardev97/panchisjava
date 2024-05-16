/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Jean
 */
public class Producto {
    //atributos
    private int idProducto;
    private String nombre;
    private int cantidad;
    private double precio;
    private String discripcion;
    private int IGV;
    private int idCategoria;
    private int estado;
    
    //cosntrucot vacio

    public Producto() {
        this.idProducto = 0;
        this.nombre = "";
        this.cantidad = 0;
        this.precio = 0.0;
        this.discripcion = "";
        this.IGV = 0;
        this.idCategoria = 0;
        this.estado = 0;
    }
    //constructor sobrecargado

    public Producto(int idProducto, String nombre, int cantidad, double precio, String discripcion, int IGV, int idCategoria, int estado) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.discripcion = discripcion;
        this.IGV = IGV;
        this.idCategoria = idCategoria;
        this.estado = estado;
    }
    
    //get and set

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDiscripcion() {
        return discripcion;
    }

    public void setDiscripcion(String discripcion) {
        this.discripcion = discripcion;
    }

    public int getIGV() {
        return IGV;
    }

    public void setIGV(int IGV) {
        this.IGV = IGV;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
}
