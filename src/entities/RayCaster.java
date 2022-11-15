package entities;

import java.awt.*;

import static main.Game.TILE_SIZE;
import static utils.helpers.*;

public class RayCaster {
    public void addCast(Graphics g, double startX, double startY, double heading) {
//        ty - Top Y
        double tyDelta =  -(startY % TILE_SIZE);
//        by - Bottom Y
        double byDelta = TILE_SIZE - (startY % TILE_SIZE);
//        rx - Right X
        double rxDelta = TILE_SIZE - (startX % TILE_SIZE);
//        lx - Left X
        double lxDelta = startX % TILE_SIZE;

//        to - Top Right
        double tr = getLineAngle(startX, startY, startX + rxDelta, startY + tyDelta);
//        br - Bottom Right
        double br = getLineAngle(startX, startY, startX + rxDelta, startY + byDelta);
//        bl - Bottom Left
        double bl = getLineAngle(startX, startY, startX - lxDelta, startY + byDelta);
//        tL - Top Left
        double tl = getLineAngle(startX, startY, startX - lxDelta, startY + tyDelta);

        double firstYStep, firstXStep, relativeAngle;
        String realHeading;
        if (heading > tl) {
            realHeading = "up";
            firstYStep = tyDelta;
            firstXStep = -(Math.tan(Math.toRadians(360 - heading)) * -tyDelta);

        } else if (heading > bl) {
            realHeading = "left";
            firstXStep = -lxDelta;
            relativeAngle = heading - bl;

            firstYStep = getOppositeLength(relativeAngle, lxDelta, byDelta);
        } else if (heading > br) {
            realHeading = "down";
            firstYStep = byDelta;
            relativeAngle = heading - br;

            firstXStep = getOppositeLength(relativeAngle, byDelta, rxDelta);
        }
        else if (heading > tr) {
            realHeading = "right";
            firstXStep = rxDelta;
            relativeAngle = heading - tr;

            firstYStep = -getOppositeLength(relativeAngle, rxDelta, -tyDelta);
        } else {
            realHeading = "up";
            firstYStep = tyDelta;
            firstXStep = -(Math.tan(Math.toRadians(heading)) * (tyDelta));
        }
        drawCast(g, new Point((int) startX, (int) startY) , new Point((int) (startX + firstXStep), (int) (startY + firstYStep)));

        drawDebug(g, String.valueOf(heading), 260);
        drawDebug(g, realHeading, 240);

    }
    private double getOppositeLength(double theta, double height, double halfWidth){
        double halfWay = Math.toDegrees(Math.atan(halfWidth / height));
        if (theta < halfWay) {
            return Math.tan(Math.toRadians(halfWay - theta)) * height;

        } else {
            return -(Math.tan(Math.toRadians(theta - halfWay)) * height);
        }
    }
    private void drawCast(Graphics g, Point start, Point end) {
        g.setColor(Color.GREEN);
        g.drawLine(start.x, start.y, end.x, end.y);
    }
}
