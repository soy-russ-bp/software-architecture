package org.example.controller;

import javax.swing.*;

public class Game extends JFrame {
    private Player player;
    private Map gameMap;
    private boolean level;
    private int globalTotalDimonds = 0;

    public Game() {
        player = new Player();
        gameMap = new Map();
    }

    public void playerMove(int xScale, int yScale, String[][] currentMatrix, int totalDimonds) {
        globalTotalDimonds = totalDimonds;
        nextLevel(false); // no avanzar aún al siguiente nivel
    
        try {
            boolean winned = player.movePlayer(xScale, yScale, currentMatrix);
    
            if (winned) {
                nextLevel(true); // Si ganó, avanza al siguiente nivel
            }
    
            if (player.getCollected() == totalDimonds) {
                gameMap.showExit();
            }
            gameMap.setMatrix(currentMatrix);
            
        } catch (Player.StupidAssMove e) {
            JOptionPane.showMessageDialog(null, e.getMessage());  //Show the GUI message
        }
    }
    
    public void setExit(int x, int y) {
        gameMap.setExit(x, y);
    }

    public void showWall() {
        gameMap.showExit();
    }

    public void nextLevel(boolean tOrF) {
        level = tOrF;
    }

    public boolean getLevel() {
        return level;
    }

    public int getDimondsLeft() {
        return globalTotalDimonds - player.getCollected();
    }

    public String[][] getUpdatedMatrix() {
        return gameMap.getMatrix();
    }

}