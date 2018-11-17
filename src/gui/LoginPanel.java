package gui;

import javax.swing.*;

public class LoginPanel extends Panel {

    public LoginPanel(JFrame frame){
        this.window = frame;
        panel.setLayout(null);

        JButton iniciar = new JButton("Iniciar");
        iniciar.setBounds(348, 410, 100, 30);

        JButton acercaDe = new JButton("Info");
        acercaDe.setBounds(690, 20, 70, 20);

        JComboBox rolSelect = new JComboBox();
        rolSelect.setBounds(350, 360, 190, 25);
        rolSelect.addItem("Cajero");
        rolSelect.addItem("Supervisor");

        JLabel userText = new JLabel("Usuario:");
        userText.setBounds(230, 225, 330, 20);

        JLabel passText = new JLabel("Contraseña:");
        passText.setBounds(230, 290, 330, 20);

        JLabel rolText = new JLabel("Iniciar como:");
        rolText.setBounds(230, 362, 100, 20);

        JTextField userBox = new JTextField();
        userBox.setBounds(220, 250, 350, 30);

        JTextField passBox = new JTextField();
        passBox.setBounds(220, 315, 350, 30);

        JLabel aboutText = new JLabel("Trabajo Práctico:" + 
        " Técnicas de Diseño - 2do Cuatrimestre 2018");
        aboutText.setBounds(180, 525, 440, 20);
        aboutText.setOpaque(false);      

        panel.add(iniciar);
        panel.add(acercaDe);
        panel.add(userText);
        panel.add(passText);
        panel.add(userBox);
        panel.add(passBox);
        panel.add(rolSelect);
        panel.add(rolText);
        panel.add(aboutText);
    }
}