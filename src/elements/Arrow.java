package elements;

import GameStates.PlayState;
import logic.GameLogic;
import logic.GameStart;
import managers.GameStateManager;

import java.awt.*;
import java.awt.event.MouseEvent;


public class Arrow {

    private double x;
    private double y;
    private int countReflections;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    private double arrowDX;
    private double arrowDY;

    private double distX;
    private double distY;
    private double dist;
    private double angle;

    private int r;
    private int speed;
    private Color color;
    private double damage;


    private boolean isReflecting;

    public Arrow(boolean isR) {
        x = Hero.getX();
        y = Hero.getY();
        r = 4;
        speed = 11;
        isReflecting = isR;
        countReflections = 3;
        damage = Hero.getDamage();
        distX = GameStateManager.mouseX - x;
        distY = GameStateManager.mouseY - y;
        dist = Math.sqrt(distX * distX + distY * distY);
        arrowDX = distX / dist * speed;
        arrowDY = distY / dist * speed;

        color = Color.BLUE;
    }

    public void update() {
        x += arrowDX;
        y += arrowDY;
        if(isReflecting) {
            if (x <= 0 && arrowDX < 0) {
                arrowDX = -arrowDX;
                --countReflections;
            }
            if (x >= GameLogic.WIDTH && arrowDX > 0) {
                arrowDX = -arrowDX;
                --countReflections;
            }
            if (y <= 0 && arrowDY < 0) {
                arrowDY = -arrowDY;
                --countReflections;
            }
            if (y >= GameLogic.HEIGHT && arrowDY > 0){
                arrowDY = -arrowDY;
                --countReflections;
            }
        }
    }

    public boolean remove() {
        if(isReflecting && countReflections != 0) {
            return false;
        } else if (isReflecting && countReflections == 0) {
            countReflections = 2;
            return true;
        } else {
            if (y < 0 || y > GameLogic.HEIGHT || x < 0 || x > GameLogic.WIDTH) return true;
            else return false;
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int) x, (int) y, r, 2 * r);
    }

    public int getR() {
        return  r;
    }
}
