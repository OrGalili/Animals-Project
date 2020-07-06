//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package graphics;

import animals.Animal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * This class show the information about the animals in the zoo panel:
 * Animal type , color , weight , horizontal and vertical speed , eat count of each animal
 * @Author Or Galili 302813464 SCE Ashdod
 * @version 4 5/24/2017
 */
public class AnimalsInformation extends JFrame {

    /**
     * the table information
     */
    private JTable table;
    /**
     * the information
     */
    private DefaultTableModel tableDetails;
    /**
     * The singleton instance
     */
    public static AnimalsInformation instance = null;
    /**
     * Total eat of all animals in the zoo
     */
    private int totalEat;

    /**
     * List of animals in the zoo
     */
    private ArrayList<Animal> animalList;

    /**
     * Constructor
     */
    private AnimalsInformation(){}

    /**
     * Creating an instance of the class by the singleton design pattern
     * when calling to the method if there is already an instance, the current instance
     * will be destroyed and a new one will be created
     * @param animalList the animal list to show in the table
     * @return the instance
     */
    public static AnimalsInformation getInstance(ArrayList<Animal> animalList){
        if(instance != null) {
            instance.dispatchEvent(new WindowEvent(instance, WindowEvent.WINDOW_CLOSING));
            instance = null;
        }

        if(instance == null)
            instance = new AnimalsInformation(animalList);

        return instance;
    }

    /**
     * Constructor creating the table information
     * @param animalList the animal list to show in the table
     */
    private AnimalsInformation(ArrayList<Animal> animalList){
        this.animalList = animalList;
        initComponents();
        initListeners();
        initFrame();
    }

    /**
     * Initiating the frame display
     */
    private void initFrame(){
        setLayout(new GridLayout(1,1));
        setVisible(true);
        this.pack();
    }

    /**
     * Initiating the components of the frame
     */
    private void initComponents(){
        String[] columnNames = new String[]{"Animal",
                "Color",
                "Weight",
                "Hor.speed",
                "Ver.speed",
                "Eat counter"};

        int runCnt = 0;
        int waitCnt = 0;

        tableDetails = new DefaultTableModel(columnNames,0);
        table = new JTable(tableDetails);
        for(Animal animal : animalList){
            if(animal.isRun())
                runCnt++;
            else
                waitCnt++;
            tableDetails.addRow(new String[]{animal.getAnimalName(),animal.getColor(),String.valueOf(Math.floor(animal.getWeight() * 100) / 100),String.valueOf(animal.getHorSpeed()),String.valueOf(animal.getVerSpeed()),String.valueOf(animal.getEatCount())});
            totalEat += animal.getEatCount();
        }

        tableDetails.addRow(new String[]{"Total eat: "+String.valueOf(totalEat),"run="+runCnt,"blc="+waitCnt,"","",""});

        JScrollPane tableScroll = new JScrollPane(table);

        add(tableScroll);
    }

    /**
     * Initiating the listeners of the class
     */
    public void initListeners(){
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                instance = null;
            }
        });
    }



}
