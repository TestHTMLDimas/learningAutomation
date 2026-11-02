import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AssertionTaskOneTest {
    private static final String CHARS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    static String generateText(int length) {
        StringBuilder result = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            result.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
        }

        return result.toString();
    }

    public static boolean automatedTestsAreCool(String text) {
        return (text == "AutomatedTestsAreCool") ? true : false;
    }

    public static List<String> generateList(int count, int length) {
        List<String> strings = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            strings.add(generateText(length));
        }

        return strings;
    }

    public static String toUpperCase(String text) {
        return text == null ? null : text.toUpperCase(Locale.ROOT);
    }

    @Test
    void testAutomatedTestsAreCool() {
        assertThat(automatedTestsAreCool("AutomatedTestsAreCool")).isEqualTo(true);
    }

    @Test
    void testCheckText() {
        String text = generateText(6);

        assertThat(text).isEqualTo("Anna");
    }

    @Test
    void testCheckList() {
        List<String> textList = generateList(5, 5);

        assertThat(textList).containsExactlyElementsOf(textList);
    }

    @Test
    void testCheckTextUpperCase() {
        String text = toUpperCase("qwerty");

        assertThat(text).isUpperCase();
    }
}
