package gui;

import javax.swing.*;

public class LoginPanel {

    private JPanel panel = new JPanel();
    private JFrame window;

    public LoginPanel(JFrame frame){
        this.window = frame;
        panel.setLayout(null);

        JButton iniciar = new JButton("Iniciar");
        iniciar.setBounds(348, 364, 100, 30);

        JComboBox rolSelect = new JComboBox();
        rolSelect.setBounds(580, 40, 170, 25);
        rolSelect.addItem("Cajero");
        rolSelect.addItem("Supervisor");

        JLabel userText = new JLabel("Usuario:");
        userText.setBounds(230, 225, 330, 20);

        JLabel passText = new JLabel("Contraseña:");
        passText.setBounds(230, 290, 330, 20);

        JTextField userBox = new JTextField();
        userBox.setBounds(220, 250, 350, 30);

        JTextField passBox = new JTextField();
        passBox.setBounds(220, 315, 350, 30);

        JTextArea aboutText = new JTextArea("Trabajo Práctico:" + 
        " Técnicas de Diseño - 2do Cuatrimestre 2018");
        aboutText.setBounds(200, 505, 390, 20);
        aboutText.setOpaque(false);

        JTextArea contributors = new JTextArea("Grupo 4:" + 
        " Llauró, Manuel - Inoriza, Pablo - Arturi, Augusto - Blanco, Sebastian Ezequiel");
        contributors.setBounds(130, 525, 530, 20);
        contributors.setOpaque(false);        

        panel.add(iniciar);
        panel.add(userText);
        panel.add(passText);
        panel.add(userBox);
        panel.add(passBox);
        panel.add(rolSelect);
        panel.add(aboutText);
        panel.add(contributors);
    }
    
    public JPanel getPanel(){
        return panel;
    }
}