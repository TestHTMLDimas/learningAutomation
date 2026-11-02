import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertionTaskTwoTests {

    @RepeatedTest(10)
    @Tag("regress")
    void testIsEven() {
        assertThat(BasicJava.isEven(4)).isEqualTo(true);
        assertThat(BasicJava.isEven(3)).isEqualTo(false);
    }

    @RepeatedTest(10)
    @Tag("regress")
    void testCheckAccess() {
        assertThat(BasicJava.checkAccess(19)).isEqualTo("Allowed");
        assertThat(BasicJava.checkAccess(18)).isEqualTo("Denied");
    }

    @RepeatedTest(10)
    @Tag("regress")
    void testHasBug() {
        assertThat(BasicJava.hasBug(new String[]{"Bug"})).isEqualTo(true);
    }

    @RepeatedTest(10)
    @Tag("regress")
    void testReverse() {
        String[] actual = BasicJava.reverse(new String[]{"a", "b", "c"});
        String[] expected = {"c", "b", "a"};

        assertThat(expected).isEqualTo(actual);
    }

    @RepeatedTest(10)
    @Tag("smoke")
    void testSumToNWithRandomInput() {
        int n = ThreadLocalRandom.current().nextInt(-100, 46_341);
        int expected = n < 1 ? 0 : (int) ((long) n * (n + 1) / 2);

        assertThat(BasicJava.sumToN(n)).isEqualTo(expected);
    }

    @RepeatedTest(10)
    @Tag("smoke")
    void testCalcAverage() {
        List<Integer> list = Arrays.asList(
                ThreadLocalRandom.current().nextInt(1, 10),
                ThreadLocalRandom.current().nextInt(1, 10),
                ThreadLocalRandom.current().nextInt(1, 10)
        );
        int expected = list.stream().mapToInt(Integer::intValue).sum() / list.size();

        assertThat(BasicJava.calcAverage(list)).isEqualTo(expected);
    }

    @RepeatedTest(10)
    @Tag("smoke")
    void testBlastOff() {
        int n = ThreadLocalRandom.current().nextInt(1, 6);
        String expected = IntStream.rangeClosed(1, n)
                .mapToObj(i -> String.valueOf(n + 1 - i))
                .reduce((a, b) -> a + " " + b)
                .orElse("") + " Поехали!";

        assertThat(BasicJava.blastOff(n)).isEqualTo(expected);
    }

    @RepeatedTest(10)
    @Tag("smoke")
    void testFindMax() {
        int[] arr = new int[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(-100, 100);
        }
        int expected = Arrays.stream(arr).max().orElse(Integer.MIN_VALUE);

        assertThat(BasicJava.findMax(arr)).isEqualTo(expected);
    }

    @ParameterizedTest
    @Tag("highest")
    @CsvSource({
            "-1, Error",
            "0, E",
            "20, E",
            "21, D",
            "40, D",
            "41, C",
            "60, C",
            "61, B",
            "80, B",
            "81, A",
            "100, A",
            "101, Error"
    })
    void testGetGrade(int score, String expected) {
        for (int i = 0; i < 10; i++) {

            assertThat(BasicJava.getGrade(score)).isEqualTo(expected);

        }
    }

    @ParameterizedTest
    @Tag("highest")
    @CsvSource({
            "2, 4, 2 4",
            "5, 7, 6"
    })
    void testGetEvenInRange(int start, int end, String expected) {
        for (int i = 0; i < 10; i++) {

            assertThat(BasicJava.getEvenInRange(start, end)).isEqualTo(expected);

        }
    }

    @ParameterizedTest
    @Tag("highest")
    @CsvSource({
            "5, true",
            "10, true",
            "-1, false"
    })
    void testCheckIsPositive(int value, boolean expected) {
        for (int i = 0; i < 10; i++) {

            assertThat(BasicJava.isPositive(value)).isEqualTo(expected);

        }
    }

    @ParameterizedTest
    @Tag("highest")
    @CsvSource({
            "Анна,Иван,Анна,Олег,Анна,Иван,Олег"
    })
    void testRemoveSpecificName(String input1, String input2, String input3, String input4, String toRemove, String expected1, String expected2) {
        for (int i = 0; i < 10; i++) {

            List<String> inputList = Arrays.asList(input1, input2, input3, input4);
            List<String> expectedList = Arrays.asList(expected1, expected2);
            List<String> actual = BasicJava.removeSpecificName(inputList, toRemove);

            assertThat(actual).containsExactlyElementsOf(expectedList);

        }
    }
}