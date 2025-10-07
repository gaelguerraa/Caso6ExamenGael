/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author gael_
 */
public class CompraDTO {
    private List<ProductoDTO> productos;
    private TarjetaDTO tarjeta;
    private LocalDate fechaCompra;
    private float total;

    public CompraDTO() {
    }

    public CompraDTO(List<ProductoDTO> productos, TarjetaDTO tarjeta) {
        this.productos = productos;
        this.tarjeta = tarjeta;
    }
    
        public CompraDTO(List<ProductoDTO> productos, TarjetaDTO tarjeta, LocalDate fechaCompra, float total) {
        this.productos = productos;
        this.tarjeta = tarjeta;
        this.fechaCompra = fechaCompra;
        this.total = total;
    }

    public List<ProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDTO> productos) {
        this.productos = productos;
    }

    public TarjetaDTO getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(TarjetaDTO tarjeta) {
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
}
