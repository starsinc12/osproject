package backgrounds;

import java.awt.image.BufferedImage;

public class PlayTile {

    private BufferedImage image;

    public enum TYPE {
        GRASS, // трава, можно по ней ходить и через неё стрелять
        WALL, // стена, через неё нельзя ни ходить, ни стрелять
        BOX, // коробка, через неё нельзя ходить, разрушается при попадании снаряда и становится травой
        SOFTWALL,
        SPIKES,
        // декор / укрытие - придумать
        WALL2, // декоративная клетка. потом придумать что там будет
        WALL3, // декоративная клетка. потом придумать что там будет
    }

    private TYPE type;
    public int hit = 0;
    private boolean walkable;
    private boolean shootable;

    public TYPE getType() {
        return type;
    }
    public boolean isWalkable() {
        return walkable;
    }
    public boolean isShootable() { return shootable; }

    void toGrass() {
        type = TYPE.GRASS;
        walkable = true;
        shootable = true;
    }

    public PlayTile(TYPE type) {
        this.type = type;
        if(this.type == TYPE.GRASS) {
            walkable = true;
            shootable = true;
        }
        if(this.type == TYPE.WALL) {
            walkable = false;
            shootable = false;
            hit = Integer.MAX_VALUE;
        }
        if(this.type == TYPE.BOX) {
            walkable = false;
            shootable = false;
            hit = 2;
        }
    }


}
