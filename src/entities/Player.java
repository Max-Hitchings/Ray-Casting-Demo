package entities;


import main.Game;
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

    private int fov;


    public Player(Game game, float x, float y, int width, int height, int fov) {
        super(game, x, y, width, height);
        this.fov = fov;
        initClasses();
    }

    private void initClasses() {
        rayCaster = new RayCaster(game, fov);
        mouse.pos = new Point2D.Double(0,0);
    }

    public void update() {
        updatePos();
        Point2D.Double pos = new Point2D.Double(x+ (width/2f), y+ (height/2f));
        rayCaster.update(pos, mouse.angle);
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
        rayCaster.render(g);
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