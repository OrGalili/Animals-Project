//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package plants;

import food.EFoodType;
import food.IEdible;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.ILocatable;
import mobility.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Abstract class for creating classes
 */
public abstract class Plant implements IEdible, ILocatable, IDrawable {

	/**
	 * the location of the plant
	 */
	private Point location;
	/**
	 * the panel to add the plant on it
	 */
	protected ZooPanel pan;
	/**
	 * the image of the plant
	 */
	protected BufferedImage img;

	/**
	 * Constructor
	 * @param p the panel to add the plant on it
	 */
	public Plant(ZooPanel p) {
		pan = p;
		this.location = new Point(pan.getWidth()/2,pan.getHeight()/2);
	}

	/**
	 * loading the image of the plant
	 * @param nm the part of the path that belongs to a specific picture
	 */
	public void loadImages(String nm){
			try { 
				img = ImageIO.read(new File(PICTURE_PATH + nm + ".png"));
			}
			catch (IOException e) { System.out.println("Cannot load picture"); }
	}

	public void drawObject(Graphics g) {
		g.drawImage(img, location.getX()-20, location.getY()-20, 40, 40, pan);
	}

	/**
	 * @return the food type of the plant(always vegetable)
	 */
	public EFoodType getFoodType() { return EFoodType.VEGETABLE; }
	/**
	 * @return "green" string
	 */
	public String getColor() { return "Green"; }

	/**
	 * @return null location
	 */
	public Point getLocation() { return null; }

	/**
	 * @param location of the plant
	 * @return false
	 */
	public boolean setLocation(Point location) { return false; }


}
