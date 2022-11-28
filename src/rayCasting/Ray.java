package rayCasting;

import main.GameGrid;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import static main.Game.TILE_SIZE;
import static utils.helpers.*;

public class Ray {
    GameGrid gameGrid;
    //        ty - Top Y
    //        by - Bottom Y
    //        rx - Right X
    //        lx - Left X
    double tyDelta, byDelta, rxDelta, lxDelta;
    public double gradient, heading;
    public double xNormalStep, yNormalStep;
    public Line xLine, yLine;
    int xDir, yDir;
    public Ray(GameGrid gameGrid, Point2D.Double origin, double heading, Graphics g) {
        this.gameGrid = gameGrid;
        tyDelta =  -(origin.y % TILE_SIZE);
        byDelta = TILE_SIZE - (origin.y % TILE_SIZE);
        rxDelta = TILE_SIZE - (origin.x % TILE_SIZE);
        lxDelta = origin.x % TILE_SIZE;
        this.heading = heading;
        gradient = getGradient(heading);
        xLine = new Line(origin, origin);
        yLine = new Line(origin, origin);


        firstStep();

        boolean endFlag = false;
        while (!endFlag) {
            if (xLine.len < (10*TILE_SIZE) && yLine.len < (10*TILE_SIZE)){
                if (yLine.len > xLine.len) {
                    if (!checkCollisionX(xLine.x2, xLine.y2, g)) {
                        stepX();
                    } else {
//                        drawDebug(g, xLine.len + " x", 200);

                        endFlag = true;
                    }
                } else {
                    if (!checkCollisionY(yLine.x2, yLine.y2, g)) {
                        stepY();
                    } else {
//                        drawDebug(g, yLine.len + " y", 215);
                        endFlag = true;
                    }
                }
            } else {
                endFlag = true;
            }
        }
        if (yLine.len > xLine.len) {
            drawCast(g, xLine, Color.BLUE);
        } else {
            drawCast(g, yLine, Color.RED);
        }
        drawDebug(g, yLine.len + " y", 215);
        drawDebug(g, xLine.len + " x", 200);
    }

    private void step() {
        if (yLine.len > xLine.len) {
            stepX();
        } else {
            stepY();
        }
    }

    private void stepX() {
        xLine.x1 += TILE_SIZE * xDir;
        xLine.y1 += TILE_SIZE * gradient * xDir;
        xLine.len += xNormalStep;
    }

    private void stepY() {
        yLine.y2 += TILE_SIZE * yDir;
        yLine.x2 += TILE_SIZE / gradient * yDir;
        xLine.len += yNormalStep;
    }

    private void firstStep() {
        double xDelta, yDelta;
        if (xDir == 1) {
            xDelta = rxDelta;
        } else {
            xDelta = -lxDelta;
        }
        if (heading > 90 && heading < 270) {
            yDelta =  byDelta;
        } else {
            yDelta = tyDelta;
        }

        xNormalStep = Math.sqrt(((TILE_SIZE)*(TILE_SIZE)) + ((gradient*TILE_SIZE)*(gradient*TILE_SIZE)));
        yNormalStep = Math.sqrt(((TILE_SIZE)*(TILE_SIZE)) + ((TILE_SIZE / gradient)*(TILE_SIZE / gradient)));

        xLine.x2 += xDelta;
        xLine.y2 += xDelta * gradient;
        xLine.len += getLineLength(xLine);

        yLine.y2 += yDelta;
        yLine.x2 += yDelta / gradient;
        yLine.len += getLineLength(yLine);
    }

    private boolean checkCollisionX(double x, double y, Graphics g) {
        if (gameGrid.checkCollision((int) Math.floor((x / TILE_SIZE)+xDir), (int) Math.floor(y / TILE_SIZE))) {
            drawCircle(g, (int)x, (int)y, Color.CYAN);
        } else {
            drawFillCircle(g, (int)x, (int)y, Color.CYAN);
        }
        return gameGrid.checkCollision((int) Math.floor((x / TILE_SIZE)+xDir), (int) Math.floor(y / TILE_SIZE));
    }
    private boolean checkCollisionY(double x, double y, Graphics g) {
        if (gameGrid.checkCollision((int) Math.floor(x / TILE_SIZE), (int) Math.floor((y / TILE_SIZE) - yDir))) {
            drawCircle(g, (int)x, (int)y, Color.GREEN);
        } else {
            drawFillCircle(g, (int)x, (int)y, Color.GREEN);
        }

        return gameGrid.checkCollision((int) Math.floor(x / TILE_SIZE), (int) Math.floor((y / TILE_SIZE) - yDir));
    }

    private double getLineLength(Line line) {
        return Math.sqrt(((line.x2-line.x1)*(line.x2-line.x1)) + ((line.y2-line.y1)*(line.y2-line.y1)));
    }

    private double getGradient(double heading) {
        if (heading == 180 || heading == 0 ) return Double.MAX_VALUE;

        double tan = Math.tan(Math.toRadians(heading + 90));
        if (heading < 180) {
            xDir = 1;
        } else {
            xDir = -1;
        }

        if (heading > 270 || heading < 90) {
            yDir = -1;
        } else {
            yDir = 1;
        }
        return tan;
    }
}

class Line extends Line2D.Double {
    public double len;

    public Line(Point2D.Double start, Point2D.Double end) {
        super(start, end);
    }

    public double getLen() {
        return len;
    }
}