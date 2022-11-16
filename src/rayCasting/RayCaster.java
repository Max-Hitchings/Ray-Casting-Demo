package rayCasting;

import main.Game;

import java.awt.*;

import static main.Game.TILE_SIZE;
import static utils.helpers.*;
import static utils.Constants.rayDirection;
import static utils.Constants.gridBlocks;


public class RayCaster {
    Game game;
    public RayCaster(Game game) {
        this.game = game;
    }
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
        rayDirection realHeading;
        if (heading > tl) {
            realHeading = rayDirection.UP;
            firstYStep = tyDelta;
            relativeAngle = heading;
            firstXStep = -(Math.tan(Math.toRadians(360 - heading)) * -tyDelta);

        } else if (heading > bl) {
            realHeading = rayDirection.LEFT;
            firstXStep = -lxDelta;
            relativeAngle = heading - bl;

            firstYStep = getOppositeLength(relativeAngle, lxDelta, byDelta);
        } else if (heading > br) {
            realHeading = rayDirection.DOWN;
            firstYStep = byDelta;
            relativeAngle = heading - br;

            firstXStep = getOppositeLength(relativeAngle, byDelta, rxDelta);
        }
        else if (heading > tr) {
            realHeading = rayDirection.RIGHT;
            firstXStep = rxDelta;
            relativeAngle = heading - tr;

            firstYStep = -getOppositeLength(relativeAngle, rxDelta, -tyDelta);

        } else {
            realHeading = rayDirection.UP;
            firstYStep = tyDelta;
            relativeAngle = heading;
            firstXStep = -(Math.tan(Math.toRadians(heading)) * (tyDelta));
        }

        double finishX = startX + firstXStep;
        double finishY = startY + firstYStep;

//        TODO Fix this
        double gradient = getGradient(startX, startY, finishX, finishY);
        drawDebug(g, String.valueOf(gradient), 60);
        if (!checkCollision(g, finishX, finishY, realHeading)) {
            switch (realHeading) {
                case RIGHT ->  {
                    finishX += -(TILE_SIZE - (finishY % TILE_SIZE)) / gradient;
                    finishY += TILE_SIZE - (finishY % TILE_SIZE);
//                    finishX += Math.tan(Math.toRadians(180-heading)) * (finishY%16);
////                    finishX -= getOppositeLength(relativeAngle, TILE_SIZE, finishY%16);
//                    finishY += TILE_SIZE - ((startY + firstYStep) % TILE_SIZE);
                }
            }
        }
        drawCast(g, new Point((int) startX, (int) startY) , new Point((int) finishX, (int) finishY));

        drawDebug(g, String.valueOf(heading), 260);
        drawDebug(g, String.valueOf(realHeading), 240);

    }
    private boolean checkCollision(Graphics g, double x, double y, rayDirection direction) {
        x = x / TILE_SIZE;
        y = y / TILE_SIZE;
        gridBlocks block;
        switch (direction) {
            case UP -> {
                block = game.getGrid().getGridBlock((int) x, (int) y-1);
                drawDebug(g, String.valueOf(block), 2);
                return block == gridBlocks.WALL;
            }
            case DOWN -> {
                block = game.getGrid().getGridBlock((int) x, (int) y);
                drawDebug(g, String.valueOf(block), 15);
                return block == gridBlocks.WALL;
            }
            case RIGHT -> {
                block = game.getGrid().getGridBlock((int) x, (int) y);
                drawDebug(g, String.valueOf(block), 30);
                return block == gridBlocks.WALL;
            }
            case LEFT -> {
                block = game.getGrid().getGridBlock((int) x - 1, (int) y);
                drawDebug(g, String.valueOf(block), 45);
                return block == gridBlocks.WALL;
            }
        }
        return false;
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
