/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover;

/**
 *
 * @author paulbright
 */
public class Rover {
    private String name;
    private Position position; 
    private String command;
    private Orientation orientation;


    public Rover(String name, Position position, Orientation orientation){
        this.name = name;
        this.position = position;
        this.orientation = orientation;
        this.position.setOrientation(orientation);
    }

        public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
    
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
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

    public Position executeCommand() throws InvalidPositionException{        
        for(int i=0; i<this.command.length();i++){
            position.move(this.command.charAt(i));
        }
        return this.position;
    }
    
    public String toString(){
        return name + position.toString() + " " + orientation.toString();
    }
 
}
