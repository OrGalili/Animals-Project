//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package animals;

import diet.Herbivore;
import graphics.ZooPanel;

/**
 * Attributes and methods for Elephant instance
 * @version 1.1 12 April 2017
 * @Author Or Galili 302813464 SCE Ashdod
 */
public class Elephant extends Animal {

	/**
	 * Constructor with the basic of the elephant
	 */
	public Elephant() {
		nm = "elf";
		factor = 10;
		this.setDiet(new Herbivore());
	}

	@Override
	public Animal clone() {
		return new Elephant().setBasicParams(this.size,this.horSpeed,this.verSpeed,col,ZooPanel.getZooPanelInstance())
                .setAdvancedParams(this.location,this.x_dir,this.y_dir,this.threadSuspended,this.eatCount);
	}

}
