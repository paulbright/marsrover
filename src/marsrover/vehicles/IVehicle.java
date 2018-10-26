/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover.vehicles;

import marsrover.exceptions.InvalidPositionException;

/**
 *
 * @author paulbright
 */
public interface IVehicle {
    public void turnLeft();
    public void turnRight();
    public void moveForward() throws InvalidPositionException;
}
