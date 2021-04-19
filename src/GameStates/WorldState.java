package GameStates;

import backgrounds.WorldStateBack;
import elements.Hero;
import logic.GameLogic;
import managers.GameStateManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WorldState extends GameState {

    private Cursor myCursor;
    private Image cursorImg;

    {
        try {
            cursorImg = ImageIO.read(new File("src\\images\\menuCursor.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WorldStateBack background;

    public Cursor getCursor() {
        return myCursor;
    }


    public WorldState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }


    @Override
    public void init() {
        background = new WorldStateBack();
        Toolkit kit = Toolkit.getDefaultToolkit();
        BufferedImage bufferedImage = new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g3 = (Graphics2D) bufferedImage.getGraphics();
        g3.drawImage(cursorImg,0,0,16,16,null);
        myCursor = kit.createCustomCursor(bufferedImage, new Point(3,3), "myCursor");
        g3.dispose();
    }

    @Override
    public void update() {
        background.update();
        if(GameLogic.leftMouse){
            if(WorldStateBack.isWORLD) {
                GameLogic.gsm.setState(GameStateManager.WORLD);
            }
            if(WorldStateBack.isINVENTORY) {
                GameLogic.gsm.setState(GameStateManager.INVENTORY);
            }
            if(WorldStateBack.isTALENTS) {
                GameLogic.gsm.setState(GameStateManager.TALENTS);
            }
            if(WorldStateBack.isPLAY) {
                Hero.setX(GameLogic.WIDTH / 2);
                Hero.setY(GameLogic.HEIGHT - 20);
                GameLogic.gsm.setState(GameStateManager.PLAY);
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        background.draw(g);
    }
}
