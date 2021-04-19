package backgrounds;

import GameStates.PlayState;
import elements.enemies.Fly;
import logic.GameLogic;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayStateBack {

    private Image img = null;
    private PlayTile[][] tiles = new PlayTile[20][20];
    private Color color;
    private BufferedImage map;
    private Image wall;
    private Image grass;
    private Image box;
    BufferedImage bufferedImage = new BufferedImage(GameLogic.WIDTH,GameLogic.HEIGHT,BufferedImage.TYPE_INT_RGB);
    public static boolean isHeroDead;

    public static int tileLength;

    public PlayStateBack() {
    }

    public void update(){

    }

    private void paintMap() {

    }

    public void draw(Graphics2D g){
        for (int i = 0; i < map.getWidth(null); i++) {
            for (int j = 0; j < map.getHeight(null); j++) {
                color = new Color(map.getRGB(j, i));
                if (color.equals(Color.WHITE)) {
                    g.drawImage(grass,j * GameLogic.WIDTH / 20,i * GameLogic.HEIGHT / 20,null);
                    tiles[j][i] = new PlayTile(PlayTile.TYPE.GRASS);
                }
                if (color.equals(Color.BLACK)) {
                    g.drawImage(wall,j * GameLogic.WIDTH / 20,i * GameLogic.HEIGHT / 20,null);
                    tiles[j][i] = new PlayTile(PlayTile.TYPE.WALL);
                }
                if (color.equals(new Color(223,113,38))) {
                    g.drawImage(box,j * GameLogic.WIDTH / 20,i * GameLogic.HEIGHT / 20,null);
                    tiles[j][i] = new PlayTile(PlayTile.TYPE.BOX);
                }
                if(color.getRed() == 100 && color.getGreen() == 100) {
                    g.drawImage(grass,j * GameLogic.WIDTH / 20,i * GameLogic.HEIGHT / 20,null);
                    tiles[j][i] = new PlayTile(PlayTile.TYPE.GRASS);
                }
            }
        }

    }

    public void setMap(BufferedImage map, Graphics2D g) {
        this.map = map;
        for (int i = 0; i < map.getWidth(null); i++) {
            for (int j = 0; j < map.getHeight(null); j++) {
                color = new Color(map.getRGB(j, i));
                if (color.equals(Color.WHITE)) {
                    tiles[j][i] = new PlayTile(PlayTile.TYPE.GRASS);
                }
                if (color.equals(Color.BLACK)) {
                    tiles[j][i] = new PlayTile(PlayTile.TYPE.WALL);
                }
                if (color.equals(new Color(223,113,38))) {
                    tiles[j][i] = new PlayTile(PlayTile.TYPE.BOX);
                }
                if(color.getRed() == 100 && color.getGreen() == 100) {
                    if (color.equals(new Color(100,100,0))) {
                        tiles[j][i] = new PlayTile(PlayTile.TYPE.GRASS);
                        PlayState.enemies.add(new Fly(1,(j * GameLogic.WIDTH / 20) + 20,(i * GameLogic.HEIGHT / 20) + 20));
                    }
                }

            }
        }
        try {
            wall = ImageIO.read(new File("src\\images\\sprites\\wall.png"));
            grass = ImageIO.read(new File("src\\images\\sprites\\grass.png"));
            box = ImageIO.read(new File("src\\images\\sprites\\box.png"));
            wall =  wall.getScaledInstance(GameLogic.WIDTH / 20,GameLogic.HEIGHT / 20,Image.SCALE_DEFAULT);
            grass = grass.getScaledInstance(GameLogic.WIDTH / 20,GameLogic.HEIGHT / 20,Image.SCALE_DEFAULT);
            box = box.getScaledInstance(GameLogic.WIDTH / 20,GameLogic.HEIGHT / 20,Image.SCALE_DEFAULT);
            tileLength = wall.getHeight(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PlayTile[][] getTiles() {
        return tiles;
    }
}
