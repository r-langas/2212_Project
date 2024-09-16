/*
 * Tasks and Possible Changes:
 *
 * General
 * - create separate class from constants so adjusting values will be easier
 * - might want to implement abstract interfaces for Model and Controller
 *   following Observer pattern
 *
 * Model
 * - Increase stats and actions that cane be performed
 * - Change status updating to something that more accurately represents the
 *   pet's status
 *
 * View
 * - Probably want to use JavaFX as the GUI library, Swing works but as the
 *   view get more complicated it becomes too large
 * - Break out GUI components into separate class components to increase
 *   readability and portability; e.g. Stat bar components, panel components
 * - Redesign action selection radiobuttons to something better
 * - Create sprites/animations to display pet status and/or idle animations
 *
 * Controller
 * - Break out timer task to a separate class as having it inside is messy
 *
 */

/**
 *                    ********** READ ME **********
 *
 * The project right now is implemented following the Model-View-Controller
 * pattern, as to abstract out the data logic, GUI, and functional logic.
 * The Model will deal with all the data logic (setting, calculating, etc.).
 * The View will simply be just the visual/GUI components.
 * The Controller is the functional logic that mediates between the two.
 *      *Note that the Model and View should never directly interact*
 *
 * Concerning the view, it is currently implemented using the Javax Swing GUI
 * library but its would be better. JavaFX uses FXML which is much more
 * intuitive for design, allows for CSS styling, and the further abstraction
 * of the GUI from its Button/Event handlers
 *
 */

public class Main {
    public static void main(String[] args) {
        Model petModel = new Model();
        View view = new View();
        Controller controller = new Controller(petModel, view);

        view.setVisible(true);
    }
}