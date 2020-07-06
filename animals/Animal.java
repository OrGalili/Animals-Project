//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package animals;

import Decorator.ColoredAnimal;
import diet.IDiet;
import food.EFoodType;
import food.IEdible;
import graphics.IAnimalBehavior;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.ILocatable;
import mobility.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.Future;

/**
 * @author Or galili 302813464 SCE Ashdod
 * The basic properties and methods of some animal.
 * Eat/Move/Weight/Name/Sound of the animal
 * @version 1 13 April 2017
 */
public abstract class Animal extends Observable implements ColoredAnimal,IEdible , IDrawable, ILocatable, IAnimalBehavior , Runnable, Cloneable{

    protected Point location;
	/**
	 * The distance of eat
	 */
	protected final int EAT_DISTANCE = 5;
	/**
	 * The size of the animal
	 */
	protected int size;
	/**
	 * The color of the animal
	 */
	protected Color col;
	/**
	 * Horizontal speed
	 */
	protected int horSpeed;
	/**
	 * Vertical speed
	 */
	protected int verSpeed;
	/**
	 * X direction of the animal
	 */
	protected int x_dir;
	/**
	 * Y direction of the animal
	 */
	protected int y_dir;
	/**
	 * The counter of the animal eat
	 */
	protected int eatCount;
	/**
	 * the zoo panel that the animal belongs
	 */
	protected ZooPanel pan;
	/**
	 * flag for suspend thread
	 */
	protected boolean threadSuspended;
	/**
	 * Images for the animal to the two directions
	 */
	protected BufferedImage img1, img2;
    /**
     * boolean field checks if the animal thread is running
     */
	private boolean isRun;
    /**
     * the thread of the animal
     */
	private Future<?> task;

	/**
	 * used for eating appropriate food
	 * @see IDiet
	 */
	private IDiet diet;
	/**
	 * the name of the animal.
	 * protected - direct access to classes that inherit Animal
	 */
	protected String name;
	/**
	 * the weight of the animal
	 */
	private double weight;
    /**
     * factor of the weight of the animal
     */
    protected double factor;
    /**
     * of the name file
     */
    protected String nm;

	public Animal(){}

	/**
	 * Constructor of the animal
	 * @param size the size of the animal
	 * @param horSpeed horizontal speed
	 * @param verSpeed vertical speed
	 * @param col color of the animal
	 * @param zooPanel the zoo panel that belongs to the animal
	 */
	public Animal(int size , int horSpeed , int verSpeed , Color col,ZooPanel zooPanel){
	    setBasicParams(size,horSpeed,verSpeed,col,zooPanel);
	}

	public abstract Animal clone();

	/**
	 * Set Color of the animal
	 * @param col
	 */
	public void setColorByString(String col) {
		if(col.equals("Red"))
			setColor(Color.RED);

		else if(col.equals("Blue"))
			setColor(Color.BLUE);
	}

    /**
     * @return the horizontal direction
     */
    public int getX_dir() {
        return x_dir;
    }

    /**
     * @return the vertical direction
     */
    public int getY_dir() {
        return y_dir;
    }

    /**
	 * Set X direction of the animal
	 * @param x_dir
	 */
	public void setX_dir(int x_dir) {
		this.x_dir = x_dir;
	}

	/**
	 * Set Y direction of the animal
	 * @param y_dir
	 */
	public void setY_dir(int y_dir) {
		this.y_dir = y_dir;
	}

	/**
	 * Set the panel the animal belong
	 * @param pan
	 */
	public void setPan(ZooPanel pan) {
		this.pan = pan;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public int getEatCount() {
		return eatCount;
	}

	@Override
	public void eatInc() {
		eatCount++;
	}

    public void paintAnimal(String col){}

    /**
     * the thread of the animal
     * @param task the new thread to set
     */
    public void setTask(Future<?> task) {
		this.task = task;
	}

	/**
	 * @return the task of the animal which means his thread
	 */
	public Future<?> getTask() {
		return task;
	}

	/**
	 * set parameters of an animal
	 * @return the current object animal
	 */
	public Animal setBasicParams(int size , int horSpeed , int verSpeed , Color col,ZooPanel zooPanel){
        setLocation(/*new Point(0,0)*/getLocation());
		setSize(size);
		setWeight(size,factor);
		setHorSpeed(horSpeed);
		setVerSpeed(verSpeed);
		setColor(col);
		loadImages(nm);
		setPan(zooPanel);
		setX_dir(1);
		setY_dir(1);
        this.addObserver(ZooObserver.getZooObserverInstance(zooPanel));
		return this;
	}

	public Animal setAdvancedParams(Point location , int x_dir, int y_dir,boolean threadSuspended,int eatCount){
	    this.setLocation(location);
	    this.setX_dir(x_dir);
	    this.setY_dir(y_dir);
	    this.threadSuspended = threadSuspended;
	    this.eatCount = eatCount;
	    return this;
    }

	@Override
    public boolean setLocation(Point location) {
        this.location = new Point(location);
        return true;
    }

	/**
	 * @return the location of the animal. if null return coordinates x:0 y:0
	 */
	public Point getLocation() {
		if(location == null)
			return new Point(0,0);

        return location;
    }

    @Override
	public void loadImages(String nm) {
		char color = this.getColor().toLowerCase().charAt(0);
		try { img1 = ImageIO.read(new File(PICTURE_PATH+nm+"_"+color+"_1.png")); }//example grf_n_1.png nm = grf_n_
		catch (IOException e) { System.out.println("Cannot load image"); }

		try { img2 = ImageIO.read(new File(PICTURE_PATH+nm+"_"+color+"_2.png")); }
		catch (IOException e) { System.out.println("Cannot load image"); }
	}

	@Override
	public void drawObject(Graphics g) {
		g.setColor(col);
		if(x_dir==1)
			g.drawImage(img1, location.getX()-size/2, location.getY()-size/10, size/2, size, pan);
		else
			g.drawImage(img2, location.getX(), location.getY()-size/10, size/2, size, pan);
	}

	@Override
	public String getColor() {
		if(col == Color.RED)
			return "Red";

		else if(col == Color.BLUE)
			return "Blue";

		return "Natural";
	}

	/**
	 * Set Color of the animal
	 * @param col the color to set
	 * @return true
	 */
	public boolean setColor(Color col){
		this.col = col;
		return true;
	}

	/**
	 * Set the horizontal speed of the animal
	 * @param horSpeed to set
	 * @return true
	 */
	public boolean setHorSpeed(int horSpeed) {
		this.horSpeed = horSpeed;
		return true;
	}

	/**
	 * Set the vertical speed of the animal
	 * @param verSpeed to set
	 * @return true
	 */
	public boolean setVerSpeed(int verSpeed) {
		this.verSpeed = verSpeed;
		return true;
	}

	/**
	 * Sets the size of the animal
	 * @param size the size to set
	 * @return true
	 */
	public boolean setSize(int size) {
		this.size = size;
		return true;
	}

	/**
	 * @return ture if the thread suspended else false
	 */
	public boolean isSuspended(){
		return threadSuspended;
	}

    @Override
    public void setSuspended() {
        threadSuspended = true;
    }

    /**
     * Resuming thread of the animal
     */
    @Override
    public void setResumed() {
        threadSuspended = false;
        synchronized (this) {
            notify();
        }
    }

	/**
	 * @return true if the animal task(thread) is running else false
	 */
	public boolean isRun() {
        return isRun;
    }

	/**
	 * commands when deleting animal from the zoo panel
	 */
	public synchronized void interrupt(){
        isRun = false;
        notify();
        task.cancel(true);
    }

    /**
	 * The life cycle of the animal
	 */
	@Override
	public void run() {
	    isRun = true;
		while (isRun) {
			try {
				Thread.sleep(50);

				synchronized (this) {
					while(threadSuspended)
						wait();
				}
			} catch (InterruptedException e) {
				return;
			}

			if (this.getDiet().canEat(pan.checkFood()))  //  an animal goes to food
			{
				double oldSpead = Math.sqrt(horSpeed * horSpeed + verSpeed * verSpeed);
				double newHorSpeed = oldSpead * (location.getX() - pan.getWidth() / 2) /
						(Math.sqrt(Math.pow(location.getX() - pan.getWidth() / 2, 2) +
								Math.pow(location.getY() - pan.getHeight() / 2, 2)));
				double newVerSpeed = oldSpead * (location.getY() - pan.getHeight() / 2) /
						(Math.sqrt(Math.pow(location.getX() - pan.getWidth() / 2, 2) +
								Math.pow(location.getY() - pan.getHeight() / 2, 2)));
				int v = 1;
				if (newVerSpeed < 0) {
					v = -1;
					newVerSpeed = -newVerSpeed;
				}
				if (newVerSpeed > 10)
					newVerSpeed = 10;
				else if (newVerSpeed < 1) {
					if (location.getY() != pan.getHeight() / 2)
						newVerSpeed = 1;
					else
						newVerSpeed = 0;
				}
				int h = 1;
				if (newHorSpeed < 0) {
					h = -1;
					newHorSpeed = -newHorSpeed;
				}
				if (newHorSpeed > 10)
					newHorSpeed = 10;
				else if (newHorSpeed < 1) {
					if (location.getX() != pan.getWidth() / 2)
						newHorSpeed = 1;
					else
						newHorSpeed = 0;
				}
				location.setX((int) (location.getX() - newHorSpeed * h));
				location.setY((int) (location.getY() - newVerSpeed * v));
				if (location.getX() < pan.getWidth() / 2)
					x_dir = 1;
				else
					x_dir = -1;
				if ((Math.abs(location.getX() - pan.getWidth() / 2) < EAT_DISTANCE) &&
						(Math.abs(location.getY() - pan.getHeight() / 2) < EAT_DISTANCE)) {
					pan.eatFood(this);
				}
			} else // an animal moves without food
			{
				location.setX(location.getX() + horSpeed * x_dir);
				location.setY(location.getY() + verSpeed * y_dir);
			}

			// Change of moving direction:
			if (location.getX() > pan.getWidth()) {
				x_dir = -1;
			} else if (location.getX() < 0) {
				x_dir = 1;
			}

			if (location.getY() > pan.getHeight()) {
				y_dir = -1;
			} else if (location.getY() < 0) {
				y_dir = 1;
			}

			//setChanges(true); // == > cordChanged = true;
            setChanged();
            notifyObservers();

		}
	}

	/**
	 * @return part of the name file image of the animal
	 */
	public String getNm() {
		return nm;
	}

	/**
	 * @return the horizontal speed of the animal
	 */
	public int getHorSpeed() {
		return horSpeed;
	}

	/**
	 * @return the vertical speed of the animal
	 */
	public int getVerSpeed() {
		return verSpeed;
	}

	/**
	 * @return the diet
	 */
	public IDiet getDiet() {
		return this.diet;
	}

	@Override
	public EFoodType getFoodType() {
		return EFoodType.MEAT;
	}

	@Override
	public String getAnimalName() {
		return this.getClass().getSimpleName();
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return this.weight;
	}

	/**
	 * @param newDiet
	 */
	protected void setDiet(IDiet newDiet) {
		this.diet = newDiet;
	}

    /**
     *  setting the weight of the animal
     * @param size of the animal
     * @param factor
     * @return always true
     */
	public boolean setWeight(int size, double factor ){
		this.weight = size * factor;
		return true;
	}

	@Override
	public String toString() {
		return "[" + this.getClass().getSimpleName() + "] " + this.name;
	}
}
