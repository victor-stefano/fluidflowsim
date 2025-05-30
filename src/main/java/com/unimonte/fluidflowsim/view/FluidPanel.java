package com.unimonte.fluidflowsim.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class FluidPanel extends JPanel {
    
    private int fluidWidth = 0;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);

        g2.fillRect(0, 0, fluidWidth, 345);
    }
    
    public void setFluidWidth(int width) {
        this.fluidWidth = width;
        repaint();
    }
}
