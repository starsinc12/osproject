package GameStates;

import backgrounds.PlayTile;
import elements.Bullet;
import elements.Enemy;
import backgrounds.PlayStateBack;
import elements.HPBar;
import elements.Hero;
import logic.GameLogic;
import managers.GameStateManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlayState extends GameState {

    public static boolean isOpen = false;

    private Cursor myCursor;

    public static PlayStateBack background;
    public static ArrayList<Bullet> bullets;
    public static ArrayList<Enemy> enemies;
    public static HPBar hpBar;
    public static Integer enemiesKilled;


    public static Integer roomNumber;
    private String roomName;
    private Integer currentRoomNumber;

    private BufferedImage map;
    private String mapPath;
    public static PlayTile[][] tiles;

    public Cursor getCursor() {
        return myCursor;
    }
    public static void SetTiles(int x,int y){
        tiles[x][y].setHere(true);
        if(tiles[x][y].getType()== PlayTile.TYPE.STAR){
            Hero.setHxp(Hero.getHxp()+100);
            tiles[x][y].toGrass();
        }
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if(i!=x || j!=y){
                    tiles[i][j].setHere(false);
                }
            }
        }
        System.out.println(tiles[x+1][y].isHere());
        System.out.println(tiles[x][y].isHere());
    }

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        roomNumber = 1;
        currentRoomNumber = 1;
        enemiesKilled = 0;
    }

    @Override
    public void init() {
        background = new PlayStateBack();
        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
        hpBar = new HPBar();
        GameLogic.hero.reset();
        update();
        // Отрисовка и добавление курсора
        Toolkit kit = Toolkit.getDefaultToolkit();
        BufferedImage bufferedImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g3 = (Graphics2D) bufferedImage.getGraphics();
        g3.setColor(new Color(0, 0, 0));
        g3.drawOval(0, 0, 4, 4);
        g3.drawLine(2, 0, 2, 4);
        g3.drawLine(0, 2, 4, 2);
        myCursor = kit.createCustomCursor(bufferedImage, new Point(3, 3), "myCursor");
        g3.dispose();
    }

    @Override
    public void update() {
        if (!currentRoomNumber.equals(roomNumber) || map == null) {
            bullets.clear();
            mapPath = "src\\images\\maps\\location1\\room" + roomNumber.toString() + ".png";
            try {
                map = ImageIO.read(new File(mapPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            tiles = new PlayTile[20][20];
            background.setMap(map, GameLogic.getGraph());
            tiles = background.getTiles();
        }
        roomName = roomNumber.toString() + "/50";

        background.update();
        currentRoomNumber = roomNumber;

        GameLogic.hero.update(); // roomNumber++

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();
            if (bullets.get(i).remove()) {
                bullets.remove(i);
                --i;
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
            collisionArrowEnemy(i);
            if (enemies.get(i).isDead()) {
                enemies.get(i).xpifDed();
                enemies.remove(i);
                --i;
                enemiesKilled++;
            }
        }


        hpBar.update();


        if (currentRoomNumber == 7 && !hpBar.isHeroDead) {
            GameLogic.gsm.setWin(true);
        }

    }

    // проверка столновений выстрелов и врагов
    private void collisionArrowEnemy(int a) {
        for (int i = 0; i < bullets.size(); i++) {
            double dist = Math.sqrt(Math.pow(enemies.get(a).getX() - bullets.get(i).getX(), 2) + Math.pow(enemies.get(a).getY() - bullets.get(i).getY(), 2));
            if (dist <= enemies.get(a).getR() + bullets.get(i).getR()) {
                enemies.get(a).hit(Hero.getDamage());
                bullets.remove(i);
                --i;
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {

        background.draw(g);


        GameLogic.hero.draw(g);
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
        hpBar.draw(g);

        String str = currentRoomNumber.toString() + "/15";
        g.setColor(Color.cyan);
        g.setFont(new Font("Calibri", Font.PLAIN, 25));
        g.drawString(str, 5, 25);

        if (hpBar.isHeroDead) {
            GameLogic.gsm.setGameOver(true);
        }
        if (enemies.size() == 0) {
            isOpen = true;

        } else isOpen = false;

        //g.setStroke(new BasicStroke(3));
        // g.drawLine(Hero.getX(), Hero.getY(), enemies.get(1).getX(), enemies.get(1).getY());
        //g.drawLine(Hero.getX(), Hero.getY(), enemies.get(1).getX(), Hero.getY());
        //g.drawLine(enemies.get(1).getX(), Hero.getY(), enemies.get(1).getX(), enemies.get(1).getY());
    }
}