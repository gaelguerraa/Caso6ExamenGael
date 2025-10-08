/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaz;

import DTO.CompraDTO;
import DTO.ProductoDTO;
import DTO.TarjetaDTO;
import Entidades.Compra;
import Entidades.Producto;
import Entidades.Tarjeta;
import java.util.List;

/**
 *
 * @author gael_
 */
public interface IModelo {
    List<ProductoDTO> regresarProductos();
    ProductoDTO obtenerProductoPorNombre(String nombre);
    ProductoDTO convertirProductoADTO(Producto producto);
    Producto convertirProductoDTOAEntidad(ProductoDTO dto);
    Tarjeta convertirTarjetaDTOAEntidad(TarjetaDTO dto);
    CompraDTO convertirCompraACompraDTO(Compra compra);
    TarjetaDTO crearTarjeta(String banco, String numero, String ciudad);
    CompraDTO procesarCompra(List<ProductoDTO> productosCarrito, String banco, String numero, String ciudad);
    
}
