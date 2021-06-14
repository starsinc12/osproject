package elements;

import GameStates.PlayState;
import logic.GameLogic;
import managers.GameStateManager;

import java.awt.*;

/*
* Класс пули главного героя.
* В методах collisionCheck(Right, Left, Up, Down) описаны столкновения с стенами комнаты
*/

public class Bullet {

    private int x;
    private int y;
    private int countReflections;
    private int bulletTileX;
    private int bulletTileY;

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


    public Bullet(boolean isR) {

        double angle = Math.atan((GameLogic.mouseY - Hero.getY()) / ((GameLogic.mouseX == Hero.getX()) ? 1 : (GameLogic.mouseX - Hero.getX())));

        if (GameLogic.mouseX < Hero.getX()) {
            angle += Math.PI;
        }


        x = (int) (Hero.getX() + Math.cos(angle) * 3);
        y = (int) (Hero.getY() + Math.sin(angle) * 3);
        r = 5;
        speed = 12;

        isReflecting = isR;
        countReflections = 3;

        damage = Hero.getDamage();

        distX = GameStateManager.mouseX - x;
        distY = GameStateManager.mouseY - y;
        dist = Math.sqrt(distX * distX + distY * distY);
        arrowDX = distX / dist * speed;
        arrowDY = distY / dist * speed;

        color = Color.RED;
    }

    public void update() {
        bulletTileX = x / (GameLogic.WIDTH / 20);
        bulletTileY = y / (GameLogic.WIDTH / 20);
        if (isReflecting && countReflections != 0) {

        }
        x += arrowDX;
        y += arrowDY;
    }

    public boolean remove() {
        if (isReflecting && countReflections != 0) {
            return false;
        } else if (isReflecting && countReflections == 0) {
            return true;
        } else {
            if (y <= 0 || y >= GameLogic.HEIGHT || x <= 0 || x >= GameLogic.WIDTH) {
                return true;
            }
            if (collisionCheckLeft() || collisionCheckDown() || collisionCheckRight() || collisionCheckUp()) {
                return true;
            } else {
                return false;
            }

        }
    }

    private boolean collisionCheckRight() {
        if (x >= ((GameLogic.WIDTH / 20) * (bulletTileX + 1)) && !PlayState.background.getTiles()[bulletTileX + 1][bulletTileY].isShootable()) {
            PlayState.background.getTiles()[bulletTileX + 1][bulletTileY].hasShooted();
            return true;
        } else return false;
    }

    private boolean collisionCheckLeft() {
        if (x <= ((GameLogic.WIDTH / 20) * bulletTileX) && !PlayState.background.getTiles()[bulletTileX - 1][bulletTileY].isShootable()) {
            PlayState.background.getTiles()[bulletTileX - 1][bulletTileY].hasShooted();
            return true;
        } else return false;
    }

    private boolean collisionCheckDown() {
        if (y >= ((GameLogic.WIDTH / 20) * (bulletTileY + 1)) && !PlayState.background.getTiles()[bulletTileX][bulletTileY + 1].isShootable()) {
            PlayState.background.getTiles()[bulletTileX][bulletTileY + 1].hasShooted();
            return true;
        } else return false;
    }

    private boolean collisionCheckUp() {
        if (y <= ((GameLogic.HEIGHT / 20) * bulletTileY) && !PlayState.background.getTiles()[bulletTileX][bulletTileY - 1].isShootable()) {
            PlayState.background.getTiles()[bulletTileX][bulletTileY - 1].hasShooted();
            return true;
        } else return false;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int) x, (int) y, r, 2 * r);
    }

    public int getR() {
        return r;
    }
}
