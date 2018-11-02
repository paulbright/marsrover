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
    

    public Orientation getOrientation() {
        return orientation;
    }
    
    public Position(int x, int y, Character orientation) throws InvalidPositionException{ 
        this.orientation = new Orientation(orientation);
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
    
    private void stepForward() throws InvalidPositionException{
       Point newLoc = new Point(location);
       switch(orientation.getDirection()){
           case EAST:
	       ++newLoc.x;
               break;
           case WEST:
               --newLoc.x;
               break;
           case NORTH:
               ++newLoc.y;
               break;
           case SOUTH:
               --newLoc.y;
               break;
       }
       try{
            Grid.validate(newLoc);
            location.x = newLoc.x;
            location.y = newLoc.y;
       }
       catch(InvalidPositionException ex){
           throw ex;
       }
    }
    
    private void stepBackward() throws InvalidPositionException{
        Point newLoc = new Point(location);
        switch(orientation.getDirection()){
           case EAST:
	       --newLoc.x;
               break;
           case WEST:
               ++newLoc.x;
               break;
           case NORTH:
               --newLoc.y;
               break;
           case SOUTH:
               ++newLoc.y;
               break;
       }
       try{
            Grid.validate(newLoc);
            location.x = newLoc.x;
            location.y = newLoc.y;
       }
       catch(InvalidPositionException ex){
           throw ex;
       }
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
	this.stepForward();
    }
    
    public void reverse() throws InvalidPositionException {
	this.stepBackward();
    }
    
}
