/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover.utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author paulbright
 */
public class ImageHandler {

    public static BufferedImage loadImage(String fileName) {
        BufferedImage bi = null;

        try {
            bi = ImageIO.read(new File(fileName));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Image could not be read");
            System.exit(1);
        }

        return bi;
    }
    
    public static BufferedImage loadImage(URL url) {
        BufferedImage bi = null;

        try {
            bi = ImageIO.read(url);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Image could not be read");
            System.exit(1);
        }

        return bi;
    }
        
}
