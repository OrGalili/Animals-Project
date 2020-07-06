//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package graphics;

import AbstractFactory.AbstractZooFactory;
import animals.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Creating a Dialog to the user in order to create animals and send them to the zoopanel
 * @version 5/8/2017
 * @Author Or Galili 302813464 SCE Ashdod
 * @see ZooPanel
 */
public class AddAnimalDialog extends JDialog implements ActionListener {

    /**
     *  the instance of the class - singleton design pattern
     */
    private static AddAnimalDialog instance = null;
    /**
     * Herbivore animal names
     */
    private final String[] HerbivoreStrings = {"Elephant", "Giraffe", "Turtle"};
    /**
     * Omnivore animal names
     */
    private final String[] OmnivoreStrings = {"Bear"};
    /**
     * Carnivore animal strings
     */
    private final String[] CarnivoreStrings = {"Lion"};
    /**
     * the food type the chosen animal can eat
     */
    private String foodType;
    /**
     *  animal colors
     */
    private final String[] colorStrings = {"Natural", "Blue", "Red"};
    /**
     * "animal" label
     */
    private JLabel animalLabel;
    /**
     * animal options combo box component
     */
    private JComboBox cbAnimal;
    /**
     * "animal size" labal
     */
    private JLabel animalSizeLabel;
    /**
     *  animal size component
     */
    private JSpinner spAnimalSize;
    /**
     * the model of the animal size
     */
    private SpinnerNumberModel spSizeModel;
    /**
     * "Horizontal speed" label
     */
    private JLabel horSpeedLabel;
    /**
     * horizontal speed component
     */
    private JSlider slHorizontalSpeed;
    /*
    Vertical speed label
     */
    private JLabel verSpeedLabel;
    /*
    *Vertical speed component
     */
    private JSlider slVerticalSpeed;
    /**
     * animal color label
     */
    private JLabel animalColorLabel;
    /**
     * animal color combo box component
     */
    private JComboBox cbAnimalColor;
    /**
     * confirm button
     */
    private JButton btnOk;

    /**
     * The zoo panel of the frame
     * @see ZooPanel
     */
    private ZooPanel zooPanel;

    /**
     * Constructor with a private access
     */
    private AddAnimalDialog() {

    }

    /**
     * building a JDialog to create an animal
     * @param zooPanel in order to connect with the zoo panel and put there animals
     */
    private AddAnimalDialog(ZooPanel zooPanel, String foodType) {
        this.zooPanel = zooPanel;
        this.foodType = foodType;
        initDialog();
        initComponents();
        initListeners();
    }

    /**
     * initiating the display of the dialog
     */
    public void initDialog() {
        setBounds(1000, 50, 500, 500);
        setLayout(new GridLayout(6, 2, 0, 0));
        setVisible(true);

    }

    /**
     * initiating the components of the dialog.
     */
    private void initComponents() {
        switch (foodType){
            case "Plant":
                cbAnimal = new JComboBox(HerbivoreStrings);
                break;
            case "Mix":
                cbAnimal = new JComboBox(OmnivoreStrings);
                break;
            case "Meat":
                cbAnimal = new JComboBox(CarnivoreStrings);
                break;
            default:
                break;
        }

        spSizeModel = new SpinnerNumberModel(50, 50, 300, 1);
        spAnimalSize = new JSpinner(spSizeModel);
        slHorizontalSpeed = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
        slHorizontalSpeed.setMajorTickSpacing(10);
        slHorizontalSpeed.setMinorTickSpacing(1);
        slHorizontalSpeed.setLabelTable(slHorizontalSpeed.createStandardLabels(3));
        slHorizontalSpeed.setPaintTicks(true);
        slHorizontalSpeed.setPaintLabels(true);
        slVerticalSpeed = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
        slVerticalSpeed.setMajorTickSpacing(10);
        slVerticalSpeed.setMinorTickSpacing(1);
        slVerticalSpeed.setLabelTable(slHorizontalSpeed.createStandardLabels(3));
        slVerticalSpeed.setPaintTicks(true);
        slVerticalSpeed.setPaintLabels(true);
        cbAnimalColor = new JComboBox(colorStrings);
        btnOk = new JButton("OK");

        animalLabel = new JLabel("Animal : ");
        add(animalLabel);
        add(cbAnimal);
        animalSizeLabel = new JLabel("Animal size : ");
        add(animalSizeLabel);
        add(spAnimalSize);
        horSpeedLabel = new JLabel("Horizontal speed : ");
        add(horSpeedLabel);
        add(slHorizontalSpeed);
        verSpeedLabel = new JLabel("Vertical speed : ");
        add(verSpeedLabel);
        add(slVerticalSpeed);
        animalColorLabel = new JLabel("Animal color");
        add(animalColorLabel);
        add(cbAnimalColor);
        add(btnOk);
    }

    /**
     * initiating listeners to exit and add button
     */
    private void initListeners() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                instance = null;
            }
        });
        btnOk.addActionListener(this);
    }


    /**
     * Creating an instance of the class by the singleton design pattern
     * @param zooPanel the zoo panel to add the animals to it.
     * @return the single instance of the class
     */
    public static AddAnimalDialog getInstance(ZooPanel zooPanel,String foodType) {
        if(instance != null) {
            instance.dispatchEvent(new WindowEvent(instance, WindowEvent.WINDOW_CLOSING));
            instance = null;
        }

        if (instance == null)
            instance = new AddAnimalDialog(zooPanel,foodType);

        return instance;

    }

    /**
     * creating an animal by the properties that the user inserted in gui
     * @return animal object
     */
    private Animal createAnimal(){
        String animalType = cbAnimal.getSelectedItem().toString();
        int size = (int)spAnimalSize.getValue();
        int horSpeed = slHorizontalSpeed.getValue();
        int verSpeed = slVerticalSpeed.getValue();
        String colorString = cbAnimalColor.getSelectedItem().toString().toLowerCase();
        Color color = null;

        if(colorString.equals("red"))
            color = Color.RED;

        if(colorString.equals("blue"))
            color = Color.BLUE;
        return AbstractZooFactory.createAnimalFactory(foodType).produceAnimal(animalType).setBasicParams(size,horSpeed,verSpeed,color,zooPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnOk) {
            Animal animal = createAnimal();
            if(zooPanel.getAnimalList().size()<zooPanel.MAX_THREADS_PARALLEL+zooPanel.MAX_THREADS_WAITING){
                animal.setTask(zooPanel.getAnimalPool().submit(animal));
                zooPanel.addAnimal(animal);
            }
            else{
                JOptionPane.showMessageDialog(this,"Cannot add more than 5 animals waiting");
            }
        }
    }

}
