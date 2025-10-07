/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaCompras;

import DTO.CompraDTO;
import DTO.ProductoDTO;
import DTO.TarjetaDTO;
import Entidades.Producto;
import Entidades.Tarjeta;
import Interfaz.IModelo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gael_
 */
public class Modelo extends Observable implements IModelo {

    
    @Override
    public List<ProductoDTO> regresarProductos() {
        List<Producto> productos = Producto.obtener10Productos();
        
        List<ProductoDTO> listaDTO = new ArrayList<>();

        for (Producto p : productos) {
            ProductoDTO dto = new ProductoDTO();
            dto.setNombre(p.getNombre());
            dto.setPrecio(p.getPrecio());
            listaDTO.add(dto);
        }
        return listaDTO;
    }
    
    @Override
    public ProductoDTO obtenerProductoPorNombre(String nombre) {
        List<Producto> productos = Producto.obtener10Productos();
        
        for (Producto producto : productos) {
        if (producto.getNombre().equalsIgnoreCase(nombre)) {
            return convertirProductoADTO(producto);
            }
        }
        return null;
    }
    
    
    
    @Override
    public ProductoDTO convertirProductoADTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        return dto;
    }


    @Override
    public TarjetaDTO crearTarjeta(String banco, String numero, String ciudad) {
        Tarjeta tarjeta = new Tarjeta(banco, numero, ciudad);
        TarjetaDTO dto = new TarjetaDTO();
        dto.setBanco(tarjeta.getBanco());
        dto.setNumero(tarjeta.getNumero());
        dto.setCiudad(tarjeta.getCiudad());
        return dto;
    }
    
    @Override
    public CompraDTO procesarCompra(List<ProductoDTO> productosCarrito, String banco, String numero, String ciudad) {
        TarjetaDTO tarjeta = crearTarjeta(banco, numero, ciudad);

        float total = 0;
        for (ProductoDTO p : productosCarrito) {
            total += p.getPrecio();
        }

        CompraDTO compra = new CompraDTO(productosCarrito, tarjeta, LocalDate.now(), total);
        
        notificar("COMPRA_REALIZADA", compra);
        return compra;
    }

 
    

}
