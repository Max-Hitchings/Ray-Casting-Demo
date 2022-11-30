package rayCasting;

import main.Game;

import java.awt.*;
import java.awt.geom.Point2D;

public class RayCaster {
    Ray[] rays;
    Game game;
    Point2D.Double origin;
    double heading;
    int fov;
    public RayCaster(Game game, int fov) {
        this.game = game;
        this.fov = fov;
        rays = new Ray[fov];

        for (int i = 0; i < fov; i++) {
            rays[i] = new Ray(game.getGrid(), i);
        }
    }

    public void update(Point2D.Double newOrigin, double newHeading) {
        origin = newOrigin;
        heading = newHeading;
        for (Ray ray : rays) {
            ray.update(origin, heading);
        }
    }

    public void render(Graphics g) {
        for (Ray ray : rays) {
            ray.draw(g);
        }
    }

    private void updateCasts() {

    }
    public void addCast(double startX, double startY, double heading) {}
}
