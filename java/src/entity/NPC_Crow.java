package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Crow extends Entity {
	public NPC_Crow(GamePanel gp) {
		super(gp);
		
		direction = "up";
		speed = 1;
		
		getImage();
	}
	
    public void getImage() {
        
        up1 = setup("npc/crow_front_1");
        up2 = setup("npc/crow_front_2");
        left1 = setup("npc/crow_left_1");
        left2 = setup("npc/crow_left_2");
        right1 = setup("npc/crow_right_1");
        right2 = setup("npc/crow_right_2");
        down1 = setup("npc/crow_back_1");
        down2 = setup("npc/crow_back_2");

    }
    
    public void setAction() {
    	
    	actionLockCounter++;
    	
    	if (actionLockCounter == 120) {
    		Random random = new Random();
    		int i = random.nextInt(100)+1; // pickup a number from 1 to 100
    	
    		if (i <= 25) {
    			direction = "up";
    		}
    		if (i > 25 && i <= 50) {
    			direction = "down";
    		}
    		if (i > 50 && i <= 75) {
    			direction = "right";
    		}
    		if (i > 75 && i <= 100) {
    			direction = "left";
    		}
    		
    		actionLockCounter = 0;
    	}
    }
    

}
