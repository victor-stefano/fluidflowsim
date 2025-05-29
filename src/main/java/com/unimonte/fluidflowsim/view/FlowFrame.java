package com.unimonte.fluidflowsim.view;

import javax.swing.JFrame;

public class FlowFrame extends JFrame {
    
    public FlowFrame() {
        setTitle("Simulador de Pressão - Torre d'Água");
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        add(new ReservatoryPanel());
    }
}
