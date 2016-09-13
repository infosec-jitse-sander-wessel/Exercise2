import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by wessel on 9/9/16.
 */
class SubstitutionTool {

    private final static int START = (int) 'a';
    private final static int END = (int) 'z';
    private final static int CAPTITAL_START = (int) 'A';
    private final static int CAPTITAL_END = (int) 'Z';
    private boolean decryptMode;
    private boolean oMode;
    private Crypter crypter;

    public SubstitutionTool(String key, boolean oMode, boolean decryptMode) {
        this.decryptMode = decryptMode;
        this.crypter = getCrypter(key, oMode);
    }

    public String start(String input) {
        String result;
        if (decryptMode) {
            result = crypter.decrypt(input);
        } else {
            result = crypter.encrypt(input);
        }
        return result;
    }

//    public void crypter(InputStream in, String key, boolean oMode, boolean decryptMode) {
//        this.oMode = oMode;
//        this.decryptMode = decryptMode;
//        Scanner s = null;
//        if (decryptMode) {
//            System.out.println("decrypt: ");
//        } else {
//            System.out.println("encrypt: ");
//        }
//        String str;
//        try {
//            s = new Scanner(in);
//
//            while (s.hasNext()) {
//                if (!oMode) {
//                    str = s.next().toLowerCase();
//                } else {
//                    str = s.next();
//                }
//                if (key.length() <= 2 && key.length() != 0) {
//                    try {
//                        int shift = Integer.parseInt(key);
//                        System.out.println(this.shift(shift, str));
//                    } catch (NumberFormatException e) {
//                        System.err.println("Invalid key");
//                    }
//                } else if (key.length() == 26) {
//                    System.out.println(map(convertToIntegers(str), key));
//                }
//
//            }
//        } finally {
//            if (s != null) {
//                s.close();
//            }
//        }
//    }

//    private String map(int[] ascii, String key) {
//        String keyCapitals = key.toUpperCase();
//        int length = ascii.length;
//        String encrypted = "";
//
//        for (int i = 0; i < length; i++) {
//            if (isLetter(ascii[i])) {
//                if ((ascii[i] - START) >= 0) {
//                    if (decryptMode) {
//                        encrypted += (char) (key.indexOf(ascii[i]) + START);
//                    } else {
//                        encrypted += key.charAt(ascii[i] - START);
//                    }
//                } else {
//                    if (decryptMode) {
//                        encrypted += (char) (keyCapitals.indexOf(ascii[i]) - CAPITAL_START);
//                    } else {
//                        encrypted += keyCapitals.charAt(ascii[i] - CAPITAL_START);
//                    }
//
//                }
//            } else if (!oMode) {
//                encrypted += (char) ascii[i];
//            }
//        }
//        return encrypted;
//    }
//
//    private int[] convertToIntegers(String str) {
//        int length = str.length();
//        int[] array = new int[length];
//        for (int i = 0; i < length; i++) {
//            array[i] = (int) str.charAt(i);
//        }
//        return array;
//    }

    private Crypter getCrypter(String key, boolean oMode) throws IllegalArgumentException {
        if (isShiftKey(key)) {
            return new ShiftCrypter(key, oMode);
        } else if (isValidMapping(key)) {
            return new MappingCrypter(key, oMode);
        } else {
            throw new IllegalArgumentException("Invalid key given, it should either be an Integer " +
                    "or a mapping of all 26 English alphabet letters");
        }
    }

    private boolean isShiftKey(String key) {
        try {
            Integer.parseInt(key);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isValidMapping(String key) {
        boolean allLetters = key.chars().allMatch(Character::isLetter);
        boolean sizeCorrect = key.length() == 26;
        boolean allUnique = allCharactersUnique(key);
        return allLetters && sizeCorrect && allUnique;
    }

    private static boolean allCharactersUnique(String key) {
        String lowerCaseKey = key.toLowerCase();
        boolean[] charactersExist = new boolean[26];
        for (int index = 0; index < lowerCaseKey.length(); index++) {
            int code = lowerCaseKey.charAt(index) - 'a';
            if (!charactersExist[code]) {
                charactersExist[code] = true;
            } else {
                return false;
            }
        }
        return true;
    }
}
