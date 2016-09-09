import org.apache.commons.cli.*;

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
            CommandLine commandLine = parser.parse(options, args);

            Controller controller = new Controller(commandLine);
            controller.run();
        } catch (ParseException e) {
            System.out.println("Incorrect arguments:");
//            print help page
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("[-h] [-o] [-d] <key>",
                    "This application will take the standard input and encrypt or decrypt it using a substitution cypher.",
                    options, "");
        }
    }
}
