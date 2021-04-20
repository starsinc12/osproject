package backgrounds;

import logic.GameLogic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WorldStateBack {

    private Image backImg;
    public static boolean isWORLD = false;
    public static boolean isINVENTORY = false;
    public static boolean isTALENTS = false;
    public static boolean isPLAY = false;

    {
        try {
            backImg = ImageIO.read(new File("src\\images\\backs\\worldback.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(GameLogic.mouseX >= 20 && GameLogic.mouseX <= 260 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
            isINVENTORY = true;
        } else {
            isINVENTORY = false;
        }
        if(GameLogic.mouseX >= 540 && GameLogic.mouseX <= 780 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
            isTALENTS = true;
        } else {
            isTALENTS = false;
        }
        if(GameLogic.mouseX >= 260 && GameLogic.mouseX <= 540 && GameLogic.mouseY >= 175 && GameLogic.mouseY <= 455) {
            isPLAY = true;
        } else {
            isPLAY = false;
        }
    }

    public void draw(Graphics2D g) {
        backImg = backImg.getScaledInstance(GameLogic.WIDTH,GameLogic.HEIGHT, Image.SCALE_DEFAULT);
        g.drawImage(backImg, 0,0, GameLogic.WIDTH,GameLogic.HEIGHT,null);
        if(GameLogic.mouseX >= 20 && GameLogic.mouseX <= 260 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
            g.setColor(new Color(255,255,255,60));
            g.fillRect(20,700, 240,100);
        }
        if(GameLogic.mouseX >= 540 && GameLogic.mouseX <= 780 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
            g.setColor(new Color(255,255,255,60));
            g.fillRect(540,700, 240,100);
        }
        if(GameLogic.mouseX >= 260 && GameLogic.mouseX <= 540 && GameLogic.mouseY >= 175 && GameLogic.mouseY <= 455) {
            g.setColor(new Color(255,255,255,60));
            g.fillRect(260,175, 280,280);
        }
    }
}
