package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.UtilityTool;
import main.GamePanel;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gp) {

        double screenX = worldX - gp.player.worldX + gp.player.screenX;
        double screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (
        worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && // ? When going right
        worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && // ? When going left
        worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && // ? When going up
        worldY - gp.tileSize < gp.player.worldY + gp.player.screenY // ? When going up
        ) {
            g2.drawImage(image, (int)screenX, (int)screenY, gp.tileSize, gp.tileSize, null);
            if (gp.debug == true) {
                g2.setColor(Color.BLUE);
                g2.drawRect((int)screenX + solidArea.x, (int)screenY + solidArea.y, solidArea.width, solidArea.height);
            }
        }
    }
    
}
