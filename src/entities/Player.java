package entities;


import java.awt.*;

public class Player extends Entity{
    private boolean up, down, left, right;
    private float playerSpeed = 2.0f;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
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
}