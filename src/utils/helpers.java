package utils;

import java.awt.*;

public class helpers {
    public static double getLineAngle(double startX, double startY, double endX, double endY) {
        return (Math.toDegrees(Math.atan2(startY - endY, startX - endX)) + 270)%360;
    }

    public static void drawDebug(Graphics g, String text, int offset) {
        g.setColor(Color.ORANGE);
        g.drawString(text, 5, 15 + offset);
    }
}
