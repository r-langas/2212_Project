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

    private final JProgressBar healthBar = new JProgressBar();
    private final JProgressBar energyBar = new JProgressBar();
    private final JProgressBar hungerBar = new JProgressBar();
    private final JProgressBar happinessBar = new JProgressBar();

    public JButton sleepButton = new JButton("Sleep");
    public JButton vetButton = new JButton("Go to Vet");

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


        /** This needs to be reworked

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
        mainPanel.add(optionsPanel, BorderLayout.LINE_START); */


        // Pet panel, using label to temporarily display status, WIP: sprites
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(statusLabel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);


        // setup stat bars
        healthBar.setForeground(Color.RED);
        healthBar.setStringPainted(true);
        energyBar.setForeground(Color.YELLOW);
        energyBar.setStringPainted(true);
        hungerBar.setForeground(Color.ORANGE);
        hungerBar.setStringPainted(true);
        happinessBar.setForeground(Color.GREEN);
        happinessBar.setStringPainted(true);


        // pet stats panel
        JPanel statsGrid = new JPanel();
        statsGrid.setBorder(new CompoundBorder(
                BorderFactory.createEmptyBorder(10,10,10,10),
                BorderFactory.createTitledBorder("Stats")
        ));
        statsGrid.setLayout(new BoxLayout(statsGrid, BoxLayout.Y_AXIS));

        statsGrid.add(new JLabel("Health:"));
        statsGrid.add(healthBar);
        statsGrid.add(Box.createRigidArea(new Dimension(10,0)));
        statsGrid.add(new JLabel("Energy:"));
        statsGrid.add(energyBar);
        statsGrid.add(new JLabel("Hunger:"));
        statsGrid.add(hungerBar);
        statsGrid.add(Box.createRigidArea(new Dimension(10,0)));
        statsGrid.add(new JLabel("Happiness:"));
        statsGrid.add(happinessBar);

        // wrapping panel to center it
        JPanel statsPanel = new JPanel(new GridBagLayout());
        statsPanel.setMinimumSize(new Dimension(200, 0));
        statsPanel.add(statsGrid);
        mainPanel.add(statsPanel, BorderLayout.LINE_END);


        // pet action panel
        JPanel actionPanel = new JPanel();
        actionPanel.add(sleepButton);
        actionPanel.add(vetButton);
        mainPanel.add(actionPanel, BorderLayout.PAGE_END);


        // frame boilerplate
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.add(mainPanel);
    }


    //********** Action Event Listeners **********//
    /**
     * when we move to JavaFX, these will not exist and all event handling
     * exists solely in the controller
     */

    public void AddSleepListener(ActionListener vetListener) {
        sleepButton.addActionListener(vetListener);
    }

    public void AddVetListener(ActionListener vetListener) {
        vetButton.addActionListener(vetListener);
    }



    //********** Display Updating Methods **********//

    public void DisplayHealth(int maxHealth, int health) {
        healthBar.setMaximum(maxHealth);
        healthBar.setValue(health);
        healthBar.setString(String.format("%d / %d", health, maxHealth));
    }

    public void DisplayEnergy(int energy) {
        energyBar.setValue(energy);
    }

    public void DisplayHunger(int hunger) { hungerBar.setValue(hunger); }

    public void DisplayHappiness(int happiness) {happinessBar.setValue(happiness); }

    public void DisplayStatus(PetState state) {
        statusLabel.setText(state.name());
    }

    public void ToggleVetButton() { vetButton.setEnabled(!vetButton.isEnabled());}
    public void ToggleSleepState(boolean awaken) {
        sleepButton.setEnabled(awaken);
        vetButton.setEnabled(awaken);

        // BUG to fix: if vetButton is currently on cool down, then when pet is
        // put to sleep the vetButton cool down may activate vetButton before
        // pet is awake, need to somehow kill the vewButton cool down thread
    }
}
