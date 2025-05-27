import controller.ArmyController;
import javax.swing.SwingUtilities;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArmyController controller = new ArmyController();
            controller.launchApplication();
        });
    }
}
