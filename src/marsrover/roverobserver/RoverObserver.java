/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover.roverobserver;

import marsrover.vehicles.IVehicle;

/**
 *
 * @author paulbright
 */
public class RoverObserver implements IVehicleObserver{

    @Override
    public void update(IVehicle vehicle, String msg) {
        System.out.println(msg);
    }
    
}
