/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import marsrover.exceptions.InvalidPositionException;
import marsrover.geometry.Grid;
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
        System.out.println("inside grid test");
        int x=5,y=4;
        Grid.initGrid(x, y);
        boolean success = false;
        try {
            Grid.validate(new Point(3,4));
            success = true;
        } catch (InvalidPositionException ex) {
            success = false;
        }
        
        assert success == true; 
        
        try {
            Grid.validate(new Point(6,4));
            success = true;
        } catch (InvalidPositionException ex) {
            success = false;
        }
        
        assert success == false; 
        
        assert 0 == Grid.getLx();
        assert 0 == Grid.getLy();
        assert 5 == Grid.getTx();
        assert 4 == Grid.getTy();
        
        assert 0 == Grid.getRovers().size();
        
        System.out.println("Grid test success");
    }
}
