/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author gael_
 */
public class PanelTarjeta extends JPanel {
    
    public JLabel labelBanco;
    public JTextField txtBanco;
    public JLabel labelCiudad;
    public JTextField txtCiudad;
    public JLabel labelNumeroTarjeta;
    public JTextField txtNumeroTarjeta;
    
    public PanelTarjeta() {
        initComponents();
    }
    
    private void initComponents() {
        // Configuración del layout - GridLayout simple
        setLayout(new GridLayout(3, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Inicializar componentes
        labelBanco = new JLabel("Banco:");
        txtBanco = new JTextField();
        
        labelCiudad = new JLabel("Ciudad:");
        txtCiudad = new JTextField();
        
        labelNumeroTarjeta = new JLabel("No. Tarjeta:");
        txtNumeroTarjeta = new JTextField();
        
        // Agregar componentes directamente al panel
        add(labelBanco);
        add(txtBanco);
        add(labelCiudad);
        add(txtCiudad);
        add(labelNumeroTarjeta);
        add(txtNumeroTarjeta);
        
        // Action listener
        txtBanco.addActionListener(evt -> txtBancoActionPerformed(evt));
    }
    
    private void txtBancoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    
    // MÉTODOS PARA OBTENER DATOS - FUNCIONAN IGUAL
    public String getBanco() {
        return txtBanco.getText().trim();
    }
    
    public String getCiudad() {
        return txtCiudad.getText().trim();
    }
    
    public String getNumeroTarjeta() {
        return txtNumeroTarjeta.getText().trim();
    }
    
    // Métodos para establecer datos
    public void setBanco(String banco) {
        txtBanco.setText(banco);
    }
    
    public void setCiudad(String ciudad) {
        txtCiudad.setText(ciudad);
    }
    
    public void setNumeroTarjeta(String numeroTarjeta) {
        txtNumeroTarjeta.setText(numeroTarjeta);
    }
    
    // Método para limpiar todos los campos
    public void limpiarCampos() {
        txtBanco.setText("");
        txtCiudad.setText("");
        txtNumeroTarjeta.setText("");
    }
    
    // Método para validar campos
    public boolean validarCampos() {
        if(getBanco().isEmpty() || getCiudad().isEmpty() || getNumeroTarjeta().isEmpty()) {
            return false;
        }
        if(!getNumeroTarjeta().matches("\\d+")) { // solo dígitos
            return false;
        }
        if(getNumeroTarjeta().length() != 16) {
            return false;
        }
        if(getBanco().length() > 26) { 
            return false;
        }
        return true;
    }
    }