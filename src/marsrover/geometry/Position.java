/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover.geometry;

import marsrover.geometry.Orientation;
import marsrover.geometry.Grid;
import marsrover.exceptions.InvalidPositionException;
import java.awt.Point;

/**
 *
 * @author paulbright
 */
public class Position {

    private final Point location;
   
    private Orientation orientation;

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Point getLocation(){
	return location;
    }
    

    public Orientation getDirection() {
        return orientation;
    }
    
    public Position(int x, int y) throws InvalidPositionException{ 
	location = new Point(x,y);
	Grid.validate(location);
    }
  
//    private void move(Character move)throws InvalidPositionException {
//        switch(move){
//            case 'M':
//                step();                                                
//                break;
//            case 'L':
//            case 'R':
//                orientation.setDirection(move);
//        }
//    }
    
    private void step() throws InvalidPositionException{
       switch(orientation.getDirection()){
           case EAST:
	       ++location.x;
               break;
           case WEST:
               --location.x;
               break;
           case NORTH:
               ++location.y;
               break;
           case SOUTH:
               --location.y;
               break;
       }
       Grid.validate(location);
    }
    @Override
    public String toString(){
        return  location.x + " " + location.y; 
    }

    public void turnLeft() {
	this.orientation.setDirectionLeft();
    }

    public void turnRight() {
	this.orientation.setDirectionRight();
    }

    public void moveForward() throws InvalidPositionException {
	this.step();
    }
    
}
