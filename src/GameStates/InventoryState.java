package GameStates;

import logic.GameLogic;
import managers.GameStateManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class InventoryState extends GameState {
    public InventoryState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }
    public static boolean isWORLD = false;
    public static boolean isINVENTORY = false;
    public static boolean isTALENTS = false;
    public static boolean isPLAY = false;
    Image img;
    {
        try{
            img= ImageIO.read(new File("src\\images\\backs\\inventory.png"));

        } catch (
    IOException e) {
        e.printStackTrace();
    } }

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
