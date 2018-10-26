/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import marsrover.geometry.Grid;
import marsrover.exceptions.InvalidPositionException;
import marsrover.Marsrover;
import marsrover.commands.ICommandMovement;
import marsrover.commands.MovementCommandGenerator;
import marsrover.vehicles.Rover;
import marsrover.factory.RoverFactory;
import marsrover.exceptions.InvalidCommandException;
import marsrover.exceptions.InvalidTestcaseFile;

/**
 *
 * @author paulbright
 */
public class TestRovers {

    public static void test() {
        TestRovers.testRovers();
        Grid.initGrid(5, 5);
        TestRovers.testRovers(1, 2, 'N', "LMLMLMLMM");
        TestRovers.testRovers(3, 3, 'E', "MMRMMRMRRM");
    }

    public static void testRovers() {
        Object[][] data = {
            {5, 5},
            {1, 2, 'N', "LMLMLMLMM"},
            {3, 3, 'E', "MMRMMRMRRM"}
        };
        
        testRovers(data);
    }

    public static void testRovers(Object[][] data) {
        try {
            Grid.initGrid((int) data[0][0], (int) data[0][1]);
            for (int i = 1; i < data.length; i++) {
                Rover rover;
                try {
                    rover = RoverFactory.createRover("", (int) data[i][0],
                            (int) data[i][1], (Character) data[i][2],
                            (String) data[i][3]);
                    Grid.addRover(rover);
                } catch (InvalidCommandException ex) {
                    Logger.getLogger(Marsrover.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            Grid.animateRovers();

        } catch (InvalidPositionException ex) {
            Logger.getLogger(Marsrover.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void testRovers(int x, int y, Character orientation, String command) {

        Rover rover;
        try {
            rover = RoverFactory.createRover("", x, y, orientation);
            Grid.addRover(rover);
            List<ICommandMovement> commands = MovementCommandGenerator.generateCommands(command);
            for (ICommandMovement cmd : commands) {
                cmd.execute(rover);
            }
            System.out.println(rover.toString());
        } catch (InvalidCommandException ex) {
            Logger.getLogger(Marsrover.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidPositionException ex) {
            Logger.getLogger(Marsrover.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void test(String filename) throws InvalidTestcaseFile, InvalidPositionException, InvalidCommandException {
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);

            String sGrid = br.readLine();
            if(sGrid  == null) {
                throwTestCaseException(filename);
            }
            initGrid(sGrid);
            String sPos,sCommand;
            while( (sPos = br.readLine()) !=null)
            {               
                if ((sCommand = br.readLine()) == null) {
                    throwTestCaseException(filename);
                }
                Rover rover = initRover(sPos, sCommand);
                Grid.addRover(rover);
            }
            Grid.animateRovers();
                        
        } catch (IOException ex) {
            Logger.getLogger(Marsrover.class.getName()).log(Level.SEVERE, null, ex);

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
    
    private static void throwTestCaseException(String msg) throws InvalidTestcaseFile{
        throw new InvalidTestcaseFile(msg + " invalid testcase file");
    }
    private static void initGrid(String sGrid) throws InvalidTestcaseFile{
        String [] topPoint = sGrid.split(" ");
        if(topPoint.length != 2) throwTestCaseException("incorrect grid point");
        int x = Integer.valueOf(topPoint[0]);
        int y = Integer.valueOf(topPoint[1]);
        Grid.initGrid(x, y);
    }

    private static Rover initRover(String sPos, String sCommand) throws InvalidTestcaseFile, InvalidPositionException, InvalidCommandException{
        String [] sText = sPos.split(" ");
        if(sText.length != 3) throwTestCaseException("incorrect rover initialization");
        int x = Integer.valueOf(sText[0]);
        int y = Integer.valueOf(sText[1]);
        Character orientation = sText[2].charAt(0); 
        
        String command = sCommand.replaceAll("\"", "");
        Rover rover = RoverFactory.createRover("", x, y, orientation, command);
        return rover;
        
    }
}
