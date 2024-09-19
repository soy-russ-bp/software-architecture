package org.example.exceptions;

import javax.swing.*;
// if a level is loaded with ether two players or two exits
// throw this
public class GameFileError extends RuntimeException {
    public GameFileError() {
        JFrame frame = new JFrame("Alert");
        JOptionPane.showMessageDialog(frame,
                "Your maze file ether had more than one player, or more than one exit.");
    }
}// end inner class
