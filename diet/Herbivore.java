//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package diet;

import food.EFoodType;
import food.IEdible;

/**
 * @Author Or Galili 302813464 SCE Ashdod
 *
 */
public class Herbivore implements IDiet {

	/**
	 * Weight gain factor after eating a plant
	 */
	public static final double WEIGHT_GAIN_FACTOR = 0.07;

	@Override
	public boolean canEat(EFoodType ft) {
		return (ft == EFoodType.VEGETABLE);
	}

	@Override
	public double eat(double currentWeight, IEdible food) {
		if (canEat(food.getFoodType())) {
			return currentWeight * WEIGHT_GAIN_FACTOR;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "[" + this.getClass().getSimpleName() + "]";
	}
}
