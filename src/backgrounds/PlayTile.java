package backgrounds;

import Sounds.Audio;
import logic.GameLogic;

import java.awt.image.BufferedImage;

public class PlayTile {

    private BufferedImage image;

    public enum TYPE {
        GRASS, // трава, можно по ней ходить и через неё стрелять
        WALL, // стена, через неё нельзя ни ходить, ни стрелять
        BOX, // коробка, через неё нельзя ходить, разрушается при попадании снаряда и становится травой
        STAR,
        SOFTWALL,
        SPIKES,
        // декор / укрытие - придумать
        WALL2, // декоративная клетка. потом придумать что там будет
        WALL3, // декоративная клетка. потом придумать что там будет
    }

    private TYPE type;
    private TYPE left;
    private TYPE right;
    private TYPE up;
    private TYPE down;
    private double randomexp;
    public static Audio SoundDrop;

    public int hit = 0;
    private boolean walkable;
    private boolean shootable;

    public boolean isHere() {
        return isHere;
    }

    public void setHere(boolean here) {
        isHere = here;
    }
    public boolean getHere() {
        return isHere;
    }

    private boolean isHere;

    public TYPE getType() {
        return type;
    }
    public boolean isWalkable() { return walkable; }
    public boolean isShootable() { return shootable; }

    public void toGrass() {
        this.type=TYPE.GRASS;
        this.walkable = true;
        this.shootable = true;

    }

    public void hasShooted() {
        --hit;
        if(hit <= 0) {
            if((randomexp=Math.random()*100)<10){
                SoundDrop = new Audio("src//Sounds//drop.wav", GameLogic.volume);
                SoundDrop.sound();
                SoundDrop.setVolume();
                this.type=TYPE.STAR;
                this.walkable = true;
                this.shootable = true;
            }else {
                this.type = TYPE.GRASS;
                toGrass();
            }

        }
    }

    public PlayTile(TYPE type) {
        this.type = type;
        isHere=false;
        if(this.type == TYPE.GRASS|| this.type==TYPE.STAR) {
            this.walkable = true;
            this.shootable = true;
        }
        if(this.type == TYPE.WALL) {
            this.walkable = false;
            this.shootable = false;
            this.hit = Integer.MAX_VALUE;
        }
        if(this.type == TYPE.BOX) {
            this.walkable = false;
            this.shootable = false;
            this.hit = 2;
        }
    }


}
