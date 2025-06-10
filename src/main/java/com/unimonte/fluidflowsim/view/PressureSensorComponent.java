package com.unimonte.fluidflowsim.view;

import com.unimonte.fluidflowsim.controller.FlowSimController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class PressureSensorComponent extends JPanel {
    private JLabel label;
    private String pressureValue;

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
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        addFluidListener();
    }

    private void addFluidListener() {
        Timer timer = new Timer(16, null);
        timer.addActionListener(e -> {
            updatePressureLabel();
        });
        timer.start();
    }

    private void updatePressureLabel() {
        int centerY = getY() + getHeight() / 2;
        double pressure = FlowSimController.updatePressure(centerY);
        pressureValue = FlowSimController.formatPressure(pressure);
        label.setText(pressureValue);
    }
}
