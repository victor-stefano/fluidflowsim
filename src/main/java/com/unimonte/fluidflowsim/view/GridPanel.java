package com.unimonte.fluidflowsim.view;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {

    public GridPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(0, 0, 0, 100));

        Font font = new Font("SansSerif", Font.PLAIN, 12);
        g2.setFont(font);

        for (int i = 0; i <= 3; i++) {
            int y = getHeight() - i * 100;

            g2.drawLine(0, y, getWidth(), y);

            String label = i + " m";
            g2.drawString(label, 5, y - 5);
        }

        g2.dispose();
    }
}
