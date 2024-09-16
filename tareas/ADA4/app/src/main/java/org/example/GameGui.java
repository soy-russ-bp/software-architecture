package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;

public class GameGui extends JFrame implements ActionListener {

    public static void main(String[] args) throws IOException {
        new GameGui();
    }

    public GameGui() throws IOException {
        super("Maze, a game of wondering"); // call super to initilize title bar of G.U.I.
        cp = getContentPane();

        InputStream inputStream = getClass().getResourceAsStream("/images/yeababyyea.jpg");
        shagLabel = new JLabel("", new ImageIcon(inputStream.readAllBytes()), JLabel.LEFT);// GUI background for initial
                                                                                           // load
        cp.add(shagLabel);
        // Add Exit & New Game Menu Items
        itemExit = new JMenuItem("Exit");
        itemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));// press CTRL+X to exit if
                                                                                           // you want
        itemSaveScore = new JMenuItem("Save High Score");
        itemSaveScore.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));// press CTRL+S to save
                                                                                                // high score if you
                                                                                                // want
        itemHighScore = new JMenuItem("High Score");
        itemHighScore.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));// press CTRL+H to view
                                                                                                // high score if you
                                                                                                // want
        itemEnterName = new JMenuItem("Enter Player Name");
        itemEnterName.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));// press CTRL+N to enter
                                                                                                // your name if you want
        newGameItem = new JMenuItem("New Game");
        openFileItem = new JMenuItem("Open Maze File.");
        openFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));// press CTRL+O to open a
                                                                                               // level if you want
        newGameItem.setActionCommand("New Game");
        newGameItem.addActionListener(this);
        itemEnterName.setActionCommand("EnterName");
        itemEnterName.addActionListener(this);
        itemSaveScore.setActionCommand("SaveScore");
        itemSaveScore.addActionListener(this);
        itemHighScore.setActionCommand("HighScore");
        itemHighScore.addActionListener(this);
        itemExit.setActionCommand("Exit");
        itemExit.addActionListener(this);
        openFileItem.setActionCommand("Open");
        openFileItem.addActionListener(this);
        newMenu = new JMenu("File");
        newMenu.add(newGameItem);
        newMenu.add(itemEnterName);
        newMenu.add(openFileItem);
        newMenu.add(itemHighScore);
        newMenu.add(itemSaveScore);
        newMenu.add(itemExit);

        // Add Exit Menu Item
        // Add Menu Bar
        menuBar = new JMenuBar();
        menuBar.add(newMenu);
        setJMenuBar(menuBar);
        // Add Menu Bar
        newPanel = new JPanel();
        hs = new HighScore();
        tk = new TimeKeeper();
        pack();
        setVisible(true);// show our menu bar and shagLabel.. Yea baby Yea! Whoa.. to much java.
    }// end constructor

    private class MyKeyHandler extends KeyAdapter // captures arrow keys movement
    {
        public void keyPressed(KeyEvent theEvent) {
            switch (theEvent.getKeyCode()) {
                case KeyEvent.VK_UP: {
                    theArc.playerMove(-1, 0, scrapMatrix, fl.dimondCount());// let the Architect know we moved, along
                                                                            // with the current matrix
                    loadMatrixGui("updateLoad");// reload the gui to show the move
                    if (theArc.getLevel() == true) {
                        nextLevelLoad();// if the player hit an exit door, load the next level
                    }
                    break;
                }
                case KeyEvent.VK_DOWN: {
                    theArc.playerMove(1, 0, scrapMatrix, fl.dimondCount());// see above
                    loadMatrixGui("updateLoad");// see above
                    if (theArc.getLevel() == true)// see above
                    {
                        nextLevelLoad();// see above
                    }
                    break;
                }
                case KeyEvent.VK_LEFT: {
                    theArc.playerMove(0, -1, scrapMatrix, fl.dimondCount());// see above
                    loadMatrixGui("updateLoad");// see above
                    if (theArc.getLevel() == true)// see above
                    {
                        nextLevelLoad();// see above
                    }
                    break;
                }
                case KeyEvent.VK_RIGHT: {
                    theArc.playerMove(0, 1, scrapMatrix, fl.dimondCount()); // see above
                    loadMatrixGui("updateLoad");// see above
                    if (theArc.getLevel() == true) {
                        nextLevelLoad();// see above
                    }
                    break;
                }
            }// end switch
            JLabel mainLabel = new JLabel("Total Dimonds Left to Collect" + theArc.getDimondsLeft() + "",
                    JLabel.CENTER);// show how many dimonds are left to collect on the gui!
            JPanel dimondsPanel = new JPanel();
            dimondsPanel.add(mainLabel);
            cp.add(dimondsPanel, BorderLayout.SOUTH);
        }// end method
    }// end inner class

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Exit"))// exit on the menu bar
        {
            new Timer(1000, updateCursorAction).stop();
            System.exit(0); // exit the system.
        } else if (e.getActionCommand().equals("New Game"))// new game on the menu bar
        {
            return; // maybe implent this feature later
        } // end New Game Command
        else if (e.getActionCommand().equals("EnterName"))// Allows user to enter their name for high score
        {
            JOptionPane optionPane = new JOptionPane();
            playerName = optionPane.showInputDialog("Please Enter your Earth Name");
        } else if (e.getActionCommand().equals("HighScore"))// Displays the high scores
        {
            ScoreGui sg = new ScoreGui();
            sg.ShowScoreGui();
        } else if (e.getActionCommand().equals("SaveScore"))// allows the user to save their score at any time.
        {
            hs.addHighScore(playerName, tk.getMinutes(), tk.getSeconds(), levelNum);
        } else if (e.getActionCommand().equals("Open"))// to start the game you have to open a maze file. this is on the
                                                       // menu
        {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                fl.loadFile(chooser.getSelectedFile().getAbsolutePath());// load the file we need
                theArc.setExit(fl.ExitXCord(), fl.ExitYCord());
                loadMatrixGui("newLoad");
            }
        }
    }// end actionPerformed method

    public void loadMatrixGui(String event) {
        if (event == "newLoad") {
            remove(newPanel);// remove the previous level's game from the screen
            if (progBarPanel != null)// remove the progress bar from the gui as long as its already been created.
                remove(progBarPanel);
            String[][] temp = fl.getGameMatrix();
            scrapMatrix = new String[fl.getMatrixSizeRow()][fl.getMatrixSizeColumn()];
            for (int i = 0; i < scrapMatrix.length; i++) {
                for (int j = 0; j < scrapMatrix[i].length; j++) {
                    scrapMatrix[i][j] = temp[i][j];// create a new matrix so we dont have a refrence to another objects
                                                   // matrix!
                }
            } // end double for loop
            timeCalc = new TimeCalculator();// create the time calculator used to determine how much time each level is
                                            // given.
            timeCalc.calcTimeforMaze(fl.dimondCount(), fl.getMatrixSizeRow(), fl.getMatrixSizeColumn());// let time
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
            newPanel.setLayout(new GridLayout(fl.getMatrixSizeRow(), fl.getMatrixSizeColumn()));// set our panel for the
                                                                                                // game to the size of
                                                                                                // the matrix
            labelMatrix = new JLabel[fl.getMatrixSizeRow()][fl.getMatrixSizeColumn()];
            newPanel.addKeyListener(new MyKeyHandler());
        } // end if
        else if (event == "updateLoad")// every time the player moves the gui must be updated.
        {
            scrapMatrix = theArc.getUpdatedMatrix();// get the new matrix to be displayed from the architect
            remove(newPanel);// remove the old game
            newPanel = new JPanel();
            newPanel.setLayout(new GridLayout(fl.getMatrixSizeRow(), fl.getMatrixSizeColumn()));
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
        levelNum += 1;
        tk.KeepTime(timeLeft, ix);// The TimeKeeper object keeps a running tab of the total time the player has
                                    // used.(for high score)
        timely.stop();// dont count while we are loading the next level.
        theArc = new TheArchitect();// flush everything from TheArchitect so we dont get goffee results
        catFileName += 01;// the next file to be loaded (number)
        InputStream fileName = getClass().getResourceAsStream("/levels/level" + catFileName + ".maz");
        // "level" + catFileName + ".maz";
        System.gc();
        fl.loadFileInternal(fileName);// load the file we need
        scrapMatrix = fl.getGameMatrix();// get the new matrix from the fileloader for the next level.
        theArc.setExit(fl.ExitXCord(), fl.ExitYCord());
        loadMatrixGui("newLoad");
    }

    Action updateCursorAction = new AbstractAction() {
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
                InputStream inputStream = getClass().getResourceAsStream("/images/yeababyyea.jpg");
                JLabel yousuckLabel;
                try {
                    yousuckLabel = new JLabel("", new ImageIcon(inputStream.readAllBytes()), JLabel.LEFT);
                    cp.add(yousuckLabel);
                } catch (IOException e1) {
                    // TODO ESTO ESTÁ MAL Y DEBERÍA SER MANEJADO DE OTRA FORMA
                    e1.printStackTrace();
                }

                remove(newPanel);
                remove(progBarPanel);
                pack();
                setVisible(true);
                timely.stop();
                catFileName -= 01;
                if (catFileName < 01)
                    throw new SlowAssPlayer("Slow ass took to long.");
                else
                    loadMatrixGui("newLoad");
            } // end first if
            progressBar.setValue(jx);
            progressBar.setString(timeLeft + ":" + ix);
        }// end actionPerformed
    };// end class

    private class SlowAssPlayer extends RuntimeException {
        public SlowAssPlayer(String event) {
            // the game is over, here we must tell our high score method to recond the
            // details.
            hs.addHighScore(playerName, tk.getMinutes(), tk.getSeconds(), levelNum);
            JFrame frame = new JFrame("Warning");
            JOptionPane.showMessageDialog(frame, "You Stupid Ass, Did you eat to much for dinner?  Move Faster!");// the
                                                                                                                  // entire
                                                                                                                  // game
                                                                                                                  // has
                                                                                                                  // ended.
        }
    }// end class

    private HighScore hs;
    private int catFileName = 01;
    private Container cp;
    private FileLoader fl = new FileLoader();
    // create menu items
    private JMenuBar menuBar;
    private JMenu newMenu;
    private JMenuItem itemExit;
    private JMenuItem newGameItem;
    private JMenuItem openFileItem;
    private JMenuItem itemEnterName;
    private JMenuItem itemHighScore;
    private JMenuItem itemSaveScore;
    // end create menu items
    private JLabel shagLabel;
    private int ix;
    private int jx;
    private int timeLeft;
    private JPanel progBarPanel;
    private JLabel[][] labelMatrix;
    private TimeCalculator timeCalc;
    private JProgressBar progressBar;
    private JPanel newPanel;// = new JPanel();
    private TheArchitect theArc = new TheArchitect();
    private String[][] scrapMatrix;
    private Timer timely;
    private TimeKeeper tk;
    private String playerName;
    private int levelNum = 1;
}// end class