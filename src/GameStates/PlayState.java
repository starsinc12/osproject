package GameStates;

import backgrounds.PlayTile;
import elements.Arrow;
import elements.Enemy;
import backgrounds.PlayStateBack;
import elements.Hero;
import elements.enemies.Fly;
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
    public static ArrayList<Arrow> arrows;
    public static ArrayList<Enemy> enemies;

    public static Integer roomNumber;
    private String roomName;
    private Integer currentRoomNumber;

    private BufferedImage map;
    private String mapPath;
    public static PlayTile[][] tiles = new PlayTile[20][20];

    public Cursor getCursor() {
        return myCursor;
    }

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        roomNumber = 1;
        currentRoomNumber = 1;
    }

    @Override
    public void init() {
        background = new PlayStateBack();
        arrows = new ArrayList<Arrow>();
        enemies = new ArrayList<Enemy>();

        mapPath = "src\\images\\maps\\location1\\room" + roomNumber.toString() + ".png";
        enemies.add(new Fly(1));
        try {
            map = ImageIO.read(new File(mapPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        background.setMap(map, GameLogic.getGraph());
        tiles = background.getTiles();


        // Отрисовка и добавление курсора
        Toolkit kit = Toolkit.getDefaultToolkit();
        BufferedImage bufferedImage = new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g3 = (Graphics2D) bufferedImage.getGraphics();
        g3.setColor(new Color(255,255,255));
        g3.drawOval(0,0,4,4);
        g3.drawLine(2,0,2,4);
        g3.drawLine(0,2,4,2);
        myCursor = kit.createCustomCursor(bufferedImage, new Point(3,3), "myCursor");
        g3.dispose();
    }

    @Override
    public void update() {
        if(!currentRoomNumber.equals(roomNumber)) {
            mapPath = "src\\images\\maps\\location1\\room" + roomNumber.toString() + ".png";
            enemies.add(new Fly(1));
            try {
                map = ImageIO.read(new File(mapPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            background.setMap(map, GameLogic.getGraph());
            tiles = background.getTiles();
        }
        roomName = roomNumber.toString() + "/50";

        background.update();
        currentRoomNumber = roomNumber;

        GameLogic.hero.update(); // roomNumber++

        for (int i = 0; i < arrows.size(); i++) {
            arrows.get(i).update();
            if(arrows.get(i).remove()){
                arrows.remove(i);
                --i;
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
            collisionArrowEnemy(i);
            if(enemies.get(i).isDead()){
                enemies.remove(i);
                --i;
            }
        }
        if(enemies.size() == 0) isOpen = true;

    }

    // проверка столновений выстрелов и врагов
    private void collisionArrowEnemy(int a) {
        for (int i=0; i < arrows.size(); i++) {
            double dist = Math.sqrt(Math.pow(enemies.get(a).getX() - arrows.get(i).getX(),2) + Math.pow(enemies.get(a).getY() - arrows.get(i).getY(),2));
            if(dist <= enemies.get(a).getR() + arrows.get(i).getR()) {
                enemies.get(a).hit(Hero.getDamage());
                arrows.remove(i);
                --i;
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        background.draw(g);
        GameLogic.hero.draw(g);
        for (Arrow arrow : arrows) {
            arrow.draw(g);
        }
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
    }
}
