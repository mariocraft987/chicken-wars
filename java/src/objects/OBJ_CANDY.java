package objects;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_CANDY extends SuperObject {
	
	GamePanel gp;

    public OBJ_CANDY(GamePanel gp) {
    	this.gp = gp;

        name = "Candy";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/lolli_image.png"));
            
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        collision = true;
    }

}
