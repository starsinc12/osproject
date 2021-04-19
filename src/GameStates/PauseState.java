package GameStates;

import elements.Hero;
import logic.GameLogic;
import managers.GameStateManager;

import java.awt.*;

public class PauseState extends GameState {

    public PauseState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    public static boolean isResume = false;
    public static boolean isQuit = false;

    @Override
    public void init() {

    }

    @Override
    public void update() {
        if (GameLogic.mouseX >= 210 && GameLogic.mouseX <= 590 && GameLogic.mouseY >= 310 && GameLogic.mouseY <= 390) {
            isResume = true;
        } else {
            isResume = false;
        }
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
        g.fillRect(210,310,380,80);
        g.fillRect(210,410,380,80);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 80));
        g.setColor(Color.BLACK);
        g.drawString("PAUSE",265,280);
        g.setColor(Color.WHITE);
        g.drawString("RESUME",230,380);
        g.drawString("QUIT",310,480);

        if(GameLogic.mouseX >= 210 && GameLogic.mouseX <= 590 && GameLogic.mouseY >= 310 && GameLogic.mouseY <= 390) {
            g.setColor(new Color(255,255,255,60));
            g.fillRect(210,310,380,80);
        }
        if(GameLogic.mouseX >= 210 && GameLogic.mouseX <= 590 && GameLogic.mouseY >= 410 && GameLogic.mouseY <= 490) {
            g.setColor(new Color(255,255,255,60));
            g.fillRect(210,410,380,80);
        }
    }
}
