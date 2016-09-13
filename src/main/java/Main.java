/**
 * Created by wessel on 9/9/16.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Controller controller = new Controller(args);
            controller.run();
        } catch (Exception ignored) {
            // we let the program end.
        }
    }
}
