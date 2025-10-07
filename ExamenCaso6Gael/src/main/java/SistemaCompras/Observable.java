/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaCompras;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gael_
 */
public abstract class Observable {
    private List<Observador> observadores = new ArrayList<>();

    public void agregarObservador(Observador o) {
        observadores.add(o);
    }

    public void eliminarObservador(Observador o) {
        observadores.remove(o);
    }

    public void notificar(String evento, Object data) {
        for (Observador o : observadores) {
            o.actualizar(evento, data);
        }
    }
}