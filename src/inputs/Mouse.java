package inputs;

import main.Game;

import java.awt.*;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static utils.Constants.MOUSE;

public class Mouse {
    public Robot robot;
    public State state;
    private double oldX, newX, deltaX;
    Game game;
    float sensitivity = MOUSE.SENSITIVITY;

    public Mouse(Game game) throws AWTException {
        this.game = game;
        robot = new Robot();
    }

    public void update() {
        PointerInfo info = MouseInfo.getPointerInfo();
        newX = info.getLocation().x;
        deltaX = (newX - oldX) * sensitivity;
        oldX = GAME_WIDTH/2;
        if (state == State.LOCKED) {
            game.getPlayer().updateHeading(deltaX);
            robot.mouseMove(GAME_WIDTH/2, GAME_HEIGHT/2);
        }

    }

    public void lock() {
        state = State.LOCKED;
    }

    public void unLock() {
        state = State.UNLOCKED;
    }
    enum State {
        LOCKED,
        UNLOCKED
    }
}

