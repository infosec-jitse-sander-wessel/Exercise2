import org.apache.commons.cli.*;

/**
 * Created by wessel on 9/9/16.
 */
class Controller {
    private CommandLine commandLine;
    private Options options;
    private String key;

    Controller(CommandLineParser parser, Options options, String[] args) throws ParseException {
        this.options = options;

        try {
            CommandLine commandLine = parser.parse(options, args);

            if (commandLine.getArgs().length != 1) {
                printHelpPage();
                return;
            }

            this.commandLine = commandLine;
            key = commandLine.getArgs()[0];
        } catch (ParseException e) {
            System.out.println("Incorrect arguments:");
            printHelpPage();
            throw e;
        }
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

        if (commandLine.hasOption("o")) {
            //delete unused chars and decapitalize
        }

        //todo: normalize key

        StringBuilder result = commandLine.hasOption("d") ? SubstitutionTool.decrypt(System.in, key) : SubstitutionTool.encrypt(System.in, key);
    }
}
