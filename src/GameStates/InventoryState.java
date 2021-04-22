package GameStates;

import logic.GameLogic;
import managers.GameStateManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InventoryState extends GameState {

    public InventoryState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }


    private Cursor myCursor;

    public Cursor getCursor() {
        return myCursor;
    }

    private Image cursorImg;
    private Image backImg;
    public static boolean isWORLD = false;
    public static boolean isINVENTORY = false;
    public static boolean isTALENTS = false;
    public static boolean isPLAY = false;


    @Override
    public void init() {
        try {
            backImg = ImageIO.read(new File("src\\images\\backs\\inventory.png"));
            cursorImg = ImageIO.read(new File("src\\images\\menuCursor.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toolkit kit = Toolkit.getDefaultToolkit();
        BufferedImage bufferedImage = new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g3 = (Graphics2D) bufferedImage.getGraphics();
        g3.drawImage(cursorImg,0,0,16,16,null);
        myCursor = kit.createCustomCursor(bufferedImage, new Point(3,3), "myCursor");
        g3.dispose();


    }

    @Override
    public void update() {

        if(GameLogic.leftMouse) {

            if (GameLogic.mouseX >= 280 && GameLogic.mouseX <= 520 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
                isWORLD = true;
                GameLogic.gsm.setState(GameStateManager.WORLD);
            } else {
                isWORLD = false;
            }

            if (GameLogic.mouseX >= 540 && GameLogic.mouseX <= 780 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
                isTALENTS = true;
                GameLogic.gsm.setState(GameStateManager.TALENTS);
            } else {
                isTALENTS = false;
            }
        }
    }


    @Override
    public void draw(Graphics2D g) {
        backImg = backImg.getScaledInstance(GameLogic.WIDTH,GameLogic.HEIGHT, Image.SCALE_DEFAULT);
        g.drawImage(backImg, 0,0, GameLogic.WIDTH, GameLogic.HEIGHT,null);

        if (GameLogic.mouseX >= 280 && GameLogic.mouseX <= 520 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
            g.setColor(new Color(255, 255, 255, 60));
            g.fillRect(280, 700, 240, 100);
        }
        if (GameLogic.mouseX >= 540 && GameLogic.mouseX <= 780 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
            g.setColor(new Color(255, 255, 255, 60));
            g.fillRect(540, 700, 240, 100);
        }
    }
}
