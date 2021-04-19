package elements.enemies;

import elements.Enemy;
import elements.Hero;

import java.awt.*;

public class Bomber extends Enemy {

    private int r;
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    public Bomber(int level, int tilex, int tiley) {
        this.level = level;
        x = tilex;
        y = tiley;
        speed = 3;
        r = 10;
        health = 20 + (level - 1) * 4;
        expForKill = 10 + (level - 1) * 4;
        damage = 300;

    }


    @Override
    public void update() {
        distX = Hero.getX() - x;
        distY = Hero.getY() - y;
        distX = Hero.getX() - x;
        distY = Hero.getY() - y;

        if(distX == 0 && distY == 0) {
            dist = 1;
        } else {
            dist = Math.sqrt(distX * distX + distY * distY);
        }

    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void hit(double heroDamage) {

    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public int getR() {
        return 0;
    }

}
