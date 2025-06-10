package com.unimonte.fluidflowsim.view;

import com.unimonte.fluidflowsim.controller.FlowSimController;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    private JCheckBox gravityCheckBox;
    private JCheckBox atmosphereCheckBox;
    private JRadioButton waterRadioButton;
    private JRadioButton mercuryRadioButton;
    private ButtonGroup fluidGroup;

    public ControlPanel() {
        setOpaque(false);
        setLayout(null);

        JPanel content = new JPanel(new GridLayout(0, 1));
        content.setOpaque(false);
        content.setBounds(10, 10, 180, 180);

        gravityCheckBox = new JCheckBox("Ativar gravidade");
        gravityCheckBox.setSelected(true);
        gravityCheckBox.setOpaque(false);
        gravityCheckBox.addActionListener(e -> onGravityToggle(gravityCheckBox.isSelected()));

        atmosphereCheckBox = new JCheckBox("Ativar atmosfera");
        atmosphereCheckBox.setSelected(true);
        atmosphereCheckBox.setOpaque(false);
        atmosphereCheckBox.addActionListener(e -> onAtmosphereToggle(atmosphereCheckBox.isSelected()));

        waterRadioButton = new JRadioButton("Água");
        waterRadioButton.setOpaque(false);
        mercuryRadioButton = new JRadioButton("Mercúrio");
        mercuryRadioButton.setOpaque(false);
        waterRadioButton.setSelected(true);

        waterRadioButton.addActionListener(e -> onFluidSelected("agua"));
        mercuryRadioButton.addActionListener(e -> onFluidSelected("mercurio"));

        fluidGroup = new ButtonGroup();
        fluidGroup.add(waterRadioButton);
        fluidGroup.add(mercuryRadioButton);

        content.add(gravityCheckBox);
        content.add(atmosphereCheckBox);
        content.add(waterRadioButton);
        content.add(mercuryRadioButton);

        add(content);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(225, 225, 225, 200));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        g2.dispose();
    }

    private void onGravityToggle(boolean enabled) {
        FlowSimController.setGravity(enabled);
    }

    private void onAtmosphereToggle(boolean enabled) {
        FlowSimController.setAtmosphere(enabled);
    }

    private void onFluidSelected(String fluid) {
        if (fluid.equals("agua")) {
            FlowSimController.setFluidToWater();
        } else {
            FlowSimController.setFluidToMercury();
        }
    }
}
