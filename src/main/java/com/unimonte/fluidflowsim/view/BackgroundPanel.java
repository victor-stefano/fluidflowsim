package com.unimonte.fluidflowsim.view;

import com.unimonte.fluidflowsim.controller.FlowSimController;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel() {
        setLayout(null);
        Timer bgTimer = new Timer(100, e -> {
            backgroundImage = new ImageIcon(getClass().getResource(FlowSimController.getBgImagePath())).getImage();
            repaint();
        });
        bgTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int imageWidth = 3840;
            int imageHeight = 2160;

            int startX = (imageWidth - panelWidth) / 2;
            int startY = (imageHeight - panelHeight) / 2;

            if (startX < 0) startX = 0;
            if (startY < 0) startY = 0;

            int endX = Math.min(startX + panelWidth, imageWidth);
            int endY = Math.min(startY + panelHeight, imageHeight);

            g.drawImage(
                    backgroundImage,
                    0, 0,
                    panelWidth, panelHeight,
                    startX, startY,
                    endX, endY,
                    this
            );
        }
    }
}
