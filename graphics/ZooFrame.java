//Or Galili 3012813464
//Mor Amira 203190350
//Ashdod Campus
package graphics;

import animals.Animal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The Frame of the program the includes the zoo panel amd the menu bar
 * @Author Or Galili 302813464 SCE Ashdod
 * @version 3 Created by Or on 5/8/2017.
 * @see ZooPanel
 */
public class ZooFrame extends JFrame implements ActionListener {

    /**
     * the menu bar of Frame
     */
    private JMenuBar menuBar;

    /**
     * file menu
     */
    private JMenu fileMenu;
    /**
     * Exit item belongs to file menu
     */
    private JMenuItem exitItem;

    /**
     * Background menu
     */
    private JMenu backgroundMenu;
    /**
     * Image item belongs to background menu
     */
    private JMenuItem imageItem;
    /**
     * green item belongs to background menu
     */
    private JMenuItem greenItem;
    /**
     * none item belongs to background menu
     */
    private JMenuItem noneItem;

    /**
     * Help menu
     */
    private JMenu helpMenu;
    /**
     * help item belongs to help menu
     */
    private JMenuItem helpItem;

    /**
     * Image for background
     */
    private BufferedImage img = null;

    private ZooPanel zp;

    /**
     * the main method running the zoo frame
     * @param args
     */
    public static void main(String[] args){
        ZooFrame zF = new ZooFrame();
    }

    /**
     * The Zoo frame constructor
     */
    public ZooFrame(){
        initComponents();
        initListeners();
        initFrameDisplay();
    }

    /**
     * Initiating components
     */
    private void initComponents(){
        exitItem = new JMenuItem("Exit");
        fileMenu = new JMenu("File");
        fileMenu.add(exitItem);

        imageItem = new JMenuItem("Image");
        greenItem = new JMenuItem("Green");
        noneItem = new JMenuItem("None");
        backgroundMenu = new JMenu("Background");
        backgroundMenu.add(imageItem);
        backgroundMenu.addSeparator();
        backgroundMenu.add(greenItem);
        backgroundMenu.addSeparator();
        backgroundMenu.add(noneItem);


        helpItem = new JMenuItem("Help");
        helpMenu = new JMenu("Help");
        helpMenu.add(helpItem);

        menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(backgroundMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        zp = ZooPanel.getZooPanelInstance();
        getContentPane().add(zp);
    }

    /**
     * Initiating listeners
     */
    private void initListeners(){
        exitItem.addActionListener(this);
        imageItem.addActionListener(this);
        greenItem.addActionListener(this);
        noneItem.addActionListener(this);
        helpItem.addActionListener(this);
    }

    /**
     * initiating the frame display
     */
    private void initFrameDisplay(){
        setLocation(900,50);
        this.pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Handling all the menu items
     * @param e the event that occurred
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exitItem){
            zp.clearAll();
            zp.getAnimalPool().shutdown();
            System.exit(0);
        }

        else if(e.getSource() == imageItem){
            String BACKGROUND_PATH = "src/pictures/savanna.jpg";//"src\\pictures\\savanna.jpg";
            img = null;
            try {
                img = ImageIO.read(new File(BACKGROUND_PATH));
            }
            catch (IOException e1) {
                System.out.println("Cannot load image");
            }

            ((ZooPanel)this.getContentPane().getComponent(0)).setImage(img);
            repaint();

        }

        else if(e.getSource() == greenItem){

            ((ZooPanel)this.getContentPane().getComponent(0)).setImage(null);
            repaint();
            getContentPane().getComponent(0).setBackground(Color.GREEN);


        }

        else if(e.getSource() == noneItem){

            ((ZooPanel)this.getContentPane().getComponent(0)).setImage(null);
            repaint();
            getContentPane().getComponent(0).setBackground(Color.WHITE);
        }

        else if(e.getSource() == helpItem){
            JOptionPane.showMessageDialog(this, "Home Work 3\nGUI @ Threads");
        }
    }
}
