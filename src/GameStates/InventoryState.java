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

        if(GameLogic.mouseX >= 280 && GameLogic.mouseX <= 520 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
            isWORLD = true;
        } else {
            isWORLD = false;
        }
        if(GameLogic.mouseX >= 540 && GameLogic.mouseX <= 780 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
            isTALENTS = true;
        } else {
            isTALENTS = false;
        }
    }


    @Override
    public void draw(Graphics2D g) {
        g.drawImage(img,0,0, GameLogic.WIDTH,GameLogic.HEIGHT,null);

        if(GameLogic.mouseX >= 280 && GameLogic.mouseX <= 520 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
            g.setColor(new Color(255,255,255,60));
            g.fillRect(280,700, 240,100);
        }
        if(GameLogic.mouseX >= 540 && GameLogic.mouseX <= 780 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
            g.setColor(new Color(255,255,255,60));
            g.fillRect(540,700, 240,100);
        }
    }
}
