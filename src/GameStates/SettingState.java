package GameStates;

import logic.GameLogic;
import managers.GameStateManager;

import java.awt.*;

public class SettingState extends GameState{
    public static boolean isQuit = false;
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
        g.setColor(Color.BLACK);
        g.drawString("GAME OVER",250,260);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
        g.drawString("QUIT",330,475);
        if(GameLogic.mouseX >= 210 && GameLogic.mouseX <= 590 && GameLogic.mouseY >= 410 && GameLogic.mouseY <= 490) {
            g.setColor(new Color(255,255,255,60));
            g.fillRect(210,410,380,80);
        }
    }
}
