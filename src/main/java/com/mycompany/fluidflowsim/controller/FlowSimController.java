package com.mycompany.fluidflowsim.controller;

import com.mycompany.fluidflowsim.view.FlowFrame;
import javax.swing.SwingUtilities;

public class FlowSimController {
    public static void init() {
        SwingUtilities.invokeLater(() -> {
            FlowFrame frame = new FlowFrame();
            frame.setVisible(true);
        });
    }
}
