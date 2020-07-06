//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package graphics;

import Memento.ZooMemento;
import animals.Animal;
import animals.ZooObserver;
import food.EFoodType;
import plants.Cabbage;
import plants.Lettuce;
import plants.Plant;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

/**
 * Zoo panel compose with buttons that can control the animals in the zoo
 * and the display that showing the animals moving in the screen.
 * @Author Or Galili 302813464 SCE Ashdod
 * @version 3 5/8/2017.
 * @see ActionListener
 * @see Runnable
 */
public class ZooPanel extends JPanel implements ActionListener {

    /**
     * Maximum animal threads in thread pool
     */
    public static final int MAX_THREADS_PARALLEL = 10;
    /**
     * Maximum animal waiting for thread pool
     */
    public static final int MAX_THREADS_WAITING  = 5;
    /**
     * Maximum states to save
     */
    public static final int MAX_STATES = 3;
    /**
     * state options
     */
    private Object[] optionState = {"State 1","State 2","State 3","Cancel"};
    /**
     * Array string with all name of buttons
     */
    private final String[] buttonNames = {"Add Animal","Sleep","Wake up","Clear","Food","Info","Decorate","Duplicate","Save state","Restore state","Exit"};
    /**
     * Array of buttons
     */
    private JButton[] btnArray;
    /**
     * Thread of the zoo panel
     */
    protected ZooObserver controller;
    /**
     * Image of the background panel
     */
    private BufferedImage img = null;
    /**
     * Image of the food meat
     */
    private BufferedImage meatImg = null;
    /**
     * Array with the animals that added by the user
     */
    private ArrayList<Animal> animalList;
    /**
     * Thread pool with the animals that running
     */
    private ExecutorService animalPool;
    /**
     * Plant
     */
    private Plant plant;
    /**
     * The food type in the middle screen
     */
    private EFoodType foodType;
    /**
     *  the object that is going to maintain the state of originator
     */
    private ZooMemento zooMemento;
    /**
     * the object that keeps track of multiple memento.
     * like maintaining save points(caretaker).
     */
    private ArrayList<ZooMemento> zooMementoArr;
    /**
     * all options of food types
     */
    private final String[] foodTypes = {"Plant","Mix","Meat"};

    /**
     * the only instance of the class
     */
    private static ZooPanel zooPanelInstance = null;

    /**
     * @return the only instance of the class
     */
    public static ZooPanel getZooPanelInstance(){
        if(zooPanelInstance == null)
            zooPanelInstance = new ZooPanel();

        return  zooPanelInstance;
    }

    /**
     * Creating the zoo panel
     */
    private ZooPanel() {
        plant = null;
        loadMeatImage();
        initPanelDisplay();
        initComponents();
        animalPool = Executors.newFixedThreadPool(MAX_THREADS_PARALLEL);
        animalList = new ArrayList<Animal>();
        zooMemento = new ZooMemento();
        zooMementoArr = new ArrayList<>();
        controller = ZooObserver.getZooObserverInstance(this);//new Thread(this);
        controller.start();
    }

    public ExecutorService getAnimalPool() {
        return animalPool;
    }

    /**
     * The panel display
     */
    private void initPanelDisplay() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1000, 500));
        setBackground(Color.WHITE);
    }

    /**
     * The components of the zoo panel
     */
    private void initComponents() {
        JPanel southPanel = new JPanel(new GridLayout());

        btnArray = new JButton[buttonNames.length];
        for(int i=0 ; i<buttonNames.length;i++){
            btnArray[i] = new JButton(buttonNames[i]);
            btnArray[i].addActionListener(this);
            southPanel.add(btnArray[i],BorderLayout.SOUTH);
        }

        add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * painting the image of the panel and the animals and the food
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (img != null)
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

        synchronized (this) {
            for (Animal animal : animalList)
                animal.drawObject(g);
        }

        if (foodType != EFoodType.NOTFOOD) {
            if (foodType == EFoodType.VEGETABLE) {
                plant.drawObject(g);
            } else if (foodType == EFoodType.MEAT) {
                drawMeat(g);
            }
        }

    }

    /**
     * @return list of animals in the zoo panel
     */
    public ArrayList<Animal> getAnimalList() {
        return animalList;
    }

    /**
     * Set the image of the zoo panel background
     * @param img
     * @return true
     */
    public boolean setImage(BufferedImage img) {
        this.img = img;
        return true;
    }

    /**
     * Checking if animal in the zoo can eat else
     * this function is a critical section
     */
    synchronized public void findPrey() {

        boolean prey_eaten = false;
        for (Animal predator : animalList) {
            if(predator.isRun()) {
                for (Animal prey : animalList) {
                    if(prey.isRun()) {
                        if (predator != prey && predator.getDiet().canEat(prey.getFoodType()) && predator.getWeight() / prey.getWeight() >= 2 &&
                                (Math.abs(predator.getLocation().getX() - prey.getLocation().getX()) < prey.getSize()) &&
                                (Math.abs(predator.getLocation().getY() - prey.getLocation().getY()) < prey.getSize())) {
                            predator.eatInc();
                            prey.getTask().cancel(true);
                            animalList.remove(prey);
                            //addWaitingAnimalToList();
                            repaint();
                            prey_eaten = true;
                            break;
                        }
                    }
                }
                if (prey_eaten)
                    break;
            }
        }
    }

    /**
     * Loading the meat image
     */
    public void loadMeatImage() {
        try {
            meatImg = ImageIO.read(new File("src/pictures/meat.gif"));
        }
        catch (IOException e) {
            System.out.println("Cannot load image");
        }
    }

    /**
     * Drawing the meat image on the panel
     * @param g
     */
    public void drawMeat(Graphics g) {
        g.drawImage(meatImg, this.getWidth() / 2, this.getHeight() / 2 -45, 50, 50, this);
    }

    /**
     * Suspending all the animals in the zoo.
     */
    public void suspendAll() {
        for (Animal animal : animalList) {
            animal.setSuspended();
        }
    }

    /**
     * Resuming all the animals in the zoo
     */
    public void resumeAll() {
        for (Animal animal : animalList) {
            animal.setResumed();
        }
    }

    /**
     * Killing the threads of all the animals in the zoo
     * and clear the animal list
     * @see this.getAnimalList
     * this function is a critical section
     */
    synchronized public void clearAll() {
       for(int i = 0 ; i<MAX_THREADS_PARALLEL ; i++) {
                if (animalList.isEmpty()) {
                    break;
                }
                if (animalList.get(0).isRun()) {
                    animalList.get(0).interrupt();//animal.getTask().cancel(true);
                    animalList.remove(0);
                }
           }
        repaint();
    }

    /**
     * @return the food type that in the zoo.
     */
    public EFoodType checkFood() {
        return foodType;
    }

    /**
     * Handling all the button events
     * @param e the event that occurred
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnArray[0]) {//add animal
            int i = chooseDialog(new Object[]{"Herbivore", "Omnivore", "Carnivore"},"Please choose animal type","animal types");
            if(i>=0 && i<=2)
                AddAnimalDialog.getInstance(this,foodTypes[i]);
        }

        else if (e.getSource() == btnArray[1]) {//sleep
            suspendAll();

        }

        else if (e.getSource() == btnArray[2]) {//wake up
            resumeAll();

        }

        else if (e.getSource() == btnArray[3]) {//clear
            clearAll();
        }

        else if (e.getSource() == btnArray[4]) {//food
            switch (chooseDialog(new Object[]{"Meat", "Cabbage", "Lettuce"},"Please choose food","Food for animals")){
                case 0:
                    foodType = EFoodType.MEAT;
                    break;
                case 1:
                    plant = Cabbage.getCabbageInstance(this);
                    foodType = EFoodType.VEGETABLE;
                    break;
                case 2:
                    plant = Lettuce.getLettuceInstance(this);
                    foodType = EFoodType.VEGETABLE;
                    break;
                default:

            }
            repaint();
        }

        else if (e.getSource() == btnArray[5]) {//info
            AnimalsInformation.getInstance(animalList);
        }

        else if(e.getSource() == btnArray[6]){//decorate
            if(isNaturalAnimalExist())
                DecorateDialog.getDialogInstance(zooPanelInstance);
            else
                JOptionPane.showMessageDialog(this,"You have not animals for decoration","Message",JOptionPane.INFORMATION_MESSAGE);
        }

        else if(e.getSource() == btnArray[7]){//duplicate
            DuplicateDialog.getDialogInstance(this);
        }

        else if(e.getSource() == btnArray[8]){//save state
                if (zooMementoArr.size() < MAX_STATES)
                    zooMementoArr.add(new ZooMemento(animalList, foodType));
                else
                    JOptionPane.showMessageDialog(this,"cannot have more than 3 states!","capacity",JOptionPane.INFORMATION_MESSAGE);
        }

        else if(e.getSource() == btnArray[9]){//restore
                if(zooMementoArr.size()==0)
                    JOptionPane.showMessageDialog(this,"You have not saved states","Message",JOptionPane.INFORMATION_MESSAGE);
                else if(JOptionPane.showOptionDialog(this, "choose state", "States", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionState, optionState[3])!=3) {
                    foodType = EFoodType.NOTFOOD;
                    clearAll();
                    zooMemento = zooMementoArr.remove(zooMementoArr.size() - 1);
                    foodType = zooMemento.getFood();
                    for(Animal an : zooMemento.getAnimals()){
                        an.setTask(animalPool.submit(an));
                        addAnimal(an);
                    }
                }
        }

        else if (e.getSource() == btnArray[10]) {//exit
            clearAll();
            animalPool.shutdown();
            System.exit(0);
        }
    }

    /**
     * @return true if animal with natural color exist else if not or empty animal list false
     */
    private boolean isNaturalAnimalExist() {
        for(Animal an : animalList){
            if(an.getColor().equals("Natural"))
                return true;
        }
        return false;
    }

    /**
     * Increamting eat count of the animal
     * and deleting the food from the panel
     * @param animal the animal object that ate the food
     */
    public void eatFood(Animal animal) {
        animal.eatInc();
        plant = null;
        foodType = EFoodType.NOTFOOD;
    }

    /**
     * Options for creating a food in the zoo panel
     * @return the value of the chosen food
     */
    public int chooseDialog(Object[] options,String msg , String title) {
        return JOptionPane.showOptionDialog(this,
                msg,
                title,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);
    }

    /**
     * Adding an animal to the list animal
     * this function is a critical section
     * @param animal the animal to add to the list
     * @return true if add succeeded else false
     */
    synchronized public boolean addAnimal(Animal animal) {

        if (animalList.size() < MAX_THREADS_PARALLEL+MAX_THREADS_WAITING) {
            animalList.add(animal);
            return true;
        }
        return false;
    }
}
