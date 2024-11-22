package objects;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_ARROW extends SuperObject { 
	
	GamePanel gp;

    public OBJ_ARROW(GamePanel gp) {
    	this.gp = gp;

        name = "Arrow";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/arrow_image.png"));
            
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        collision = true;
    }

}
