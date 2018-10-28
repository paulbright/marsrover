/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.List;
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
public class RoverHandlerTest {
    
    public RoverHandlerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws InvalidCommandException, InvalidPositionException {
        Grid.initGrid(5, 5);
        RoverFactory rf = new RoverFactory();
        Rover rover = (Rover)rf.makeVehicle("", 1, 2, "N", "LMLMLMLMM");
        Grid.addRover(rover);
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void createRoverHandlerTest() {
        System.out.println("running RoverHandler test...");
        RoverHandler rh = null;
        
        rh = RoverHandler.getInstance();
        
        assertNotNull("RoverHandler ", rh);                
        List<String> result = rh.animateVehicles();
        assertEquals("RoverHandler.getInstance().animateVehicles", "1 3 N", result.get(0));
        
        System.out.println("RoverHandler test success");
    }
}
