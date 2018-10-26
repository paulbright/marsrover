/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.logging.Level;
import java.util.logging.Logger;
import marsrover.Grid;
import marsrover.InvalidPositionException;
import marsrover.Marsrover;
import marsrover.Rover;
import marsrover.RoverFactory;

/**
 *
 * @author paulbright
 */
public class TestRovers {
    
    public static void testRovers() {        
        Object [][] data =  {   
                                {5,5},
                                {1,2, 'N', "LMLMLMLMM" },
                                {3,3, 'E', "MMRMMRMRRM" } 
                            };
        
        testRovers(data);
    }
    
    public static void testRovers(Object [][] data) {  
        try {
            Grid.initGrid(0,0, (int)data[0][0],(int)data[0][1]);
            for(int i=1; i< data.length;i++){
                Rover rover = RoverFactory.createRover("", (int)data[i][0], (int)data[i][1], (Character)data[i][2],(String)data[i][3]);                
                Grid.addRover(rover);                                
            }
            
            Grid.animateRovers();
            
        } catch (InvalidPositionException ex) {
            Logger.getLogger(Marsrover.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
