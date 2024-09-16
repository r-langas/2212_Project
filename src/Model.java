/**
 * This simple class will function as the Model, handling all data logic
 * (getting, setting, modifying, etc.)
 * Accessed by Controller which will call functions to modify Pet data per
 * user input, whenever a value is updated it will notify the controller
 */

public class Model {

    //********** Variables **********//

    float _health, _energy, _exp;
    int _level;
    State _state;


    //********** Constructor **********//

    public Model() {
        _health = Constants.MAX_HEALTH;
        _energy = Constants.MAX_ENERGY;
        _exp = 0;
        _level = 0;
        _state = State.Good;
    }


    //********** Public Getters and Setters **********//

    public float GetHealth() { return _health; }
    public float GetEnergy() { return _energy; }
    public float GetExp() { return _exp; }
    public int GetLevel() { return _level; }
    public State GetState() { return _state; }

    /**
     * Reduces the health of the pet to a minimum of 0
     *
     * @param damage = amount to be removed from health
     */
    public void TakeDamage(float damage) {
        if (_health - damage >= 0) _health -= damage;
        else _health = 0;

        Notify(Property.Health);
        CheckStatus();
    }

    /**
     * Increases health of the pet to a maximum of MAX_HEALTH
     *
     * @param replenishedHealth = amount to be added to health
     */
    public void Heal(float replenishedHealth) {
        if (_health + replenishedHealth <= Constants.MAX_HEALTH) _health += replenishedHealth;
        else _health = Constants.MAX_HEALTH;

        Notify(Property.Health);
        CheckStatus();
    }

    /**
     * Reduces energy of the pet to a minimum of zero
     *
     * @param energyLost = amount of energy lost
     */
    public void LoseEnergy(float energyLost) {
        if (_energy - energyLost >= 0) _energy -= energyLost;
        else _energy = 0;

        Notify(Property.Energy);
        CheckStatus();
    }

    /**
     * Increases energy of the pet to a maximum of MAX_ENERGY
     *
     * @param replenishedEnergy = amount of energy replenished
     */
    public void Rest(float replenishedEnergy) {
        if (_energy + replenishedEnergy <= Constants.MAX_ENERGY) _energy += replenishedEnergy;
        else _energy = Constants.MAX_ENERGY;

        Notify(Property.Energy);
        CheckStatus();
    }

    /**
     * Increases exp, once exp reaches MAX_EXP, increase level and reset exp
     *
     * @param expGained = amount of exp gained
     */
    public void GainExp(float expGained) {
        if(_exp + expGained < Constants.MAX_EXP) _exp += expGained;
        else {
            _exp = 0;
            if(_level < Constants.MAX_EXP) {
                _level++;
                Notify(Property.Level);
            }
        }

        Notify(Property.Exp);
    }


    //********** Helper Methods **********//

    /**
     * Updates status depending on the values of the pet
     * Rudimentary logic calculates state based on how many thirds of each stat
     * bars are filled
     */
    public void CheckStatus() {
        int status = 0;
        status += 2*((int)Math.min(_health, 99) / 33 + (int)Math.min(_energy, 99) / 33);

        if (status >= 8) _state = State.Good;
        else if (status >= 6) _state = State.Ok;
        else if (status >= 4) _state = State.Neutral;
        else if (status >= 2) _state = State.Poor;
        else _state = State.Poor;

        Notify(Property.Status);
    }


    //********** Observer **********//
    // using the Observer pattern to trigger event in Controller class

    private Controller _controller;

    /**
     * Attaches the controller instance to this Model
     *
     * @param controller = the controller that is observing this model
     */
    public void Subscribe(Controller controller) {
        _controller = controller;
    }

    /**
     * Notifies the controller that the given property was updated
     *
     * @param property = property which was updated
     */
    public void Notify(Property property) {
        _controller.Update(property);
    }

    // Enum to cleanly identify each property
    public enum Property {
        Level, Exp, Health, Energy, Status
    }

    // Enum to describe pet states
    public enum State {
        Good, Ok, Neutral, Poor, Bad
    }
}
