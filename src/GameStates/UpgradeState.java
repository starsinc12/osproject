package GameStates;

import elements.Hero;
import logic.GameLogic;
import managers.GameStateManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class UpgradeState extends GameState {
    public UpgradeState(GameStateManager gsm) {
        super(gsm);
    }

    public static boolean isQuit = false;
    private Image backimg;

    private int counter = 0;

    @Override
    public void init() {

    }

    @Override
    public void update() {
        if (GameLogic.mouseX >= 210 && GameLogic.mouseX <= 590 && GameLogic.mouseY >= 410 && GameLogic.mouseY <= 490) {
            isQuit = true;
        } else {
            isQuit = false;
        }
        if (GameLogic.mouseX >= 250 && GameLogic.mouseX <= 375 && GameLogic.mouseY >= 295 && GameLogic.mouseY <= 435) {
            if (GameLogic.leftMouse && Hero.getSkillPoints() > 0) {
                Hero.setDamage(Hero.getDamage() + Hero.getDmgincres());
                Hero.setSkillPoints(Hero.getSkillPoints() - 1);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (GameLogic.mouseX >= 425 && GameLogic.mouseX <= 650 && GameLogic.mouseY >= 295 && GameLogic.mouseY <= 435) {
            if (GameLogic.leftMouse && Hero.getSkillPoints() > 0) {
                Hero.setProtection(Hero.getProtection() + 1);
                Hero.setSkillPoints(Hero.getSkillPoints() - 1);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        try {
            backimg = ImageIO.read(new File("src\\images\\backs\\upgrade.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        backimg = backimg.getScaledInstance(GameLogic.WIDTH, GameLogic.HEIGHT, Image.SCALE_DEFAULT);
        g.drawImage(backimg, 200, 200, 400, 300, null);


        g.setStroke(new BasicStroke(30));

        g.setColor(Color.GRAY);

        //КНОПКИ
        g.fillRect(210, 445, 380, 50);

        //КНОПКИ

        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.setColor(Color.BLACK);
        g.drawString("UPGRADE YOUR SKILLS", 220, 240);
        Integer ek = Hero.getSkillPoints();
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawString("Points : " + Integer.toString(ek), 220, 280);
        g.setColor(Color.WHITE);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g.drawString("QUIT", 360, 485);
        if (GameLogic.mouseX >= 210 && GameLogic.mouseX <= 590 && GameLogic.mouseY >= 445 && GameLogic.mouseY <= 490) {
            g.setColor(new Color(255, 255, 255, 60));
            g.fillRect(210, 445, 380, 50);
        }
        if (GameLogic.mouseX >= 250 && GameLogic.mouseX <= 375 && GameLogic.mouseY >= 295 && GameLogic.mouseY <= 435) {
            g.setColor(new Color(255, 255, 255, 60));
            g.fillRect(250, 295, 125, 140);
        }
        if (GameLogic.mouseX >= 425 && GameLogic.mouseX <= 650 && GameLogic.mouseY >= 295 && GameLogic.mouseY <= 435) {
            g.setColor(new Color(255, 255, 255, 60));
            g.fillRect(425, 295, 125, 140);
        }
    }

}
