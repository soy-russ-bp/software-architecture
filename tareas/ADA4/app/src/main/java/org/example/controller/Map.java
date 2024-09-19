package org.example.controller;

public class Map {
    private String[][] updatedMatrix;
    private int exitX;
    private int exitY;

    public void setMatrix(String[][] matrix) {
        this.updatedMatrix = matrix;
    }

    public String[][] getMatrix() {
        return updatedMatrix;
    }

    public void setExit(int x, int y) {
        exitX = x;
        exitY = y;
    }

    public void showExit() {
        updatedMatrix[exitX][exitY] = "E";
    }

    public void updatePlayerPosition(int x, int y, int newX, int newY) {
        updatedMatrix[x][y] = "N";
        updatedMatrix[newX][newY] = "P";
    }
}