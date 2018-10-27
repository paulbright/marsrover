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
public interface IVehicleObserver {
    public void update(IVehicle vehicle, String msg);
}
