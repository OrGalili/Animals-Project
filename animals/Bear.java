//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package animals;

import diet.Omnivore;
import graphics.ZooPanel;


/**
 * Attributes and methods for Bear
 * @version 2.4 5 April 2017
 * @Author Or Galili 302813464 SCE Ashdod
 */
public class Bear extends Animal{

	/**
	 * Constructor with the basic of the bear
	 */
	public Bear(){
		nm = "bea";
		factor = 1.5;
		this.setDiet(new Omnivore());
	}

	@Override
	public Animal clone() {
		return new Bear().setBasicParams(this.size,this.horSpeed,this.verSpeed,col,ZooPanel.getZooPanelInstance())
                .setAdvancedParams(this.location,this.x_dir,this.y_dir,this.threadSuspended,this.eatCount);
	}
}
