//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package mobility;

/**
 *
 * @Author Or Galili 302813464 SCE Ashdod
 *
 */
public interface ILocatable {

	/**
	 * @return The current location
	 */
	public Point getLocation();

	/**
	 * 
	 * @param location
	 *            - The new location
	 * @return True if assignment is successful
	 */
	public boolean setLocation(Point location);
}
