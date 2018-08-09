/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import vista.VentaBoletos;
/**
 *
 * @author Adrián Scott
 */
public class ControladorVentaBoletos extends ControladorPrincipal{
    VentaBoletos vista;

    public ControladorVentaBoletos(VentaBoletos vista) {
        this.vista = vista;
    }
    
    @Override
    public void iniciarVista() {
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        int anchoPanel = vista.panelPeliculas.getSize().width;
        System.out.println(anchoPanel);
        for(int i = 0;i < 50 ; i++){
            JPanel peli = new JPanel(new GridLayout(0, 2));
            peli.setSize(vista.panelPeliculas.getSize().width, 200);
            peli.setAlignmentY(Component.CENTER_ALIGNMENT);
            JLabel lbl2 = new JLabel("El extraño mundo del cedi"+i);
            lbl2.setAlignmentX(Component.LEFT_ALIGNMENT);
            peli.add(new JLabel(new ImageIcon(getClass().getResource("/assets/icons/accept.png"))));
            peli.add(lbl2);
            vista.panelPeliculas.add(peli);
            //lbl2.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        }
        vista.panelPeliculas.getWidth();
    }
}
