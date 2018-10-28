/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover.commands;

import marsrover.exceptions.InvalidPositionException;
import marsrover.vehicles.IVehicle;

/**
 *
 * @author paulbright
 */
public class Reverse implements ICommandMovement{

    @Override
    public void execute(IVehicle vehicle) throws InvalidPositionException {
	vehicle.reverse();
    }
}
