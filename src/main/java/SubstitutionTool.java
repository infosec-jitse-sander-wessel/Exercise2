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

    public StringBuilder decrypt(InputStream in, String key) {
        this.decryptMode = true;

        return null;
    }

    public StringBuilder encrypt(InputStream in, String key) {
        this.decryptMode = false;

        return null;
    }

    public void crypter(InputStream in, String key, boolean oMode, boolean decryptMode){
        this.oMode = oMode;
        this.decryptMode = oMode;
        Scanner s = null;
        System.out.println("encrypt");
        String str;

        try {
            s = new Scanner(in);

            while (s.hasNext()) {
                if(!oMode){
                    str = s.next().toLowerCase();
                } else{
                    str = s.next();
                }
                System.out.println(mapper(key, str));
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }


    private String mapper(String key, String str) {
        if (key.length() != 26) {
            throw new IllegalArgumentException("Key of wrong size");
        }
        int[] asciiString = convertToIntegers(str);
        int[] offsets = getOffsets(convertToIntegers(key));
        String encrypted = "";
        int offset;
        for (int symbol : asciiString) {
            if (isLetter((char)symbol)) {
                offset = mapShift(symbol, offsets);
                encrypted += shiftChar(symbol, offset);
            } else if (!oMode) {
                encrypted += (char)symbol;
            }
        }
        return encrypted;
    }

    private int mapShift(int character, int[] offsets) {
        if ((character - START) >= 0) {
            character = character - START;
        } else {
            character = character - CAPTITAL_START;
        }
        return offsets[character];
    }


    /*
    private String crypter(String key, String str, boolean oMode, boolean decrypt) {
        key = key.toLowerCase();
        int[] asciiString = convertToIntegers(str);
        int[] offsets = getOffsets(convertToIntegers(key));
        int strLength = asciiString.length;
        int keyLength = offsets.length;
        int keyIndex = 0;
        String encrypted = "";
        for (int i = 0; i < strLength; i++) {
            if (isLetter(asciiString[i])) {
                keyIndex++;
                if (keyIndex == keyLength)
                    keyIndex = 0;
                encrypted += shiftChar(asciiString[i], offsets[keyIndex], this.decryptMode);
            } else if (!oMode) {
                encrypted += (char) (asciiString[i]);
            }
        }

        return encrypted;
    }*/

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

    private int[] getOffsets(int[] key) {
        int length = key.length;
        int[] offsets = new int[length];
        for (int i = 0; i < length; i++) {
            assert isLetter(key[i]) : "Use of invalid characters in key";
            offsets[i] = key[i] - START;
        }
        return offsets;
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
