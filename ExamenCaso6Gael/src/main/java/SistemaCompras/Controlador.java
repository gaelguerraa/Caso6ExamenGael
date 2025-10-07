/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaCompras;

import DTO.CompraDTO;
import DTO.ProductoDTO;
import java.util.List;

/**
 *
 * @author gael_
 */
public class Controlador {
    
    Modelo modelo;

    public Controlador(Modelo modelo) {
        this.modelo = modelo;
    }
       
    public List<ProductoDTO> mostrarProductos(){
        return  modelo.regresarProductos();
    }
    
    public ProductoDTO buscarProductoPorNombre(String nombre){
        return modelo.obtenerProductoPorNombre(nombre);
    }
    
    public CompraDTO procesarCompra(List<ProductoDTO> productosCarrito, String banco, String numero, String ciudad) {
        CompraDTO compra = modelo.procesarCompra(productosCarrito, banco, numero, ciudad);
        return compra;
    }
    

}
