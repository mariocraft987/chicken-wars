package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.KeyHandler;

public final class Player extends Entity {
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasCandy = 0;
    public int arrowCount = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
    	super(gp);
    	
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 17;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 30;
    }
    public void setDefaultValues() {
        worldX = gp.tileSize * 3;
        worldY = gp.tileSize * 3;
        speed = 4;
        speed = gp.worldWidth/312;
        
        direction = "up";
    }

    public void getPlayerImage() {
        System.out.println("Player image loading started...");
        
        up1 = setup("player/blue_front_1");
        up2 = setup("player/blue_front_2");
        left1 = setup("player/blue_left_1");
        left2 = setup("player/blue_left_2");
        right1 = setup("player/blue_right_1");
        right2 = setup("player/blue_right_2");
        down1 = setup("player/blue_back_1");
        down2 = setup("player/blue_back_2");
        
        System.out.println("Player image loading ended");
    }

    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {

        if (keyH.upPressed == true) {
            direction = "down";
        }
        else if (keyH.downPressed == true) {
            direction = "up";
        }
        else if (keyH.leftPressed == true) {
            direction = "left";
        }
        else if (keyH.rightPressed == true) {
            direction = "right";
        }

        // Check tile collision
        collisionOn = false;
        gp.cChecker.checkTile(this);

        // CHECK OBJECT COLLISION
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);

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

    public void pickUpObject(int i) {
        if (i != 999) {

            String objName = gp.obj[i].name;
            
            switch(objName) {
                case "Candy":
                    gp.playSE(1);
                    hasCandy++;
                    speed += 8;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got CANDY!");
                    break;
                case "Chest":
                    gp.obj[i] = null;
                    gp.ui.showMessage("You opened a chest!");
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(1);
                    break;
                case "Arrow":
                	arrowCount++;
                	gp.obj[i] = null;
                	gp.ui.showMessage("You got a arrow!");   
                	break;
        		case "Popcorn":
        				gp.obj[i] = null;
        				gp.ui.showMessage("You got popcorn!");   
        				break;
        		case "IAmSteve Hammer":
        			gp.obj[i] = null;
        			gp.ui.showMessage("I AM STEVE");  
        			speed = 1.4;
        			break;
        		}
        }
    }

    public void draw(Graphics2D g2) {
        //  g2.setColor(Color.white);
        //  g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

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
        g2.drawImage(image, screenX, screenY, null);

        if (gp.debug == true) {
            g2.setColor(Color.RED);
            g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }
    }
}