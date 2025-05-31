package com.unimonte.fluidflowsim.view;

import com.unimonte.fluidflowsim.controller.FlowSimController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class PressureSensorComponent extends JPanel {
    private JLabel label;

    public PressureSensorComponent() {
        setSize(40, 40);
        setBackground(new Color(255, 0, 0, 150));
        setLayout(new BorderLayout());

        label = new JLabel("0 Pa", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        add(label, BorderLayout.CENTER);

        MouseAdapter mouseAdapter = new MouseAdapter() {
            private Point offset;

            @Override
            public void mousePressed(MouseEvent e) {
                offset = e.getPoint();
                updatePressureLabel();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point parentPoint = SwingUtilities.convertPoint(PressureSensorComponent.this, e.getPoint(), getParent());
                int newX = parentPoint.x - offset.x;
                int newY = parentPoint.y - offset.y;
                setLocation(newX, newY);
                updatePressureLabel();
            }

            private void updatePressureLabel() {
                int centerY = getY() + getHeight() / 2;
                double pressure = FlowSimController.updatePressure(centerY);
                label.setText(FlowSimController.formatPressure(pressure));
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }
}
