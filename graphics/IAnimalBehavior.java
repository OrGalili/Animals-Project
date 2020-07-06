//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package graphics;

/**
 * The common behaviors of the animals
 * @Author Or Galili 302813464 SCE Ashdod
 * @Version 3 Created by Or on 5/8/2017.
 */
public interface IAnimalBehavior {

    /**
     * @return the type of the animal in string
     */
    public String getAnimalName();

    /**
     * @return the size of the animal
     */
    public int getSize();

    /**
     * Incrementing the eat count of the animal
     */
    public void eatInc();

    /**
     * @return the number of eat of the animal
     */
    public int getEatCount();

    /**
     * Change the status of the thread to suspended
     */
    public void setSuspended();

    /**
     * Notify the animal thread
     */
    public void setResumed();
}
