package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {

	GamePanel gp;
    public double worldX, worldY;
    public double speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    
    public Entity(GamePanel gp) {
    	this.gp = gp;
    }
    
    public void setAction() {
    	
    }
    
    public void update() {
    	setAction();
    	
    	collisionOn = false;
    	gp.cChecker.checkTile(this);
    	
        // If collision false, player can move
        if (collisionOn == false) {
            switch(direction) {
                case "up":
                    worldY += speed;
                    break;
                case "down":
                    worldY -= speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        spriteCounter++;
        if (spriteCounter > 17) {
            if (spriteNum == 1) {
                spriteNum = 2;
            }
            else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        } 
    }
    
    public void draw(Graphics2D g2) {
    	
    	BufferedImage image = null;
        double screenX = worldX - gp.player.worldX + gp.player.screenX;
        double screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (
        worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && // ? When going right
        worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && // ? When going left
        worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && // ? When going up
        worldY - gp.tileSize < gp.player.worldY + gp.player.screenY // ? When going up
        ) {
            
        	
            switch(direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                } else if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                }
            }
         }
        	
        g2.drawImage(image, (int)screenX, (int)screenY, gp.tileSize, gp.tileSize, null);
        
        if (gp.debug == true) {
            g2.setColor(Color.RED);
            g2.drawRect((int)screenX + solidArea.x, (int)screenY + solidArea.y, solidArea.width, solidArea.height);
        }
    }
}
    
    public BufferedImage setup(String imagePath) {
    	UtilityTool uTool = new UtilityTool();
    	BufferedImage image = null;
    	
    	try {
    		image = ImageIO.read(getClass().getResourceAsStream("/res/" + imagePath + ".png"));
    		image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return image;
    }
}