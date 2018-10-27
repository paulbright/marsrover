/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Point;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import marsrover.commands.ICommandMovement;
import marsrover.commands.MovementCommandGenerator;
import marsrover.exceptions.InvalidCommandException;
import marsrover.exceptions.InvalidPositionException;
import marsrover.factory.RoverFactory;
import marsrover.geometry.Grid;
import marsrover.vehicles.Rover;
import marsrover.vehicles.RoverHandler;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author paulbright
 */
public class JUnitTest {
    
    public JUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void createGridTest() {
        System.out.println("running Grid test...");
        int x = 5, y = 5;
        Grid.initGrid(x, y);
        boolean success = false;
        try {
            Grid.validate(new Point(3,4));
            success = true;
        } catch (InvalidPositionException ex) {
            success = false;
        }
        
        assertEquals("Grid.validate", true, success);
        
        try {
            Grid.validate(new Point(6,4));
            success = true;
        } catch (InvalidPositionException ex) {
            success = false;
        }
        
        assertEquals("Grid.validate", false, success);

        assertEquals("Grid.getLx", 0, Grid.getLx());
        assertEquals("Grid.getLy", 0, Grid.getLy());        
        assertEquals("Grid.getTx", x, Grid.getTx());
        assertEquals("Grid.getTy", y, Grid.getTy());
        assertEquals("Grid.getRovers().size", 0, Grid.getRovers().size());
        Grid.addRover(null);
        assertEquals("Grid.getRovers().size", 1, Grid.getRovers().size());
        Grid.getRovers().clear();
        assertEquals("Grid.getRovers().size", 0, Grid.getRovers().size());
        
        System.out.println("Grid test success");
    }
    
    @Test
    public void createRoverFactoryTest() {
        System.out.println("running RoverFactory test...");
        boolean success = false;
        RoverFactory rf = null;
        rf = new RoverFactory();
        assert rf != null; 
        int x = 5, y = 5;
        Grid.initGrid(5, 5);        
        Rover rover = null;
        try {
            rover = (Rover)rf.makeVehicle("", 1, 2, "N", "LMLMLMLMM");
            
        } catch (InvalidPositionException | InvalidCommandException ex) {
            
        }                
        assertNotEquals("RoverFactory makeVehicle", rover, null);        
        Grid.addRover(rover);
        List<String> result = RoverHandler.getInstance().animateVehicles();
        assertEquals("RoverHandler.getInstance().animateVehicles", "1 3 N", result.get(0));
        
        System.out.println("RoverFactory test success");
    }
    
    @Test
    public void createMovementCommandGeneratorTest(){
        System.out.println("running MovementCommandGenerator test...");
        List <ICommandMovement> commands = null;
        
        try {
            commands = MovementCommandGenerator.generateCommands("LMMLRRMR");
        } catch (InvalidCommandException ex) {
           
        }               
        assertNotEquals("MovementCommandGenerator.generateCommands", commands, null);
        try {
            commands = MovementCommandGenerator.generateCommands("DLMMLRRMR");
        } catch (InvalidCommandException ex) {
            commands = null;
        }  
        
        assertEquals("MovementCommandGenerator.generateCommands", commands,null);
        System.out.println("MovementCommandGenerator test success");
    }
}
