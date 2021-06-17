package GameStates;

import managers.GameStateManager;

import javax.swing.*;
import java.awt.*;

/*
 * Абстрактный Класс с методами и полями описывающими каждое состояние игры.
*/


public abstract class GameState  extends JPanel {

    protected GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void init();

    public abstract void update();

    public abstract void draw(Graphics2D g);

}
