/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover.vehicles;

import marsrover.vehicles.IVehicle;
import marsrover.exceptions.InvalidPositionException;
import java.util.ArrayList;
import java.util.List;
import marsrover.geometry.Orientation;
import marsrover.geometry.Position;
import marsrover.commands.ICommandMovement;
import marsrover.commands.MoveForward;
import marsrover.commands.MovementCommandGenerator;
import marsrover.commands.TurnLeft;
import marsrover.commands.TurnRight;
import marsrover.exceptions.InvalidCommandException;

/**
 *
 * @author paulbright
 */
public class Rover implements IVehicle {
    private String name;
    private Position position; 
    private String command;   
    private List <ICommandMovement> commands;


    public Rover(String name, Position position){
        this.name = name;
        this.position = position;                
	commands = new ArrayList<>();
    }

    public Orientation getOrientation() {
        return position.getOrientation();
    }
    
    public void clearCommads(){
	command = "";
	commands.clear();
    }
    public void setOrientation(Orientation orientation) {
        position.setOrientation(orientation);
    }
    
    public String getCommandString() {
        return command;
    }
    
    public List <ICommandMovement> getCommandList() {
        return commands;
    }
    
    public void setCommandString(String command) throws InvalidCommandException {
        this.command = command;
	validateCommands();
    }
    
    public void addCommand(ICommandMovement command){
	commands.add(command);
    }
    public void addCommand(int index, ICommandMovement command){
	commands.add(index, command);
    }
    public void removeCommand(ICommandMovement command){
	commands.remove(command);
    }
    public void removeCommand(int index){
	commands.remove(index);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void executeStoredCommands() throws InvalidPositionException{        
	for(ICommandMovement command: commands){
	    command.execute(this);
	}
    }
    
    public void executeCommand(ICommandMovement command) throws InvalidPositionException{
	command.execute(this);
    }
    
    @Override
    public String toString(){
        return name + position.toString() + " " + getOrientation().toString();
    }

    @Override
    public void turnLeft() {
	position.turnLeft();
    }

    @Override
    public void turnRight() {
	position.turnRight();
    }

    @Override
    public void moveForward() throws InvalidPositionException {
	position.moveForward();
    }

    private void validateCommands() throws InvalidCommandException{
	this.commands =  MovementCommandGenerator.generateCommands(command);
    }
 
}
