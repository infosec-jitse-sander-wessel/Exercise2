/**
 * Created by Sander on 13-9-2016.
 */
abstract class Crypter {

    protected final static int START = (int) 'a';
    protected final static int END = (int) 'z';
    protected final static int CAPITAL_START = (int) 'A';
    protected final static int CAPITAL_END = (int) 'Z';
    protected final boolean oMode;

    public Crypter(boolean oMode) {
        this.oMode = oMode;
    }

    public abstract String encrypt(String toEncrypt);

    public abstract String decrypt(String toDecrypt);
}
