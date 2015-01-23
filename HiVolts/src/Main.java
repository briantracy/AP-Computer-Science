import java.awt.*;


/**
 * This is the entry point of the application. Here we create the frame, and set all of its properties.
 */
public class Main {

    public static void main(String[] args) {



        Controller controller = new Controller();
        controller.setVisible(true);
        controller.setDefaultCloseOperation(controller.EXIT_ON_CLOSE);
        controller.setSize(Toolkit.getDefaultToolkit().getScreenSize());    // make the game full screen
        System.out.println(Toolkit.getDefaultToolkit().getScreenSize());
        controller.setResizable(false);
        controller.setTitle("HiVolts");





    }
}
