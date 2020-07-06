//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package animals;

import diet.Herbivore;
import graphics.ZooPanel;

/**
 * Attributes and methods for Turtle
 * @version 2.4 5 April 2017
 * @Author Or Galili 302813464 SCE Ashdod
 */
public class Turtle extends Animal{
	/**
	 * Constructor with the basic of the turtle
	 */
	public Turtle(){
		nm = "trt";
		//loadImages("trt");
		factor = 0.5;
		this.setDiet(new Herbivore());
	}

	@Override
	public Animal clone() {
		return new Turtle().setBasicParams(this.size,this.horSpeed,this.verSpeed,col,ZooPanel.getZooPanelInstance())
                .setAdvancedParams(this.location,this.x_dir,this.y_dir,this.threadSuspended,this.eatCount);
	}
}
