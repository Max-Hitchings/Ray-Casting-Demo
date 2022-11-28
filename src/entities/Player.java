package entities;


import main.Game;
import rayCasting.Ray;
import rayCasting.RayCaster;

import java.awt.*;
import java.awt.geom.Point2D;

import static utils.helpers.drawDebug;
import static utils.helpers.getLineAngle;

public class Player extends Entity{
    private RayCaster rayCaster;
    private boolean up, down, left, right;
    private final float playerSpeed = 0.75f;
    public Mouse mouse = new Mouse();


    public Player(Game game, float x, float y, int width, int height) {
        super(game, x, y, width, height);
        rayCaster = new RayCaster(game);
        mouse.pos = new Point2D.Double(0,0);
    }
    public void update() {
        updatePos();
    }

    private void updatePos() {
        if (left && !right) {
            x -= playerSpeed;
        } else if (right && !left) {
            x += playerSpeed;
        }

        if (up && !down) {
            y -= playerSpeed;
        } else if (down && !up) {
            y += playerSpeed;
        }
    }

    public void render(Graphics g) {
        Point2D.Double pos = new Point2D.Double(x+ (width/2f), y+ (height/2f));
        Ray r = new Ray(game.getGrid(), pos, mouse.angle, g);
        drawDebug(g, String.valueOf(-r.gradient), 100);
        drawDebug(g, String.valueOf(mouse.angle), 115);

//        drawCast(g, r.xLine, Color.BLUE);
//        drawCast(g, r.yLine, Color.ORANGE);
        drawDebug(g, String.valueOf(r.xNormalStep), 300);
        drawDebug(g, String.valueOf(r.yNormalStep), 315);        drawDebug(g, String.valueOf(r.xNormalStep), 300);
//        drawDebug(g, String.valueOf(r.xLine.getLen()), 315);
//        drawDebug(g, String.valueOf(r.yNormalStep), 315);


//        for (int i = 0; i <360; i += 1.1) {
//            var x = new Ray(game.getGrid(), pos, i);
//            drawCast(g, x.xLine, Color.BLUE);
////            drawCast(g, x.yLine, Color.ORANGE);
//        }

        g.setColor(new Color(255, 0, 0));
        g.fillOval((int) x,(int) y, width, height);
    }

    public void cancelMovement() {
        right = down = left = up = false;
    }
    public void setUp(boolean up) {
        this.up = up;
    }
    public void setDown(boolean down) {
        this.down = down;
    }
    public void setLeft(boolean left) {
        this.left = left;
    }
    public void setRight(boolean right) {
        this.right = right;
    }

    public void updateMouse(double mouseX, double mouseY) {
            mouse.pos.x = mouseX;
            mouse.pos.y = mouseY;
            mouse.angle = getLineAngle((x +(width/2f)), (y - (height/2f)), mouseX, mouseY);
        }
    }

    class Mouse {
        Point2D.Double pos;
        double angle;
    }