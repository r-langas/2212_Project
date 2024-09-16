# Intro
***
This project is a starting point for the 2212 group project, meant to be a jumping off point.
Currently it is implemented following the Model-View-Controller pattern in order to abstract and
compartmentalize code into data logic, GUI, and functional logic repectively. This should make the
project much more digestible and reduce merge conflicts in Git.

## Model
***
The Model is simply the pet object and handles all the data logic (and thus should do no more than
create the pet, manipulate its stats, and return values).
It will create events whenever data is manipulated which will notify the controller which will then
update the view to display the new data.

## View
***
The View is the entirety of the graphical user interface, as it is functioning only as the front end
no logic should be implemented in this class.
I have intially bootstrapped a simple GUI using Java Swing, but it is probably in our best interest
to swap to JavaFX. This will reduce all the boilerplate code and allow for CSS formatting.

## Cntroller
The Controller will mediate between the View and Model (as those two should never directly interact)
and perform all the functional logic. While the GUI recieves user inputs (button presses etc.) these
will be routed to the controller which will the perform the function and access the Model if need be.
