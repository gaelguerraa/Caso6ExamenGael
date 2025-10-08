/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaCompras;

import DTO.CompraDTO;
import DTO.ProductoDTO;
import DTO.TarjetaDTO;
import Entidades.Compra;
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
    public Producto convertirProductoDTOAEntidad(ProductoDTO dto){
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        return producto;
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
    public Tarjeta convertirTarjetaDTOAEntidad(TarjetaDTO dto) {
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setBanco(dto.getBanco());
        tarjeta.setNumero(dto.getNumero());
        tarjeta.setCiudad(dto.getCiudad());
        return tarjeta;
    }
    
    @Override
    public CompraDTO convertirCompraACompraDTO(Compra compra) {
        List<ProductoDTO> productosDTO = new ArrayList<>();
        for (Producto producto : compra.getProductos()) {
            productosDTO.add(convertirProductoADTO(producto));
        }

        Tarjeta tarjeta = compra.getTarjeta(); 
        TarjetaDTO dto = new TarjetaDTO();
        dto.setBanco(tarjeta.getBanco());
        dto.setCiudad(tarjeta.getCiudad());
        dto.setNumero(tarjeta.getNumero());

        CompraDTO compraDTO = new CompraDTO(
            productosDTO, 
            dto, 
            compra.getFechaCompra(),
            compra.getTotal()  
        );

        return compraDTO;
    }
    
    @Override
    public CompraDTO procesarCompra(List<ProductoDTO> productosCarrito, String banco, String numero, String ciudad) {
        TarjetaDTO tarjeta = crearTarjeta(banco, numero, ciudad);

        float total = 0;
        for (ProductoDTO p : productosCarrito) {
            total += p.getPrecio();
        }
        
        List<Producto> productosEntidad = new ArrayList<>();  
        for(ProductoDTO dto : productosCarrito){
            productosEntidad.add(convertirProductoDTOAEntidad(dto)); 
        }
        
        Tarjeta tarjetaEntidad = convertirTarjetaDTOAEntidad(tarjeta);
        
        Compra compra = new Compra(productosEntidad, tarjetaEntidad, LocalDate.now(), total);

        CompraDTO compraDTO = convertirCompraACompraDTO(compra);
        
        notificar("COMPRA_REALIZADA", compraDTO);
        return compraDTO;
    }

 
    

}
