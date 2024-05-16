/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Jean
 */
public class CabeceraVenta {
    private int idCabeceraVenta ;private int idCliente; private double ValorPagar;private String fechaVenta;private int estado;
    
    //contructor vacio

    public CabeceraVenta() {
        this.idCabeceraVenta = 0;
        this.idCliente = 0;
        this.ValorPagar = 0.0;
        this.fechaVenta = "";
        this.estado = 0;
    }
    //constructo sobrecargado

    public CabeceraVenta(int idCabeceraVenta, int idCliente, double ValorPagar, String fechaVenta, int estado) {
        this.idCabeceraVenta = idCabeceraVenta;
        this.idCliente = idCliente;
        this.ValorPagar = ValorPagar;
        this.fechaVenta = fechaVenta;
        this.estado = estado;
    }
    
    //getter and setter

    public int getIdCabeceraVenta() {
        return idCabeceraVenta;
    }

    public void setIdCabeceraVenta(int idCabeceraVenta) {
        this.idCabeceraVenta = idCabeceraVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getValorPagar() {
        return ValorPagar;
    }

    public void setValorPagar(double ValorPagar) {
        this.ValorPagar = ValorPagar;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    // metodo ToString

    @Override
    public String toString() {
        return "CabeceraVenta{" + "idCabeceraVenta=" + idCabeceraVenta + ", idCliente=" + idCliente + ", ValorPagar=" + ValorPagar + ", fechaVenta=" + fechaVenta + ", estado=" + estado + '}';
    }
    

}
