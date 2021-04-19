package GameStates;

import backgrounds.MenuStateBack;
import elements.Hero;
import logic.GameLogic;
import managers.GameStateManager;

import java.awt.*;

public class WorldState extends GameState {


    public WorldState(GameStateManager gameStateManager) {
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
