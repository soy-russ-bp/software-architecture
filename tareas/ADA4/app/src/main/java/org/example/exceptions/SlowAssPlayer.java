package org.example.exceptions;
import javax.swing.*;

public class SlowAssPlayer extends RuntimeException{
    public SlowAssPlayer(String event) {
        // the game is over, here we must tell our high score method to recond the details.
        JFrame frame = new JFrame("Warning");
        JOptionPane.showMessageDialog(frame, "You Stupid Ass, Did you eat to much for dinner?  Move Faster!");// the entire game has ended.
    }
}
