import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Mediates between the Model and View, controlling all the functional logic
 */
public class Controller implements IObserver {

    //********** View and Model Instances **********//

    private Model _model;
    private View _view;

    private float _food, _gift;


    //********** Constructor **********//

    public Controller(Model model, View view) {
        _view = view;
        _model = model;

        // subscribe to the model to attach itself as an observer
        _model.Subscribe(this);

        // adding listeners to View buttons
        _view.AddEatFoodListener(null);
        _view.AddRestListener(null);
        _view.AddExerciseListener(null);

        // display health intially
        _view.DisplayHealth(_model.GetHealth());

        // Timer that will reduce the pets energy at a fixed time interval
        TimerTask energyLoss = new EnergyLoss();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(energyLoss, 0, Constants.TIME_INTERVAL);
    }


    //********** Event Listeners **********//
    // Later implement these as static classes

    private class SleepEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            _model.SetEnergy(Constants.MAX_STAT);

            // Thread.Sleep() for cooldown

            // disable all actions
        }
    }

    private class FeedEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            _model.SetHunger(_food);
        }
    }

    private class GiftEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            _model.SetHappiness(_gift);
        }
    }

    private class VetEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // implement vet event
        }
    }

    private class PlayEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // implement play event
        }
    }

    private class ExerciseEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            _model.SetEnergy(-Constants.WALK);
            _model.SetHunger(-Constants.WALK);
            _model.SetHealth(Constants.WALK);

        }
    }


    //********** Helper Methods **********//

    /**
     * Enable exerciseButton if there is enough health and energy to use,
     * otherwise disable it
     */
    public void ToggleExerciseButton() {
        _view.exerciseButton.setEnabled(
                _model.GetHealth() >= _view.GetExerciseType() &&
                        _model.GetEnergy() >= _view.GetExerciseType()
        );
    }

    public void Sleep() {
        // implement a sleep
    }


    //********** TimerTask class **********//

    /**
     * Private inner class which defines a task that will reduce the health
     * and energy of the pet, if those resulting values falls bellow the
     * requirements of exercise, disable that ability
     */
    private class EnergyLoss extends TimerTask {
        @Override
        public void run() {
            _model.SetHunger(-Constants.ENERGY_LOSS);
            _model.SetEnergy(-Constants.ENERGY_LOSS);
            _model.SetHappiness(-Constants.ENERGY_LOSS);

            ToggleExerciseButton();
        }
    }


    //********** Observer implementation **********//

    /**
     * Observer update method that will update the corresponding GUI element
     * to the property that was updated
     *
     * @param property = property that updated and published the notification
     */
    @Override
    public void Update(Property property) {
        switch(property) {
            case Health:
                _view.DisplayHealth(_model.GetHealth());
                break;
            case Energy:
                _view.DisplayEnergy(_model.GetEnergy());
                break;

            case Hunger:
                _view.DisplayHunger(_model.GetEnergy());
                break;
            case Happiness:
                _view.DisplayHappiness(_model.GetHappiness());
                break;

            case PetState:
                _view.DisplayStatus(_model.GetState());
        }

        ToggleExerciseButton();
    }
}
