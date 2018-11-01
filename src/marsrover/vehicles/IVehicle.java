/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover.vehicles;

import java.util.List;
import marsrover.commands.ICommandMovement;
import marsrover.exceptions.InvalidPositionException;
import marsrover.geometry.Position;

/**
 *
 * @author paulbright
 */
public interface IVehicle {
    public void turnLeft();
    public void turnRight();
    public void moveForward() throws InvalidPositionException;
    public void reverse() throws InvalidPositionException;
    
    public Position getPosition();
    public String getName();
    public List <ICommandMovement> getCommandList();
    public void executeCommand(ICommandMovement command) throws InvalidPositionException;
}
