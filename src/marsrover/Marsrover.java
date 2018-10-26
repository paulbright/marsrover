/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover;

import test.TestRovers;

/**
 *
 * @author paulbright
 */
public class Marsrover {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        TestRovers.testRovers();
	TestRovers.testRovers(5,5, 1,2,'N',"LMLMLMLMM");
	TestRovers.testRovers(5,5, 3,3, 'E', "MMRMMRMRRM");
    }
}
