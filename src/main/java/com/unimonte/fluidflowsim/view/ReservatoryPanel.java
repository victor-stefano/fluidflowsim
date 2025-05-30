package com.unimonte.fluidflowsim.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ReservatoryPanel extends JPanel {
    
    private int fluidLevel = 0;
    private int level = 0;
    
    public ReservatoryPanel() {
        setOpaque(false);
        fillReservatory();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);

        int width = getWidth();
        int height = getHeight();

        int y = height - fluidLevel;
        g2.fillRect(0, y, width, fluidLevel);
    }
    
    public void fillReservatory() {
        Timer timer = new Timer(100, null);
        timer.addActionListener(e -> {
            if (fluidLevel < getHeight()) {
                addFluidLevel(level);
            } else {
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }
    
    public void setFluidLevel(int level) {
        this.fluidLevel = Math.max(0, Math.min(level, getHeight()));
        this.repaint();
    }
    
    public void addFluidLevel(int level) {
        this.level = level;
        this.fluidLevel = Math.max(0, Math.min(fluidLevel + level, getHeight()));
        this.repaint();
    }
}
