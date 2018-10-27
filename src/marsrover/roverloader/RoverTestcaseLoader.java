/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover.roverloader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import marsrover.MarsRover;
import marsrover.exceptions.InvalidCommandException;
import marsrover.exceptions.InvalidPositionException;
import marsrover.exceptions.InvalidTestcaseFile;
import marsrover.factory.RoverFactory;
import marsrover.geometry.Grid;
import marsrover.vehicles.Rover;

/**
 *
 * @author paulbright
 */
public class RoverTestcaseLoader {

    /**
     *
     * @param filename
     * @throws InvalidTestcaseFile
     * @throws InvalidPositionException
     * @throws InvalidCommandException
     */
    public static void initTestcase(String filename) throws InvalidTestcaseFile, InvalidPositionException, InvalidCommandException {
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);

            String sGrid = br.readLine();
            if (sGrid == null) {
                throwTestCaseException(filename);
            }
            initGrid(sGrid);
            String sPos, sCommand;
            while ((sPos = br.readLine()) != null) {
                if ((sCommand = br.readLine()) == null) {
                    throwTestCaseException(filename);
                }
                Rover rover = initRover(sPos, sCommand);
                Grid.addRover(rover);
            }

        } catch (IOException ex) {
            Logger.getLogger(MarsRover.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void loadFile(String filename, List<String> data) throws IOException{
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            String sText;
            
            while ((sText = br.readLine()) != null) {
                data.add(sText);
            }

        } catch (IOException ex) {            
            throw ex;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {                
                throw ex;
            }
        }
    }
    private static void throwTestCaseException(String msg) throws InvalidTestcaseFile {
        throw new InvalidTestcaseFile(msg + " invalid testcase file");
    }

    private static void initGrid(String sGrid) throws InvalidTestcaseFile {
        String[] topPoint = sGrid.split(" ");
        if (topPoint.length != 2) {
            throwTestCaseException("incorrect grid point");
        }
        int x = Integer.valueOf(topPoint[0]);
        int y = Integer.valueOf(topPoint[1]);
        Grid.initGrid(x, y);
    }

    private static Rover initRover(String sPos, String sCommand) throws InvalidTestcaseFile, InvalidPositionException, InvalidCommandException {
        String[] sText = sPos.split(" ");
        if (sText.length != 3) {
            throwTestCaseException("incorrect rover initialization");
        }
        int x = Integer.valueOf(sText[0]);
        int y = Integer.valueOf(sText[1]);
        String orientation = sText[2];

        String command = sCommand.replaceAll("\"", "");
        Rover rover = (Rover) (new RoverFactory()).makeVehicle("", x, y, orientation, command);
        return rover;

    }
}
