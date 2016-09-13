/**
 * Created by Sander on 13-9-2016.
 */
abstract class Crypter {

    protected final static int START = (int) 'a';
    protected final static int END = (int) 'z';
    protected final static int CAPTITAL_START = (int) 'A';
    protected final static int CAPTITAL_END = (int) 'Z';
    protected boolean decryptMode;
    protected boolean oMode;

    public abstract String encrypt(String toEncrypt);

    public abstract String decrypt(String toDecrypt);
}
