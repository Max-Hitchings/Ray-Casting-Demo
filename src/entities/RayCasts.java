package entities;

import java.awt.*;

import static main.Game.TILE_SIZE;
import static utils.helpers.drawDebug;
import static utils.helpers.getLineAngle;

public class RayCasts {
//    float heading = 25f;
    public RayCasts() {
//        newCast(g, player.x, player.y, player.heading);
    }

    public void updateCasts(Graphics g, float startX, float startY, double heading) {
        drawDebug(g, String.valueOf(heading), 300);
        Point yCast, xCast;
        double firstYStep;

        double tyDelta =  -(startY % TILE_SIZE);
        double byDelta = TILE_SIZE - (startY % TILE_SIZE);
        double rxDelta = TILE_SIZE - (startX % TILE_SIZE);
        double lxDelta = startX % TILE_SIZE;
        drawDebug(g, String.valueOf(tyDelta), 350);
        drawDebug(g, String.valueOf(byDelta), 375);
        drawDebug(g, String.valueOf(rxDelta), 400);
        drawDebug(g, String.valueOf(lxDelta), 425);

//      TODO These 4 are wrong and need fixing
        double trTheta = Math.abs(Math.toDegrees(Math.atan(rxDelta/tyDelta)));
        double tlTheta = Math.abs(Math.toDegrees(Math.atan(lxDelta/tyDelta)));

        double brTheta = Math.abs(Math.toDegrees(Math.atan(rxDelta/byDelta)));
        double blTheta = Math.abs(Math.toDegrees(Math.atan(lxDelta/byDelta)));

        double down =  brTheta + blTheta;
        double up = trTheta + tlTheta;
        drawDebug(g, String.valueOf(up), 80);
        drawDebug(g, String.valueOf(down), 95);

        double rtTheta = Math.abs(Math.toDegrees(Math.atan(tyDelta/rxDelta)));
        double rbTheta = Math.abs(Math.toDegrees(Math.atan(byDelta/rxDelta)));
        double right = rbTheta + rtTheta;

        double ltTheta = Math.abs(Math.toDegrees(Math.atan(tyDelta/lxDelta)));
        double lbTheta = Math.abs(Math.toDegrees(Math.atan(byDelta/lxDelta)));
        double left = ltTheta + lbTheta;
        drawDebug(g, String.valueOf(right), 110);
        drawDebug(g, String.valueOf(left), 125);

        double total = up + down + right + left;
        drawDebug(g, String.valueOf(total), 160);
        drawDebug(g, String.valueOf(down+up), 180);
        drawDebug(g, String.valueOf(right + left), 200);

//        heading = 30;
        double tr = getLineAngle(startX, startY, startX + rxDelta, startY + tyDelta);
        double br = getLineAngle(startX, startY, startX + rxDelta, startY + byDelta);

        double bl = getLineAngle(startX, startY, startX - lxDelta, startY + byDelta);
        double tl = getLineAngle(startX, startY, startX - lxDelta, startY + tyDelta);
        double realRayAngle = heading;
        String realHeading;
        if (realRayAngle > tl) {
            realHeading = "up";
        } else if (realRayAngle > bl) {
            realHeading = "left";
        } else if (realRayAngle > br) {
            realHeading = "down";
        } else if (realRayAngle > tr) {
            realHeading = "right";
        } else {
            realHeading = "up";
        }

        drawDebug(g, String.valueOf(heading), 220);
        drawDebug(g, realHeading, 240);


        //      Top right:
//        drawDebug(g, String.valueOf(trTheta), 0);
        drawCast(g, new Point((int)startX, (int)startY),new Point((int) (startX + rxDelta), (int) (startY + tyDelta)));

//        Bottom Right
//        drawDebug(g, String.valueOf(brTheta), 15);
        drawCast(g, new Point((int)startX, (int)startY),new Point((int) (startX + rxDelta), (int) (startY + byDelta)));


//        Bottom left
//        drawDebug(g, String.valueOf(blTheta), 30);
        drawCast(g, new Point((int)startX, (int)startY),new Point((int) (startX - lxDelta), (int) (startY + byDelta)));

//        Top Left
//        drawDebug(g, String.valueOf(tlTheta), 45);
        drawCast(g, new Point((int)startX, (int)startY),new Point((int) (startX - lxDelta), (int) (startY + tyDelta)));

        int theta = 1;
//        Top and bottom cone work x cones need work
        if (heading < theta) {
            firstYStep = startY % TILE_SIZE;
//        } else {
//            firstYStep = TILE_SIZE - (startY % TILE_SIZE);
//            return;
//        }

            double second = firstYStep / Math.cos(Math.toRadians(heading));
            double yStep = TILE_SIZE / Math.cos(Math.toRadians(heading));
            double xStep = Math.sqrt((yStep * yStep) - (TILE_SIZE * TILE_SIZE));

            if (heading > 180) {
                xStep = -xStep;
            }

//            drawCast(g, new Point((int) startX, (int) startY), new Point((int) startX + (int) xStep, (int) startY - (int) second));
        }
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
