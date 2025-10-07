/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author gael_
 */
public class Compra {
    private List<Producto> productos;
    private Tarjeta tarjeta;
    private LocalDate fechaCompra;
    private float total;

    public Compra(List<Producto> productos, Tarjeta tarjeta, LocalDate fechaCompra, float total) {
        this.productos = productos;
        this.tarjeta = tarjeta;
        this.fechaCompra = fechaCompra;
        this.total = total;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    private static float calcularTotal(List<Producto> productos) {
        return (float) productos.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
    }
    
    public static Compra crearCompra(List<Producto> productos, Tarjeta tarjeta) {
        float total = calcularTotal(productos);
        LocalDate fechaCompra = LocalDate.now();

        return new Compra(productos, tarjeta, fechaCompra, total);
    }
    
    @Override
    public String toString() {
        return String.format("Compra{fecha=%s, total=%.2f, productos=%d}", 
            fechaCompra, total, productos.size());
    }

}
