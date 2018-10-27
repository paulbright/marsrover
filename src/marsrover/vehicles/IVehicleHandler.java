/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover.vehicles;

import java.util.List;
import marsrover.roverobserver.IVehicleObserver;

/**
 *
 * @author paulbright
 */
public interface IVehicleHandler {
    public List<String> animateVehicles();
    public void update(IVehicleObserver observer, IVehicle vehicle, String msg);
}
