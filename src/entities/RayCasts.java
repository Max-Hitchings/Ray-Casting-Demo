package entities;

import java.awt.*;
import java.awt.geom.Point2D;

import static main.Game.TILE_SIZE;
import static utils.helpers.drawDebug;
import static utils.helpers.getLineAngle;

public class RayCasts {
//    float heading = 25f;
    public RayCasts() {
//        newCast(g, player.x, player.y, player.heading);
    }

    public void updateCasts(Graphics g, double startX, double startY, double heading) {
        Point2D startPos = new Point2D.Double(startX, startY);
        //        ty - Top Y
        double tyDelta =  -(startY % TILE_SIZE);
//        by - Bottom Y
        double byDelta = TILE_SIZE - (startY % TILE_SIZE);
//        rx - Right X
        double rxDelta = TILE_SIZE - (startX % TILE_SIZE);
//        lx - Left X
        double lxDelta = startX % TILE_SIZE;

//        heading = 30;
        double tr = getLineAngle(startX, startY, startX + rxDelta, startY + tyDelta);
        double br = getLineAngle(startX, startY, startX + rxDelta, startY + byDelta);

        double bl = getLineAngle(startX, startY, startX - lxDelta, startY + byDelta);
        double tl = getLineAngle(startX, startY, startX - lxDelta, startY + tyDelta);
        double realRayAngle = heading;

        double firstYStep=0, firstXStep = 0, theta = 0, relativeAngle;
        String realHeading;
        if (realRayAngle > tl) {
            realHeading = "up";
            firstYStep = tyDelta;
            firstXStep = -(Math.tan(Math.toRadians(360 - realRayAngle)) * -tyDelta);

        } else if (realRayAngle > bl) {
//            theta = tl - realRayAngle;
//            if (theta > 90) theta = theta - 90;
            realHeading = "left";
            firstXStep = -lxDelta;

            relativeAngle = realRayAngle - bl;
            double halfWay = Math.toDegrees(Math.atan(byDelta / lxDelta));
            if (relativeAngle < halfWay) {
                relativeAngle = halfWay - relativeAngle;
                firstYStep = (Math.tan(Math.toRadians(relativeAngle)) * lxDelta);

            } else {
                relativeAngle = relativeAngle - halfWay;
                firstYStep = -(Math.tan(Math.toRadians(relativeAngle)) * lxDelta);
            }

        } else if (realRayAngle > br) {
            realHeading = "down";
            firstYStep = byDelta;

            relativeAngle = realRayAngle - br;
            double halfWay = Math.toDegrees(Math.atan(rxDelta / byDelta));
            if (relativeAngle < halfWay) {
                relativeAngle = halfWay - relativeAngle;
                firstXStep = (Math.tan(Math.toRadians(relativeAngle)) * byDelta);

            } else {
                relativeAngle = relativeAngle - halfWay;
                firstXStep = -(Math.tan(Math.toRadians(relativeAngle)) * byDelta);
            }
        }
        else if (realRayAngle > tr) {
            realHeading = "right";
            firstXStep = rxDelta;

            relativeAngle = realRayAngle-tr;
            double test = realRayAngle - tr;
            double halfWay = Math.toDegrees(Math.atan(tyDelta / rxDelta));
            if (test < halfWay) {
                test = halfWay - test;
                firstYStep = (Math.tan(Math.toRadians(test)) * rxDelta);
//                firstYStep = 1;

            } else {
                test = test - halfWay;
                firstYStep = (Math.tan(Math.toRadians(test)) * rxDelta);
//                firstYStep = 45;
            }
            drawDebug(g, String.valueOf(tr), 10);
            drawDebug(g, String.valueOf(halfWay), 25);
            drawDebug(g, String.valueOf(test), 50);

        } else {
            realHeading = "up";
            firstYStep = tyDelta;
            firstXStep = -(Math.tan(Math.toRadians(realRayAngle)) * (tyDelta));

        }
        drawCast(g, new Point((int) startX, (int) startY) , new Point((int) (startX + firstXStep), (int) (startY + firstYStep)));


        drawDebug(g, String.valueOf(theta), 180);
        drawDebug(g, String.valueOf(firstYStep), 200);
        drawDebug(g, String.valueOf(firstXStep), 220);
        drawDebug(g, String.valueOf(heading), 260);
        drawDebug(g, realHeading, 240);

    }
    private void drawCast(Graphics g, Point start, Point end) {
//        heading += 0.5f;
//        if (heading > 360) { heading = 0f;}
        g.setColor(Color.GREEN);
        g.drawLine(start.x, start.y, end.x, end.y);
        g.setColor(Color.ORANGE);
//        g.drawString(String.valueOf(heading), 5, 20);
//        g.drawString();
    }
}
