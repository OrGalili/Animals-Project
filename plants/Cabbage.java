//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package plants;

import graphics.ZooPanel;

/**
 * class creating cabbage object
 */
public class Cabbage extends Plant {

	/**
	 * the only instance of the class
	 */
	public static Cabbage cabbageInstance = null;

	/**
	 * @param pan the zoo panel to put the image
	 * @return the only object of the class
	 */
	public static Cabbage getCabbageInstance(ZooPanel pan){
		if(cabbageInstance == null)
			cabbageInstance = new Cabbage(pan);

		return cabbageInstance;
	}

    /**
     * Constructor
     * @param pan the panel which the cabbage should be
     */
	public Cabbage(ZooPanel pan) {
		super(pan);
		loadImages("cabbage");
	}
}
