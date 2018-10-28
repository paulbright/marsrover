/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import marsrover.exceptions.InvalidCommandException;
import marsrover.exceptions.InvalidPositionException;
import marsrover.exceptions.InvalidTestcaseFile;
import marsrover.roverloader.RoverTestcaseLoader;
import marsrover.utility.FileHandler;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author paulbright
 */
public class RoverTestcaseLoaderTest {
    private static final String FILENAME_GOOD_TESTCASE = "testcase.txt";
    private static final String GOOD_TESTCASE = "5 5\n" +
                        "1 2 N\n" +
                        "LMLMLMLMMB\n" +
                        "3 3 E\n" +
                        "MMRMMRMRRM";
    
    private static final String FILENAME_FAIL_TESTCASE = "testcase_fail.txt";
    private static final String FAILE_TESTCASE = "5 5\n" +
                        "1 2 N\n" +
                        "LMLMLMBLMM\n" +
                        "3 3 E\n" +
                        "MMRDRMRRM";
    
    public RoverTestcaseLoaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        FileHandler.deleteFile(FILENAME_GOOD_TESTCASE);
        FileHandler.deleteFile(FILENAME_FAIL_TESTCASE);
        FileHandler.createFile(FILENAME_GOOD_TESTCASE);
        FileHandler.createFile(FILENAME_FAIL_TESTCASE);
        FileHandler.write(GOOD_TESTCASE, FILENAME_GOOD_TESTCASE);
        FileHandler.write(FAILE_TESTCASE, FILENAME_FAIL_TESTCASE);
    }
    
    @After
    public void tearDown() {
        FileHandler.deleteFile(FILENAME_GOOD_TESTCASE);
        FileHandler.deleteFile(FILENAME_FAIL_TESTCASE);
    }

    
    @Test
    public void createRoverTestcaseLoaderTest() {
        System.out.println("running RoverTestcaseLoader test...");
        
        boolean success = true;
        try {
            RoverTestcaseLoader.initTestcase(FILENAME_GOOD_TESTCASE);
            success = true;
        } catch (InvalidTestcaseFile | InvalidPositionException | InvalidCommandException ex) {
            success = false;
        }
        
        assertTrue("RoverTestcaseLoader initTestcase", success);
        try {
            RoverTestcaseLoader.initTestcase(FILENAME_FAIL_TESTCASE);
            success = true;
        } catch (InvalidTestcaseFile | InvalidPositionException | InvalidCommandException ex) {
          success = false;  
        }
        
        assertFalse("RoverTestcaseLoader initTestcase", success);
        
        System.out.println("RoverTestcaseLoader success");
    }
}
