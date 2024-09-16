import java.util.Timer;
import java.util.TimerTask;

/**
 * Mediates between the Model and View, controlling all the functional logic
 */
public class Controller {

    //********** View and Model Instances **********//

    private final Model _model;
    private final View _view;


    //********** Constructor **********//

    public Controller(Model model, View view) {
        _view = view;
        _model = model;

        // subscribe to the model to attach itself as an observer
        _model.Subscribe(this);

        // adding listeners to View buttons
        _view.AddEatFoodListener(e -> _model.Heal(_view.GetEatType()));
        _view.AddRestListener(e -> _model.Rest(_view.GetRestType()));
        _view.AddExerciseListener(e -> {
            _model.TakeDamage(_view.GetExerciseType());
            _model.LoseEnergy(_view.GetExerciseType());
            _model.GainExp(_view.GetExerciseType());
        });

        // Timer that will reduce the pets energy at a fixed time interval
        TimerTask energyLoss = new EnergyLoss();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(energyLoss, 0, Constants.TIME_INTERVAL);
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


    //********** TimerTask class **********//

    /**
     * Private inner class which defines a task that will reduce the health
     * and energy of the pet, if those resulting values falls bellow the
     * requirements of exercise, disable that ability
     */
    private class EnergyLoss extends TimerTask {
        @Override
        public void run() {
            _model.TakeDamage(Constants.ENERGY_LOSS);
            _model.LoseEnergy(Constants.ENERGY_LOSS);

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
    public void Update(Model.Property property) {
        switch(property) {
            case Level:
                _view.DisplayLevel(_model.GetLevel());
                break;
            case Exp:
                _view.DisplayExp(_model.GetExp());
                break;
            case Health:
                _view.DisplayHealth(_model.GetHealth());
                break;
            case Energy:
                _view.DisplayEnergy(_model.GetEnergy());
                break;
            case Status:
                _view.DisplayStatus(_model.GetState());
        }

        ToggleExerciseButton();
    }
}
