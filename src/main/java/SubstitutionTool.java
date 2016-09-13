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

    public void crypter(InputStream in, String key, boolean oMode, boolean decryptMode){
        this.oMode = oMode;
        this.decryptMode = decryptMode;
        if (decryptMode) {
            System.out.println("decrypt: ");
        } else {
            System.out.println("encrypt: ");
        }
        String str;
        try (Scanner s = new Scanner(in)) {
            while (s.hasNext()) {
                if(!oMode){
                    str = s.next().toLowerCase();
                } else{
                    str = s.next();
                }
                if (key.length() <= 2 && key.length() != 0) {
                    try {
                        int shift = Integer.parseInt(key);
                        System.out.println(this.shift(shift, str));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid key");
                    }
                } else if (key.length() == 26) {
                    System.out.println(map(convertToIntegers(str), key));
                }

            }
        }
    }

    private String shift(int shift, String str) {
        int length = str.length();
        String crypted = "";
        for (int i = 0; i < length; i++) {
            crypted += shiftChar(str.charAt(i), shift);
        }
        return crypted;
    }

    private char shiftChar(int c, int offset) {
        int start;
        int end;
        if ((c - START) < 0) {
            start = CAPTITAL_START;
            end = CAPTITAL_END;
        } else {
            start = START;
            end = END;
        }
        int reset = end - start;
        if (this.decryptMode) {
            c = c - offset;
            if (c < start) {
                c = c + reset;
            }
        } else {
            c = c + offset;
            if (c > end) {
                c = c - reset;
            }
        }
        return (char) c;
    }


    private boolean isLetter(int character) {
        return ((character >= START && character <= END) || (character >= CAPTITAL_START && character <= CAPTITAL_END));
    }

    private String map(int[] ascii, String key) {
        String keyCapitals = key.toUpperCase();
        int length = ascii.length;
        String encrypted = "";

        for (int i = 0; i < length; i++) {
            if (isLetter(ascii[i])) {
                if ((ascii[i] - START) >= 0) {
                    if (decryptMode) {
                        encrypted += (char) (key.indexOf(ascii[i]) + START);
                    } else {
                        encrypted += key.charAt(ascii[i] - START);
                    }
                } else {
                    if (decryptMode) {
                        encrypted += (char) (keyCapitals.indexOf(ascii[i]) - CAPTITAL_START);
                    } else {
                        encrypted += keyCapitals.charAt(ascii[i] - CAPTITAL_START);
                    }

                }
            } else if (!oMode) {
                encrypted += (char) ascii[i];
            }
        }
        return encrypted;
    }

    private int[] convertToIntegers(String str) {
        int length = str.length();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int) str.charAt(i);
        }
        return array;
    }

}
