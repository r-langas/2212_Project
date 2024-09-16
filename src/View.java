import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The GUI that the user sees, components are given event listeners by the
 * controller which will dynamically
 */

public class View extends JFrame {

    //********** Getters **********//

    public float GetEatType() {
        if (appleOpt.isSelected()) { return Constants.APPLE; }
        else if (chickenOpt.isSelected()) { return Constants.CHICKEN; }
        else return Constants.POTION;
    }
    public float GetRestType() {
        if (sitOpt.isSelected()) { return Constants.SIT; }
        else if (napOpt.isSelected()) { return Constants.NAP; }
        else return Constants.SLEEP;
    }
    public float GetExerciseType() {
        if (walkOpt.isSelected()) { return Constants.WALK; }
        else return Constants.RUN;
    }


    //********** GUI Components **********//

    private final JRadioButton appleOpt = new JRadioButton("Apple");
    private final JRadioButton chickenOpt = new JRadioButton("Chicken");
    private final JRadioButton potionOpt = new JRadioButton("Potion");
    private final JRadioButton sitOpt = new JRadioButton("Breath");
    private final JRadioButton napOpt = new JRadioButton("Nap");
    private final JRadioButton sleepOpt = new JRadioButton("Sleep");
    private final JRadioButton walkOpt = new JRadioButton("Walk");
    private final JRadioButton runOpt = new JRadioButton("Run");

    private final ButtonGroup foodGroup = new ButtonGroup();
    private final ButtonGroup restGroup = new ButtonGroup();
    private final ButtonGroup exerciseGroup = new ButtonGroup();

    private final JProgressBar levelBar = new JProgressBar();
    private final JProgressBar expBar = new JProgressBar();
    private final JProgressBar healthBar = new JProgressBar();
    private final JProgressBar energyBar = new JProgressBar();

    private final JButton eatFoodButton = new JButton("Eat food");
    private final JButton restButton = new JButton("Rest");
    public JButton exerciseButton = new JButton("Exercise");

    private final JLabel statusLabel = new JLabel("Good");


    //********** Constructor **********//

    public View() {
        // main frame panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // header/title panel
        JPanel headerPanel = new JPanel();
        headerPanel.add(new JLabel("Pet Game"));
        mainPanel.add(headerPanel, BorderLayout.PAGE_START);


        // setup button groups
        appleOpt.setSelected(true);
        foodGroup.add(appleOpt);
        foodGroup.add(chickenOpt);
        foodGroup.add(potionOpt);
        sitOpt.setSelected(true);
        restGroup.add(sitOpt);
        restGroup.add(napOpt);
        restGroup.add(sleepOpt);
        walkOpt.setSelected(true);
        exerciseGroup.add(walkOpt);
        exerciseGroup.add(runOpt);

        // options panel
        JPanel optionsGrid = new JPanel(new GridLayout(14, 2));
        optionsGrid.setBorder(BorderFactory.createTitledBorder("Options"));
        optionsGrid.add(new Label("Food Options:"));
        optionsGrid.add(appleOpt);
        optionsGrid.add(chickenOpt);
        optionsGrid.add(potionOpt);
        optionsGrid.add(Box.createRigidArea(new Dimension(10,0)));
        optionsGrid.add(new Label("Rest Options:"));
        optionsGrid.add(sitOpt);
        optionsGrid.add(sitOpt);
        optionsGrid.add(napOpt);
        optionsGrid.add(sleepOpt);
        optionsGrid.add(Box.createRigidArea(new Dimension(10,0)));
        optionsGrid.add(new Label("Exercise Options:"));
        optionsGrid.add(walkOpt);
        optionsGrid.add(walkOpt);
        optionsGrid.add(runOpt);

        // wrapping panel to center it
        JPanel optionsPanel = new JPanel(new GridBagLayout());
        optionsPanel.add(optionsGrid);
        mainPanel.add(optionsPanel, BorderLayout.LINE_START);


        // Pet panel, using label to temporarily display status, WIP: sprites
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(statusLabel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);


        // setup stat bars
        levelBar.setForeground(Color.BLUE);
        levelBar.setStringPainted(true);
        expBar.setForeground(Color.GREEN);
        expBar.setStringPainted(true);
        healthBar.setForeground(Color.RED);
        healthBar.setStringPainted(true);
        energyBar.setForeground(Color.YELLOW);
        energyBar.setStringPainted(true);

        // pet stats panel
        JPanel statsGrid = new JPanel();
        statsGrid.setBorder(new CompoundBorder(
                BorderFactory.createEmptyBorder(10,10,10,10),
                BorderFactory.createTitledBorder("Stats")
        ));
        statsGrid.setLayout(new BoxLayout(statsGrid, BoxLayout.Y_AXIS));

        statsGrid.add(new JLabel("Level:"));
        statsGrid.add(levelBar);
        statsGrid.add(Box.createRigidArea(new Dimension(10,0)));
        statsGrid.add(new JLabel("Experience:"));
        statsGrid.add(expBar);
        statsGrid.add(Box.createRigidArea(new Dimension(10,0)));
        statsGrid.add(new JLabel("Health:"));
        statsGrid.add(healthBar);
        statsGrid.add(Box.createRigidArea(new Dimension(10,0)));
        statsGrid.add(new JLabel("Energy:"));
        statsGrid.add(energyBar);

        // wrapping panel to center it
        JPanel statsPanel = new JPanel(new GridBagLayout());
        statsPanel.setMinimumSize(new Dimension(200, 0));
        statsPanel.add(statsGrid);
        mainPanel.add(statsPanel, BorderLayout.LINE_END);


        // pet action panel
        JPanel actionPanel = new JPanel();
        actionPanel.add(eatFoodButton)  ;
        actionPanel.add(restButton);
        actionPanel.add(exerciseButton);
        mainPanel.add(actionPanel, BorderLayout.PAGE_END);


        // frame boilerplate
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.add(mainPanel);
    }


    //********** Action Event Listeners **********//

    // Add listeners for GUI components (supplied by the controller)
    public void AddEatFoodListener(ActionListener eatFoodListener) {
        eatFoodButton.addActionListener(eatFoodListener);
    }

    public void AddRestListener(ActionListener restListener) {
        restButton.addActionListener(restListener);
    }

    public void AddExerciseListener(ActionListener exerciseListener) {
        exerciseButton.addActionListener(exerciseListener);
    }


    //********** Display Updating Methods **********//

    public void DisplayLevel(int level) {
        levelBar.setValue(level);
        levelBar.setString("" + level);
    }

    public void DisplayExp(float exp) {
        expBar.setValue((int) exp);
    }

    public void DisplayHealth(float health) {
        healthBar.setValue((int) (health));
    }

    public void DisplayEnergy(float energy) {
        energyBar.setValue((int) (energy));
    }

    public void DisplayStatus(Model.State state) {
        statusLabel.setText(state.name());
    }
}
