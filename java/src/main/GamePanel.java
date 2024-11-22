package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import entity.Entity;
import objects.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable { 

    // ! DEV SETTINGS
    public boolean debug = false;

    // ! SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // ! WORLD SETTINGS
    public final int maxWorldCol = 28;
    public final int maxWorldRow = 19;
    public final int worldWidth = tileSize * maxWorldCol; // 48 * 26 = 1248
    public final int worldHeight = tileSize * maxWorldRow;

    // ! FPS
    int FPS = 60;
    public int frames;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    // ! ENTITYS AND OBJECTS
    public Player player = new Player(this,keyH);
    public SuperObject obj[] = new SuperObject[10];
    public Entity npc[] = new Entity[10];
    
    //! GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();
        playMusic(0);
        
        gameState = playState;
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // * DELTA TIME
        // * if lag, run at same speed
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                frames = drawCount;
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update() {

    	if (gameState == playState) {
    		// PLAYER
    		player.update();
    		
    		// NPC
    		for (int i = 0; i < npc.length; i++) {
    			if (npc[i] != null) {
    				npc[i].update();
    			}
    		}
    	} else if (gameState == pauseState) {
    		// nothing
    	}
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        
        // DEBUG
        long drawStart = 0;
        drawStart = System.nanoTime();

        // * PAINT TILES
        tileM.draw(g2);

        // * PAINT OBJECTS
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }
        
        // * PAINT NPC
        for (int i = 0; i < npc.length; i++) {
        	if (npc[i] != null) {
        		npc[i].draw(g2);
        	}
        }

        // * PAINT PLAYER
        player.draw(g2);

        // * PAINT UI
        ui.draw(g2);
        
        
        // DEBUG
        long drawEnd = System.nanoTime();
        long passed = drawEnd - drawStart;
        
        if (debug == true) {
        	g2.setColor(Color.WHITE);
        	g2.drawString("Draw Time: " + passed, 40, 120);
        	g2.drawString("X: " + Math.round(player.worldX / tileSize) + " Y: " + Math.round(player.worldY / tileSize), 40, 150);
        	g2.drawString("WX: " + player.worldX + " WY: " + player.worldY, 40, 180);
        	g2.drawString("FPS:" + frames, 630, 65 );
        }

        g2.dispose();
    }

    public Player getPlayer() {
        return player;
    }

    // FOR MUSIC
    public void playMusic(int i) {
        
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic() {
        music.stop();
    }
    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}