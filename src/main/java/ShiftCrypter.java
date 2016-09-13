/**
 * Created by Sander on 13-9-2016.
 */
public class ShiftCrypter extends Crypter {

    private final int shift;

    public ShiftCrypter(String shift, boolean oMode) {
        super(oMode);

        int normalizedShift = Integer.parseInt(shift) % 26;
        if (normalizedShift < 0) {
            normalizedShift = 26 + normalizedShift;
        }
        this.shift = normalizedShift;
    }

    @Override
    public String encrypt(String toEncrypt) {
        return shift(toEncrypt, shift);
    }

    @Override
    public String decrypt(String toDecrypt) {
        return shift(toDecrypt, -shift);
    }

    private String shift(String input, int offset) {
        int length = input.length();
        StringBuilder shifted = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char character = input.charAt(i);
            if (Character.isAlphabetic(character)) {
                shifted.append(shiftChar(input.charAt(i), offset));
            } else if (oMode) {
                shifted.append(character);
            }
        }
        if (!oMode) {
            return shifted.toString().toLowerCase();
        }
        return shifted.toString();
    }

    private char shiftChar(int character, int offset) {
        int start;
        int end;
        if ((character - START) < 0) {
            start = CAPITAL_START;
            end = CAPITAL_END;
        } else {
            start = START;
            end = END;
        }
        int reset = 26;
        character = character + offset;
        if (character < start) {
            character = character + reset;
        } else if (character > end) {
            character = character - reset;
        }

        return (char) character;
    }
}
