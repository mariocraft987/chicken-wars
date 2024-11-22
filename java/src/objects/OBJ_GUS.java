package objects;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_GUS extends SuperObject {
	
	GamePanel gp;

    public OBJ_GUS(GamePanel gp) {
    	this.gp = gp;

        name = "Gus";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/gus_image.png"));
            
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        collision = true;
    }

}
