/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover;

import java.util.List;
import marsrover.exceptions.InvalidCommandException;
import marsrover.exceptions.InvalidPositionException;
import marsrover.exceptions.InvalidTestcaseFile;
import marsrover.roverloader.RoverTestcaseLoader;
import marsrover.roverobserver.RoverObserver;
import marsrover.vehicles.RoverHandler;

/**
 *
 * @author paulbright
 */
public class Marsrover {

    /**
     * @param args the command line arguments
     * @throws marsrover.exceptions.InvalidTestcaseFile
     * @throws marsrover.exceptions.InvalidPositionException
     * @throws marsrover.exceptions.InvalidCommandException
     */
    public static void main(String[] args) throws InvalidTestcaseFile, InvalidPositionException, InvalidCommandException {   
        
        if(args.length == 0){
            System.out.println("usage: java -cp marsrover.jar marsrover.Marsrover <testcasefile.txt>");
            return;
        }
        
        RoverTestcaseLoader.initTestcase(args[0]);
        RoverHandler.getInstance().addObserver(new RoverObserver());
        List<String>results = RoverHandler.getInstance().animateVehicles();
        
    }
}
