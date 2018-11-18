package gui;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.table.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class ShoppingListCashier extends Panel{

    public ShoppingListCashier(JFrame frame){
        this.window = frame;
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(40,30,30,30));

        JLabel textTitle = new JLabel("Lista de compras");
        JButton botonVolver = new JButton("Volver");
        JButton botonOpenShop = new JButton("Abrir compra");
        String colNames[] = {"ID de Compra","Fecha/Hora"};
        String data[][] = {{"00000000","18-11-2018 13:25"}, {"00000001","20-11-2018 17:41"}};
        
        //Avoid for the table is editable
        TableModel model = new DefaultTableModel(data, colNames){

            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        JTable shoppingList = new JTable(model);
        shoppingList.setFocusable(false);
        shoppingList.setRowSelectionAllowed(false);
        shoppingList.setRowHeight(30);
        shoppingList.getTableHeader().setReorderingAllowed(false);
                
        //Center cells text
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i=0; i < shoppingList.getColumnCount(); i++){
            shoppingList.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            shoppingList.getColumnModel().getColumn(i).setResizable(false);
        }

        textTitle.setFont(textTitle.getFont().deriveFont(22.0f));    
        JScrollPane sp = new JScrollPane(shoppingList);

        sp.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(30,0,40,0), new EtchedBorder()));
        sp.getViewport().setBackground(Color.WHITE);

        GridBagConstraints components = new GridBagConstraints();
        components.gridx = 0;
        components.gridy = 0;
        components.gridwidth = 2;
        components.gridheight = 1;
        components.weightx = 1.0;
        components.anchor = GridBagConstraints.WEST;
        panel.add(textTitle, components);
        components.anchor = GridBagConstraints.CENTER;

        components.gridx = 0;
        components.gridy = 1;
        components.gridwidth = 2;
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
        panel.add(botonOpenShop, components);

        components.gridx = 1;
        components.gridy = 2;
        components.gridwidth = 1;
        components.gridheight = 1;
        components.anchor = GridBagConstraints.EAST;
        panel.add(botonVolver, components);
    }

}