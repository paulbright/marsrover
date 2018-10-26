/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover;

/**
 *
 * @author paulbright
 */
public class RoverFactory {
    public static Rover createRover(String name, int x, int y, Character direction, String command) 
            throws InvalidPositionException{
        Grid.validate(x, y);
        Position position = new Position(x,y);
        Orientation orientation = new Orientation(direction);
        Rover rover = new Rover(name, position, orientation);
        rover.setCommand(command);
        return rover;
    }
}
