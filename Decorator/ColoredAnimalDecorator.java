//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package Decorator;

import animals.Animal;

/**
 * Decorating an animal (changing values of the fields)
 * by the following methods that in the Colored animal interface
 * Created by or and mor on 07/06/17.
 * @see ColoredAnimal
 */
public class ColoredAnimalDecorator implements ColoredAnimal {
    /**
     * attribute for the type of interface.
     * Instance is assigned dynamically at the creation of decorator using its constructor.
     * Once assigned that instance method will be invoked.
     */
    ColoredAnimal coloredAnimal;

    /**
     * constructor
     * @param coloredAnimal can be an animal type
     * @see Animal class that implementing ColoredAnimal interface
     */
    public ColoredAnimalDecorator(ColoredAnimal coloredAnimal){
        this.coloredAnimal = coloredAnimal;
    }

    @Override
    public void paintAnimal(String col) {
        ((Animal)coloredAnimal).setColorByString(col);
        ((Animal)coloredAnimal).loadImages(((Animal)coloredAnimal).getNm());
    }
}
