/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover;

import marsrover.exceptions.InvalidCommandException;
import marsrover.exceptions.InvalidPositionException;
import marsrover.exceptions.InvalidTestcaseFile;
import test.TestRovers;

/**
 *
 * @author paulbright
 */
public class Marsrover {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InvalidTestcaseFile, InvalidPositionException, InvalidCommandException {   
        //TestRovers.test();
        if(args.length == 0){
            System.out.println("usage: java -cp marsrover.jar marsrover.Marsrover <testcasefile.txt>");
            return;
        }
        
        TestRovers.test(args[0]);
    }
}
