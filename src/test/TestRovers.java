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
import marsrover.roverloader.RoverTestcaseLoader;
import marsrover.vehicles.RoverHandler;

/**
 *
 * @author paulbright
 */
public class TestRovers {

    
    public static void main(String[] args) {
        runTests();
    }
    
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
        RoverHandler.getInstance().animateVehicles();
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
        RoverTestcaseLoader.initTestcase(filename);
    }

    private static void runTests() {
    }
    

}
