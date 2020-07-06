//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package AbstractFactory;

import animals.Animal;

/**
 * Abstract factory for all kind of the animals in the system
 * Created by Or and Mor on 6/6/2017.
 * @Version 1.3
 */
public interface AbstractZooFactory {
    /**
     * @param type : identity for the type of the animal. for example: "Bear" for Bear object
     * @return animal object
     */
    public Animal produceAnimal(String type);

    /**
     * @param foodType identity for what kind of animal
     * @return the suitable factory according to the foodType parameter
     */
    public static AbstractZooFactory createAnimalFactory(String foodType){
        AbstractZooFactory factory = null;
        switch(foodType){
            case "Plant":
                factory = new HerbivoreFactory();
                break;
            case "Mix":
                factory = new OmnivoreFactory();
                break;
            case "Meat":
                factory = new CarnivoreFactory();
                break;
        }
        return factory;
    }
}
