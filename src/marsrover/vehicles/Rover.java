/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover.vehicles;

import java.awt.Canvas;
import java.awt.Image;
import marsrover.exceptions.InvalidPositionException;
import java.util.ArrayList;
import java.util.List;
import marsrover.geometry.Orientation;
import marsrover.geometry.Position;
import marsrover.commands.ICommandMovement;
import marsrover.commands.MovementCommandGenerator;
import marsrover.enums.DIRECTION;
import marsrover.exceptions.InvalidCommandException;
import marsrover.geometry.Grid;
import marsrover.utility.ImageHandler;


/**
 *
 * @author paulbright
 */
public class Rover implements IVehicle {
    private String name;
    private Position position; 
    private String command;   
    private List <ICommandMovement> commands;
    private static Image img_n = ImageHandler.loadImage("/Users/paulbright/Pictures/rover_n.png");
    private static Image img_s = ImageHandler.loadImage("/Users/paulbright/Pictures/rover_s.png");
    private static Image img_e = ImageHandler.loadImage("/Users/paulbright/Pictures/rover_e.png");
    private static Image img_w = ImageHandler.loadImage("/Users/paulbright/Pictures/rover_w.png");
    
    
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
        validateCommands(command);
        this.command = command;
	
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
	for(ICommandMovement cmd: commands){
	    cmd.execute(this);
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

    private void validateCommands(String command) throws InvalidCommandException{
	this.commands =  MovementCommandGenerator.generateCommands(command);
    }

    @Override
    public void reverse() throws InvalidPositionException {
        position.reverse();
    }

    public void draw(Canvas canvas) {
       
        int width = canvas.getSize().width;
        int height = canvas.getSize().height;
        int w = width/Grid.getTx();
        int h = height/Grid.getTy();
        int x = (position.getLocation().x) * w;
        int y = (position.getLocation().y + 1) * h;
        Image img = position.getOrientation().getDirection() == DIRECTION.NORTH ? img_n :
                    position.getOrientation().getDirection() == DIRECTION.SOUTH ? img_s :
                    position.getOrientation().getDirection() == DIRECTION.EAST ? img_e : img_w;
        canvas.getGraphics().drawImage(img, x, height-y,w,h,null);
                
    }

}
