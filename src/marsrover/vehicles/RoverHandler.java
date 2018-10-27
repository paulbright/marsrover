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
    private List<IVehicleObserver> observers;
    
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
    private String animateRovers(){
        StringBuilder sb = new StringBuilder();
        
        if(Grid.getRovers() == null ||  Grid.getRovers().size() == 0) {
            return "no rovers found";
        } 
        
        for(Rover rover : Grid.getRovers()){
            try{
                rover.executeStoredCommands();
                sb.append(rover.toString());
                updateAll(rover, rover.toString());
                sb.append("\n");
            }
            catch(InvalidPositionException ex){
                sb.append(ex.getMessage());
                 updateAll(rover, ex.getMessage());
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    @Override
    public String animateVehicles() {
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
