package GameStates;

import elements.Hero;
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
    private Image pushkaimg;
    private Image pushkaimg2;
    private Image pushkaimgmain;
    private Image tankimg;
    private static boolean[] itemsArr;
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
        itemsArr=new boolean[4];
        for (int i = 0; i < itemsArr.length; i++) {
            itemsArr[i]=false;
        }
        itemsArr[Hero.getPushkaNumber()-1]=true;
        Toolkit kit = Toolkit.getDefaultToolkit();
        BufferedImage bufferedImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g3 = (Graphics2D) bufferedImage.getGraphics();
        g3.drawImage(cursorImg, 0, 0, 16, 16, null);
        myCursor = kit.createCustomCursor(bufferedImage, new Point(3, 3), "myCursor");
        g3.dispose();


    }

    @Override
    public void update() {

        if (GameLogic.leftMouse) {

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
            if (GameLogic.mouseX >= 5 && GameLogic.mouseX <= 130 && GameLogic.mouseY >=     5 && GameLogic.mouseY <= 127) {
                itemsArr[0]=true;
                Hero.setImgPushka(1);
            }
            if (GameLogic.mouseX >= 136 && GameLogic.mouseX <= 263 && GameLogic.mouseY >=     5 && GameLogic.mouseY <= 127) {
                itemsArr[1]=true;
                Hero.setImgPushka(2);
            }
        }
    }


    @Override
    public void draw(Graphics2D g) {
        String str="";
        backImg = backImg.getScaledInstance(GameLogic.WIDTH, GameLogic.HEIGHT, Image.SCALE_DEFAULT);
        g.drawImage(backImg, 0, 0, GameLogic.WIDTH, GameLogic.HEIGHT, null);
        try {
            tankimg = ImageIO.read(new File("src\\images\\tankbezpushki.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tankimg = tankimg.getScaledInstance(184, 336, Image.SCALE_DEFAULT);
        g.drawImage(tankimg, 580, 30, 184, 336, null);
        try {
            pushkaimg = ImageIO.read(new File("src\\images\\pushka1.png"));
            pushkaimg2 = ImageIO.read(new File("src\\images\\pushka2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        pushkaimg = pushkaimg.getScaledInstance(92, 164, Image.SCALE_DEFAULT);
        g.drawImage(pushkaimg,25,5,92,164,null);

        pushkaimg2 = pushkaimg2.getScaledInstance(92, 164, Image.SCALE_DEFAULT);
        g.drawImage(pushkaimg2,153,5,92,164,null);
        for (int i = 0; i < itemsArr.length; i++) {
            if(itemsArr[Hero.getPushkaNumber()-1]){
                str="src\\images\\pushka"+Integer.toString(Hero.getPushkaNumber())+".png";

                continue;
            }
            itemsArr[i]=false;
        }
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLUE);
        g.drawRect(5+(Hero.getPushkaNumber()-1)*133,5,123,125);
        try {
            pushkaimgmain=ImageIO.read(new File(str));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pushkaimgmain = pushkaimgmain.getScaledInstance(184, 328, Image.SCALE_DEFAULT);
        g.drawImage(pushkaimgmain, 580, 30, 184, 328, null);

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
