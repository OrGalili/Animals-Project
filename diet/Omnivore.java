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
public class Omnivore implements IDiet {

	private IDiet canrivore;
	private IDiet herbivore;

	public Omnivore() {
		this.canrivore = new Carnivore();
		this.herbivore = new Herbivore();
	}

	@Override
	public boolean canEat(EFoodType ft) {
		return this.canrivore.canEat(ft) || this.herbivore.canEat(ft);
	}

	@Override
	public double eat(double currentWeight, IEdible food) {
		if (canEat(food.getFoodType())) {
			return this.canrivore.eat(currentWeight, food) + this.herbivore.eat(currentWeight, food);
		}
		return 0;
	}

	@Override
	public String toString() {
		return "[" + this.getClass().getSimpleName() + "]";
	}
}
