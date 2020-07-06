//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package AbstractFactory;

import animals.Animal;
import animals.Bear;

/**
 * the factory for omnivore animals
 * Created by or on 06/06/17.
 */
public class OmnivoreFactory implements AbstractZooFactory {
    @Override
    public Animal produceAnimal(String type) {
        switch (type){
            default://case "Bear": (the only option)
                return new Bear();
        }
    }
}
