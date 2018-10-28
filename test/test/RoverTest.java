/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import marsrover.commands.MoveForward;
import marsrover.commands.Reverse;
import marsrover.commands.TurnLeft;
import marsrover.commands.TurnRight;
import marsrover.exceptions.InvalidCommandException;
import marsrover.exceptions.InvalidPositionException;
import marsrover.factory.RoverFactory;
import marsrover.geometry.Grid;
import marsrover.vehicles.Rover;
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
public class RoverTest {
    
    public RoverTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws InvalidPositionException, InvalidCommandException {
        Grid.initGrid(5, 5);
        RoverFactory rf = new RoverFactory();
        Rover rover = (Rover)rf.makeVehicle("", 1, 2, "N", "LMLMLMLMM");
        Grid.addRover(rover);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void createRoverTest() {
        System.out.println("running Rover test...");
        List<Rover> rovers = null;
        rovers = Grid.getRovers();
        assertNotNull("Grid.getRovers", rovers);
        
        Rover rover = null;
        rover = rovers.get(0);
        assertNotNull("Rover getRovers", rover);
        
        assertNotNull("Rover getCommandList", rover.getCommandList());
        boolean success  = false;
        try {
            rover.setCommandString("LMLMXLMLMM");
            success = true;
        } catch (InvalidCommandException ex) {
           success = false; 
        }
        
        assertFalse("Rover getCommandList", success);
        
        try {
            rover.executeStoredCommands();
            success = true;
        } catch (InvalidPositionException ex) {
            success = false;
        }
        
        assertTrue("Rover executeStoredCommands", success);
        
        assertEquals( "Rover toString", rover.toString(), "1 3 N");
        
        TurnRight turnRight = new TurnRight();        
        turnRight.execute(rover);        
        assertEquals( "Rover TurnRight", rover.toString(), "1 3 E");
                     
        (new TurnLeft()).execute(rover);        
        assertEquals( "Rover TurnLeft", rover.toString(), "1 3 N");
        
        try {
            (new MoveForward()).execute(rover);
            success = true;
        } catch (InvalidPositionException ex) {
            success = false;
        }
        assertTrue("Rover MoveForward", success);        
        assertEquals( "Rover MoveForward", rover.toString(), "1 4 N");
        
        try {
            (new Reverse()).execute(rover);
            success = true;
        } catch (InvalidPositionException ex) {
            success = false;
        }
        assertTrue("Rover Reverse", success);                
        assertEquals( "Rover Reverse", rover.toString(), "1 3 N");
        
        (new TurnLeft()).execute(rover);
        assertEquals( "Rover Reverse", rover.toString(), "1 3 W");
        
        (new TurnLeft()).execute(rover);
        assertEquals( "Rover Reverse", rover.toString(), "1 3 S");
        System.out.println("Rover test success");
    }
}
