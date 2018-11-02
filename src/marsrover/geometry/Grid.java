/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover.geometry;

import marsrover.vehicles.Rover;
import marsrover.exceptions.InvalidPositionException;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author paulbright
 */
public class Grid {
    private static int lx, ly, tx, ty;
    private static List<Rover> rovers = null;    
    public static void initGrid(int tx, int ty) {
        Grid.lx = 0;
        Grid.ly = 0;
        Grid.tx = tx;
        Grid.ty = ty;
        if(rovers != null) rovers.clear();
        rovers = new ArrayList<>();
    }
    
    public static List<Rover> getRovers() {
        return rovers;
    }
    public static void addRover(Rover rover){
        rovers.add(rover);        
    }
    public static int getLx() {
        return lx;
    }

    public static int getLy() {
        return ly;
    }

    public static int getTx() {
        return tx;
    }

    public static int getTy() {
        return ty;
    }

    private static void validate(int x, int y) throws InvalidPositionException{
        if( x < getLx() || x >= getTx() || y < getLy() || y >= getTy() ) 
            throw new InvalidPositionException("Invalid poistion (" + x +"," + y +")" );
       
    }

    public static void validate(Point location) throws InvalidPositionException{
	validate(location.x, location.y);
    }
}
