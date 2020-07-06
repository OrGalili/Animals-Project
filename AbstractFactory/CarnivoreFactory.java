//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package AbstractFactory;

import animals.Animal;
import animals.Lion;

/**
 * the factory for carnivore animals
 * Created by or and mor on 06/06/17.
 */
public class CarnivoreFactory implements AbstractZooFactory {
    @Override
    public Animal produceAnimal(String type) {
        switch (type){
            default://case "Lion":(the only option)
                return new Lion();
        }
    }
}
