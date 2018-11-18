package gui;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.table.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class ProductList extends Panel{

    public ProductList(JFrame frame){
        this.window = frame;
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(40,30,30,30));

        JLabel textTitle = new JLabel("Lista de productos");
        JButton botonCancelarCompra = new JButton("Cancelar compra");
        JButton botonCerrarCompra = new JButton("Cerrar compra");
        JButton botonAgregarProducto = new JButton("Agregar producto");
        String colNames[] = {"Producto","Código","Cantidad","Precio"};
        String data[][] = {};
        
        //Avoid for the table is editable
        TableModel model = new DefaultTableModel(data, colNames){

            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        JTable productList = new JTable(model);
        productList.setFocusable(false);
        productList.setRowHeight(30);
        productList.getTableHeader().setReorderingAllowed(false);
                
        //Center cells text
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i=0; i < productList.getColumnCount(); i++){
            productList.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            productList.getColumnModel().getColumn(i).setResizable(false);
        }

        textTitle.setFont(textTitle.getFont().deriveFont(22.0f));
        JScrollPane sp = new JScrollPane(productList);

        sp.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(30,0,40,0), new EtchedBorder()));
        sp.getViewport().setBackground(Color.WHITE);

        GridBagConstraints components = new GridBagConstraints();
        components.gridx = 0;
        components.gridy = 0;
        components.gridwidth = 3;
        components.gridheight = 1;
        components.weightx = 1.0;
        components.anchor = GridBagConstraints.WEST;
        panel.add(textTitle, components);
        components.anchor = GridBagConstraints.CENTER;

        components.gridx = 0;
        components.gridy = 1;
        components.gridwidth = 3;
        components.gridheight = 1;
        components.weighty = 1.0;
        components.fill = GridBagConstraints.BOTH;
        panel.add(sp, components);
        components.weighty = 0.0;
        components.fill = GridBagConstraints.NONE;

        components.gridx = 0;
        components.gridy = 2;
        components.gridwidth = 1;
        components.gridheight = 1;
        components.anchor = GridBagConstraints.WEST;
        panel.add(botonAgregarProducto, components);

        components.gridx = 1;
        components.gridy = 2;
        components.gridwidth = 1;
        components.gridheight = 1;
        components.anchor = GridBagConstraints.CENTER;
        panel.add(botonCerrarCompra, components);

        components.gridx = 2;
        components.gridy = 2;
        components.gridwidth = 1;
        components.gridheight = 1;
        components.anchor = GridBagConstraints.EAST;
        panel.add(botonCancelarCompra, components);
    }

}