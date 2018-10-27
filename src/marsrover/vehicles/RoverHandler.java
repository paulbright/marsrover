/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover.vehicles;

import java.util.ArrayList;
import java.util.List;
import marsrover.exceptions.InvalidPositionException;
import marsrover.geometry.Grid;
import marsrover.roverobserver.IVehicleObserver;

/**
 *
 * @author paulbright
 */
public class RoverHandler implements IVehicleHandler{
    
    private static RoverHandler instance = new RoverHandler();
    private final List<IVehicleObserver> observers;
    
    private RoverHandler(){
        observers = new ArrayList<>();
    }
    
    public static RoverHandler getInstance(){
        if(instance == null){
            instance = new RoverHandler();
        }        
        return instance;
    }
    
    public void addObserver(IVehicleObserver observer){
        observers.add(observer);
    }
    
    public void removeObserver(IVehicleObserver observer){
        observers.remove(observer);
    }
    
    public void clearObservers(){
        observers.clear();
    }
    private List<String> animateRovers(){
        List<String> results = new ArrayList<>();
        
        if(Grid.getRovers() == null ||  Grid.getRovers().size() == 0) {
            results.add("no rovers found");
            return results;
        } 
        
        for(Rover rover : Grid.getRovers()){
            try{
                rover.executeStoredCommands();
                results.add(rover.toString());
                updateAll(rover, rover.toString());
                
            }
            catch(InvalidPositionException ex){
                results.add(ex.getMessage());
                updateAll(rover, ex.getMessage());
            }
        }
        
        return results;
    }

    @Override
    public List<String> animateVehicles() {
        return animateRovers();
    }

    @Override
    public void update(IVehicleObserver observer, IVehicle vehicle, String msg) {
        observer.update(vehicle, msg);
    }

    private void updateAll(Rover rover, String msg) {
        for(IVehicleObserver observer: observers){
            observer.update(rover, msg);
        }
    }
}
