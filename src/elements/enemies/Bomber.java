package elements.enemies;

import GameStates.PlayState;
import Sounds.Audio;
import elements.Enemy;
import elements.Hero;
import logic.GameLogic;

import java.awt.*;
import java.util.ArrayList;


/*
* Класс врага, которы бежит на героя, чтобы взорвать его
* поле FieldForMoving формирует маршрут к герою
* перемещения и взаимодействия с героем описаны в методе update
*/
public class Bomber extends Enemy {

    private int r;
    private FieldForMoving ffm = null;
    private ArrayList<int[]> path;
    public static Audio boom;
    private int htx = 0;
    private int hty = 0;
    private int letx = 0;
    private int lety = 0;
    private int pathInd = 0;


    public Bomber(int level, int tilex, int tiley) {
        this.level = level;
        x = tilex;
        y = tiley;
        speed = 5;
        r = 20;
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
        return r;
    }

    @Override
    public void xpifDed() {
        Hero.setHxp(Hero.getHxp() + expForKill);
    }


    private boolean collisionCheckRight() {
        if (x >= ((GameLogic.WIDTH / 20) * (enemyTileX + 1) - r) && !PlayState.background.getTiles()[enemyTileX + 1][enemyTileY].isWalkable()) {
            return true;
        } else return false;
    }

    private boolean collisionCheckLeft() {
        if (x <= ((GameLogic.WIDTH / 20) * enemyTileX + r) && !PlayState.background.getTiles()[enemyTileX - 1][enemyTileY].isWalkable()) {
            return true;
        } else return false;
    }

    private boolean collisionCheckDown() {
        if (y >= ((GameLogic.WIDTH / 20) * (enemyTileY + 1) - r) && !PlayState.background.getTiles()[enemyTileX][enemyTileY + 1].isWalkable()) {
            return true;
        } else return false;
    }

    private boolean collisionCheckUp() {
        if (y <= ((GameLogic.HEIGHT / 20) * enemyTileY + r) && !PlayState.background.getTiles()[enemyTileX][enemyTileY - 1].isWalkable()) {
            return true;
        } else return false;
    }

    @Override
    public void update() {

        enemyTileX = x / (GameLogic.WIDTH / 20);
        enemyTileY = y / (GameLogic.WIDTH / 20);
        // ПЕРЕМЕЩЕНИЕ
        if (Hero.getHeroTileX() != htx || Hero.getHeroTileY() != hty) {
            letx = enemyTileX;
            lety = enemyTileY;
            htx = Hero.getHeroTileX();
            hty = Hero.getHeroTileY();
            ffm = new FieldForMoving();
            path = ffm.findPath(enemyTileX, enemyTileY, Hero.getHeroTileX(), Hero.getHeroTileY());
            pathInd = 1;
        }


        if(x != path.get(pathInd)[0] * 40 + 20 || y != path.get(pathInd)[1] * 40 + 20) {
            x += Math.signum(path.get(pathInd)[0] * 40 + 20 - x) * speed / 2;
            y += Math.signum(path.get(pathInd)[1] * 40 + 20 - y) * speed / 2;
        } else ++pathInd;

        /*if (path.size() == 1) {
            distX = Hero.getX() - x;
            distY = Hero.getY() - y;
            if (distX == 0 && distY == 0) {
                dist = 1;
            } else {
                dist = Math.sqrt(distX * distX + distY * distY);
            }
            dx = distX / dist * speed;
            dy = distY / dist * speed;
            if (x == Hero.getX() || y == Hero.getY()) {
                dy = speed / Math.sqrt(2);
                dx = speed / Math.sqrt(2);
            }
        } else if(path.size() > 1) {
            dx = 0;
            dy = 0;
            //r
            if(path.get(0)[0] < path.get(1)[0] && path.get(0)[1] == path.get(1)[1]) {
                dx = speed;
                dy = 0;
            }
            //rd
            else if(path.get(0)[0] < path.get(1)[0] && path.get(0)[1] < path.get(1)[1]) {
                dx = speed / Math.sqrt(2);
                dy = speed / Math.sqrt(2);
                if(collisionCheckDown()) {
                    dy = 0;
                }
                if(collisionCheckRight()) {
                    dx = 0;
                }

            }
            //d
            else if(path.get(0)[0] == path.get(1)[0] && path.get(0)[1] < path.get(1)[1]) {
                dx = 0;
                dy = speed;
            }
            //ld
            else if(path.get(0)[0] > path.get(1)[0] && path.get(0)[1] < path.get(1)[1]) {
                dx = -speed / Math.sqrt(2);
                dy = speed / Math.sqrt(2);
                if(collisionCheckDown()) {
                    dy = 0;
                }
                if(collisionCheckLeft()) {
                    dx = 0;
                }
            }
            //l
            else if(path.get(0)[0] > path.get(1)[0] && path.get(0)[1] == path.get(1)[1]) {
                dx = -speed;
                dy = 0;
            }
            //lu
            else if(path.get(0)[0] > path.get(1)[0] && path.get(0)[1] > path.get(1)[1]) {
                dx = -speed / Math.sqrt(2);
                dy = -speed / Math.sqrt(2);
                if(collisionCheckUp()) {
                    dy = 0;
                }
                if(collisionCheckLeft()) {
                    dx = 0;
                }
            }
            //u
            else if(path.get(0)[0] == path.get(1)[0] && path.get(0)[1] > path.get(1)[1]) {
                dx = 0;
                dy = -speed;
            }
            //ru
            else if(path.get(0)[0] < path.get(1)[0] && path.get(0)[1] > path.get(1)[1]) {
                dx = speed / Math.sqrt(2);
                dy = -speed / Math.sqrt(2);
                if(collisionCheckUp()) {
                    dy = 0;
                }
                if(collisionCheckRight()) {
                    dx = 0;
                }
            }

        }*/

        // ВЗАИМОДЕЙСТВИЕ С ГЕРОЕМ
        if ((x >= Hero.getX() - 23 && x <= Hero.getX() + 23) && (y >= Hero.getY() - 36 && y <= Hero.getY() + 31)) {
            boom = new Audio("src//Sounds//boom.wav", GameLogic.volume);
            boom.sound();
            boom.setVolume();
            GameLogic.hero.hit(damage);
            health = 0;
        }
    }


    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillOval(x - r / 2, y - r / 2, r, r);
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLACK);
        g.drawOval(x - r / 2, y - r / 2, r, r);
        g.setStroke(new BasicStroke(2));
        g.setColor(Color.RED);
        g.fillOval(x - r / 4, y - r / 4, r / 2, r / 2);
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLACK);
        g.drawOval(x - r / 4, y - r / 4, r / 2, r / 2);
        g.setStroke(new BasicStroke(2));
        /*for (int i = 0; i < path.size(); i++) {
            g.setColor(Color.BLACK);
            g.fillOval(path.get(i)[0] * 40 + 20, path.get(i)[1] * 40 + 20,7,7);
        }*/
    }


}
