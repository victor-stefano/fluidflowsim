package com.unimonte.fluidflowsim.view;

import com.unimonte.fluidflowsim.controller.FlowSimController;
import com.unimonte.fluidflowsim.model.IFluid;

import javax.swing.*;
import java.awt.*;

public class ReservatoryPanel extends JPanel implements IFluid {

    private int level = 0;
    private int outLevel = 0;
    private int visualLevel = 0;

    public ReservatoryPanel() {
        setOpaque(false);
        Timer animationTimer = new Timer(16, e -> animateFluid());
        animationTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(FlowSimController.getReservatoryColor());

        int width = getWidth();
        int height = getHeight();
        int y = height - visualLevel;

        g2.fillRect(0, y, width, visualLevel);
    }

    public void animateFluid() {
        int targetLevel = FlowSimController.getFluidLevel();

        if (visualLevel < targetLevel) {
            visualLevel += Math.max(1, (targetLevel - visualLevel) / 4);
            if (visualLevel > targetLevel) {
                visualLevel = targetLevel;
            }
            repaint();
        } else if (visualLevel > targetLevel) {
            visualLevel -= Math.max(1, (visualLevel - targetLevel) / 4);
            if (visualLevel < targetLevel) {
                visualLevel = targetLevel;
            }
            repaint();
        }

        if (targetLevel < getHeight()) {
            FlowSimController.setFluidLevel(Math.min(targetLevel + level, getHeight()));
        }

        if (outLevel > 0) {
            FlowSimController.setFluidLevel(Math.max(targetLevel - outLevel, 0));
        }
    }

    public void setFluidLevel(int level) {
        FlowSimController.setFluidLevel(level);
    }

    public void removeFluidLevel(int level) {
        this.outLevel = level;
    }

    public void addFluidLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return FlowSimController.getFluidLevel();
    }

    public void updateHeight() {
        FlowSimController.setReservatoryBottom(getY() + 400);
    }
}