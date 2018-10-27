/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover.factory;

import marsrover.exceptions.InvalidCommandException;
import marsrover.exceptions.InvalidPositionException;
import marsrover.vehicles.IVehicle;

/**
 *
 * @author paulbright
 */
public interface IVehicleFactory {
    public IVehicle makeVehicle(String name, int x, int y, String direction, String command)
            throws InvalidPositionException, InvalidCommandException;
    
}
