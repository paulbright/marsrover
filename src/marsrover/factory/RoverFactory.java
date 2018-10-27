/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover.factory;

import marsrover.geometry.Grid;
import marsrover.geometry.Position;
import marsrover.vehicles.Rover;
import marsrover.exceptions.InvalidCommandException;
import marsrover.exceptions.InvalidPositionException;
import marsrover.vehicles.IVehicle;

/**
 *
 * @author paulbright
 */
public class RoverFactory implements IVehicleFactory{
    private Rover createRover(String name, int x, int y, String direction, String command) 
            throws InvalidPositionException, InvalidCommandException{
        Position position = new Position(x,y,direction.charAt(0));
	Grid.validate(position.getLocation());      
        Rover rover = new Rover(name, position);
        rover.setCommandString(command);
        return rover;
    }

    @Override
    public IVehicle makeVehicle(String name, int x, int y, String direction, String command) throws InvalidPositionException, InvalidCommandException {
        return createRover(name, x, y, direction, command);
    }
    
    
}
