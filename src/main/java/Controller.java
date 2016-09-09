import org.apache.commons.cli.CommandLine;

/**
 * Created by wessel on 9/9/16.
 */
class Controller {
    private static boolean decripting = false;
    private static boolean withNonAlphabeticalCharacters = false;
    private static String key;

    Controller(CommandLine args) {
        for (String arg : args) {
            if (arg.equals("-o")) {
                withNonAlphabeticalCharacters = true;
            }
            if (arg.equals("-d")) {
                decripting = true;
            }
        }

        if (args.length == 0) {
            System.err.println("incorrect user input. should be of the format: Usage: substitution [-o] [-d] <mapping> \n"
                    + "For more help type substitution help");
            return;
        }

        key = args[args.length - 1];
    }

    void run() {
        if (key.equals("help")) {
            System.out.println("Usage: substitution [-o] [-d] mapping\n" +
                    "Where:\n" +
                    "   -o: keep non-letters as is, honor letter casing\n" +
                    "   -d: decrypt\n" +
                    "   mapping: 26 letter char-mapping\n" +
                    "            or an int-value");
            return;
        }

        if (withNonAlphabeticalCharacters) {
            //delete unused chars and decapitalize
        }

        StringBuilder result = decripting ? SubstitutionTool.decrypt(System.in) : SubstitutionTool.encrypt(System.in);
    }
}
