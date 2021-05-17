package elements;

import java.awt.*;

public abstract class Enemy {

    protected int x;
    protected int y;
    protected double dx;
    protected double dy;
    protected int enemyTileX;
    protected int enemyTileY;

    protected double speed;
    protected double health;
    protected int level;
    protected int damage;
    protected int expForKill;
    public int getExpForKill() {
        return expForKill;
    }

    protected double distX;
    protected double distY;
    protected double dist;

    public abstract void update();
    public abstract void draw(Graphics2D g);
    public abstract void hit(double heroDamage);
    public abstract boolean isDead();

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public abstract int getR();
    public abstract void xpifDed();
}
