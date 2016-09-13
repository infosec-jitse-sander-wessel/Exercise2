/**
 * Created by Sander on 13-9-2016.
 */
public class MappingCrypter extends Crypter {

    public MappingCrypter(String key, boolean oMode) {

    }

    @Override
    public String encrypt(String toEncrypt) {
        return null;
    }

    @Override
    public String decrypt(String toDecrypt) {
        return null;
    }

    public static boolean isValidMapping(String key) {
        boolean allLetters = key.chars().allMatch(Character::isLetter);
        boolean sizeCorrect = key.length() == 26;
        boolean allUnique = allCharactersUnique(key);
        return allLetters && sizeCorrect && allUnique;
    }

    private static boolean allCharactersUnique(String key) {
        String upperCase = key.toUpperCase();
        boolean[] charactersExist = new boolean[26];
        for (int index = 0; index < upperCase.length(); index++) {
            int code = upperCase.charAt(index);
            if (!charactersExist[code]) {
                charactersExist[code] = true;
            } else {
                return false;
            }
        }
        return true;
    }
}
