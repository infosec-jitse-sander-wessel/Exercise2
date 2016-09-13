/**
 * Created by Sander on 13-9-2016.
 */
public class MappingCrypter extends Crypter {

    private final String mapping;

    public MappingCrypter(String key, boolean oMode) {
        super(oMode);
        this.mapping = key;

    }

    @Override
    public String encrypt(String toEncrypt) {
        return null;
    }

    @Override
    public String decrypt(String toDecrypt) {
        return null;
    }
}