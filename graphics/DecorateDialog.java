//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package graphics;

import Decorator.ColoredAnimalDecorator;
import animals.Animal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

/**
 * Created by or on 07/06/17.
 * the gui for change a color of a specific animal in the zoo panel
 */
public class DecorateDialog extends JDialog implements ActionListener {

    /**
     * combo box with the animals
     */
    private JComboBox cbAnimals;

    /**
     * Ok button for finish action
     */
    private JButton btnOk;

    /**
     * Array of radio buttons
     */
    private JRadioButton[] rbCol;
    /**
     * Group of buttons
     */
    private ButtonGroup group;

    /**
     * the panel on the right of the dialog
     */
    private JPanel eastPanel;

    /**
     *  the panel on the left of the dialog
     */
    private JPanel westPanel;

    /**
     * information about the animal
     */
    private String animalInfo;

    /**
     * The Zoo panel
     */
    private ZooPanel zooPanel;

    /**
     * The only instance of the class
     */
    private static DecorateDialog dialogInstance = null;

    /**
     * Array of string colors
     */
    private final String[] colors = {"Red","Blue"};

    private ArrayList<Animal> naturalAnimals;
    /**
     * ensure that will be one instance in this class(singleton)
     * @param zooPanel
     * @return the only instance of the class
     */
    public static DecorateDialog getDialogInstance(ZooPanel zooPanel){
        if(dialogInstance == null){
            dialogInstance = new DecorateDialog(zooPanel);
        }

        return  dialogInstance;
    }

    /**
     * default constructor
     */
    private DecorateDialog(){}

    /**
     * Initiating the decorate dialog gui
     * @param zooPanel
     */
    public DecorateDialog(ZooPanel zooPanel){
        naturalAnimals = new ArrayList<Animal>();
        this.zooPanel = zooPanel;
        initDialogDisplay();
        SetComponents();
        initListeners();
    }

    /**
     * initiating basic display of the dialog
     */
    private void initDialogDisplay() {
        setBounds(300,300,600,300);
        setLayout(new BorderLayout());
        setVisible(true);
    }

    /**
     * set the components of the dialog
     */
    private void SetComponents() {
        westPanel =  new JPanel();
        westPanel.setLayout(new GridLayout(2,1));
        cbAnimals = new JComboBox();

        int i = 0;
        for(Animal animal : zooPanel.getAnimalList()) {
            i++;
            if(animal.getColor().equals("Natural")) {
                animalInfo = i + ".[" + animal.getAnimalName() + ": running=true, weight=" + Math.floor(animal.getWeight() * 100) / 100 + ", color=Natural]";
                naturalAnimals.add(animal);
                cbAnimals.addItem(animalInfo);
            }
        }

        westPanel.add(cbAnimals);

        btnOk = new JButton("OK");
        westPanel.add(btnOk);

        getContentPane().add(westPanel,BorderLayout.WEST);

        eastPanel = new JPanel(new GridLayout(colors.length,1));

        group = new ButtonGroup();
        rbCol = new JRadioButton[colors.length];
        for(int j=0; j< colors.length; j++){
            rbCol[j] = new JRadioButton(colors[j]);
            group.add(rbCol[j]);
            eastPanel.add(rbCol[j]);
        }
        getContentPane().add(eastPanel,BorderLayout.EAST);
    }

    private void initListeners() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialogInstance = null;
            }
        });
        btnOk.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String color = "Natural";

        for(JRadioButton rb : rbCol) {
            if (group.isSelected(rb.getModel())) {
                color = rb.getText();
                break;
            }
        }
        if(cbAnimals.getItemCount()!=0 && !color.equals("Natural")) {
            ColoredAnimalDecorator dec = new ColoredAnimalDecorator(naturalAnimals.get(cbAnimals.getSelectedIndex()));
            dec.paintAnimal(color);
        }
        dialogInstance.dispose();
        dialogInstance = null;
    }


}
