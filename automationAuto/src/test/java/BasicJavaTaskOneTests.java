import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class BasicJavaTaskOneTests {

    @Test
    void isEvenWithRandomNumber() {
        int number = ThreadLocalRandom.current().nextInt(1, 101);

        boolean result = runWithMarkers(() -> BasicJava.isEven(number));

        assertEquals(number % 2 == 0, result);
    }

    @Test
    void checkAccessWithTwentyRandomAges() {
        for (int i = 0; i < 20; i++) {
            int age = ThreadLocalRandom.current().nextInt(0, 100);

            String result = runWithMarkers(() -> BasicJava.checkAccess(age));

            assertNotNull(result);
        }
    }

    @ParameterizedTest
    @MethodSource("randomScores")
    void getGradeWithRandomScores(int score) {
        String result = runWithMarkers(() -> BasicJava.getGrade(score));

        assertNotNull(result);
    }

    private static Stream<Arguments> randomScores() {
        int[] scores = ThreadLocalRandom.current()
                .ints(10, 0, 101)
                .toArray();

        return Arrays.stream(scores)
                .mapToObj(Arguments::of);
    }

    private static <T> T runWithMarkers(Supplier<T> method) {
        System.out.println("========================Test method start");

        try {
            return method.get();
        } finally {
            System.out.println("Test method end");
            System.out.println("========================");
        }
    }
}