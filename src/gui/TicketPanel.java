package gui;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.table.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class TicketPanel extends Panel{

    public TicketPanel(JFrame frame){
        this.window = frame;
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(40,30,30,30));

        JLabel textTitle = new JLabel("Ticket");
        JButton botonAceptar = new JButton("Aceptar");
        JEditorPane textTicket = new JEditorPane();
        textTicket.setContentType("text/html");

        String data = "<h2><center>Ticket de compra</center></h2>"
        + "<br />"
        + "<center><table width=\"700\" border=\"1\"><tr><td>"
        + "<center><b>CUIT</b></center>" 
        + "</td><td>"
        + "<center><b>Razón social</b></center>"
        + "</td><td>"
        + "<center><b>Inicio de actividad</b></center>"
        + "</td><td>"
        + "<center><b>Fecha/hora</b></center>"
        + "</td></tr>"
        + "<tr><td><center>"
        + ""
        + "</center></td><td><center>"
        + ""
        + "</center></td><td><center>"
        + ""
        + "</center></td><td><center>"
        + ""
        + "</center></td></tr>"
        + "</table></center>"
        + "<br />"
        + "<h3><center>Lista de productos comprados</center></h3>"
        + "<center><table width=\"700\" border=\"1\"><tr><td>"
        + "<center><b>Cantidad</b></center>" 
        + "</td><td>"
        + "<center><b>Código</b></center>"
        + "</td><td>"
        + "<center><b>Descripción del producto</b></center>"
        + "</td><td>"
        + "<center><b>Monto</b></center>"
        + "</td></tr>"
        + "<tr><td><center>"
        + ""
        + "</center></td><td><center>"
        + ""
        + "</center></td><td><center>"
        + ""
        + "</center></td><td><center>"
        + ""
        + "</center></td></tr>"
        + "</table></center>"
        + "<br />"
        + "<h3><center>Descuentos aplicados</center></h3>"
        + "<center><table width=\"700\" border=\"1\"><tr><td>"
        + ""
        + "</td></tr></table></center>"
        + "<br />"
        + "<center><table width=\"660\"><tr>"
        + "<td><b>Total descuentos aplicados:</b></td><td>"
        + "0"
        + "</td></tr>"
        + "<tr><td><b>Total a pagar:</b></td><td>"
        + "0.00"
        + "</td></tr>"
        + "<tr><td><b>Forma de pago:</b></td><td>"
        + "UNKNOWN"
        + "</td></tr>"
        + "<tr><td><b>Total de efectivo:</b></td><td>"
        + "UNKNOWN"
        + "</td></tr>"
        + "<tr><td><b>Total cobrado por medio de pago:</b></td><td>"
        + "UNKNOWN"
        + "</td></tr></table></center>"
        + "<br />"
        + "<center><table width=\"660\"><tr>"
        + "<td><b>Nombre del cajero:</b></td><td>"
        + "UNKNOWN"
        + "</td></tr>"
        + "<tr><td><b>Fecha/hora apertura:</b></td><td>"
        + "UNKNOWN"
        + "</td></tr>"
        + "<tr><td><b>Fecha/hora cierre:</b></td><td>"
        + "UNKNOWN"
        + "</td></tr>"
        + "</table></center>";

        textTicket.setText(data);
        textTicket.setEditable(false);

        JScrollPane sp = new JScrollPane(textTicket);

        sp.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(30,0,40,0), new EtchedBorder()));
        textTitle.setFont(textTitle.getFont().deriveFont(22.0f));
        
        GridBagConstraints components = new GridBagConstraints();
        components.gridx = 0;
        components.gridy = 0;
        components.gridwidth = 1;
        components.gridheight = 1;
        components.weightx = 1.0;
        components.anchor = GridBagConstraints.WEST;
        panel.add(textTitle, components);
        components.anchor = GridBagConstraints.CENTER;

        components.gridx = 0;
        components.gridy = 1;
        components.gridwidth = 1;
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
        components.anchor = GridBagConstraints.CENTER;
        panel.add(botonAceptar, components);
    }

}