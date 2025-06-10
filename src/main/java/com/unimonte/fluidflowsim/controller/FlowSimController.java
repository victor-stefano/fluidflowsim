package com.unimonte.fluidflowsim.controller;

import com.unimonte.fluidflowsim.view.FlowFrame;
import javax.swing.SwingUtilities;
import java.awt.*;

public class FlowSimController {
    
    private static final int RESERVATORY_BOTTOM = 625;
    private static Color FLOW_COLOR = new Color(107, 203, 232, 125);
    private static Color RESERVATORY_COLOR = new Color( 151, 203, 214, 255);
    private static double rho = 1000;
    private static double g = 9.81;
    private static double atm = 101325;
    private static int fluidLevel = 0;
    
    public static void init() {
        SwingUtilities.invokeLater(() -> {
            FlowFrame frame = new FlowFrame();
            frame.setVisible(true);
        });
    }
    
    public static double updatePressure(int yPosition) {
        int fluidHeight = getFluidLevel();
        int fluidTop = RESERVATORY_BOTTOM - fluidHeight;
        int fluidBottom = RESERVATORY_BOTTOM;
        
        if (yPosition < fluidTop) {
            return atm;
        }

        if (yPosition > fluidBottom) {
            yPosition = fluidBottom;
        }

        double h = (yPosition - fluidTop) / 100.0;

        double pressure = rho * g * h;
        return pressure + atm;
    }
    
    public static String formatPressure(double pressure) {
        return String.format("%.0f Pa", pressure);
    }
    
    public static void setGravity(boolean bool) {
        if (bool) {
            g = 9.81;
        } else {
            g = 1;
        }
    }
    
    public static void setAtmosphere(boolean bool) {
        if (bool) {
            atm = 101325;
        } else {
            atm = 0;
        }
    }
    
    public static void setFluidToWater() {
        rho = 1000;
        FLOW_COLOR = new Color(107, 203, 232, 125);
        RESERVATORY_COLOR = new Color( 151, 203, 214, 255);
    }
    
    public static void setFluidToMercury() {
        rho = 13600;
        FLOW_COLOR = new Color(176, 176, 176, 125);
        RESERVATORY_COLOR = new Color(150, 150, 150, 255);
    }
    
    public static int getFluidLevel() {
        return fluidLevel;
    }
    
    public static void setFluidLevel(int level) {
        fluidLevel = level;
    }

    public static Color getFlowColor() {
        return FLOW_COLOR;
    }

    public static Color getReservatoryColor() {
        return RESERVATORY_COLOR;
    }
}
