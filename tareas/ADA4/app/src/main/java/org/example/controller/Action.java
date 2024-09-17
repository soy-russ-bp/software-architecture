package org.example.controller;
import org.example.datasorce.*;

import org.example.view.*;

public class Action {
    private Game theArc;
    private String[][] scrapMatrix;
    private FileLoader fl;
    private GameGui gameGui; // Referencia a GameGui para poder llamar a sus métodos

    public Action(Game theArc, String[][] scrapMatrix, FileLoader fl, GameGui gameGui) {
        this.theArc = theArc;
        this.scrapMatrix = scrapMatrix;
        this.fl = fl;
        this.gameGui = gameGui;
    }

    public void handleKeyAction(int moveRow, int moveCol) {
        theArc.playerMove(moveRow, moveCol, scrapMatrix, fl.dimondCount()); // Mover jugador
        gameGui.loadMatrixGui("updateLoad"); // Actualizar GUI
        if (theArc.getLevel()) { // Si se alcanza el siguiente nivel
            gameGui.nextLevelLoad(); // Cargar siguiente nivel
        }
    }
}