package objects;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

/*
 
 Object idea and art made by dev's lil brother, muchclover/mucat
 
*/

public class OBJ_IAMSTEVE_HAMMER extends SuperObject {
	
	GamePanel gp;

    public OBJ_IAMSTEVE_HAMMER(GamePanel gp) {
    	this.gp = gp;

        name = "IAmSteve Hammer";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/iamsteve_hammer_image.png"));
            
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        collision = true;
    }

}
