package entities;


import java.awt.*;

import static utils.helpers.getLineAngle;

public class Player extends Entity{
    private RayCasts casts;
    private boolean up, down, left, right;
    private float playerSpeed = 0.4f;
    public float heading = 27.0f;
    public double mouseAngle = 0;


    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        casts = new RayCasts();

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
        for (int i = 0; i <45; i ++) {
            casts.updateCasts(g, x + (width/2f), y + (height/2f), mouseAngle);
//            i += 2;
        }
//        for (int j = 360; j > 315; j--) {
//            casts.newCast(g, x + (width/2f), y + (height/2f), j);
//            j += 2;
//        }

//        g.drawImage(sprite, (int) x, (int) y, width, height, null);
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
            mouseAngle = getLineAngle((x +(width/2f)), (y - (height/2f)), mouseX, mouseY);
        }
    }
