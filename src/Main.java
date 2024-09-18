/*
 * Tasks and Possible Changes:
 *
 * General
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

public class Main {
    public static void main(String[] args) {
        Model petModel = new Model();
        View view = new View();
        Controller controller = new Controller(petModel, view);

        view.setVisible(true);
    }
}