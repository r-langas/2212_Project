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

    private static Pet _pet;
    private static View _view;

    private static int _food = Constants.POTION, _gift = 100;


    //********** Constructor **********//

    public Controller(Pet pet, View view) {
        _view = view;
        _pet = pet;

        // subscribe to the model to attach itself as an observer
        _pet.Subscribe(this);

        // adding listeners to View buttons
        _view.AddSleepListener(new SleepEvent());
        _view.AddVetListener(new VetEvent());

        // display health initially
        _view.DisplayHealth(_pet.GetMaxHealth(), _pet.GetHealth());

        // Timer that will reduce the pets energy at a fixed time interval
        new Timer().scheduleAtFixedRate(
                new EnergyLoss(),
                0,
                Constants.TIME_INTERVAL
        );
    }

    //********** Setters **********//

    public void SetFoodItem(int foodItem) { _food = foodItem; }
    public void SetGiftItem(int giftItem) { _gift = giftItem; }


    //********** Event Listeners **********//
    /**
     * when we move to JavaFX, these should just be simplified to methods
     * rather than this class creation and overriding
     */

    private class SleepEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            _pet.SetEnergy(Constants.MAX_STAT);
            new Timer().schedule(new SleepState(), 0);
        }
    }

    private class FeedEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            _pet.SetHunger(_food);
        }
    }

    private class GiftEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            _pet.SetHappiness(_gift);
        }
    }

    private class VetEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            _pet.IncreaseMaxHealth(10);
            new Timer().schedule(new VetCooldown(), 0);
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
            _pet.SetEnergy(-Constants.WALK);
            _pet.SetHunger(-Constants.WALK);
            _pet.SetHealth(Constants.WALK);

        }
    }


    //********** TimerTask class **********//

    /**
     * TimerTask to decrement stats
     */
    private class EnergyLoss extends TimerTask {
        @Override
        public void run() {
            _pet.SetHunger(-Constants.ENERGY_LOSS);
            _pet.SetEnergy(-Constants.ENERGY_LOSS);
            _pet.SetHappiness(-Constants.ENERGY_LOSS);
        }
    }

    /**
     * TimerTask intended to implement a sleep state for the pet, this would
     * replenish energy but disable all other actions
     */
    private class SleepState extends TimerTask {
        @Override
        public void run() {
            _view.ToggleSleepState(false);

            try {
                Thread.sleep(Constants.TIMER_COOLDOWN);
            }
            catch (Exception ex) {
                // fatal thread error
                System.out.print(ex.getMessage());
            }
            finally {
                _view.ToggleSleepState(true);
            }

        }
    }

    /**
     * TimerTask intended to implement an asynchronous cool down for the "Go
     * to vet" button, probably need to implement proper exception handling
     */
    private class VetCooldown extends TimerTask {
        @Override
        public void run() {
            _view.ToggleVetButton();

            try {
                Thread.sleep(Constants.TIMER_COOLDOWN);
            }
            catch (Exception ex) {
                // fatal thread error
                System.out.print(ex.getMessage());
            }
            finally {
                _view.ToggleVetButton();
            }

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
                _view.DisplayHealth(_pet.GetMaxHealth(), _pet.GetHealth());
                break;
            case Energy:
                _view.DisplayEnergy(_pet.GetEnergy());
                break;
            case Hunger:
                _view.DisplayHunger(_pet.GetEnergy());
                break;
            case Happiness:
                _view.DisplayHappiness(_pet.GetHappiness());
                break;

            case PetState:
                _view.DisplayStatus(_pet.GetState());
        }
    }
}
