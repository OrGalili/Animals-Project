//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package animals;

import diet.Carnivore;
import graphics.ZooPanel;

/**
 * @version Created by or on 17/03/17.
 * @Author Or Galili 302813464 SCE Ashdod
 * @see Animal
 */
public class Lion extends Animal{
	/**
	 * Constructor with the basic of the lion
	 */
	public Lion(){
	    nm = "lio";
		factor = 0.8;
		this.setDiet(new Carnivore());
	}

	@Override
	public Animal clone() {
		return new Lion().setBasicParams(this.size,this.horSpeed,this.verSpeed,col,ZooPanel.getZooPanelInstance())
                .setAdvancedParams(this.location,this.x_dir,this.y_dir,this.threadSuspended,this.eatCount);
	}
}
