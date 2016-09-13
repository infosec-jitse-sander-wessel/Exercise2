/**
 * Created by Sander on 13-9-2016.
 */
public class ShiftCrypter extends Crypter {

    private int shift;

    public ShiftCrypter(String shift, boolean oMode) {
        this.shift = Integer.parseInt(shift);
    }

    @Override
    public String encrypt(String toEncrypt) {
        return null;
    }

    @Override
    public String decrypt(String toDecrypt) {
        return null;
    }

    public static boolean isShiftKey(String key) {
        try {
            int shift = Integer.parseInt(key);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
