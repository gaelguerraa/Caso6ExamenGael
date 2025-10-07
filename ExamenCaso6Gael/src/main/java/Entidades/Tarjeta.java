/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author gael_
 */
public class Tarjeta {
    private String banco;
    private String numero;
    private String ciudad;

    public Tarjeta(String banco, String numero, String ciudad) {
        this.banco = banco;
        this.numero = numero;
        this.ciudad = ciudad;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    public Tarjeta crearTarjeta(String banco, String numero, String ciudad){
        return new Tarjeta(banco, numero, ciudad);
    }

    @Override
    public String toString() {
        return "Tarjeta{" + "banco=" + banco + ", numero=" + numero + ", ciudad=" + ciudad + '}';
    }
    
    
}
