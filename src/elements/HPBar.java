package elements;

import logic.GameLogic;

import java.awt.*;

public class HPBar {

    private int hpToPix;
    public boolean isHeroDead;



    public void update() {
        hpToPix = (int) (Hero.getHealth() / Hero.getMaxHealth() * 200);
        if(hpToPix == 0) {
            isHeroDead = true;
        } else isHeroDead = false;
    }

    public  void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,GameLogic.HEIGHT - 210, 30,210);
        g.setColor(Color.RED);
        g.fillRect(5,GameLogic.HEIGHT - 205, 20,200);
        g.setColor(Color.GREEN);
        g.fillRect(5,GameLogic.HEIGHT - 5 - hpToPix, 20, hpToPix);
    }
}

