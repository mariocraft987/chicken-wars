package objects;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_POPCORN extends SuperObject {
	
	GamePanel gp;

    public OBJ_POPCORN(GamePanel gp) {
    	this.gp = gp;

        name = "Popcorn";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/popcorn_image.png"));
            
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        collision = true;
    }

}
