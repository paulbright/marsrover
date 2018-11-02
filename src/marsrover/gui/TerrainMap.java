/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover.gui;

/**
 *
 * @author paulbright
 */
import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import marsrover.commands.MoveForward;
import marsrover.geometry.Grid;
import marsrover.vehicles.Rover;
import marsrover.vehicles.RoverHandler;

/**
 * This is the demo class.
 */
public class TerrainMap extends Frame {

    /*
   * Construct a GfxDemo2 given its title, width and height. Uses a
   * GridBagLayout to make the Canvas resize properly.
     */
    private GridCanvas canvas = null;

    public TerrainMap(String title, int w, int h, int rows, int cols) {
        setTitle(title);
        setFocusable(true);
        // Now create a Canvas and add it to the Frame.
        canvas = new GridCanvas(w, h, rows, cols);
        add(canvas);
        canvas.setRovers(Grid.getRovers());
        RoverHandler.getInstance().addObserver(canvas);
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
                System.exit(0);
            }

        });

        pack();
    }

    public GridCanvas getCanvas() {
        return this.canvas;
    }

}
