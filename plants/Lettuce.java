//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package plants;

import graphics.ZooPanel;

/**
 * class creating leccute object
 */
public class Lettuce extends Plant {

	/**
	 * the only instance of the class
	 */
	public static Lettuce lettuceInstance = null;

	/**
	 * @return the only instance of the class
	 * if not exist , creating it
	 */
	public static Lettuce getLettuceInstance(ZooPanel pan){
		if(lettuceInstance == null)
			lettuceInstance = new Lettuce(pan);

		return lettuceInstance;
	}

	/**
	 * the constructor of the lettuce
	 */
	public Lettuce(ZooPanel pan) {
		super(pan);
		loadImages("lettuce");
	}
}
