import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Created by wessel on 9/9/16.
 */
public class Main {
    public static void main(String[] args) {
        CommandLineParser parser = new BasicParser();
        Options options = new Options();
        options.addOption("d", "decrypt", false, "Sets the mode of the program to decrypting");
        options.addOption("o", "original", false,
                "Sets the mode of the program to keeping characters that can not be encrypted from the input in the output");
        options.addOption("h", "help", false, "Display this help page");

        try {
            Controller controller = new Controller(parser, options, args);
            controller.run();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
