package main;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics2D;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font NESFont_30, NESFont_60B;
    // BufferedImage arrowImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    public UI(GamePanel gp) {
        this.gp = gp;

        NESFont_30 = new Font("IPix", Font.PLAIN, 30);
        NESFont_60B = new Font("IPix", Font.BOLD, 60);
        // OBJ_ARROW arrow = new OBJ_ARROW(gp);
        // arrowImage = arrow.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
    	this.g2 = g2;
    	
    	g2.setFont(NESFont_30);
    	g2.setColor(Color.WHITE);
    	
    	if (messageOn == true) {
    	g2.drawString(message, 486, 509);
    	}
            
    	if (gp.gameState == gp.playState) {
    		
    	} else if (gp.gameState == gp.pauseState) {
    		drawPauseScreen();
    	}
    }
    
    public void drawPauseScreen() {
    	String text = "PAUSED";
    	int x = getXForCenteredText(text);
    	int y = gp.screenHeight/2;
    	
    	g2.drawString(text, x, y);
    }
    public int getXForCenteredText(String text) {
    	int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    	int x = gp.screenWidth/2 - length/2;
    	
    	return x;
    }

}
