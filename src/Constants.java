/**
 *  This class is used to store global constants, store all constants here in
 *  order to unify all class constants and make modifications occur globally
 */

public interface Constants {

    // ********* Timer Constants ********* //

    float ENERGY_LOSS = 1;
    long TIME_INTERVAL = 1000; // in nanoseconds


    // ********* Pet Stat Constants ********* //

    float MAX_HEALTH = 100, MAX_ENERGY = 100, MAX_EXP = 100;


    // ********* Event Modifier Constants ********* //

    // options for pet actions
    float APPLE = 10;
    float CHICKEN = 25;
    float POTION = 50;

    float SIT = 10;
    float NAP = 25;
    float SLEEP = 50;

    float WALK = 25;
    float RUN = 50;

}