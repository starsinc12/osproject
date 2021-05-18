package elements.enemies;

import GameStates.PlayState;
import Sounds.Audio;
import elements.Enemy;
import elements.Hero;
import logic.GameLogic;

import java.awt.*;

public class Bomber extends Enemy {

    private int r;
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    public static Audio boom;

    public Bomber(int level, int tilex, int tiley) {
        this.level = level;
        x = tilex;
        y = tiley;
        speed = 3;
        r = 10;
        health = 20 + (level - 1) * 4;
        expForKill = 10 + (level - 1) * 4;
        damage = 400;
    }

    @Override
    public void hit(double heroDamage) {
        health = 0;
    }

    @Override
    public boolean isDead() {
        return health <= 0;
    }

    @Override
    public int getR() {
        return 0;
    }

    @Override
    public void xpifDed() {
        Hero.setHxp(Hero.getHxp()+expForKill);
    }


    private boolean collisionCheckRight() {
        if (x >= ((GameLogic.WIDTH / 20) * (enemyTileX + 1) - r) && !PlayState.background.getTiles()[enemyTileX + 1][enemyTileY].isWalkable()) {
            return true;
        } else return false;
    }

    private boolean collisionCheckLeft(){
        if (x <= ((GameLogic.WIDTH / 20) * enemyTileX + r) && !PlayState.background.getTiles()[enemyTileX - 1][enemyTileY].isWalkable()) {
            return true;
        } else return false;
    }

    private boolean collisionCheckDown() {
        if (y >= ((GameLogic.WIDTH / 20) * (enemyTileY + 1) - r) && !PlayState.background.getTiles()[enemyTileX][enemyTileY + 1].isWalkable()) {
            return true;
        } else return false;
    }

    private boolean collisionCheckUp(){
        if (y <= ((GameLogic.HEIGHT / 20) * enemyTileY + r) && !PlayState.background.getTiles()[enemyTileX][enemyTileY - 1].isWalkable()) {
            return true;
        } else return false;
    }




    @Override
    public void update() {

        enemyTileX = x / (GameLogic.WIDTH / 20);
        enemyTileY =  y / (GameLogic.WIDTH / 20);

        // ПЕРЕМЕЩЕНИЕ
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
            dy = dy / Math.sqrt(2);
            dx = dx / Math.sqrt(2);
        }

        if(collisionCheckRight() || collisionCheckLeft()) {
            dx = 0;
            if(distY > 0) dy = speed;
            else dy = -speed;

        } else if(collisionCheckDown() || collisionCheckUp()) {
            dy = 0;
            if(distX > 0) dx = speed;
            else dx = -speed;
        }

        x += dx;
        y += dy;

        // ВЗАИМОДЕЙСТВИЕ С ГЕРОЕМ
        if (x == Hero.getX() && y == Hero.getY()){
            boom= new Audio("src//Sounds//boom.wav",GameLogic.volume);
            boom.sound();
            boom.setVolume();
            GameLogic.hero.hit(damage);
            health = 0;
        }
    }






    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillOval(x - r, y - r , 2 * r, 2 * r);
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLACK);
        g.drawOval(x - r, y - r , 2 * r, 2 * r);
        g.setStroke(new BasicStroke(2));
        g.setColor(Color.RED);
        g.fillOval(x - r / 2, y - r / 2 ,  r,  r);
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLACK);
        g.drawOval(x - r / 2, y - r / 2 ,   r,  r);
        g.setStroke(new BasicStroke(2));
    }


}
