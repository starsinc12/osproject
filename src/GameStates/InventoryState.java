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

    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(img,0,0, GameLogic.WIDTH,GameLogic.HEIGHT,null);
    }
}
