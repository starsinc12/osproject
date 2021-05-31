package elements.enemies;

import GameStates.PlayState;
import elements.Bullet;
import logic.GameLogic;
import managers.GameStateManager;

import java.awt.*;


public class EnemyBullet {

    private int x;
    private int y;
    private int eBulletTileX;
    private int eBulletTileY;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    private double bulletDX;
    private double bulletDY;
    private double distX;
    private double distY;
    private double dist;
    private double angle;

    private int r;
    private int speed;
    private Color color;
    private double damage;

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public EnemyBullet(double a, int x, int y, int damage) {
        angle = a;
        this.x = x;
        this.y = y;
        r = 6;
        speed = 12;
        this.damage = damage;
        color = Color.BLACK;
    }


    public void update() {
        eBulletTileX = x / (GameLogic.WIDTH / 20);
        eBulletTileY =  y / (GameLogic.WIDTH / 20);

        bulletDX = speed * Math.cos(angle);
        bulletDY = speed * Math.sin(angle);
        x += bulletDX;
        y += bulletDY;
    }

    public boolean remove() {
        if (y <= 0 || y >= GameLogic.HEIGHT || x <= 0 || x >= GameLogic.WIDTH) {
            return true;
        }
        if (collisionCheckLeft() || collisionCheckDown() || collisionCheckRight() || collisionCheckUp()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean collisionCheckRight() {
        if (x >= ((GameLogic.WIDTH / 20) * (eBulletTileX + 1)) && !PlayState.background.getTiles()[eBulletTileX + 1][eBulletTileY].isShootable()) {
            return true;
        } else return false;
    }

    private boolean collisionCheckLeft(){
        if (x <= ((GameLogic.WIDTH / 20) * eBulletTileX) && !PlayState.background.getTiles()[eBulletTileX - 1][eBulletTileY].isShootable()) {
            return true;
        } else return false;
    }

    private boolean collisionCheckDown() {
        if (y >= ((GameLogic.WIDTH / 20) * (eBulletTileY + 1)) && !PlayState.background.getTiles()[eBulletTileX][eBulletTileY + 1].isShootable()) {
            return true;
        } else return false;
    }

    private boolean collisionCheckUp(){
        if (y <= ((GameLogic.HEIGHT / 20) * eBulletTileY) && !PlayState.background.getTiles()[eBulletTileX][eBulletTileY - 1].isShootable()) {
            return true;
        } else return false;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int) x, (int) y, r, 2 * r);
    }

    public int getR() {
        return  r;
    }
}