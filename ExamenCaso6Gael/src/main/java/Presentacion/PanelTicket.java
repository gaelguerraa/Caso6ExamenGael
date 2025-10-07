/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;

import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gael_
 */
 public class PanelTicket extends JPanel {
    
    public JLabel ponerBancoAqui;
    public JLabel ponerCiudadAqui;
    public JLabel ponerNumTarjetaAqui;
    public JScrollPane scrollpaneProductosComprados;
    public JTable tablaProductosComprados;
    public JLabel ponerTotalAqui;
    
    public PanelTicket() {
        initComponents();
    }
    
    private void initComponents() {
        // Inicializar componentes
        ponerBancoAqui = new JLabel();
        ponerCiudadAqui = new JLabel();
        ponerNumTarjetaAqui = new JLabel();
        scrollpaneProductosComprados = new JScrollPane();
        tablaProductosComprados = new JTable();
        ponerTotalAqui = new JLabel();
        
       
        
        
        // Configurar la tabla de productos comprados
        tablaProductosComprados.setModel(new DefaultTableModel(
            new Object [][] {
                // Datos vacíos inicialmente
            },
            new String [] {
                "producto y precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        scrollpaneProductosComprados.setViewportView(tablaProductosComprados);
        
        // Configurar el layout (GroupLayout como en el form original)
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(scrollpaneProductosComprados, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ponerTotalAqui))
                    .addComponent(ponerNumTarjetaAqui)
                    .addGroup(layout.createSequentialGroup()
//                        .addComponent(ponerBancoAqui, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
//                        .addGap(79, 79, 79)
//                        .addComponent(ponerCiudadAqui, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
                    //
                        .addComponent(ponerBancoAqui, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(ponerCiudadAqui, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    //
                .addContainerGap(87, Short.MAX_VALUE))
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(ponerBancoAqui)
                    .addComponent(ponerCiudadAqui))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ponerNumTarjetaAqui)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollpaneProductosComprados, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                    .addComponent(ponerTotalAqui))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
    
}
