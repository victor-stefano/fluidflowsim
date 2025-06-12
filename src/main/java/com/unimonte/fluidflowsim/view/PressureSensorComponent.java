package com.unimonte.fluidflowsim.view;

import com.unimonte.fluidflowsim.controller.FlowSimController;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class PressureSensorComponent extends JPanel {
    private JLabel label;
    private JLabel meterLabel;
    private double pressure;
    private String pressureValue;
    private Image backgroundImage;

    public PressureSensorComponent() {
        setSize(100, 100);
        setOpaque(false);
        setLayout(null);

        backgroundImage = new ImageIcon(getClass().getResource(FlowSimController.getManometerImagePath())).getImage();

        label = new JLabel("0 Pa", SwingConstants.CENTER);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setBounds(20, 65, 60, 20);
        add(label);

        meterLabel = new JLabel("0 m", SwingConstants.CENTER);
        meterLabel.setForeground(Color.BLACK);
        meterLabel.setFont(new Font("Arial", Font.BOLD, 12));
        meterLabel.setBounds(20, 15, 60, 20);
        add(meterLabel);

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
                updateMeterLabel();
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        addFluidListener();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.RED);

        int centerY = getHeight() / 2;
        int arrowSize = 6;

        Polygon arrow = new Polygon();
        arrow.addPoint(0, centerY);
        arrow.addPoint(arrowSize, centerY - arrowSize);
        arrow.addPoint(arrowSize, centerY + arrowSize);

        g2d.fill(arrow);
        g2d.dispose();

        super.paintComponent(g);
    }

    private void addFluidListener() {
        Timer timer = new Timer(16, null);
        timer.addActionListener(e -> {
            updatePressureLabel();
            updateMeterLabel();
            FlowSimController.changeManometerImage(pressure);
            backgroundImage = new ImageIcon(getClass().getResource(FlowSimController.getManometerImagePath())).getImage();
            repaint();
        });
        timer.start();
    }

    private void updatePressureLabel() {
        int centerY = getY() + getHeight() / 2;
        pressure = FlowSimController.updatePressure(centerY);
        pressureValue = FlowSimController.formatPressure(pressure);
        label.setText(pressureValue);
    }

    private void updateMeterLabel() {
        int centerY = getY() + getHeight() / 2;
        int fluidHeight = FlowSimController.getFluidLevel();
        int reservatoryBottom = FlowSimController.getReservatoryBottom();
        int reservatoryTop = reservatoryBottom - fluidHeight;

        double meter = (centerY - reservatoryTop) * 0.01;
        meter = (centerY < reservatoryTop || centerY > reservatoryBottom) ? 0 : meter;

        meterLabel.setText(String.format("%.2f m", meter));
    }
}
