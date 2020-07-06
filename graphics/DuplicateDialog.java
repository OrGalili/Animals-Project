//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package graphics;

import animals.Animal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * gui for cloning a specific animal which the user choose
 * Created by Or and mor on 6/12/2017.
 */
public class DuplicateDialog extends JDialog implements ActionListener {

    /**
     * the only instance of the class
     */
    private static DuplicateDialog dialogInstance = null;
    /**
     * combo box contains the animals
     */
    private JComboBox cbAnimals;
    /**
     * confirm button
     */
    private JButton btnOk;
    /**
     * horizontal speed
     */
    private JSlider sHorSp;
    /**
     * Vertical speed
     */
    private JSlider sVerSp;
    /**
     * the left panel of the dialog
     */
    private JPanel westPanel;
    /**
     * the zoo panel
     */
    private ZooPanel zooPanel;
    /**
     * the information about a specific animal
     */
    private String animalInfo;
    /**
     * the right panel of the dialog
     */
    private JPanel eastPanel;

    /**
     * this method invoke the only instance of the class
     * if the dialogInstance null the method call the constructor
     * @return the only instance of the class
     */
    public static DuplicateDialog getDialogInstance(ZooPanel zooPanel) {
        if(dialogInstance == null)
            dialogInstance = new DuplicateDialog(zooPanel);

        return dialogInstance;
    }

    private DuplicateDialog(){}

    private DuplicateDialog(ZooPanel zooPanel){
        this.zooPanel = zooPanel;
        initDisplayDialog();
        initComponents();
        this.pack();

    }

    private void initDisplayDialog() {
        setVisible(true);
        setLocation(new Point(800,200));
        setLayout(new BorderLayout());
    }

    private void initComponents() {
        westPanel = new JPanel(new GridLayout(2,1));
        cbAnimals = new JComboBox();
        cbAnimals.addItem("no animal");
        int i=0;
        for(Animal animal : ZooPanel.getZooPanelInstance().getAnimalList()) {
            i++;
            animalInfo = i + ".[" + animal.getAnimalName() + ": running=true, weight=" + Math.floor(animal.getWeight() * 100) / 100 + ", color="+animal.getColor()+"]";
            cbAnimals.addItem(animalInfo);
        }

        westPanel.add(cbAnimals);
        btnOk = new JButton("OK");
        btnOk.addActionListener(this);
        westPanel.add(btnOk);
        getContentPane().add(westPanel,BorderLayout.WEST);

        eastPanel = new JPanel(new GridLayout(2,1));
        sHorSp = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
        sHorSp.setMajorTickSpacing(10);
        sHorSp.setMinorTickSpacing(1);
        sHorSp.setLabelTable(sHorSp.createStandardLabels(2));
        sHorSp.setPaintTicks(true);
        sHorSp.setPaintLabels(true);
        eastPanel.add(sHorSp);
        sVerSp = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
        sVerSp.setMajorTickSpacing(10);
        sVerSp.setMinorTickSpacing(1);
        sVerSp.setLabelTable(sVerSp.createStandardLabels(2));
        sVerSp.setPaintTicks(true);
        sVerSp.setPaintLabels(true);
        eastPanel.add(sVerSp);

        getContentPane().add(eastPanel,BorderLayout.EAST);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialogInstance = null;
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = cbAnimals.getSelectedIndex() - 1;
        if(index>=0) {
            Animal an = zooPanel.getAnimalList().get(index).clone();
            an.setHorSpeed(sHorSp.getValue());
            an.setVerSpeed(sVerSp.getValue());

            if (zooPanel.getAnimalList().size() < zooPanel.MAX_THREADS_PARALLEL + zooPanel.MAX_THREADS_WAITING) {
                an.setTask(zooPanel.getAnimalPool().submit(an));
                zooPanel.addAnimal(an);
            } else {
                JOptionPane.showMessageDialog(this, "Cannot add more than 5 animals waiting");
            }
        }
    }
}
