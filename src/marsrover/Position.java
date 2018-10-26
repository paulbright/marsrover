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
public class Position {

    private int x,y;
    private Orientation orientation;

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    

    public Orientation getDirection() {
        return orientation;
    }
    
    public Position(int x, int y) throws InvalidPositionException{
        Grid.validate(x, y);
        this.x = x;
        this.y = y;
       
    }
    
    public void move(Character move)throws InvalidPositionException {
        switch(move){
            case 'M':
                step();
                Grid.validate(x, y);                                    
                break;
            case 'L':
            case 'R':
                orientation.setDirection(move);
        }
    }
    
    public void step(){
       switch(orientation.getDirection()){
           case EAST:
               ++x;
               break;
           case WEST:
               --x;
               break;
           case NORTH:
               ++y;
               break;
           case SOUTH:
               --y;
               break;   
            
       }
    }
    public String toString(){
        return  x + " " + y; 
    }
}
