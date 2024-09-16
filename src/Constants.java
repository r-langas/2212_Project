/**
 *  This class is used to store global constants, store all constants here in
 *  order to unify all class constants and make modifications occur globally
 */

public abstract interface Constants {

    // ********* Timer Constants ********* //

    final float ENERGY_LOSS = 1;
    final long TIME_INTERVAL = 1000; // in nanoseconds


    // ********* Pet Stat Constants ********* //

    final float MAX_HEALTH = 100, MAX_ENERGY = 100, MAX_EXP = 100;


    // ********* Event Modifier Constants ********* //

    // options for pet actions
    final float APPLE = 10;
    final float CHICKEN = 25;
    final float POTION = 50;

    final float SIT = 10;
    final float NAP = 25;
    final float SLEEP = 50;

    final float WALK = 25;
    final float RUN = 50;

}