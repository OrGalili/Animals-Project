//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package AbstractFactory;

import animals.Animal;
import animals.Elephant;
import animals.Giraffe;
import animals.Turtle;

/**
 * the factory for herbivore animals
 * Created by or on 06/06/17.
 */
public class HerbivoreFactory implements AbstractZooFactory {
    @Override
    public Animal produceAnimal(String type) {
        switch (type){
            case "Elephant":
                return new Elephant();
            case "Giraffe":
                return new Giraffe();
            case "Turtle":
                return new Turtle();
            default:
                return null;
        }
    }
}
