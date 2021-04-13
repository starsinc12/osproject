package backgrounds;

import logic.GameLogic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WorldStateBack {

    private Image backImg;

    {
        try {
            backImg = ImageIO.read(new File("src\\images\\backs\\worldback.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

    }

    public void draw(Graphics2D g) {
        backImg = backImg.getScaledInstance(GameLogic.WIDTH,GameLogic.HEIGHT, Image.SCALE_DEFAULT);
        g.drawImage(backImg, 0,0, GameLogic.WIDTH,GameLogic.HEIGHT,null);
        System.out.println("Tima LOH ob'elsya BLOH sel na lavochku i ZDOH");
    }
}
