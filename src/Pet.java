/**
 * This simple class will function as the Model, handling all data logic
 * (getting, setting, modifying, etc.)
 * Accessed by Controller which will call functions to modify Pet data per
 * user input, whenever a value is updated it will notify the controller
 */

public abstract class Pet implements ISubject {

    //********** Variables **********//

    int _maxHealth, _maxEnergy, _maxHunger, _maxHappiness;
    int _health, _energy, _hunger, _happiness;
    PetState _petState;


    //********** Default Constructor **********//

    public Pet(int maxHealth, int maxEnergy, int maxHunger, int maxHappiness) {
        _maxHealth = maxHealth;
        _maxEnergy = maxEnergy;
        _maxHunger = maxHunger;
        _maxHappiness = maxHappiness;

        _health = _maxHealth;
        _energy = _maxEnergy;
        _hunger = _maxHunger;
        _happiness = _maxHappiness;

        _petState = PetState.Normal;
    }


    //********** Getters **********//

    public int GetMaxHealth() { return _maxHealth; }

    public int GetHealth() { return _health; }
    public int GetEnergy() { return _energy; }
    public int GetHunger() { return _hunger; }
    public int GetHappiness() { return _happiness; }
    public PetState GetState() { return _petState; }


    //********** Setters **********//

    // Setters must be bounds checked ensuring all stats range from 0 - MAX

    public void IncreaseMaxHealth(int increment) {
        _maxHealth += increment;
        _health += increment;
        Notify(Property.Health);
    }

    public void SetHealth(int modify) {
        _health += modify;

        if (_health > _maxHealth) _health = _maxHealth;
        else if (_health < 0) _health = 0;

        Notify(Property.Health);
        CheckStatus();
    }

    public void SetEnergy(int modify) {
        _energy += modify;

        if (_energy > _maxEnergy) _energy = _maxEnergy;
        else if (_energy < 0) _energy = 0;

        Notify(Property.Energy);
        CheckStatus();
    }

    public void SetHunger(int modify) {
        _hunger += modify;

        if (_hunger > _maxHunger) _hunger = _maxHunger;
        else if (_hunger < 0) _hunger = 0;

        Notify(Property.Hunger);
        CheckStatus();
    }

    public void SetHappiness(int modify) {
        _happiness += modify;

        if (_happiness > _maxHappiness) _happiness = _maxHappiness;
        else if (_happiness < 0) _happiness = 0;

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

        /** WIP: Implement proper state checker */

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
