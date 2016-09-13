import org.apache.commons.cli.*;

import java.util.Scanner;

/**
 * Created by wessel on 9/9/16.
 */
class Controller {
    private CommandLine commandLine;
    private Options options;
    private String key;

    Controller(String[] args) throws ParseException {
        this.options = getOptions();
        CommandLineParser parser = new BasicParser();

        try {
            CommandLine commandLine = parser.parse(options, args);

            if (commandLine.getArgs().length != 1) {
                printHelpPage();
                throw new ParseException("The encryption key and only the encryption key should be passed as a non flag argument.");
            }

            this.commandLine = commandLine;
            key = commandLine.getArgs()[0];
        } catch (ParseException e) {
            System.out.println("Incorrect arguments:");
            printHelpPage();
            throw e;
        }
    }

    private static Options getOptions() {
        Options options = new Options();
        options.addOption("d", "decrypt", false, "Sets the mode of the program to decrypting");
        options.addOption("o", "original", false,
                "Sets the mode of the program to keeping characters that can not be encrypted from the input in the output");
        options.addOption("h", "help", false, "Display this help page");
        return options;
    }

    private void printHelpPage() {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("[-h] [-o] [-d] <key>",
                "This application will take the standard input and encrypt or decrypt it using a substitution cypher.",
                options, "");
    }

    void run() {
        if (key.equals("help") || commandLine.hasOption("h")) {
            printHelpPage();
            return;
        }

        SubstitutionTool substitutionTool = new SubstitutionTool(key, commandLine.hasOption("o"), commandLine.hasOption('d'));

        processInput(substitutionTool);
    }

    private void processInput(SubstitutionTool tool) {
        Scanner s = null;
        try {
            s = new Scanner(System.in);

            while (s.hasNextLine()) {
                String input = s.nextLine();
                System.out.println(tool.start(input));
            }

        } finally {
            if (s != null) {
                s.close();
            }
        }
    }
}
