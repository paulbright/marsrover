/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover.geometry;

import marsrover.enums.DIRECTION;
import marsrover.enums.TURN;

/**
 *
 * @author paulbright
 */
public class Orientation {
    DIRECTION direction;

    public Orientation(Character direction) {
        this.direction = direction=='E'?DIRECTION.EAST:
                direction=='W'?DIRECTION.WEST:
                direction=='N'?DIRECTION.NORTH:DIRECTION.SOUTH;
    }
    public Orientation(DIRECTION direction) {
        this.direction = direction;
    }
    
    public DIRECTION setDirection(Character turn) {
        return this.direction = this.setDirection(turn=='L'? TURN.LEFT : TURN.RIGHT);
    }
    
    public DIRECTION setDirectionLeft() {
        return this.direction = Turning.turn(this.direction, TURN.LEFT);
    }
    public DIRECTION setDirectionRight() {
        return this.direction = Turning.turn(this.direction, TURN.RIGHT);
    }
    public DIRECTION setDirection(TURN turn) {
        return this.direction = Turning.turn(this.direction, turn);
    }
    
    public DIRECTION getDirection(){
        return this.direction;
    }
    public Character getDirectionInChar(){
        return this.direction==DIRECTION.EAST?'E': 
                this.direction==DIRECTION.WEST?'W':
                this.direction==DIRECTION.NORTH?'N':'S';
    }
    public String toString(){
        return getDirectionInChar().toString() ; 
    }
}

class Turning{
    public static DIRECTION turn(DIRECTION currentDirection, TURN turn){
        switch(currentDirection){
            case NORTH:
                currentDirection = turn == TURN.LEFT ? DIRECTION.WEST : DIRECTION.EAST;
                break;
            case EAST:
                currentDirection = turn == TURN.LEFT ? DIRECTION.NORTH : DIRECTION.SOUTH;
                break;
            case WEST:
                currentDirection = turn == TURN.LEFT ? DIRECTION.SOUTH : DIRECTION.NORTH;
                break;
            case SOUTH:
                currentDirection = turn == TURN.LEFT ? DIRECTION.EAST : DIRECTION.WEST;
                break;
        }
        return currentDirection;
    }
}


