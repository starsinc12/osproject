package elements.enemies;

import GameStates.PlayState;
import elements.Arrow;
import elements.Enemy;
import elements.Hero;
import logic.GameLogic;
import managers.GameStateManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Fly extends Enemy {

    private int attackTimer;
    private int attackSpeed;
    private int attackDelay;
    private int r;

    public Fly(int level, int tilex, int tiley) {
        this.level = level;
        x = tilex;
        y = tiley;
        speed = 3;
        r = 7;
        health = 20 + (level - 1) * 4;
        expForKill = 10 + (level - 1) * 4;
        damage = 200;


        attackTimer = 0;
        attackSpeed = 90;
        attackDelay = 3600 / (attackSpeed + 60);
        if(attackDelay < 6) attackDelay = 6;

    }


    @Override
    public void update() {

        distX = Hero.getX() - x;
        distY = Hero.getY() - y;

        if(distX == 0 && distY == 0) {
            dist = 1;
        } else {
            dist = Math.sqrt(distX * distX + distY * distY);
        }

        dx = distX / dist * speed;
        dy = distY / dist * speed;

        if(x == Hero.getX() || y == Hero.getY()){
            dy = dy / Math.sqrt(2); // * Math.cos(Math.PI/4)
            dx = dx / Math.sqrt(2);
        }

        x += dx;
        y += dy;

        if(x == Hero.getX() && y == Hero.getY()){
            if (attackTimer == 0) {
                GameLogic.hero.hit(damage);
                attackTimer = attackDelay;
            }
            --attackTimer;
        } else {
            attackTimer = attackDelay / 2;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.GRAY);
        g.fillOval(x - r, y - r , 2 * r, 2 * r);
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLACK);
        g.drawOval(x - r, y - r , 2 * r, 2 * r);
        g.setStroke(new BasicStroke(2));

        System.out.println(dx + " " + dy);
        if(dx > 0 && dy > 0 || dx < 0 && dy < 0) {
            g.fillOval(x - 2 * r, y + 3 * r / 2 - 2, r,  r); // левое
            g.fillOval(x + r, y - 3 * r / 2, r,  r); // правое
        } else if(dx > 0 && dy < 0 || dx < 0 && dy > 0) {
            g.fillOval(x - 2 * r, y - 2 * r, r,  r); // левое
            g.fillOval(x + r, y + r / 2, r,  r); // правое
        } else if (dx == 0){
            g.fillOval(x - 2 * r, y - r + r / 2, r,  r); // левое
            g.fillOval(x + r, y - r + r / 2, r,  r); // правое
        } else if (dy == 0){
            g.fillOval(x - r/2, y - 2 * r, r, r);
            g.fillOval(x- r/2, y + r, r, r);
        }

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
