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
    public void addCast(Graphics g, double startX, double startY, double heading) {}
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
}
