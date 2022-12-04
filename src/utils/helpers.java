package utils;

import java.awt.*;
import java.awt.geom.Line2D;

public class helpers {
    public static double getGradient(double startX, double startY, double endX, double endY) {
        return (startY-endY) / (endX-startX);
    }
    public static double getLineAngle(double startX, double startY, double endX, double endY) {
        return (Math.toDegrees(Math.atan2(startY - endY, startX - endX)) + 270)%360;
    }

    public static void drawDebug(Graphics g, String text, int offset, Color c) {
        g.setColor(c);
        g.drawString(text, 5, 15 + offset);
    }public static void drawDebug(Graphics g, String text, int offset) {
        drawDebug(g, text, offset, Color.ORANGE);
    } public static void drawDebug(Graphics g, double text, int offset, Color c) {
        drawDebug(g, String.valueOf(text), offset, c);
    }
    public static void drawCast(Graphics g, Line2D.Double line, Color c) {
        g.setColor(c);
        g.drawLine((int) line.x1, (int) line.y1, (int) line.x2, (int) line.y2);
    }
    public static void drawCircle(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.drawOval(x-5, y-5, 10, 10);
    }
    public static void drawFillCircle(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.fillOval(x-5, y-5, 10, 10);
    }
}
