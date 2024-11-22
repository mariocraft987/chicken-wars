package objects;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_CHEST extends SuperObject {
	
	GamePanel gp;

    public OBJ_CHEST(GamePanel gp) {
    	this.gp = gp;

        name = "Chest";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/chest_image.png"));
            
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        collision = true;
    }

}
