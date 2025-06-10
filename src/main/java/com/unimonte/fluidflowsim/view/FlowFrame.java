package com.unimonte.fluidflowsim.view;

import com.unimonte.fluidflowsim.controller.FlowSimController;

import java.awt.*;
import javax.swing.*;

public class FlowFrame extends JFrame {
    
    public final String IMAGE_PATH = "/assets/fluidsim-bg.png";
    
    public FlowFrame() {
        setTitle("Simulador de Pressão - Reservatório d'Água");
        setSize(1382, 746);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        ReservatoryPanel reservatoryPanel = new ReservatoryPanel();
        reservatoryPanel.setBounds(366, 325, 428, 300);
        reservatoryPanel.setFluidLevel(150);
        
        FluidPanel fluidPanel = new FluidPanel();
        fluidPanel.setBounds(370, 278, 50, 345);

        FluidOutPanel fluidOutPanel = new FluidOutPanel();
        fluidOutPanel.setBounds(460, 656, 50, 100);
        
        JSlider sliderIn = new JSlider(0, 10);
        sliderIn.setValue(0);
        sliderIn.setBounds(252, 106, 165, 30);
        sliderIn.setOpaque(false);
        sliderIn.addChangeListener(e -> {
            int value = sliderIn.getValue();
            reservatoryPanel.addFluidLevel(value);
            int level = reservatoryPanel.getLevel();
            if (level < 300) {
                fluidPanel.setFluidWidth(value * 5);
            } else {
                fluidPanel.setFluidWidth(0);
                sliderIn.setValue(0);
            }
        });
        
        JSlider sliderOut = new JSlider(0, 10);
        sliderOut.setValue(0);
        sliderOut.setBounds(450, 620, 80, 30);
        sliderOut.setOpaque(false);
        sliderOut.addChangeListener(e -> {
            int value = sliderOut.getValue();
            reservatoryPanel.removeFluidLevel(value);
            int level = reservatoryPanel.getLevel();
            if (level > 0) {
                fluidOutPanel.setFluidWidth(value * 5);
            } else {
                fluidOutPanel.setFluidWidth(0);
                sliderOut.setValue(0);
            }
        });
        
        PressureSensorComponent sensor = new PressureSensorComponent();
        sensor.setBounds(0, 0, 80, 30);
        sensor.setLocation(1000, 175);

        PressureSensorComponent sensor2 = new PressureSensorComponent();
        sensor2.setBounds(0, 0, 80, 30);
        sensor2.setLocation(1000, 175);

        PressureSensorComponent sensor3 = new PressureSensorComponent();
        sensor3.setBounds(0, 0, 80, 30);
        sensor3.setLocation(1000, 175);

        ControlPanel controlPanel = new ControlPanel();
        controlPanel.setBounds(10, 10, 200, 200);

        GridPanel gridPanel = new GridPanel();
        gridPanel.setBounds(366, 305, 428, 320);

        BackgroundPanel backgroundPanel = new BackgroundPanel(IMAGE_PATH);
        backgroundPanel.setLayout(null);
        
        backgroundPanel.add(sensor);
        backgroundPanel.add(sensor2);
        backgroundPanel.add(sensor3);
        backgroundPanel.add(gridPanel);
        backgroundPanel.add(reservatoryPanel);
        backgroundPanel.add(fluidPanel);
        backgroundPanel.add(fluidOutPanel);
        backgroundPanel.add(sliderIn);
        backgroundPanel.add(sliderOut);
        backgroundPanel.add(controlPanel);
        
        setContentPane(backgroundPanel);
    }
}
