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
    public static boolean isWORLD = false;
    public static boolean isINVENTORY = false;
    public static boolean isTALENTS = false;
    public static boolean isPLAY = false;
    Image img;
    {
        try{
            img= ImageIO.read(new File("src\\images\\backs\\characteristics.png"));

        } catch (
                IOException e) {
            e.printStackTrace();
        } }
    @Override
    public void init() {

    }

    @Override
    public void update() {
        if(GameLogic.mouseX >= 20 && GameLogic.mouseX <= 260 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
            isINVENTORY = true;
        } else {
            isINVENTORY = false;
        }
        if(GameLogic.mouseX >= 280 && GameLogic.mouseX <= 520 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
            isWORLD = true;
        } else {
            isWORLD = false;
        }

    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(img,0,0, GameLogic.WIDTH,GameLogic.HEIGHT,null);
        if(GameLogic.mouseX >= 20 && GameLogic.mouseX <= 260 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
            g.setColor(new Color(255,255,255,60));
            g.fillRect(20,700, 240,100);
        }
        if(GameLogic.mouseX >= 280 && GameLogic.mouseX <= 520 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
            g.setColor(new Color(255,255,255,60));
            g.fillRect(280,700, 240,100);
        }


    }
}
