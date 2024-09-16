# Intro
This project is a starting point for the 2212 group project, and will be modified to fulfill the project requirements.
Currently it is implemented following the Model-View-Controller pattern in order to abstract and compartmentalize code into data logic, GUI, and functional logic repectively.
This should make the project much more digestible (allowing for multiple people to work on the codebase at once) and reduce merge conflicts in Git.

## Model
The Model is simply the pet object and handles all the data logic (and thus should do no more than create the pet, manipulate its stats, and return values).
It will create events whenever data is manipulated which will notify the Controller which will then update the View to display the new data.
Note that this should never directly interact with the GUI.

## View
The View is the entirety of the graphical user interface, as it is functioning only as the front end, thus no logic should be implemented in this class.
I have intially bootstrapped a simple GUI using Java Swing, but it is probably in our best interest to swap to JavaFX.
JavaFX uses FXML markup which will allow us to reduce the boilerplate code, additionally it allows us to implement CSS stylesheets to make formatting easier.

## Controller
The Controller will mediate between the View and Model and perform all the functional logic.
The user interacts wil the GUI, which will route to a function in the Controller to perform any logic.
The Controller can then access the Model when it wants to modify or access data.
