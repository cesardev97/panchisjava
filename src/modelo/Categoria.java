/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Jean
 */
public class Categoria {

    private int idCategoria;
    private String discripcion; // asi se guardo en la base de dato con error ortografico, pendiente corregirlo
    private int estado;

    // constructo vacia
    public Categoria() {
        this.idCategoria = 0;
        this.discripcion = "";
        this.estado = 0;
    }
// constructo sobrecargado

    public Categoria(int idCategoria, String discripcion, int estado) {
        this.idCategoria = idCategoria;
        this.discripcion = discripcion;
        this.estado = estado;
    }
    // geat and set

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDiscripcion() {
        return discripcion;
    }

    public void setDiscripcion(String discripcion) {
        this.discripcion = discripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
//        public Object[] RegistroCategoria(int numeracion){
//        Object[] fila={numeracion,idCategoria,discripcion,estado};
//        return fila;
//    }
}
