package gui;

import javax.swing.*;
import gui.LoginPanel;

public class StartGui {

    private JFrame window = new JFrame();
    
    //Inicializar la ventana de la Gui
    public StartGui(){
        window.setTitle("RegCash - BETA");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800,600);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
    }

    //Actualizar pantalla
    public void refreshScreen(){
        window.setVisible(true);
    }

    public JFrame getFrame(){
        return window;
    }

    public static void main(String[] args){
        StartGui gui = new StartGui();
        LoginPanel login = new LoginPanel(gui.getFrame());
        
        gui.getFrame().getContentPane().add(login.getPanel());
        gui.refreshScreen();
    }

}