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
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import marsrover.commands.MoveForward;
import marsrover.commands.Reverse;
import marsrover.commands.TurnLeft;
import marsrover.commands.TurnRight;
import marsrover.geometry.Grid;

public class DrawImageOnCavas implements Runnable {
    private TerrainMap terrain;
    private Thread t;
    private boolean running;
    public int width, height;
    private String title;
    private BufferStrategy bs;
    private Graphics g;
    private BufferedImage testImage;

    public DrawImageOnCavas(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;

    }

    @Override
    public void run() {
        init();
        System.err.println("run..." + running);
        while (running) {
            //System.err.println("run..." + running);
            tick();
            render();
        }
        //stop();
    }

    private void render() {
        bs = terrain.getCanvas().getBufferStrategy();

        if (bs == null) {
            System.out.println("bs is null....");
            terrain.getCanvas().createBufferStrategy(3);
            return;
        }
        
        GridCanvas canvas = terrain.getCanvas(); 
        //canvas.execCommand(new MoveForward());
       
    }

    private void tick() {
        Scanner in = new Scanner(System.in);
        System.out.println("enter command:(m,d,l,r,x)");
        String c = in.next();
        switch(c){
            case "m":
                terrain.getCanvas().execCommand(new MoveForward());
                break;
            case "d":
                terrain.getCanvas().execCommand(new Reverse());
                break;
            case "l":
                terrain.getCanvas().execCommand(new TurnLeft());
                break;
            case "r":
                terrain.getCanvas().execCommand(new TurnRight());
                break;
            case "x":
                stop();
                break;
            default:
                break;
        }
    }
    
    private void init() {
        terrain = new TerrainMap(title, width, height, Grid.getTx(), Grid.getTy()); 
        terrain.show(true);        
        terrain.setEnabled(true);
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        t = new Thread(this);
        t.start();

    }

    public synchronized void stop() {
        if (!running)
            return;
        running = false;
        try {
            t.join();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

    }

}