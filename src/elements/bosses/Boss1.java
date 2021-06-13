package elements.bosses;

import GameStates.PlayState;
import elements.Enemy;
import elements.Hero;
import elements.enemies.EnemyBullet;
import logic.GameLogic;

import java.awt.*;

public class Boss1 extends Enemy {

    private int r;
    private int attackTimer;
    private int attackSpeed;
    private int attackDelay;
    private int hpToPix;
    private double maxHealth;
    private double angle;

    public Boss1(){
        x = GameLogic.WIDTH / 2;
        y = 100;
        speed = 5;
        r = 15;
        health = 100;
        maxHealth = health;
        expForKill = 115 + (level - 1) * 4;
        damage = 400;
        attackTimer = 0;
        attackSpeed = 120;
        attackDelay = 3600 / (attackSpeed + 60);
        if (attackDelay < 6) attackDelay = 6;
    }
    @Override
    public void update() {
        hpToPix = (int) (health / maxHealth * 800);

        // Перемещение
        if(x < 750 && y == 100) {
            x += speed;
        } else if(x == 750 && y < 750) {
            y += speed;
        } else if(x > 50 && y == 750) {
            x -= speed;
        } else if(x == 50 && y > 100) {
            y -= speed;
        }

        // Стрельба
        distX = Hero.getX() - x;
        distY = Hero.getY() - y;
        if (distX == 0 && distY == 0) {
            dist = 1;
        } else {
            dist = Math.sqrt(distX * distX + distY * distY);
        }
        if(distY <= 0){
            angle = -Math.acos(distX / dist);
        } else angle = Math.acos(distX / dist);
        if (attackTimer == 0) {
            PlayState.ebullets.add(new EnemyBullet((angle), x, y, damage));
            PlayState.ebullets.add(new EnemyBullet((angle + Math.PI / 12), x, y, damage));
            PlayState.ebullets.add(new EnemyBullet((angle - Math.PI / 12), x, y, damage));
            attackTimer = 30;
        }
        --attackTimer;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillOval(x - r, y - r, 2 * r, 2 * r);
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLUE);
        g.drawOval(x - r, y - r, 2 * r, 2 * r);


        g.setStroke(new BasicStroke(2));
        g.setColor(Color.BLACK);
        g.fillRect(0,0, 800,30);
        g.setColor(Color.RED);
        g.fillRect(5,5, 790,20);
        g.setColor(Color.BLUE);
        g.fillRect(5,5, hpToPix, 20);
    }

    @Override
    public void hit(double heroDamage) {
        health -= heroDamage;
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
        PlayState.bossHere = false;
        Hero.setHxp(Hero.getHxp() + expForKill);
    }
}
