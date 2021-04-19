package GameStates;

import logic.GameLogic;
import managers.GameStateManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TalentsState extends GameState {

    public TalentsState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        GameLogic.gsm.getGameStates()[GameStateManager.MENU].update();
    }

    @Override
    public void draw(Graphics2D g) {
        GameLogic.gsm.getGameStates()[GameStateManager.MENU].draw(g);
    }
}
