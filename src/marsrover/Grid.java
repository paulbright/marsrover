/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author paulbright
 */
public class Grid {
    private static int lx, ly, tx, ty;
    private static List<Rover> rovers;
    public static void initGrid(int lx, int ly, int tx, int ty) {
        Grid.lx = lx;
        Grid.ly = ly;
        Grid.tx = tx;
        Grid.ty = ty;
        rovers = new ArrayList<>();
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

    public static void validate(int x, int y) throws InvalidPositionException{
        if( x < getLx() || x > getTx() || y < getLy() || y > getTy() ) 
            throw new InvalidPositionException("Invalid poistion (" + x +"," + y +")" );
       
    }
    
    public static void animateRovers(){
        for(Rover rover : rovers){
            try{
                rover.executeCommand();
                System.out.println(rover.toString());
            }
            catch(InvalidPositionException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
