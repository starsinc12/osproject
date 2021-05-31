package elements.enemies;

import GameStates.PlayState;
import elements.Bullet;
import elements.Enemy;
import elements.Hero;


import java.awt.*;

public class Tower extends Enemy {

    private int attackTimer;
    private int attackSpeed;
    private int attackDelay;
    private double angle;
    private double n;
    private int r;

    @Override
    public void hit(double heroDamage) {
        health -= heroDamage;
    }

    public Tower(int level, int tilex, int tiley) {
        n = Math.PI / 6;
        this.level = level;
        x = tilex;
        y = tiley;
        r = 10;
        health = 60 + (level - 1) * 4;
        expForKill = 50 + (level - 1) * 4;
        damage = 400;
        attackTimer = 0;
    }

    @Override
    public boolean isDead() {
        return health <= 0;
    }

    @Override
    public int getR() {
        return r;
    }

    @Override
    public void xpifDed() {
        Hero.setHxp(Hero.getHxp() + expForKill);
    }

    @Override
    public void update() {
        if (attackTimer == 0) {
            for (int i = 0; i < 6; i++) {
                PlayState.ebullets.add(new EnemyBullet(i * (Math.PI / 3) + n,x, y, damage));
            }
            attackTimer = 30;
            n += Math.PI / 12;
        }
        --attackTimer;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(2));
        g.setColor(Color.black);
        g.drawOval(x - r, y - r, 2 * r, 2 * r);
        g.setColor(Color.WHITE);
        g.drawOval(x - r / 2, y - r / 2, r, r);
    }
}
