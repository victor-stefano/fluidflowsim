package com.unimonte.fluidflowsim.view;

import java.awt.Color;
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
        sliderIn.setBounds(252, 106, 165, 30);
        sliderIn.setOpaque(false);
        sliderIn.addChangeListener(e -> {
            int value = sliderIn.getValue();
            reservatoryPanel.addFluidLevel(value);
            fluidPanel.setFluidWidth(value * 5);
        });
        
        JSlider sliderOut = new JSlider(0, 10);
        sliderOut.setValue(0);
        sliderOut.setBounds(450, 620, 80, 30);
        sliderOut.setOpaque(false);
        sliderOut.addChangeListener(e -> {
            int value = sliderOut.getValue();
            reservatoryPanel.removeFluidLevel(value);
        });
        
        PressureSensorComponent sensor = new PressureSensorComponent();
        sensor.setBounds(500, 500, 100, 30);
        sensor.setLocation(400, 100);
        
        
        BackgroundPanel backgroundPanel = new BackgroundPanel(IMAGE_PATH);
        backgroundPanel.setLayout(null);
        
        backgroundPanel.add(sensor);
        backgroundPanel.add(reservatoryPanel);
        backgroundPanel.add(fluidPanel);
        backgroundPanel.add(sliderIn);
        backgroundPanel.add(sliderOut);
        
        setContentPane(backgroundPanel);
    }
}
