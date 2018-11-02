/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover.gui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import marsrover.commands.ICommandMovement;
import marsrover.commands.MoveForward;
import marsrover.commands.Reverse;
import marsrover.commands.TurnLeft;
import marsrover.commands.TurnRight;
import marsrover.exceptions.InvalidPositionException;
import marsrover.geometry.Grid;
import marsrover.roverobserver.IVehicleObserver;
import marsrover.vehicles.IVehicle;
import marsrover.vehicles.Rover;

/**
 *
 * @author paulbright
 */
public class GridCanvas extends Canvas implements IVehicleObserver {

    private static final int UP = 38;
    private static final int DOWN = 40;
    private static final int LEFT = 37;
    private static final int RIGHT = 39;

    int width, height;
    List<Rover> rovers = null;
    int rows;
    int cols;

    GridCanvas(int w, int h, int r, int c) {
        setSize(width = w, height = h);
        rows = r;
        cols = c;
        setFocusable(true);

        addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case UP:
                        execCommand(new MoveForward());
                        break;
                    case DOWN:
                        execCommand(new Reverse());
                        break;
                    case LEFT:
                        execCommand(new TurnLeft());
                        break;
                    case RIGHT:
                        execCommand(new TurnRight());
                        break;
                }
            }

            public void keyReleased(KeyEvent e) {
            }
        });
    }

    public void setRovers(List<Rover> rovers) {
        this.rovers = rovers;
    }

    public void paint(Graphics g) {
        int i;
        super.paint(g);
        width = getSize().width;
        height = getSize().height;

        // draw the rows
        int rowHt = height / (rows);
        for (i = 0; i < rows; i++) {
            g.drawLine(0, i * rowHt, width, i * rowHt);
        }

        // draw the columns
        int rowWid = width / (cols);
        for (i = 0; i < cols; i++) {
            g.drawLine(i * rowWid, 0, i * rowWid, height);
        }

        drawRovers();        
    }

    private void drawRovers() {
        for (Rover r : rovers) {
            r.draw(this);
        }
    }

    public void execCommand(ICommandMovement command) {
        try {
            command.execute(rovers.get(0));                        
        } catch (InvalidPositionException ex) {
            System.out.println("illegal move");
        } finally{
            paint(this.getGraphics());
            System.out.println(rovers.get(0).toString());
        }
    }

    @Override
    public void update(IVehicle vehicle, String msg) {
        Rover rover = (Rover) vehicle;
        rover.draw(this);
    }

}
