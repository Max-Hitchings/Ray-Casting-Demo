package entities;


import main.Game;
import rayCasting.RayCaster;

import java.awt.*;
import java.awt.geom.Point2D;

import static main.Game.*;
import static utils.helpers.*;

public class Player extends Entity{
    private RayCaster rayCaster;
    private boolean up, down, left, right;
    private final float playerSpeed = 0.33f;
    public Mouse mouse = new Mouse();
    private int fov;
    private double heading;

    public Player(Game game, int fov) {
        super(game, (TILE_SIZE/2)*2, (TILE_SIZE/2)*2, 10, 10);
        this.fov = 90;
        initClasses();
    }

    private void initClasses() {
        rayCaster = new RayCaster(game, fov, 10);
        mouse.pos = new Point2D.Double(0,0);
    }

    public void update() {
        updatePos();
        Point2D.Double pos = new Point2D.Double(x+ (width/2f), y+ (height/2f));
        rayCaster.update(pos, heading);
    }

    public void updateHeading(double headingDelta) {
        heading += headingDelta;

//        clamp heading to 0 <= heading <= 360
        if (heading < 0) {
            heading = 360 + heading;
        } else if (heading > 360) {
            heading -= 360;
        }
//        game.mouseRobot.mouseMove(GAME_WIDTH/2, GAME_HEIGHT/2);
    }

    private void updatePos() {
        if (left && !right) {
            move(Direction.LEFT);
        } else if (right && !left) {
            move(Direction.RIGHT);
        }

        if (up && !down) {
            move(Direction.FORWARD);
        } else if (down && !up) {
            move(Direction.BACKWARD);
        }
    }

    private void move(Direction direction) {
        int delta;
        switch (direction) {
            case FORWARD -> {
                x += Math.sin(Math.toRadians(heading)) * playerSpeed;
                y -= Math.cos(Math.toRadians(heading)) * playerSpeed;
            }
            case BACKWARD -> {
                x -= Math.sin(Math.toRadians(heading)) * playerSpeed;
                y += Math.cos(Math.toRadians(heading)) * playerSpeed;
            }
            case LEFT -> {
                x -= Math.sin(Math.toRadians(heading+90)) * playerSpeed;
                y += Math.cos(Math.toRadians(heading+90)) * playerSpeed;
            }
            case RIGHT -> {
                x += Math.sin(Math.toRadians(heading+90)) * playerSpeed;
                y -= Math.cos(Math.toRadians(heading+90)) * playerSpeed;
            }
            case default -> {
                return;
            }
        }
//        System.out.println(heading % 90);
//        gradient = -gradient;
//        hyp = player speed
//        y -= playerSpeed * gradient;
//        x -=  playerSpeed / gradient;
//        switch (direction) {
//            case FORWARD -> gradient = getGradientFromHeading(heading);
//            case BACKWARD -> gradient = getGradientFromHeading(heading+180);
//            case LEFT -> gradient = getGradientFromHeading(heading+270);
//            case RIGHT -> gradient = getGradientFromHeading(heading+90);
//            case default -> {
//                return;
//            }
//        }
    }

    enum Direction {
        FORWARD,
        BACKWARD,
        LEFT,
        RIGHT
    }

    public void render(Graphics g) {
        rayCaster.render(g);
        drawPlayer(g);
    }

    public void drawPlayer(Graphics g) {
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