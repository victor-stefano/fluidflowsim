package com.unimonte.fluidflowsim.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class FlowFrame extends JFrame {

    private ReservatoryPanel reservatoryPanel;
    private FluidPanel fluidPanel;
    private FluidOutPanel fluidOutPanel;
    private JSlider sliderIn, sliderOut;
    private ControlPanel controlPanel;
    private GridPanel gridPanel;
    private PressureSensorComponent sensor, sensor2, sensor3;
    private JButton configButton;
    private double screenBoundsFactorX = 1.0;
    private double screenBoundsFactorY = 1.0;

    public FlowFrame() {
        setTitle("Simulador de Pressão - Reservatório d'Água");
        setSize(1920, 1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        reservatoryPanel = new ReservatoryPanel();
        fluidPanel = new FluidPanel();
        fluidOutPanel = new FluidOutPanel();
        sliderIn = new JSlider(0, 10);
        sliderOut = new JSlider(0, 10);
        controlPanel = new ControlPanel();
        gridPanel = new GridPanel();
        sensor = new PressureSensorComponent();
        sensor2 = new PressureSensorComponent();
        sensor3 = new PressureSensorComponent();
        configButton = new JButton("⚙ Ajustar");

        reservatoryPanel.setFluidLevel(250);

        sliderIn.setValue(0);
        sliderOut.setValue(0);

        sliderIn.setOpaque(false);
        sliderOut.setOpaque(false);

        sliderIn.addChangeListener(e -> {
            int value = sliderIn.getValue();
            reservatoryPanel.addFluidLevel(value);
            int level = reservatoryPanel.getLevel();
            if (level < 400) {
                fluidPanel.setFluidWidth(value * 5);
            } else {
                fluidPanel.setFluidWidth(0);
                sliderIn.setValue(0);
            }
        });

        sliderOut.addChangeListener(e -> {
            int value = sliderOut.getValue();
            reservatoryPanel.removeFluidLevel(value);
            int level = reservatoryPanel.getLevel();
            if (level > 0) {
                fluidOutPanel.setFluidWidth(value * 5);
            } else {
                fluidOutPanel.setFluidWidth(0);
                sliderOut.setValue(0);
            }
        });

        configButton.addActionListener(e -> openAdjustmentDialog());

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(null);

        backgroundPanel.add(sensor);
        backgroundPanel.add(sensor2);
        backgroundPanel.add(sensor3);
        backgroundPanel.add(gridPanel);
        backgroundPanel.add(reservatoryPanel);
        backgroundPanel.add(fluidPanel);
        backgroundPanel.add(fluidOutPanel);
        backgroundPanel.add(sliderIn);
        backgroundPanel.add(sliderOut);
        backgroundPanel.add(controlPanel);
        backgroundPanel.add(configButton);

        setContentPane(backgroundPanel);

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                updateLayout();
            }

            public void componentShown(ComponentEvent e) {
                updateLayout();
            }
        });
    }

    private void updateLayout() {
        int w = getWidth();
        int h = getHeight();

        reservatoryPanel.setBounds(
                (int) (602 * screenBoundsFactorX), (int) (516 * screenBoundsFactorY),
                482, 400
        );
        reservatoryPanel.updateHeight();

        fluidPanel.setBounds(
                (int) (607 * screenBoundsFactorX), (int) (416 * screenBoundsFactorY),
                50, 500
        );

        fluidOutPanel.setBounds(
                (int) (704 * screenBoundsFactorX), (int) (963 * screenBoundsFactorY),
                50, 100
        );

        sliderIn.setBounds(
                (int) (485 * screenBoundsFactorX), (int) (177 * screenBoundsFactorY),
                165, 30
        );

        sliderOut.setBounds(
                (int) (697 * screenBoundsFactorX), (int) (935 * screenBoundsFactorY),
                80, 30
        );

        gridPanel.setBounds(
                (int) (602 * screenBoundsFactorX), (int) (496 * screenBoundsFactorY),
                482, 420
        );

        controlPanel.setBounds(
                (int) (w * 0.01), (int) (h * 0.01),
                200, 200
        );

        sensor.setBounds(
                (int) (w * 0.05), (int) (h * 0.30),
                100, 100
        );

        sensor2.setBounds(
                (int) (w * 0.05), (int) (h * 0.40),
                100, 100
        );

        sensor3.setBounds(
                (int) (w * 0.05), (int) (h * 0.50),
                100, 100
        );

        configButton.setBounds(
                (w / 2 - 100), (int) (h * 0.01),
                100, 30
        );

        if (w != 1920 || h != 1080) {
            enableDefaultElPos(w, h);
        }
    }

    private void openAdjustmentDialog() {
        JDialog dialog = new JDialog(this, "Ajustar Posição", true);
        dialog.setSize(420, 220);
        dialog.setLocationRelativeTo(this);

        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel xPanel = new JPanel(new BorderLayout(10, 0));
        JLabel xLabel = new JLabel("X:");
        JSlider xSlider = new JSlider(0, getWidth() * 4, (int) (screenBoundsFactorX * getWidth()));
        xPanel.add(xLabel, BorderLayout.WEST);
        xPanel.add(xSlider, BorderLayout.CENTER);

        JPanel yPanel = new JPanel(new BorderLayout(10, 0));
        JLabel yLabel = new JLabel("Y:");
        JSlider ySlider = new JSlider(0, getHeight() * 4, (int) (screenBoundsFactorY * getHeight()));
        yPanel.add(yLabel, BorderLayout.WEST);
        yPanel.add(ySlider, BorderLayout.CENTER);

        xSlider.addChangeListener(e -> {
            screenBoundsFactorX = (double) xSlider.getValue() / getWidth();
            updateLayout();
        });

        ySlider.addChangeListener(e -> {
            screenBoundsFactorY = (double) ySlider.getValue() / getHeight();
            updateLayout();
        });

        mainPanel.add(xPanel);
        mainPanel.add(yPanel);

        dialog.add(mainPanel);
        dialog.setVisible(true);
    }

    public void enableDefaultElPos(int w, int h) {
        fluidPanel.setBounds(
                0, 0,
                0, 0
        );

        fluidOutPanel.setBounds(
                0, 0,
                0, 0
        );

        sliderIn.setBounds(
                (int) (w * 0.90), (int) (h * 0.1),
                165, 30
        );

        sliderOut.setBounds(
                (int) (w * 0.90), (int) (h * 0.15),
                80, 30
        );

        gridPanel.setBounds(
                0, 0,
                0, 0
        );
    }
}

