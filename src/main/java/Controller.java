import org.apache.commons.cli.*;

/**
 * Created by wessel on 9/9/16.
 */
class Controller {
    private CommandLine commandLine;
    private Options options;
    private String key;

    Controller(String[] args) throws Exception {
        this.options = getOptions();
        CommandLineParser parser = new BasicParser();

        try {
            CommandLine commandLine = parser.parse(options, args);

            if (commandLine.getArgs().length != 1) {
                throw new ParseException("The encryption key and only the encryption key should be passed as a non flag argument.");
            }

            this.commandLine = commandLine;
            key = commandLine.getArgs()[0];
        } catch (ParseException e) {
            System.out.println("Incorrect arguments:");
            printHelpPage();
            throw new Exception("incorrect input program should close");
        }
    }

    private static Options getOptions() {
        Options options = new Options();
        options.addOption("d", "decrypt", false, "decrypt");
        options.addOption("o", "original", false, "keep non-letters as is, honor letter casing");
        options.addOption("h", "help", false, "Display this help page");
        return options;
    }

    private void printHelpPage() {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("[-h] [-o] [-d] <key>",
                "En/Decrypts stdin to stdout. Only letters are encrypted,\n" +
                        "all other characters are silently ignored, unless -o was\n" +
                        "specified, in which case they are used as-is.\n" +
                        "When -o is specified, letter casing is honored, otherwise all\n" +
                        "letters are converted to lower-case letters.\n" +
                        "Use an int-value to to a letter shift (% 26, 0: a = a)\n" +
                        "Shift 3 is the classical Caesar encryption.",
                options, "");
    }

    void run() {
        if (key.equals("help") || commandLine.hasOption("h")) {
            printHelpPage();
            return;
        }

        System.out.println("running substitution with options -o: "
                + commandLine.hasOption('o') + ", -d: "
                + commandLine.hasOption('d') + "and key: " + key);

        new SubstitutionTool(key)
                .setHonourCaps(commandLine.hasOption('o'))
                .setDecrypting(commandLine.hasOption('d'))
                .run(System.in);
    }
}
