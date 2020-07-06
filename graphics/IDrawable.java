//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package graphics;

import java.awt.*;

/**
 * Created by Or on 5/8/2017.
 * @Author Or Galili 302813464 SCE Ashdod
 */
public interface IDrawable {

    /**
     * The root path of the pictures
     */
    public final static String PICTURE_PATH = "src/pictures/";

    /**
     * Load the image to object
     * @param nm the part of the path that belongs to a specific picture
     */
    public void loadImages(String nm);

    /**
     * Drawing the image on the screen
     * @param g
     */
    public void  drawObject(Graphics g);

    /**
     * @return the color of the image
     */
    public String getColor();
}
