package utils;

import java.awt.*;

public class helpers {
    public static double getOppositeLength(double theta, double height, double halfWidth){
        double halfWay = Math.toDegrees(Math.atan(halfWidth / height));
        if (theta < halfWay) {
            return Math.tan(Math.toRadians(halfWay - theta)) * height;

        } else {
            return -(Math.tan(Math.toRadians(theta - halfWay)) * height);
        }
    }
    public static double getLineAngle(double startX, double startY, double endX, double endY) {
        return (Math.toDegrees(Math.atan2(startY - endY, startX - endX)) + 270)%360;
    }

    public static void drawDebug(Graphics g, String text, int offset) {
        g.setColor(Color.ORANGE);
        g.drawString(text, 5, 15 + offset);
    }
}
