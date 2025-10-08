/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import DTO.CompraDTO;
import DTO.ProductoDTO;
import SistemaCompras.Controlador;
import SistemaCompras.Observador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gael_
 */
public class Vista extends javax.swing.JFrame implements Observador {

    PanelTarjeta panelTarjeta;
    PanelTicket panelTicket;
    Controlador controlador;
    public JButton botonPagar;
    public JButton botonRegresar;
    
    /**
     * Creates new form Vista
     */
    public Vista(Controlador controlador) {
        initComponents();
        this.controlador=controlador;
        setSize(900, 530);
        setTitle("Amazon fake");
        setLocationRelativeTo(null); 
        llenarTablaProductos();
        crearBotones();
        crearPanelTarjeta();
        
    }
    
    @Override
    public void actualizar(String evento, Object data) {
        if (evento.equals("COMPRA_REALIZADA")) {
           crearPanelTicket(); 
           CompraDTO compra = (CompraDTO) data;

           // Mostrar la factura con los datos
           mostrarDatosCompra(compra); 
           mostrarFactura();
    }
    }
    
    public void llenarTablaProductos(){
        List<ProductoDTO> productos = controlador.mostrarProductos();

        DefaultTableModel modeloTabla = (DefaultTableModel) tablaProductos.getModel();
        modeloTabla.setRowCount(0);
        
        for (ProductoDTO p : productos) {
            String precioFormateado = String.format("$%.2f", p.getPrecio());
            modeloTabla.addRow(new Object[]{p.getNombre(), precioFormateado});
        }
    }
    
   public void añadirProductoACarrito() {
        int filaSeleccionada = tablaProductos.getSelectedRow();

        if (filaSeleccionada >= 0) {
            String nombre = tablaProductos.getValueAt(filaSeleccionada, 0).toString(); 
            ProductoDTO productoSeleccionado = controlador.buscarProductoPorNombre(nombre); 

            if (productoSeleccionado != null) { 
                DefaultTableModel modeloCarrito = (DefaultTableModel) tablaCarrito.getModel();
                String precioFormateado = String.format("$%.2f", productoSeleccionado.getPrecio());
                modeloCarrito.addRow(new Object[]{ 
                    productoSeleccionado.getNombre(), 
                    precioFormateado
                });
            } 
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un producto primero"); 
        }
    }
    
    public List<ProductoDTO> obtenerProductosDTODeTablaCarrito() {
        List<ProductoDTO> productosDTO = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) tablaCarrito.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            String nombreProducto = model.getValueAt(i, 0).toString(); // Asumiendo que el nombre está en la columna 0
            ProductoDTO productoDTO = controlador.buscarProductoPorNombre(nombreProducto);

            if (productoDTO != null) {
                productosDTO.add(productoDTO);
            }
        }

        return productosDTO;
    }
    
    public String formatearNumeroTarjeta(String numero) {
        if(numero == null) return "";
        numero = numero.replaceAll("\\D", ""); 
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < numero.length(); i++) {
            sb.append(numero.charAt(i));
            if((i + 1) % 4 == 0 && i != numero.length() - 1) {
                sb.append("-");
            }
        }
        return sb.toString();
    }

    public void mostrarDatosCompra(CompraDTO compra) {
        if (compra == null) return;

        // Mostrar datos de la tarjeta
        if (compra.getTarjeta() != null) {
            panelTicket.ponerBancoAqui.setText("Banco: " + compra.getTarjeta().getBanco());
            panelTicket.ponerCiudadAqui.setText("Ciudad: " + compra.getTarjeta().getCiudad());
            panelTicket.ponerNumTarjetaAqui.setText("Num Tarjeta: " 
        + formatearNumeroTarjeta(compra.getTarjeta().getNumero()));
        } else {
            panelTicket.ponerBancoAqui.setText("Banco: -");
            panelTicket.ponerCiudadAqui.setText("Ciudad: -");
            panelTicket.ponerNumTarjetaAqui.setText("Num Tarjeta: -");
        }

        // Mostrar total
        panelTicket.ponerTotalAqui.setText(String.format("Total: $%.2f", compra.getTotal()));

        // Llenar la tabla con los productos comprados
        DefaultTableModel modelo = (DefaultTableModel) panelTicket.tablaProductosComprados.getModel();
        modelo.setRowCount(0); 

        if (compra.getProductos() != null && !compra.getProductos().isEmpty()) {
            for (ProductoDTO producto : compra.getProductos()) {
                String texto = producto.getNombre() + " - $" + String.format("%.2f", producto.getPrecio());
                modelo.addRow(new Object[]{texto});
            }
        } else {
            modelo.addRow(new Object[]{"(Sin productos)"});
        }
    }

    
    private void crearPanelTarjeta(){
        if(panelTarjeta == null){
            panelTarjeta = new PanelTarjeta();
            panelTarjeta.setBounds(380, 310, 380, 110);
            PanelFondo.add(panelTarjeta);
        }
    }
    
    private void crearPanelTicket(){
        if(panelTicket == null){
            panelTicket = new PanelTicket();
            panelTicket.setBounds(380, 280, 400, 180);
            PanelFondo.add(panelTicket);
        }
    }
    
     private void crearBotones() {
        // Botón Pagar
        botonPagar = new JButton("Pagar");
        botonPagar.setBounds(380, 450, 118, 18);
        PanelFondo.add(botonPagar);
        
        botonPagar.addActionListener(e -> {
            if(!panelTarjeta.validarCampos()) {
            JOptionPane.showMessageDialog(this, "Revisa los datos de la tarjeta. Todo debe ser correcto.");
            return;
        }

        List<ProductoDTO> productosCarrito = obtenerProductosDTODeTablaCarrito();
        
        if(productosCarrito.isEmpty()){
            JOptionPane.showMessageDialog(this, "Agrega al menos un producto al carrito.");
            return;
        }

        String banco = panelTarjeta.getBanco();
        String numero = panelTarjeta.getNumeroTarjeta();
        String ciudad = panelTarjeta.getCiudad();

        CompraDTO compra = controlador.procesarCompra(productosCarrito, banco, numero, ciudad);

    });

        botonRegresar = new JButton("Regresar");
        botonRegresar.setBounds(380, 450, 118, 18); 
        PanelFondo.add(botonRegresar);
        botonRegresar.addActionListener(e -> {

        borrarRegistros();
        mostrarIngresoTarjeta();
    });

        // Estado inicial: Pagar visible, Regresar invisible
        botonPagar.setVisible(true);

    }
     
    public void borrarRegistros(){
        DefaultTableModel modelo = (DefaultTableModel) tablaCarrito.getModel();
        modelo.setRowCount(0);
        
        panelTarjeta.txtBanco.setText("");
        panelTarjeta.txtCiudad.setText("");
        panelTarjeta.txtNumeroTarjeta.setText("");
    } 
     
     
     
    public void mostrarIngresoTarjeta() {
        panelTarjeta.setVisible(true);
        botonPagar.setVisible(true);
        botonAgregarProducto.setEnabled(true);

        panelTicket.setVisible(false);
        botonRegresar.setVisible(false);
    }

    public void mostrarFactura() {
        panelTarjeta.setVisible(false);
        botonPagar.setVisible(false);
        botonAgregarProducto.setEnabled(false);

        panelTicket.setVisible(true);
        botonRegresar.setVisible(true);
    } 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelFondo = new javax.swing.JPanel();
        PanelCabeza = new javax.swing.JPanel();
        Titulo = new javax.swing.JLabel();
        labelProductos = new javax.swing.JLabel();
        labelCarritos = new javax.swing.JLabel();
        paneProductos = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        botonAgregarProducto = new javax.swing.JButton();
        paneCarrito = new javax.swing.JScrollPane();
        tablaCarrito = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelFondo.setBackground(new java.awt.Color(141, 164, 165));

        PanelCabeza.setBackground(new java.awt.Color(67, 112, 119));

        Titulo.setFont(new java.awt.Font("Balls of Bastille", 1, 24)); // NOI18N
        Titulo.setForeground(new java.awt.Color(255, 255, 255));
        Titulo.setText("Amazon");

        javax.swing.GroupLayout PanelCabezaLayout = new javax.swing.GroupLayout(PanelCabeza);
        PanelCabeza.setLayout(PanelCabezaLayout);
        PanelCabezaLayout.setHorizontalGroup(
            PanelCabezaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCabezaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(Titulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelCabezaLayout.setVerticalGroup(
            PanelCabezaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCabezaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(Titulo)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        labelProductos.setBackground(new java.awt.Color(255, 255, 255));
        labelProductos.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        labelProductos.setText("Lista de productos");

        labelCarritos.setBackground(new java.awt.Color(255, 255, 255));
        labelCarritos.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        labelCarritos.setText("Carrito de compras");

        tablaProductos.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        paneProductos.setViewportView(tablaProductos);

        botonAgregarProducto.setBackground(new java.awt.Color(0, 255, 255));
        botonAgregarProducto.setFont(new java.awt.Font("SF Sports Night NS Upright", 0, 12)); // NOI18N
        botonAgregarProducto.setText("Argegar Producto");
        botonAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarProductoActionPerformed(evt);
            }
        });

        tablaCarrito.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        tablaCarrito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        paneCarrito.setViewportView(tablaCarrito);

        javax.swing.GroupLayout PanelFondoLayout = new javax.swing.GroupLayout(PanelFondo);
        PanelFondo.setLayout(PanelFondoLayout);
        PanelFondoLayout.setHorizontalGroup(
            PanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelCabeza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelFondoLayout.createSequentialGroup()
                .addGroup(PanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelFondoLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(PanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelFondoLayout.createSequentialGroup()
                                .addComponent(paneProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)
                                .addComponent(paneCarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelFondoLayout.createSequentialGroup()
                                .addComponent(labelProductos)
                                .addGap(253, 253, 253)
                                .addComponent(labelCarritos))))
                    .addGroup(PanelFondoLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(botonAgregarProducto)))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        PanelFondoLayout.setVerticalGroup(
            PanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelFondoLayout.createSequentialGroup()
                .addComponent(PanelCabeza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelProductos)
                    .addComponent(labelCarritos))
                .addGap(18, 18, 18)
                .addGroup(PanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelFondoLayout.createSequentialGroup()
                        .addComponent(paneProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelFondoLayout.createSequentialGroup()
                        .addComponent(paneCarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(195, 195, 195)))
                .addComponent(botonAgregarProducto)
                .addGap(0, 67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarProductoActionPerformed
        añadirProductoACarrito();
    }//GEN-LAST:event_botonAgregarProductoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelCabeza;
    private javax.swing.JPanel PanelFondo;
    private javax.swing.JLabel Titulo;
    public javax.swing.JButton botonAgregarProducto;
    private javax.swing.JLabel labelCarritos;
    private javax.swing.JLabel labelProductos;
    public javax.swing.JScrollPane paneCarrito;
    public javax.swing.JScrollPane paneProductos;
    public javax.swing.JTable tablaCarrito;
    public javax.swing.JTable tablaProductos;
    // End of variables declaration//GEN-END:variables

    
}
