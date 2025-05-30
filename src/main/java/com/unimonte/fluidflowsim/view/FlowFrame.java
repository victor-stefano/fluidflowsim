package com.unimonte.fluidflowsim.view;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;

public class FlowFrame extends JFrame {
    
    public final String IMAGE_PATH = "/assets/fluidsim-bg.png";
    
    public FlowFrame() {
        setTitle("Simulador de Pressão - Torre d'Água");
        setSize(1280, 720);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        ReservatoryPanel reservatoryPanel = new ReservatoryPanel();
        reservatoryPanel.setBounds(366, 325, 428, 300);
        
        FluidPanel fluidPanel = new FluidPanel();
        fluidPanel.setBounds(370, 278, 50, 345);
        
        JSlider sliderIn = new JSlider(0, 10);
        sliderIn.setValue(0);
        sliderIn.setBounds(250, 106, 162, 30);
        sliderIn.setBackground(Color.LIGHT_GRAY);
        sliderIn.addChangeListener(e -> {
            int value = sliderIn.getValue();
            reservatoryPanel.addFluidLevel(value);
            fluidPanel.setFluidWidth(value * 5);
        });
        
        BackgroundPanel backgroundPanel = new BackgroundPanel(IMAGE_PATH);
        backgroundPanel.add(reservatoryPanel);
        backgroundPanel.add(fluidPanel);
        backgroundPanel.add(sliderIn);
        
        setContentPane(backgroundPanel);
    }
}
