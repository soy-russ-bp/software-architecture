package org.example;

import java.io.*;
import javax.swing.*;

public class FileLoader {
    //instancia de MatrixProcessor
    private MatrixProcessor matrixProcessor = new MatrixProcessor();
    private String[][] GameMatrix;

    public void loadFile(String fileName) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));// open the file
            String x;
            int lineNum = 0;
            while ((x = in.readLine()) != null) {
                matrixProcessor.matrixLoader(x, lineNum);// pass the Matrix loader method the line and the line number for parsing.
                lineNum++;// we will use the line number later in this class
            }
            in.close();
        } // end tryd try
        catch (IOException e) {
            JFrame frame = new JFrame("Alert");
            JOptionPane.showMessageDialog(frame, "Ooops IOException error, i did it again!" + e.getMessage());
        } // end catch
    }// end load file method

    /**
     * TODO: esto es un parche temporal
     * `loadFileInternal` (antes `loadFile`) permite permite seguir cargando niveles
     * pero entra en conflicto con seleccionar el nivel desde cualquier lugar del
     * disco
     * (cuestión del `JFileChooser` que se integra con `loadFile`)
     */
    public void loadFileInternal(InputStream fileInput) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(fileInput));// open the file
            String x;
            int lineNum = 0;
            while ((x = in.readLine()) != null) {
                matrixProcessor.matrixLoader(x, lineNum);// pass the Matrix loader method the line and the line number for parsing.
                lineNum++;// we will use the line number later in this class
            }
        } // end tryd try
        catch (IOException e) {
            JFrame frame = new JFrame("Alert");
            JOptionPane.showMessageDialog(frame, "Ooops IOException error, i did it again!" + e.getMessage());
        } // end catch
    }


    public int dimondCount() {
        int totalDimonds = 0;
        for (int i = 0; i < GameMatrix.length; i++) {
            for (int j = 0; j < GameMatrix[i].length; j++) {
                if (GameMatrix[i][j].equals("D") || GameMatrix[i][j].equals("H"))
                    totalDimonds += 1;
            }
        } // end double for loop
        return totalDimonds;// return the total number of dimonds in the level
    }

}// end class

