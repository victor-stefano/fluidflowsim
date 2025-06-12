package com.unimonte.fluidflowsim.controller;

import com.unimonte.fluidflowsim.view.FlowFrame;
import javax.swing.SwingUtilities;
import java.awt.*;

public class FlowSimController {

    private static int RESERVATORY_BOTTOM = 916;
    private static String BG_IMAGE_PATH = "/assets/fluidsim-bg.png";
    private static String MANOMETER_IMAGE_PATH = "/assets/manometer-icon-0.png";
    private static Color FLOW_COLOR = new Color(107, 203, 232, 125);
    private static Color RESERVATORY_COLOR = new Color( 151, 203, 214, 255);
    private static double rho = 1000;
    private static double g = 9.81;
    private static double atm = 101325;
    private static int fluidLevel = 0;
    private static boolean gravity = true;
    private static boolean atmosphere = true;
    private static boolean water = true;
    private static boolean mercury = false;
    
    public static void init() {
        SwingUtilities.invokeLater(() -> {
            FlowFrame frame = new FlowFrame();
            frame.setVisible(true);
        });
    }
    
    public static double updatePressure(int yPosition) {
        int fluidHeight = getFluidLevel();
        int fluidTop = getReservatoryBottom() - fluidHeight;
        int fluidBottom = getReservatoryBottom();
        
        if (yPosition < fluidTop) {
            return atm;
        }

        if (yPosition > fluidBottom) {
            return atm;
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
        gravity = bool;
    }
    
    public static void setAtmosphere(boolean bool) {
        if (bool) {
            atm = 101325;
            BG_IMAGE_PATH = "/assets/fluidsim-bg.png";
        } else {
            atm = 0;
            BG_IMAGE_PATH = "/assets/fluidsim-noatm-bg.png";
        }
        atmosphere = bool;
    }
    
    public static void setFluidToWater() {
        rho = 1000;
        FLOW_COLOR = new Color(107, 203, 232, 125);
        RESERVATORY_COLOR = new Color( 151, 203, 214, 255);
        water = true;
        mercury = false;
    }
    
    public static void setFluidToMercury() {
        rho = 13600;
        FLOW_COLOR = new Color(176, 176, 176, 125);
        RESERVATORY_COLOR = new Color(150, 150, 150, 255);
        water = false;
        mercury = true;
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

    public static String getBgImagePath() {
        return BG_IMAGE_PATH;
    }

    public static int getReservatoryBottom() {
        return RESERVATORY_BOTTOM;
    }

    public static void setReservatoryBottom(int height) {
        RESERVATORY_BOTTOM = height;
    }

    public static String getManometerImagePath() {
        return MANOMETER_IMAGE_PATH;
    }

    public static void changeManometerImage(double pressure) {
        if (pressure < 25000) {
            MANOMETER_IMAGE_PATH = "/assets/manometer-icon-0.png";
        } else if (pressure < 110000) {
            MANOMETER_IMAGE_PATH = "/assets/manometer-icon-1.png";
        } else if (pressure < 200000) {
            MANOMETER_IMAGE_PATH = "/assets/manometer-icon-2.png";
        } else {
            MANOMETER_IMAGE_PATH = "/assets/manometer-icon-3.png";
        }
    }

    public static boolean getGravity() {
        return gravity;
    }

    public static boolean getAtmosphere() {
        return atmosphere;
    }

    public static boolean getWater() {
        return water;
    }

    public static boolean getMercury() {
        return mercury;
    }
}
