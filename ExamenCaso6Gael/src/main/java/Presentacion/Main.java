/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;

import SistemaCompras.Controlador;
import SistemaCompras.Modelo;

/**
 *
 * @author gael_
 */
public class Main {
    public static void main(String[] args) {
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(modelo);
        Vista vista =new Vista(controlador);
        
        modelo.agregarObservador(vista);
        vista.setVisible(true);
      
    }
    
}
