package com.unimonte.fluidflowsim.view;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ItemEvent;

public class TESTE extends JFrame {

    public TESTE() {
        setTitle("Simulador de Pressão - Torre d'Água");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        PainelTanque painelTanque = new PainelTanque();

        JPanel painelControles = new JPanel();
        painelControles.setLayout(new BoxLayout(painelControles, BoxLayout.Y_AXIS));

        JSlider sliderAltura = new JSlider(0, 300, 150);
        sliderAltura.setPaintTicks(true);
        sliderAltura.setPaintLabels(true);
        sliderAltura.setMajorTickSpacing(100);
        sliderAltura.setMinorTickSpacing(10);

        JCheckBox cbGravidade = new JCheckBox("Gravidade ativada", true);

        sliderAltura.addChangeListener((ChangeEvent e) -> {
            painelTanque.setAlturaFluido(sliderAltura.getValue());
        });

        cbGravidade.addItemListener((ItemEvent e) -> {
            painelTanque.setGravidadeAtiva(cbGravidade.isSelected());
        });

        painelControles.add(new JLabel("Altura do Fluido (px):"));
        painelControles.add(sliderAltura);
        painelControles.add(cbGravidade);

        add(painelTanque, BorderLayout.CENTER);
        add(painelControles, BorderLayout.EAST);
    }

    static class PainelTanque extends JPanel {
        private int alturaFluido = 150;
        private boolean gravidadeAtiva = true;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            // Tanque
            int x = 150, y = 100, largura = 100, alturaTotal = 300;
            g2.setColor(Color.GRAY);
            g2.fillRect(x, y, largura, alturaTotal);

            // Fluido
            g2.setColor(Color.CYAN);
            g2.fillRect(x, y + (alturaTotal - alturaFluido), largura, alturaFluido);

            // Pressão
            double rho = 1000;        // kg/m³ (água)
            double gVal = gravidadeAtiva ? 9.8 : 0; // m/s²
            double h = alturaFluido / 100.0; // em metros
            double patm = 101.3; // pressão atmosférica em kPa
            double p = patm + rho * gVal * h / 1000.0; // em kPa

            g2.setColor(Color.BLACK);
            g2.drawString(String.format("Pressão no fundo: %.2f kPa", p), x, y + alturaTotal + 20);
        }

        public void setAlturaFluido(int altura) {
            this.alturaFluido = altura;
            repaint();
        }

        public void setGravidadeAtiva(boolean ativa) {
            this.gravidadeAtiva = ativa;
            repaint();
        }
    }
}
