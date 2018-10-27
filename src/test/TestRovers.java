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
import test.testcases.RoverTestcase;

/**
 *
 * @author paulbright
 */
public class TestRovers {

    List<RoverTestcase> testcases = new ArrayList<>();
    
    public static void main(String[] args) {
        TestRovers testRovers = new TestRovers();
        testRovers.loadTests();
        testRovers.runTests(args);
    }
    
    private void runTests(String[] args) {
        String success = "success";
        String failure = "failed";
        
        for(RoverTestcase tc: testcases){
            System.out.println(tc.test() ? success : failure);        
        }
    }

    private void loadTests() {
        Object[][] data1 = {
            {5, 5},
            {1, 2, 'N', "LMLMLMLMM"},
            {3, 3, 'E', "MMRMMRMRRM"}
        };
        List<String> expected_results1 = new ArrayList<>();
        expected_results1.add("1 3 N");
        expected_results1.add("5 1 E");
                
        Object[][] data2 = {
            {0, 0},
            {1, 2, 'N', "LMLMLMLMM"},
            {3, 3, 'E', "MMRMMRMRRM"}
        };
        List<String> expected_results2 = new ArrayList<>();
        expected_results2.add("Invalid poistion (1,2)");
                
        Object[][] data3 = {
            {2147483648L, 2147483648L},
            {1, 2, 'N', "LMLMLMLMM"}            
        };
        List<String> expected_results3 = new ArrayList<>();
        expected_results3.add("java.lang.Long cannot be cast to java.lang.Integer");
                        
        Object[][] data4 = {
            {2147483647, 2147483647},
            {2500, 2500, 'N', "LMLMLMLMMLMLMLMLMMLMLMLMLMMLMLMLMLMMLMLMLMLM"},
            {3, 3, 'E', "MMRMMRMRRM"},
            {2500, 2500, 'N', "LMMMMMMMMMM"},
            {3000, 1000, 'S', "LMMMMMMMMMM"}
        };

        List<String> expected_results4 = new ArrayList<>();
        expected_results4.add("2500 2504 N");
        expected_results4.add("5 1 E");
        expected_results4.add("2490 2500 W");
        expected_results4.add("3010 1000 E");

        loadTest(data1, expected_results1);
        loadTest(data2, expected_results2);
        loadTest(data3, expected_results3);
        loadTest(data4, expected_results4);
    }
    
    private void loadTest(Object[][] data, List<String> expected_results){
        RoverTestcase testcase = new RoverTestcase(data, expected_results);
        testcases.add(testcase);
    }
}
