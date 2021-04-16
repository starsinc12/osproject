package backgrounds;

import java.awt.image.BufferedImage;
import java.lang.reflect.Type;

public class PlayTile {

    private BufferedImage image;

    public enum TYPE {
        GRASS,
        WALL,
        SOFTWALL,
        SPIKES,
        // декор / укрытие - придумать
        WALL2, // декоративная клетка. потом придумать что там будет
        WALL3, // декоративная клетка. потом придумать что там будет

    }

    private TYPE type;
    public int hit = 0;
    public TYPE getType() {
        return type;
    }

    private boolean isClosed;
    public boolean isClosed() {
        return isClosed;
    }

    public void wallHit(){
        hit--;
        System.out.println(hit);
    }


    public PlayTile(TYPE type) {
       this.type = type;
       if(this.type == TYPE.WALL2) hit = 2;
       if(this.type == TYPE.WALL || this.type == TYPE.WALL2 ) isClosed = true;
       if(this.type == TYPE.GRASS) isClosed = false;
    }


}
