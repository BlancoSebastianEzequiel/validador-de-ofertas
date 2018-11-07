import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class StartGui {

    private JFrame window = new JFrame();
    
    //Inicializar la ventana de la Gui
    public StartGui(){
        window.setTitle("RegCash");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800,600);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
    }

    public void refreshScreen(){
        window.setVisible(true);
    }

    public JFrame getFrame(){
        return window;
    }

    public static void main(String[] args){
        StartGui gui = new StartGui();

        gui.refreshScreen();
    }

}