package GameStates;

import logic.GameLogic;
import managers.GameStateManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
 * Класс описывающий состояние меню-таланты.
 * Взаимодействие с пользователем(нажатие кнопок) в методе update(),
 * Отрисовка в методе Draw()
 * */

public class TalentsState extends GameState {


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

    public TalentsState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    @Override
    public void init() {
        try {
            backImg = ImageIO.read(new File("src\\images\\backs\\talents.png"));
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

            if (GameLogic.mouseX >= 20 && GameLogic.mouseX <= 260 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
                isINVENTORY = true;
                GameLogic.gsm.setState(GameStateManager.INVENTORY);
            } else {
                isINVENTORY = false;
            }


        }
    }

    @Override
    public void draw(Graphics2D g) {
        backImg = backImg.getScaledInstance(GameLogic.WIDTH,GameLogic.HEIGHT, Image.SCALE_DEFAULT);
        g.drawImage(backImg, 0,0, GameLogic.WIDTH, GameLogic.HEIGHT,null);

        if (GameLogic.mouseX >= 20 && GameLogic.mouseX <= 260 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
            g.setColor(new Color(255, 255, 255, 60));
            g.fillRect(20, 700, 240, 100);
        }
        if (GameLogic.mouseX >= 280 && GameLogic.mouseX <= 520 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
            g.setColor(new Color(255, 255, 255, 60));
            g.fillRect(280, 700, 240, 100);
        }
    }
}
