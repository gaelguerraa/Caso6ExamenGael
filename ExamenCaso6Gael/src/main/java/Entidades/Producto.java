/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author gael_
 */
public class Producto {
    private String nombre;
    private float precio;

    public Producto(String nombre, float precio) {
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
    
    public static List<Producto> obtener10Productos() {
        return Arrays.asList(
            new Producto("MacBook Air", 14999.99f),
            new Producto("Samsung S24", 8500.00f),
            new Producto("iPad Air", 5999.99f),
            new Producto("Bose QuietComfort", 3499.00f),
            new Producto("Cable HDMI", 150.99f),
            new Producto("Cargador Rápido", 290.50f),
            new Producto("Funda Protectora", 199.99f),
            new Producto("Power Bank 20000mAh", 450.75f),
            new Producto("Adaptador USB-C", 220.50f),
            new Producto("Soporte para Laptop", 350.00f)
        );
    }
    
}
