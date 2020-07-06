//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package animals;

import diet.Herbivore;
import graphics.ZooPanel;

/**
 * Attribute and methods of Giraffe
 * @version 3.2 8 April 2017
 * @Author Or Galili 302813464 SCE Ashdod
 */
public class Giraffe extends Animal{
	/**
	 * Constructor with the basic of the giraffe
	 */
	public Giraffe(){
		nm = "grf";
		factor = 2.2;
		this.setDiet(new Herbivore());
	}

	@Override
	public Animal clone() {
		return new Giraffe().setBasicParams(this.size,this.horSpeed,this.verSpeed,col,ZooPanel.getZooPanelInstance())
                .setAdvancedParams(this.location,this.x_dir,this.y_dir,this.threadSuspended,this.eatCount);
	}
}
