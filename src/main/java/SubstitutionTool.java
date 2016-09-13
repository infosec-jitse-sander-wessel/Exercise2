import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by wessel on 9/9/16.
 */
class SubstitutionTool {
    private final static int START = (int) 'a';
    private final static int END = (int) 'z';
    private final static int CAPITAL_START = (int) 'A';
    private final static int CAPITAL_END = (int) 'Z';
    private static final int TO_CAPS = CAPITAL_START - START;
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private boolean oMode;
    private BiMap<Integer, Integer> mapping = HashBiMap.create(52);

    public SubstitutionTool(String key) {
        createMap(key);
    }

    private void createMap(String key) {
        if (StringUtils.isNumeric(key)) {
            key = shift(Integer.parseInt(key), ALPHABET);
        }
        if (isValidKey(key)) {
            throw new IllegalArgumentException("Key should be exactly 26 characters long, key is: " + key);
        }
        key = key.toLowerCase();
        int from = START;
        for (int character : key.toCharArray()) {
            mapping.put(from, character);
            mapping.put(from + TO_CAPS, character + TO_CAPS);
            from++;
        }
    }

    private boolean isValidKey(String key) {
        return key.length() != 26 && uniqueCharacters(key);
    }

    private boolean uniqueCharacters(String key) {
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

    private String shift(int shift, String key) {
        return key.chars()
                .map(character -> shiftChar(character, shift))
                .mapToObj(character -> (char) character)
                .map(Object::toString)
                .reduce((left, right) -> left + right)
                .orElse("");
    }

    private int shiftChar(int character, int offset) {
        return ((character - START + offset) % 26) + START;
    }


    private boolean isLetter(int character) {
        return ((character >= START && character <= END) || (character >= CAPITAL_START && character <= CAPITAL_END));
    }

    public SubstitutionTool setHonourCaps(boolean oMode) {
        this.oMode = oMode;
        return this;
    }

    public SubstitutionTool setDecrypting(boolean decrypting) {
        if (decrypting) {
            mapping = mapping.inverse();
        }
        return this;
    }

    public void run(InputStream in) {
        try (Scanner scanner = new Scanner(in)) {
            scanner.forEachRemaining(this::mapLine);
        }
    }

    private void mapLine(String line) {
        line.chars()
                .filter(this::shouldStay)
                .map(this::mapCharacter)
                .mapToObj(character -> (char) character)
                .map(Object::toString)
                .reduce((left, right) -> left + right)
                .map(this::oModeMapper)
                .ifPresent(System.out::println);

    }

    private String oModeMapper(String line) {
        return oMode ? line : line.toLowerCase();
    }

    private int mapCharacter(int character) {
        return mapping.containsKey(character) ? mapping.get(character) : character;
    }

    private boolean shouldStay(int character) {
        return isLetter(character) || oMode;
    }
}
