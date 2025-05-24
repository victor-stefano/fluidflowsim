package com.mycompany.fluidflowsim.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class ReservatoryPanel extends JPanel {
    private boolean isGravityActive = false;
    private int fluidHeight = 0;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int x = 150, y = 0, width = 100, totalHeight = 300;
        g2.setColor(Color.GRAY);
        g2.fillRect(x, y, width, totalHeight);

        g2.setColor(Color.CYAN);
        g2.fillRect(x, y + (totalHeight - fluidHeight), width, fluidHeight);

        // Pressão
        double rho = 1000;        // kg/m³ (água)
        double gVal = isGravityActive ? 9.8 : 0; // m/s²
        double h = fluidHeight / 100.0; // em metros
        double patm = 101.3; // pressão atmosférica em kPa
        double p = patm + rho * gVal * h / 1000.0; // em kPa

        g2.setColor(Color.BLACK);
        g2.drawString(String.format("Pressão no fundo: %.2f kPa", p), x, y + totalHeight + 20);
    }

    public void setFluidHeight(int height) {
        this.fluidHeight = height;
        repaint();
    }

    public void setIsGravityActive(boolean bool) {
        this.isGravityActive = bool;
        repaint();
    }
    
    
}
