/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author gael_
 */
public class ProductoDTO {
    private String nombre;
    private float precio;


    public ProductoDTO() {
    }
    
    
    public ProductoDTO(String nombre, float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
     
   public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
}
