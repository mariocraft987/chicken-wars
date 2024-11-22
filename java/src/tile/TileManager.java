package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import main.UtilityTool;
import main.GamePanel;


public final class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/world01.txt");
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void getTileImage() {

        	setup(0, "grass", false);
        	setup(1, "water", true);
        	setup(2, "sand", false);
        	setup(3, "wall", true);
        	setup(4, "tree", true);
        	setup(5, "carpet", false);
        	setup(6, "dirt", false);

}   
    public void setup(int index, String imageName, boolean collision) {
    	
    	UtilityTool uTool = new UtilityTool();
    	
    	try {
    		tile[index] = new Tile();
    		tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName + ".png"));
    		tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
    		tile[index].collision = collision;
    		
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void loadMap(String filePath) {
        try {
            
            InputStream is = getClass().getResourceAsStream(filePath);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                int worldCol = 0;
                int worldRow = 0;
                
                while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
                    
                    String line = br.readLine();
                    
                    while (worldCol < gp.maxWorldCol) {
                        String numbers[] = line.split(" ");
                        
                        int num = Integer.parseInt(numbers[worldCol]);
                        
                        mapTileNum[worldCol][worldRow] = num;
                        worldCol++;
                    }
                    if (worldCol == gp.maxWorldCol) {
                        worldCol = 0;
                        worldRow++;
                    }
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
}

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize; 
            double screenX = worldX - gp.player.worldX + gp.player.screenX;
            double screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (
            worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && // ? When going right
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && // ? When going left
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && // ? When going up
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY // ? When going up
            ) {
                g2.drawImage(tile[tileNum].image, (int)screenX, (int)screenY, null);
                if (gp.debug == true) {
                    g2.setColor(Color.RED);
                    g2.drawRect((int)screenX, (int)screenY, gp.tileSize, gp.tileSize);
                }
            }
            
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }   
}
