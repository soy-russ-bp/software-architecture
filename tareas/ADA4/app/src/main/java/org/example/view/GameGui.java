package org.example.view;
import javax.swing.*;

import org.example.datasorce.FileLoader;
import org.example.datasorce.GameMatrix;
import org.example.controller.Game;
import org.example.controller.Action;
import org.example.controller.TimeCalculator;
import org.example.controller.TimeKeeper;

import org.example.exceptions.SlowAssPlayer;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;

public class GameGui extends JFrame implements ActionListener {


    public GameGui() throws IOException {
        super("Maze, a game of wondering"); // call super to initilize title bar of G.U.I.
        cp = getContentPane();

        InputStream inputStream = getClass().getResourceAsStream("/images/maze.jpg");
        shagLabel = new JLabel("", new ImageIcon(inputStream.readAllBytes()), JLabel.LEFT);// GUI background for initial
        cp.add(shagLabel);
        // Add Exit  Menu Items
        itemExit = new JMenuItem("Exit");
        itemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));// press CTRL+X to exit if
                                                                                           
        itemExit.setActionCommand("Exit");
        itemExit.addActionListener(this);
        
        newMenu = new JMenu("Options");
        newMenu.add(itemExit);

         // Create the play button
        playButton = new JButton("Jugar");
        playButton.setActionCommand("Play");
        playButton.addActionListener(this);
        cp.add(playButton, BorderLayout.SOUTH);
        
        // Add Exit Menu Item
        // Add Menu Bar
        menuBar = new JMenuBar();
        menuBar.add(newMenu);
        setJMenuBar(menuBar);
        // Add Menu Bar
        newPanel = new JPanel();
        tk = new TimeKeeper();
        pack();
        setVisible(true);// show our menu bar and shagLabel.. Yea baby Yea! Whoa.. to much java.
    }// end constructor

    private class MyKeyHandler extends KeyAdapter {
    private Action keyActionHandler;

    public MyKeyHandler() {
        keyActionHandler = new Action(theArc, scrapMatrix, matrix, GameGui.this);
    }

    @Override
    public void keyPressed(KeyEvent theEvent) {
        switch (theEvent.getKeyCode()) {
            case KeyEvent.VK_UP:
                keyActionHandler.handleKeyAction(-1, 0);
                break;
            case KeyEvent.VK_DOWN:
                keyActionHandler.handleKeyAction(1, 0);
                break;
            case KeyEvent.VK_LEFT:
                keyActionHandler.handleKeyAction(0, -1);
                break;
            case KeyEvent.VK_RIGHT:
                keyActionHandler.handleKeyAction(0, 1);
                break;
        }

        JLabel mainLabel = new JLabel("Total Dimonds Left to Collect" + theArc.getDimondsLeft() + "", JLabel.CENTER);
        JPanel dimondsPanel = new JPanel();
        dimondsPanel.add(mainLabel);
        cp.add(dimondsPanel, BorderLayout.SOUTH);
    }
}


    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Exit"))// exit on the menu bar
        {
            new Timer(1000, updateCursorAction).stop();
            System.exit(0); // exit the system.
        }else if (e.getActionCommand().equals("Play")) {
            matrix = fl.loadFileInternal(getClass().getResourceAsStream("/levels/level1.maz"));
            theArc.setExit(matrix.ExitXCord(), matrix.ExitYCord());
            playButton.setVisible(false);
            loadMatrixGui("newLoad");
        }
    }// end actionPerformed method

    public void loadMatrixGui(String event) {
        if (event == "newLoad") {
            remove(newPanel);// remove the previous level's game from the screen
            if (progBarPanel != null)// remove the progress bar from the gui as long as its already been created.
                remove(progBarPanel);
            String[][] temp = matrix.getGameMatrix();
            scrapMatrix = new String[matrix.getMatrixSizeRow()][matrix.getMatrixSizeColumn()];
            for (int i = 0; i < scrapMatrix.length; i++) {
                for (int j = 0; j < scrapMatrix[i].length; j++) {
                    scrapMatrix[i][j] = temp[i][j];// create a new matrix so we dont have a refrence to another objects
                                                   // matrix!
                }
            } // end double for loop
            timeCalc = new TimeCalculator();// create the time calculator used to determine how much time each level is
                                            // given.
            timeCalc.calcTimeforMaze(matrix.dimondCount(), matrix.getMatrixSizeRow(), matrix.getMatrixSizeColumn());// let time
                                                                                                        // calculator
                                                                                                        // know the
                                                                                                        // parameters of
                                                                                                        // the game
            timeLeft = timeCalc.getMinutes();// get the minutes allowed for the level
            ix = timeCalc.getSeconds();// get the seconds allowed for the level;
            jx = 0;// reset the variable used for keeping time to zero since its a new level
            timely = new Timer(1000, updateCursorAction);// create a timer to update the progress bar
            timely.start();// start the timer
            progBarPanel = new JPanel();// panel for progress bar
            progressBar = new JProgressBar(0, timeCalc.getMinutes() * 100);// minutes returns a single digit, we have to
                                                                           // multiply it for Bar.
            progressBar.setStringPainted(true);
            progBarPanel.add(progressBar);
            cp.add(progBarPanel, BorderLayout.NORTH);
            newPanel = new JPanel();
            newPanel.setLayout(new GridLayout(matrix.getMatrixSizeRow(), matrix.getMatrixSizeColumn()));// set our panel for the
                                                                                                // game to the size of
                                                                                                // the matrix
            labelMatrix = new JLabel[matrix.getMatrixSizeRow()][matrix.getMatrixSizeColumn()];
            newPanel.addKeyListener(new MyKeyHandler());
        } // end if
        else if (event == "updateLoad")// every time the player moves the gui must be updated.
        {
            scrapMatrix = theArc.getUpdatedMatrix();// get the new matrix to be displayed from the architect
            remove(newPanel);// remove the old game
            newPanel = new JPanel();
            newPanel.setLayout(new GridLayout(matrix.getMatrixSizeRow(), matrix.getMatrixSizeColumn()));
            newPanel.addKeyListener(new MyKeyHandler());
            newPanel.grabFocus();
        }
        for (int i = 0; i < labelMatrix.length; i++) {
            for (int j = 0; j < labelMatrix[i].length; j++) {
                labelMatrix[i][j] = new mazeObject(scrapMatrix[i][j]);// add our maze images into the gui
            }
        } // end double for loop
        cp.add(newPanel);
        remove(shagLabel);// remove the constructors initial background
        System.gc();// force java to clean up memory use.
        pack();
        setVisible(true);
        newPanel.grabFocus();
    }// end loadMatrixGui method

    public class mazeObject extends JLabel// inner class for each maze object, aka wall, player etc
    {
        // private JLabel imageLabel; // IDK por qué está esto aquí

        public mazeObject(String fileName) {

            InputStream inputStream = getClass().getResourceAsStream("/images/" + fileName + ".png");
            fileName += ".png";
            JLabel fancyLabel;
            try {
                fancyLabel = new JLabel("", new ImageIcon(inputStream.readAllBytes()), JLabel.LEFT);
                newPanel.add(fancyLabel);
            } catch (IOException e) {
                // Esto no es muy limpio que digamos
                throw new RuntimeException("Error loading image: " + fileName);
            }

        }
    }// end inner class

    public void nextLevelLoad() {
        tk.KeepTime(timeLeft, ix);// The TimeKeeper object keeps a running tab of the total time the player has
                                    // used.(for high score)
        timely.stop();// dont count while we are loading the next level.
        theArc = new Game();// flush everything from TheArchitect so we dont get goffee results
        catFileName += 01;// the next file to be loaded (number)
        InputStream fileName = getClass().getResourceAsStream("/levels/level" + catFileName + ".maz");
        // "level" + catFileName + ".maz";
        System.gc();
        fl.loadFileInternal(fileName);// load the file we need
        scrapMatrix = matrix.getGameMatrix();// get the new matrix from the fileloader for the next level.
        theArc.setExit(matrix.ExitXCord(), matrix.ExitYCord());
        loadMatrixGui("newLoad");
    }

    AbstractAction updateCursorAction = new AbstractAction() {
        public void actionPerformed(ActionEvent e) throws SlowAssPlayer // this inner class generates an exeption if the
                                                                        // player takes to long to finish a level
        {
            ix -= 1;
            jx += 1;
            if (ix < 0) {
                ix = 60;
                timeLeft -= 1;
            }
            if (timeLeft == 0 && ix == 0) {
                timely.stop();

                remove(newPanel);
                remove(progBarPanel);
                pack();
                setVisible(true);
                timely.stop();
                try {
                    JFrame frame = new JFrame("Warning");
                    JOptionPane.showMessageDialog(frame, "You Stupid Ass, Did you eat to much for dinner?  Move Faster!");
                    dispose();
                    new GameGui();
                    
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } // end first if
            progressBar.setValue(jx);
            progressBar.setString(timeLeft + ":" + ix);
        }// end actionPerformed
    };// end class


    private int catFileName = 01;
    private Container cp;
    private GameMatrix matrix = new GameMatrix();
    private FileLoader fl = new FileLoader();
    // create menu items
    private JMenuBar menuBar;
    private JMenu newMenu;
    private JMenuItem itemExit;

    //create buttons
    private JButton playButton;


    private JLabel shagLabel;
    private int ix;
    private int jx;
    private int timeLeft;
    private JPanel progBarPanel;
    private JLabel[][] labelMatrix;
    private TimeCalculator timeCalc;
    private JProgressBar progressBar;
    private JPanel newPanel;// = new JPanel();
    private Game theArc = new Game();
    private String[][] scrapMatrix;
    private Timer timely;
    private TimeKeeper tk;
}// end class