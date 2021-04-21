package backgrounds;

import logic.GameLogic;
import managers.GameStateManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MenuStateBack {

    private Image backImg;

    public void setBackImg(Image backImg) {
        this.backImg = backImg;
    }

    public static boolean isWORLD = false;
    public static boolean isINVENTORY = false;
    public static boolean isTALENTS = false;
    public static boolean isPLAY = false;


    public void update() {
        if(GameLogic.gsm.getCurrentState() != GameStateManager.INVENTORY) {
            if (GameLogic.mouseX >= 20 && GameLogic.mouseX <= 260 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
                isINVENTORY = true;
            } else {
                isINVENTORY = false;
            }
        } else {
            isINVENTORY = false;
        }
        if(GameLogic.gsm.getCurrentState() != GameStateManager.WORLD && GameLogic.gsm.getCurrentState() != GameStateManager.MENU) {
            if (GameLogic.mouseX >= 280 && GameLogic.mouseX <= 520 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
                isWORLD = true;
            } else {
                isWORLD = false;
            }
        } else {
            isWORLD = false;
        }
        if(GameLogic.gsm.getCurrentState() != GameStateManager.TALENTS) {
            if (GameLogic.mouseX >= 540 && GameLogic.mouseX <= 780 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
                isTALENTS = true;
            } else {
                isTALENTS = false;
            }
        } else {
            isTALENTS = false;
        }
        if(GameLogic.gsm.getCurrentState() == GameStateManager.WORLD || GameLogic.gsm.getCurrentState() == GameStateManager.MENU) {
            if (GameLogic.mouseX >= 260 && GameLogic.mouseX <= 540 && GameLogic.mouseY >= 175 && GameLogic.mouseY <= 455) {
                isPLAY = true;
            } else {
                isPLAY = false;
            }
        } else {
            isPLAY = false;
        }
    }

    public void draw(Graphics2D g) {
        backImg = backImg.getScaledInstance(GameLogic.WIDTH,GameLogic.HEIGHT, Image.SCALE_DEFAULT);
        g.drawImage(backImg, 0,0, GameLogic.WIDTH,GameLogic.HEIGHT,null);
        if(GameLogic.gsm.getCurrentState() != GameStateManager.INVENTORY) {
            if (GameLogic.mouseX >= 20 && GameLogic.mouseX <= 260 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
                g.setColor(new Color(255, 255, 255, 60));
                g.fillRect(20, 700, 240, 100);
            }
        }
        if(GameLogic.gsm.getCurrentState() != GameStateManager.WORLD && GameLogic.gsm.getCurrentState() != GameStateManager.MENU) {
            if (GameLogic.mouseX >= 280 && GameLogic.mouseX <= 520 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
                g.setColor(new Color(255, 255, 255, 60));
                g.fillRect(280, 700, 240, 100);
            }
        }
        if(GameLogic.gsm.getCurrentState() != GameStateManager.TALENTS) {
            if (GameLogic.mouseX >= 540 && GameLogic.mouseX <= 780 && GameLogic.mouseY >= 700 && GameLogic.mouseY <= GameLogic.HEIGHT) {
                g.setColor(new Color(255, 255, 255, 60));
                g.fillRect(540, 700, 240, 100);
            }
        }
        if(GameLogic.gsm.getCurrentState() == GameStateManager.WORLD || GameLogic.gsm.getCurrentState() == GameStateManager.MENU) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
            g.drawString("Location #1", 250,120);
            if (GameLogic.mouseX >= 260 && GameLogic.mouseX <= 540 && GameLogic.mouseY >= 175 && GameLogic.mouseY <= 455) {
                g.setColor(new Color(255, 255, 255, 60));
                g.fillRect(260, 175, 280, 280);
            }
        }
    }
}
