//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package animals;

import graphics.ZooPanel;

import java.util.Observable;
import java.util.Observer;

/**
 * Observer class for listening to the animals in the zoo panel
 * Created by Or and mor on 6/11/2017.
 */
public class ZooObserver extends Thread implements Observer {

    /**
     * the only instance of the class
     */
    private static ZooObserver zooObserverInstance = null;

    /**
     * the zoo panel
     */
    private ZooPanel zooPanel;

    /**
     * constructor of the zoo observer
     * @param zooPanel - the current zoo panel to listen to changes
     */
    private ZooObserver(ZooPanel zooPanel){
        this.zooPanel = zooPanel;
    }

    /**
     * @param zooPanel - the current zoo panel to listen to changes
     * @return the only instance of the class
     */
    public static ZooObserver getZooObserverInstance(ZooPanel zooPanel){
        if(zooObserverInstance == null)
            zooObserverInstance = new ZooObserver(zooPanel);

        return  zooObserverInstance;
    }

    @Override
    public synchronized void update(Observable o, Object arg) {
            notify();
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (this) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            zooPanel.repaint();
            zooPanel.findPrey();
        }
    }
}
