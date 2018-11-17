package gui;

import javax.swing.*;

public abstract class Panel{

    protected JPanel panel = new JPanel();
    protected JFrame window;

    public JPanel getPanel(){
        return panel;
    }

} 