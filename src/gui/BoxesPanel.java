package gui;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class BoxesPanel extends Panel{

    public BoxesPanel(JFrame frame){
        this.window = frame;
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(40,30,30,30));

        JLabel textTitle = new JLabel("Estado de cajas");
        JPanel boxButtonsPanel = new JPanel(new GridLayout(3,7,20,20));
        JButton botonVolver = new JButton("Volver");

        textTitle.setFont(textTitle.getFont().deriveFont(22.0f));
        boxButtonsPanel.setBorder(new EmptyBorder(40,0,80,0));

        GridBagConstraints components = new GridBagConstraints();
        components.gridx = 0;
        components.gridy = 0;
        components.gridwidth = 1;
        components.gridheight = 1;
        components.weightx = 1.0;
        components.anchor = GridBagConstraints.WEST;
        panel.add(textTitle, components);
        components.anchor = GridBagConstraints.CENTER;

        for(int i = 1; i <= 21; i++){
            JButton boxButton = new JButton(String.valueOf(i));
            boxButton.setFont(boxButton.getFont().deriveFont(20.0f));
            boxButton.setBackground(new Color(171,57,57));
            boxButton.setBorder(null);
            boxButton.setForeground(Color.white);
            boxButtonsPanel.add(boxButton);
        }
        
        components.gridx = 0;
        components.gridy = 1;
        components.gridwidth = 1;
        components.gridheight = 1;
        components.weighty = 1.0;
        components.fill = GridBagConstraints.BOTH;
        panel.add(boxButtonsPanel, components);
        components.weighty = 0.0;
        components.fill = GridBagConstraints.NONE;

        components.gridx = 0;
        components.gridy = 2;
        components.gridwidth = 1;
        components.gridheight = 1;
        components.anchor = GridBagConstraints.EAST;
        panel.add(botonVolver, components);
    }

}