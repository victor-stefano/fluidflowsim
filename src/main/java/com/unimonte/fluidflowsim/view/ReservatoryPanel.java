package com.unimonte.fluidflowsim.view;

import com.unimonte.fluidflowsim.controller.FlowSimController;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ReservatoryPanel extends JPanel {
    
    private int level = 0;
    private int outLevel = 0;
    
    public ReservatoryPanel() {
        setOpaque(false);
        fillReservatory();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color( 151, 203, 214, 255));

        int width = getWidth();
        int height = getHeight();

        int y = height - FlowSimController.getFluidLevel();
        g2.fillRect(0, y, width, FlowSimController.getFluidLevel());
    }
    
    public void fillReservatory() {
        Timer timer = new Timer(100, null);
        timer.addActionListener(e -> {
            if (FlowSimController.getFluidLevel() < getHeight()) {
                addFluidLevel(level);
            } 
            
            if (outLevel > 0) {
                removeFluidLevel(outLevel);
            }
        });
        timer.start();
    }
    
    public void setFluidLevel(int level) {
        FlowSimController.setFluidLevel(Math.max(0, Math.min(level, getHeight())));
        this.repaint();
    }
    
    public void removeFluidLevel(int level) {
        this.outLevel = level;
        FlowSimController.setFluidLevel(Math.max(0, Math.min(FlowSimController.getFluidLevel() - level, getHeight())));
        this.repaint();
    }
    
    public void addFluidLevel(int level) {
        this.level = level;
        FlowSimController.setFluidLevel(Math.max(0, Math.min(FlowSimController.getFluidLevel() + level, getHeight())));
        this.repaint();
    }
}
