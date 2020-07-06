//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package Memento;

import animals.Animal;
import food.EFoodType;

import java.util.ArrayList;

/**
 * this class maintain states of the zoo panel
 * Created by Or and mor on 6/13/2017.
 */
public class ZooMemento {
    /**
     * the animals in the zoo panel
     */
    private ArrayList<Animal> animals;

    /**
     * the food type in the middle screen
     */
    private EFoodType food;

    /**
     * animal object
     */
    private Animal an;

    public ZooMemento(){}

    /**
     * this method save the state of the zoo panel in the current time the method invoked
     * saving the food and the animals and their status
     * @param animals the animals to clone
     * @param food the food to clone
     */
    public ZooMemento(ArrayList<Animal> animals , EFoodType food){
        this.animals = new ArrayList<>();
        for (Animal originalAnimal : animals) {
            an = originalAnimal.clone();
            this.animals.add(an);
            /*an.setLocation(originalAnimal.getLocation());
            an.setX_dir(originalAnimal.getX_dir());
            an.setY_dir(originalAnimal.getX_dir());
            if(originalAnimal.isSuspended())
                an.setSuspended();*/
        }
        this.food = food;
    }

    /**
     * @return the saved animalList of a zoo panel state
     */
    public ArrayList<Animal> getAnimals() {
            return animals;
    }

    /**
     * @return the saved food in the middle screen of a zoo panel state
     */
    public EFoodType getFood() {
        return food;
    }
}
