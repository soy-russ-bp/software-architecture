package org.example.controller;

public class Player {
    private int collected = 0;

    public boolean movePlayer(int xScale, int yScale, String[][] currentMatrix) throws StupidAssMove {
        int x = 0;
        int y = 0;
        
        // Buscar la posición actual del jugador
        for (int i = 0; i < currentMatrix.length; i++) {
            for (int j = 0; j < currentMatrix[i].length; j++) {
                if (currentMatrix[i][j].equals("P")) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }

        // Procesar el movimiento del jugador
        if (currentMatrix[x + xScale][y + yScale].equals("H")) {
            currentMatrix[x][y] = "N";
            currentMatrix[x + xScale][y + yScale] = "P";
            collected += 1;
        } else if (currentMatrix[x + xScale][y + yScale].equals("D")) {
            currentMatrix[x][y] = "N";
            currentMatrix[x + xScale][y + yScale] = "P";
            collected += 1;
        } else if (currentMatrix[x + xScale][y + yScale].equals("M")
                && currentMatrix[x + (xScale * 2)][y + (yScale * 2)].equals("N")) {
            currentMatrix[x][y] = "N";
            currentMatrix[x + xScale][y + yScale] = "P";
            currentMatrix[x + (xScale * 2)][y + (yScale * 2)] = "M";
        } else if (currentMatrix[x + xScale][y + yScale].equals("N")) {
            currentMatrix[x][y] = "N";
            currentMatrix[x + xScale][y + yScale] = "P";
        } else if (currentMatrix[x + xScale][y + yScale].equals("E")) {
            currentMatrix[x][y] = "N";
            currentMatrix[x + xScale][y + yScale] = "P";
            return true;
        } else {
            throw new StupidAssMove("Ass Hole hit wall!");
        }
        return false;
    }

    public int getCollected() {
        return collected;
    }

    public void resetCollected() {
        collected = 0;
    }
    
    public static class StupidAssMove extends RuntimeException {
        public StupidAssMove(String event) {
            super(event);
        }
    }  
}
