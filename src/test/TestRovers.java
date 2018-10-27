/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
    private static void runTests() {
        System.out.println(testCase1()?"success":"failed");
    }


    public static boolean testCase1() {
        Object[][] data = {
            {5, 5},
            {1, 2, 'N', "LMLMLMLMM"},
            {3, 3, 'E', "MMRMMRMRRM"}
        };
        
        List<String> expected_results = new ArrayList<>();
        expected_results.add( "1 3 N");
        expected_results.add( "5 1 E");
        loadRovers(data);
        
        List<String> received_results = RoverHandler.getInstance().animateVehicles();
        return checkResults(expected_results, received_results);
    }

    private static void loadRovers(Object[][] data) {
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

    

    private static boolean checkResults(List<String> expected_results, List<String> received_results) {
        if(expected_results.size() != received_results.size()) return false;
        for(int i=0; i<expected_results.size();i++){
            if(expected_results.get(i).compareTo( received_results.get(i) ) != 0 ) return false;
        }
        return true;
    }
    

}
