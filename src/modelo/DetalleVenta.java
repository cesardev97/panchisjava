/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Jean
 */
public class DetalleVenta {
    //declaracion
    private int idDetalleVenta ;
    private int idCabeceraVenta;
    private int idProducto;
    //extra (no esta en la base de datos, usaremos apra no hacer tantas consultas)
    private String nombre; //nombre dle producto
    private int cantidad;
    private double precioUnitario ;
    private double subtotal ;// total a pagar
    private double descuento ;
    private double IGV ;
    private double totalPagar;
    private int estado;
    
    //cosntrdsuto vacio

    public DetalleVenta() {
        this.idDetalleVenta = 0;
        this.idCabeceraVenta = 0;
        this.idProducto = 0;
        this.nombre = "";
        this.cantidad = 0;
        this.precioUnitario = 0.0;
        this.subtotal = 0.0;
        this.descuento = 0.0;
        this.IGV = 0.0;
        this.totalPagar = 0.0;
        this.estado = 0;
    }
    //cosntrducto sobrecargado
      public DetalleVenta(int idDetalleVenta, int idCabeceraVenta, int idProducto, String nombre, int cantidad, double precioUnitario, double subtotal, double descuento, double IGV, double totalPagar, int estado) {
        this.idDetalleVenta = idDetalleVenta;
        this.idCabeceraVenta = idCabeceraVenta;
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.IGV = IGV;
        this.totalPagar = totalPagar;
        this.estado = estado;
    }
   
    // G and S

    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public int getIdCabeceraVenta() {
        return idCabeceraVenta;
    }

    public void setIdCabeceraVenta(int idCabeceraVenta) {
        this.idCabeceraVenta = idCabeceraVenta;
    }

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

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getIGV() {
        return IGV;
    }

    public void setIGV(double IGV) {
        this.IGV = IGV;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }


    //metodoString

    @Override
    public String toString() {
        return "DetalleVenta{" + "idDetalleVenta=" + idDetalleVenta + ", idCabeceraVenta=" + idCabeceraVenta + ", idProducto=" + idProducto + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + ", subtotal=" + subtotal + ", descuento=" + descuento + ", IGV=" + IGV + ", totalPagar=" + totalPagar + ", estado=" + estado + '}';
    }


    
    

}
