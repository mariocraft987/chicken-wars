package main;

import objects.OBJ_CANDY;
import objects.OBJ_ARROW;
import objects.OBJ_POPCORN;
import objects.OBJ_IAMSTEVE_HAMMER;

import entity.NPC_Crow;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_CANDY(gp);
        gp.obj[0].worldX = 18 * gp.tileSize;
        gp.obj[0].worldY = 3 * gp.tileSize;

        gp.obj[1] = new OBJ_POPCORN(gp);
        gp.obj[1].worldX = 5 * gp.tileSize;
        gp.obj[1].worldY = 3 * gp.tileSize;
        

        gp.obj[2] = new OBJ_ARROW(gp);
        gp.obj[2].worldX = 22 * gp.tileSize;
        gp.obj[2].worldY = 2 * gp.tileSize;
        
        gp.obj[3] = new OBJ_ARROW(gp);
        gp.obj[3].worldX = 22 * gp.tileSize;
        gp.obj[3].worldY = 3 * gp.tileSize;
        
        gp.obj[4] = new OBJ_ARROW(gp);
        gp.obj[4].worldX = 22 * gp.tileSize;
        gp.obj[4].worldY = 4 * gp.tileSize;
        
        gp.obj[5] = new OBJ_ARROW(gp);
        gp.obj[5].worldX = 21 * gp.tileSize;
        gp.obj[5].worldY = 3 * gp.tileSize;
        
        gp.obj[6] = new OBJ_ARROW(gp);
        gp.obj[6].worldX = 23 * gp.tileSize;
        gp.obj[6].worldY = 3 * gp.tileSize;
        
        gp.obj[7] = new OBJ_IAMSTEVE_HAMMER(gp);
        gp.obj[7].worldX = 22 * gp.tileSize;
        gp.obj[7].worldY = 7 * gp.tileSize;
    }
    public void setNPC() {
    	
    	gp.npc[0] = new NPC_Crow(gp);
    	gp.npc[0].worldX = gp.tileSize*9;
    	gp.npc[0].worldY = gp.tileSize*5;
    }

}
