import javax.swing.*;

public class Gui {
    
    //Inicializar la ventana de la Gui
    public static void startGui(String title, int width, int height){
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width,height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        startGui("Caja registradora",600,400);
    }

}