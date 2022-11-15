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

        double firstYStep=0, firstXStep = 0, theta = 0;
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

//            firstXStep = lxDelta;
//            firstYStep = Math.tan(Math.toRadians(tl - realRayAngle)) * lxDelta;
//            firstYStep = -((TILE_SIZE/2f) - firstYStep);

//            firstYStep =
        } else if (realRayAngle > br) {
            realHeading = "down";
            firstYStep = byDelta;

            double relativeAngle = realRayAngle - br;
            double halfWay = Math.toDegrees(Math.atan(rxDelta / byDelta));
            if (relativeAngle < halfWay) {
                relativeAngle = halfWay - relativeAngle;
                firstXStep = (Math.tan(Math.toRadians(relativeAngle)) * byDelta);

            } else {
                relativeAngle = relativeAngle - halfWay;
                firstXStep = -(Math.tan(Math.toRadians(relativeAngle)) * byDelta);
            }
//            drawDebug(g, String.valueOf(relativeAngle + ((bl - br)/2)), 25);
//            if (relativeAngle < (bl - br)/2){
//                firstXStep = (TILE_SIZE/2) - (Math.tan(Math.toRadians(relativeAngle)) * byDelta);
//            } else {
//
////                double angle = ((bl - br)/2) - relativeAngle;
//                double angle = relativeAngle % ((bl - br)/2);
//                drawDebug(g, String.valueOf(angle), 50);
//                relativeAngle = relativeAngle - (bl - br)/2;
//                firstXStep = (lxDelta-(Math.tan(Math.toRadians(angle)) * byDelta));
//            }
        }
        else if (realRayAngle > tr) {
            realHeading = "right";
            firstXStep = rxDelta;

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
