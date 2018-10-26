/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover.factory;

import marsrover.geometry.Grid;
import marsrover.geometry.Orientation;
import marsrover.geometry.Position;
import marsrover.vehicles.Rover;
import marsrover.exceptions.InvalidCommandException;
import marsrover.exceptions.InvalidPositionException;

/**
 *
 * @author paulbright
 */
public class RoverFactory {
    public static Rover createRover(String name, int x, int y, Character direction, String command) 
            throws InvalidPositionException, InvalidCommandException{
        Position position = new Position(x,y,direction);
	Grid.validate(position.getLocation());      
        Rover rover = new Rover(name, position);
        rover.setCommandString(command);
        return rover;
    }
    
    public static Rover createRover(String name, int x, int y, Character direction) 
            throws InvalidPositionException, InvalidCommandException{
        Position position = new Position(x,y, direction);
	Grid.validate(position.getLocation());
        Rover rover = new Rover(name, position);
        return rover;
    }
}
