package main;

import java.awt.*;

import static main.Game.*;

public class GameGrid {
    private String[] layout = {"################",
                    "#000000##",
                    "#00000000000000#",
                    "#00000000000000#",
                    "#00000000000000#",
                    "#00000000000000#",
                    "#00000000000000#",
                    "#00000000000000#",
                    "################"};
    private boolean[][] grid;
    public GameGrid() {
        constructGrid();
    }

    public void render(Graphics g) {
        g.setColor(new Color(0));
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x]) {
                    g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }
    }

    private void constructGrid() {
        grid = new boolean[TILES_IN_HEIGHT][TILES_IN_WIDTH];

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid.length; x++) {
                if (layout[y].charAt(x) == '#') {
                    grid[y][x] = true;
                } else grid[y][x] = false;
            }
        }
    }
}
