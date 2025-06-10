package com.unimonte.fluidflowsim.view;

import com.unimonte.fluidflowsim.controller.FlowSimController;

import javax.swing.*;
import java.awt.*;

public class FluidOutPanel extends JPanel {

    private int fluidWidth = 0;
    private int targetFluidWidth = 0;

    public FluidOutPanel() {
        setOpaque(false);
        Timer animationTimer = new Timer(16, e -> animateFluid());
        animationTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(FlowSimController.getFlowColor());
        g2.fillRect(0, 0, fluidWidth, 100);
    }

    public void setFluidWidth(int width) {
        this.targetFluidWidth = width;
    }

    private void animateFluid() {
        if (FlowSimController.getFluidLevel() <= 0) {
            setFluidWidth(0);
        }

        if (fluidWidth < targetFluidWidth) {
            fluidWidth += Math.max(1, (targetFluidWidth - fluidWidth) / 4);
            if (fluidWidth > targetFluidWidth) {
                fluidWidth = targetFluidWidth;
            }
            repaint();
        } else if (fluidWidth > targetFluidWidth) {
            fluidWidth -= Math.max(1, (fluidWidth - targetFluidWidth) / 4);
            if (fluidWidth < targetFluidWidth) {
                fluidWidth = targetFluidWidth;
            }
            repaint();
        }
    }
}
