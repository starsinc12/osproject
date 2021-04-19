package elements;

import GameStates.PlayState;
import inventory.typesOfEquipment.Armor;
import inventory.typesOfEquipment.Ring;
import inventory.typesOfEquipment.Shoes;
import inventory.typesOfEquipment.Weapon;
import logic.GameLogic;
import managers.GameStateManager;
import skills.Skill;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Hero {

    private static int x;
    private static int y;

    public static void setX(int x) {
        Hero.x = x;
    }

    public static void setY(int y) {
        Hero.y = y;
    }

    public static int getX() {
        return x;
    }
    public static int getY() {
        return y;
    }
    private static int heroTileX;
    private static int heroTileY;

    private int r;
    private double dx;
    private double dy;

    private long start;
    private long elapsed;
    private long wait;


    private static double health;
    private static double maxHealth;

    public static double getHealth() {
        return health;
    }

    public static double getMaxHealth() {
        return maxHealth;
    }

    private static double protection;
    private static double damage;
    public static double getDamage() {
        return damage;
    }
    private static int level;
    private static int gameLevel;
    private static int speed;
    private static int skillPoints;

    private ArrayList<Skill> skillsList;

    private Weapon weapon;
    private Armor armor;
    private Shoes shoes;
    private Ring ring;

    public static boolean up;
    public static boolean down;
    public static boolean left;
    public static boolean right;
    public static boolean isAttack;
    private static int attackTimer;
    private static int attackDelay;
    private static int attackSpeed;

    Image imgTank = null;
    Image imgPushka = null;

    public Hero() {
        x = GameLogic.WIDTH / 2 ;
        y = GameLogic.HEIGHT - 20;
        r = 8;
        speed = 7;
        dx = 0;
        dy = 0;
        up = false;
        down = false;
        left = false;
        right = false;
        isAttack = false;




        protection = 2;
        damage = 10;
        health = 1000;
        maxHealth = health;

        level = 1;
        gameLevel = 1;


        attackTimer = 15;
        attackSpeed = 90;
        changeAttackDelay(attackSpeed);

        weapon = null;
        armor = null;
        shoes = null;
        ring = null;

        imgTank = new ImageIcon("src\\images\\tankbezPushki.png").getImage();
        imgPushka = new ImageIcon("src\\images\\Pushka.png").getImage();


    }

    public static void changeAttackDelay(int attackSpeed) {
        attackDelay = 3600 / (attackSpeed + 60);
        // attackDelay = FPS * FPS / (attackSpeed + FPS);
        if(attackDelay < 6) attackDelay = 6;
        //  if(attackDelay < (FPS / 10)) attackDelay = FPS / 10;
    }

    public void hit(double enemyDamage){
        health -= enemyDamage;
    }

    public void update() {
        // ОТВЯЗАТЬ ОТ ФПС
        heroTileX = x / (GameLogic.WIDTH / 20);
        heroTileY =  y / (GameLogic.WIDTH / 20);
        if (!(up || down || right || left)) {
            //if(attackTimer < 0) attackTimer = 0;
            isAttack = true;
            if (attackTimer == 0) {
                PlayState.arrows.add(new Arrow(false));
                attackTimer = attackDelay;
            }
            --attackTimer;
        } else {
            isAttack = false;
            attackTimer = attackDelay / 2;
        }

        if(up && y > r && collisionCheckUp()) {
            dy = -speed;
        }
        if (down && y < GameLogic.HEIGHT - r && collisionCheckDown()) {
            dy = speed;
        }
        if (left && x > r && collisionCheckLeft()) {
            dx = -speed;
        }
        if (right && x < GameLogic.WIDTH - r && collisionCheckRight()) {
            dx = speed;
        }
        if(up && left || up && right || down && left || down && right) {
            dy = dy / Math.sqrt(2); // * Math.cos(Math.PI/4)
            dx = dx / Math.sqrt(2);
        }
        if( ((x <= (GameLogic.WIDTH - 255) && x >= 255) && y <= 15) && PlayState.isOpen ) {
            x = GameLogic.WIDTH / 2;
            y = GameLogic.HEIGHT - 20;
            ++PlayState.roomNumber;
        }
        y += dy;
        x += dx;
        dx = 0;
        dy = 0;
    }

    private boolean collisionCheckRight() {
        if (x >= ((GameLogic.WIDTH / 20) * (heroTileX + 1) - r) && PlayState.background.getTiles()[heroTileX + 1][heroTileY].isClosed()) {
            return false;
        } else return true;
    }

    private boolean collisionCheckLeft(){
        if (x <= ((GameLogic.WIDTH / 20) * heroTileX + r) && PlayState.background.getTiles()[heroTileX - 1][heroTileY].isClosed()) {
            return false;
        } else return true;
    }

    private boolean collisionCheckDown() {
        if (y >= ((GameLogic.WIDTH / 20) * (heroTileY + 1) - r) && PlayState.background.getTiles()[heroTileX][heroTileY + 1].isClosed()) {
            return false;
        } else return true;
    }

    private boolean collisionCheckUp(){
        if (y <= ((GameLogic.HEIGHT / 20) * heroTileY + r) && PlayState.background.getTiles()[heroTileX][heroTileY - 1].isClosed()) {
            return false;
        } else return true;
    }

    public void draw(Graphics2D g) {

        AffineTransform orig1;
        orig1 = g.getTransform();
        AffineTransform newtransX1 = (AffineTransform) orig1.clone();
        g.setTransform(newtransX1);
        newtransX1 = (AffineTransform) orig1.clone();
        if(left || right)  newtransX1.rotate(Math.PI/2, x - 1,y);
        if (up && left || down && right) newtransX1.rotate(Math.PI/4, x - 1, y);
        if (up && right || down && left) newtransX1.rotate(3 * Math.PI/4, x - 1, y);
        g.setTransform(newtransX1);
        g.drawImage(imgTank,(int) x - 24, (int) y - 42, null);
        g.setTransform(orig1);
        double distX = GameStateManager.mouseX - x;
        double distY = y - GameStateManager.mouseY;
        double dist = Math.sqrt(distX * distX + distY * distY);
        double angle = 0;
        if(distX > 0) angle = Math.acos(distY/dist);
        if(distX < 0) angle = -Math.acos(distY/dist);
        AffineTransform orig2;
        orig2 = g.getTransform();
        AffineTransform newtransX2 = (AffineTransform) orig2.clone();
        g.setTransform(newtransX2);
        newtransX2 = (AffineTransform) orig2.clone();
        newtransX2.rotate(angle, x - 1,y);
        g.setTransform(newtransX2);
        g.drawImage(imgPushka,(int) x - 24, (int) y - 42, null);
        g.setTransform(orig2);
        g.setStroke(new BasicStroke(5));

        /*g.setColor(Color.white);
        g.fillOval((int) (x - r), (int) (y - r),2 * r, 2 * r);
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.white.darker());
        g.drawOval((int) (x - r), (int) (y - r),2 * r, 2 * r);
        g.setStroke(new BasicStroke(1));*/


    }

    public void reset() {
        health = maxHealth;
    }
}
