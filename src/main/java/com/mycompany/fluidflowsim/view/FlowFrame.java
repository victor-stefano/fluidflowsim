package com.mycompany.fluidflowsim.view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class FlowFrame extends JFrame {
    
    public FlowFrame() {
        setTitle("Simulador de Pressão - Torre d'Água");
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        add(new ReservatoryPanel());
    }
}
