/**
 * This simple class will function as the Model, handling all data logic
 * (getting, setting, modifying, etc.)
 * Accessed by Controller which will call functions to modify Pet data per
 * user input, whenever a value is updated it will notify the controller
 */

public class Model implements ISubject {

    //********** Variables **********//

    float _health, _energy, _hunger, _happiness;
    PetState _petState;


    //********** Constructor **********//

    public Model() {
        _health = Constants.MAX_STAT;
        _energy = Constants.MAX_STAT;
        _hunger = Constants.MAX_STAT;
        _happiness = Constants.MAX_STAT;

        _petState = PetState.Normal;
    }


    //********** Public Getters and Setters **********//

    public float GetHealth() { return _health; }
    public float GetEnergy() { return _energy; }
    public float GetHunger() { return _hunger; }
    public float GetHappiness() { return _happiness; }
    public PetState GetState() { return _petState; }

    public void SetHealth(float modify) {
        if (_health + modify > Constants.MAX_STAT) _health = Constants.MAX_STAT;
        else if (_health + modify < 0) _health = 0;
        else _health += modify;

        Notify(Property.Health);
        CheckStatus();
    }
    public void SetEnergy(float modify) {
        if (_energy + modify > Constants.MAX_STAT) _energy = Constants.MAX_STAT;
        else if (_energy + modify < 0) _energy = 0;
        else _energy += modify;

        Notify(Property.Energy);
        CheckStatus();
    }
    public void SetHunger(float modify) {
        if (_hunger + modify > Constants.MAX_STAT) _hunger = Constants.MAX_STAT;
        else if (_hunger + modify < 0) _hunger = 0;
        else _hunger += modify;

        Notify(Property.Hunger);
        CheckStatus();
    }
    public void SetHappiness(float modify) {
        if (_happiness + modify > Constants.MAX_STAT) _happiness = Constants.MAX_STAT;
        else if (_happiness + modify < 0) _happiness = 0;
        else _happiness += modify;

        Notify(Property.Happiness);
        CheckStatus();
    }

    //********** Helper Methods **********//

    /**
     * Updates status depending on the values of the pet
     * Rudimentary logic calculates state based on how many thirds of each stat
     * bars are filled
     */
    public void CheckStatus() {

        /*
         *  WIP: Implement proper state checker
         */

        /*
        if (_health == 0) _petState = PetState.Dead;

        if (_energy == 0) _petState = PetState.Sleep;

        if (_happiness < Constants.MAX_STAT / 2) _petState = PetState.Angry;
        else if (_petState == PetState.Angry) _petState = PetState.Normal;
         */

        Notify(Property.PetState);
    }


    //********** Observer **********//

    private IObserver _observer;

    /**
     * Attaches the controller instance to this Model
     *
     * @param observer = the controller that is observing this model
     */
    @Override
    public void Subscribe(IObserver observer) {
        _observer = observer;
    }

    /**
     * Notifies the controller that the given property was updated
     *
     * @param property = property which was updated
     */
    @Override
    public void Notify(Property property) {
        _observer.Update(property);
    }
}
