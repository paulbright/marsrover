/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover;

import java.util.List;
import marsrover.commands.ICommandMovement;
import marsrover.exceptions.InvalidCommandException;
import marsrover.exceptions.InvalidPositionException;
import marsrover.exceptions.InvalidTestcaseFile;
import marsrover.factory.RoverFactory;
import marsrover.geometry.Grid;
import marsrover.gui.DrawImageOnCavas;
import marsrover.gui.TerrainMap;
import marsrover.roverloader.RoverTestcaseLoader;
import marsrover.roverobserver.RoverObserver;
import marsrover.vehicles.Rover;
import marsrover.vehicles.RoverHandler;

/**
 *
 * @author paulbright
 */
public class MarsRover {

    /**
     * @param args the command line arguments
     * @throws marsrover.exceptions.InvalidTestcaseFile
     * @throws marsrover.exceptions.InvalidPositionException
     * @throws marsrover.exceptions.InvalidCommandException
     */
    public static void main(String[] args) throws InvalidTestcaseFile, InvalidPositionException, InvalidCommandException, InterruptedException {   
        
//        if(args.length == 0){
//            System.out.println("usage: java -cp marsrover.jar marsrover.MarsRover <testcasefile.txt>");
//            return;
//        }
        
        Grid.initGrid(5, 5);
        RoverFactory rf = new RoverFactory();
        Rover rover = (Rover)rf.makeVehicle("", 0, 0, "N", "");
        Grid.addRover(rover);
        
        TerrainMap tm = new TerrainMap("Mars Rover", 800, 800, Grid.getTx(), Grid.getTy());        
        tm.show(true);
        
        //DrawImageOnCavas game = new DrawImageOnCavas("Mars Rover", 1200, 1200);
        //game.start();


        //List<String>results = RoverHandler.getInstance().animateVehicles();
        
    }
}
