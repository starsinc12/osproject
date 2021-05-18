package GameStates;

import logic.GameLogic;
import managers.GameStateManager;

import javax.swing.*;
import java.awt.*;

public class SettingState extends GameState{
    public static boolean isQuit = false;
    public static boolean valueup = false;
    public static boolean valuedown = false;
    public SettingState(GameStateManager gsm) {
        super(gsm);
    }

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
        if (GameLogic.mouseX >= 569 && GameLogic.mouseX <= 599 && GameLogic.mouseY >= 325 && GameLogic.mouseY <= 355) {
            if(GameLogic.volume<0.99) {
               valueup=true;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }else{
            valueup=false;
        }
        if(GameLogic.mouseX >= 519 && GameLogic.mouseX <= 549 && GameLogic.mouseY >= 325 && GameLogic.mouseY <= 355) {
            if(GameLogic.volume>0.01) {
                valuedown=true;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }else{
                valuedown=false;
            }

    }

    @Override
    public void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(30));
        g.setColor(Color.WHITE);
        g.fillRect(200,200,400,300);
        g.setColor(Color.GRAY);

        //КНОПКИ
        g.fillRect(210,410,380,80);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 50));

        g.drawString("GAME OVER",250,260);
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
        g.drawString("QUIT",330,475);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.setColor(Color.BLACK);
        //МУЗЫКА
        int volumesettigs=(int)(GameLogic.volume*100);
        String volume=Integer.toString(volumesettigs);
        g.drawString("VOLUME: "+volume+"%",240,350);
        g.setColor(Color.BLACK);
        g.fillRect(450,335,100,5);
        g.setColor(Color.RED);
        g.fillRect(480,332,5,10);
        if(GameLogic.mouseX >= 210 && GameLogic.mouseX <= 590 && GameLogic.mouseY >= 410 && GameLogic.mouseY <= 490){

        }
            if(GameLogic.mouseX >= 210 && GameLogic.mouseX <= 590 && GameLogic.mouseY >= 410 && GameLogic.mouseY <= 490) {
            g.setColor(new Color(255,255,255,60));
            g.fillRect(210,410,380,80);
        }
    }
}
