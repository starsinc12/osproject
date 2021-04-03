package elements.enemies;

import elements.Enemy;
import elements.Hero;
import logic.GameLogic;
import managers.GameStateManager;

import java.awt.*;

public class Fly extends Enemy {

    private int r;
    @Override
    public int getR() {
        return r;
    }

    public Fly(int level) {
        this.level = level;
        x = (int)( Math.random() * GameLogic.WIDTH);
        y = 50;
        speed = 3;
        r = 7;
        health = 40 + (level - 1) * 4;
        expForKill = 10 + (level - 1) * 4;
        damage = 5;
    }


    @Override
    public void update() {
        distX = Hero.getX() - x;
        distY = Hero.getY() - y;
        dist = Math.sqrt(distX * distX + distY * distY);
        dx = distX / dist * speed;
        dy = distY / dist * speed;
        x += dx;
        y += dy;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.GRAY);
        g.fillOval((int)x - r, (int)y - r , 2 * r, 2 * r);
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLACK);
        g.drawOval((int)x - r, (int)y - r , 2 * r, 2 * r);
        g.setStroke(new BasicStroke(3));
    }

    @Override
    public void hit(double heroDamage) {
        health -= heroDamage;
    }

    @Override
    public boolean isDead() {
        return health <= 0;
    }
}
