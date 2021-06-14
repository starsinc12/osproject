package GameStates;

import elements.Hero;
import logic.GameLogic;
import managers.GameStateManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
 * Класс описывающий состояние меню-мир.
 * Взаимодействие с пользователем(нажатие кнопок) в методе update(),
 * Отрисовка в методе Draw()
*/

public class WorldState extends GameState {

    private Image locationImg;
    private int locationNumber;


    public static Integer getLoctionNumber() {
        return loctionNumber;
    }

    public static void setLoctionNumber(Integer loctionNumber) {
        WorldState.loctionNumber = loctionNumber;
    }

    public static Integer loctionNumber=1;
    private String str;
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

    public WorldState(GameStateManager gameStateManager) {
        super(gameStateManager);
        locationNumber = 1;
    }




    @Override
    public void init() {
        String str = "src\\images\\maps\\location" + Integer.toString(loctionNumber) + "\\preview.png";
        try {
            backImg = ImageIO.read(new File("src\\images\\backs\\worldback.png"));
            locationImg = ImageIO.read(new File(str));
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
        str = "src\\images\\maps\\location" + Integer.toString(loctionNumber) + "\\preview.png";
        try {
            locationImg = ImageIO.read(new File(str));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(GameLogic.leftMouse) {
            if (GameLogic.mouseX >= 20 && GameLogic.mouseX <= 260 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
                isINVENTORY = true;
                GameLogic.gsm.setState(GameStateManager.INVENTORY);
            } else {
                isINVENTORY = false;
            }
            if (GameLogic.mouseX >= 540 && GameLogic.mouseX <= 780 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
                isTALENTS = true;
                GameLogic.gsm.setState(GameStateManager.TALENTS);
            } else {
                isTALENTS = false;
            }
            if (GameLogic.mouseX >= 260 && GameLogic.mouseX <= 540 && GameLogic.mouseY >= 175 && GameLogic.mouseY <= 455) {
                isPLAY = true;
                Hero.setX(GameLogic.WIDTH / 2);
                Hero.setY(GameLogic.HEIGHT - 20);
                GameLogic.gsm.setState(GameStateManager.PLAY);
            } else {
                isPLAY = false;
            }
            if (GameLogic.mouseX >= 648 && GameLogic.mouseX <= 799 && GameLogic.mouseY >= 0 && GameLogic.mouseY <= 151) {
                GameLogic.gsm.setSettings(true);
            }else{
                GameLogic.gsm.setSettings(false);
            }
            if (GameLogic.mouseX >= 600 && GameLogic.mouseX <= 799 && GameLogic.mouseY >= 274 && GameLogic.mouseY <= 360) {
                if(loctionNumber<2) {
                    loctionNumber++;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (GameLogic.mouseX >= 1 && GameLogic.mouseX <= 220 && GameLogic.mouseY >= 274 && GameLogic.mouseY <= 360) {
                if(loctionNumber>1) {
                    loctionNumber--;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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

        if (GameLogic.mouseX >= 540 && GameLogic.mouseX <= 780 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
            g.setColor(new Color(255, 255, 255, 60));
            g.fillRect(540, 700, 240, 100);
        }
        if (GameLogic.mouseX >= 648 && GameLogic.mouseX <= 799 && GameLogic.mouseY >= 0 && GameLogic.mouseY <= 151) {
            g.setColor(new Color(255, 255, 255, 60));
            g.fillRect(648, 0, 152, 151);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
        g.drawString("Location #"+Integer.toString(loctionNumber), 250,120);

        g.drawImage(locationImg,260,175,280,280,null);
        if (GameLogic.mouseX >= 260 && GameLogic.mouseX <= 540 && GameLogic.mouseY >= 175 && GameLogic.mouseY <= 455) {
            g.setColor(new Color(255, 255, 255, 60));
            g.fillRect(260, 175, 280, 280);
        }
        if (GameLogic.mouseX >= 600 && GameLogic.mouseX <= 799 && GameLogic.mouseY >= 274 && GameLogic.mouseY <= 360) {
            g.setColor(new Color(255, 255, 255, 60));
            g.fillRect(600, 274, 194, 86);
        }
        if (GameLogic.mouseX >= 1 && GameLogic.mouseX <= 220 && GameLogic.mouseY >= 274 && GameLogic.mouseY <= 360) {
            g.setColor(new Color(255, 255, 255, 60));
            g.fillRect(1, 274, 194, 86);
        }

    }
}