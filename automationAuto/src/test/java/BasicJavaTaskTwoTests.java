import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.junit.jupiter.api.RepeatedTest;

import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.fail;


public class BasicJavaTaskTwoTests {

    private void check(String testName, boolean testPassedFailed) {
        if (testPassedFailed) {
            System.out.println(testName + ": TEST PASSED");
        } else {
            System.out.println(testName + ": TEST FAILED");
            fail(testName + " не прошёл проверку");
        }
    }

    @Test
    void testIsEven() {
        check("isEven(4)", BasicJava.isEven(4));
        check("isEven(3)", !BasicJava.isEven(3));
    }

    @Test
    void testCheckAccess() {
        check("checkAccess(19)", "Allowed".equals(BasicJava.checkAccess(19)));
        check("checkAccess(18)", "Denied".equals(BasicJava.checkAccess(18)));
    }

    @Test
    void testHasBug() {
        check("hasBug: Bug найден", BasicJava.hasBug(new String[]{"Bug"}));
    }

    @Test
    void testReverse() {
        String[] actual = BasicJava.reverse(new String[]{"a", "b", "c"});
        String[] expected = {"c", "b", "a"};
        check("reverse", Arrays.equals(expected, actual));
    }

    @RepeatedTest(5)
    void testSumToNWithRandomInput() {
        int n = ThreadLocalRandom.current().nextInt(-100, 46_341);
        int expected = n < 1 ? 0 : (int) ((long) n * (n + 1) / 2);
        check("sumToN(" + n + ")", BasicJava.sumToN(n) == expected);
    }

    @RepeatedTest(5)
    void testCalcAverage() {
        List<Integer> list = Arrays.asList(
                ThreadLocalRandom.current().nextInt(1, 10),
                ThreadLocalRandom.current().nextInt(1, 10),
                ThreadLocalRandom.current().nextInt(1, 10)
        );
        int expected = list.stream().mapToInt(Integer::intValue).sum() / list.size();
        check("calcAverage", BasicJava.calcAverage(list) == expected);
    }

    @RepeatedTest(3)
    void testBlastOff() {
        int n = ThreadLocalRandom.current().nextInt(1, 6);
        String expected = IntStream.rangeClosed(1, n)
                .mapToObj(i -> String.valueOf(n + 1 - i))
                .reduce((a, b) -> a + " " + b)
                .orElse("") + " Поехали!";
        check("blastOff(" + n + ")", expected.equals(BasicJava.blastOff(n)));
    }

    @RepeatedTest(4)
    void testFindMax() {
        int[] arr = new int[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(-100, 100);
        }
        int expected = Arrays.stream(arr).max().orElse(Integer.MIN_VALUE);
        check("findMax", BasicJava.findMax(arr) == expected);
    }

    @ParameterizedTest
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
        check("getGrade(" + score + ")", expected.equals(BasicJava.getGrade(score)));
    }

    @ParameterizedTest
    @CsvSource({
            "2, 4, 2 4",
            "5, 7, 6"
    })
    void testGetEvenInRange(int start, int end, String expected) {
        check("getEvenInRange(" + start + ", " + end + ")", expected.equals(BasicJava.getEvenInRange(start, end)));
    }

    @ParameterizedTest
    @CsvSource({
            "5, true",
            "10, true",
            "-1, false"
    })
    void testCheckIsPositive(int value, boolean expected) {
        check("isPositive(" + value + ")", BasicJava.isPositive(value) == expected);
    }

    @ParameterizedTest
    @CsvSource({
            "Анна,Иван,Анна,Олег,Анна,Иван,Олег"
    })
    void testRemoveSpecificName(String input1, String input2, String input3, String input4, String toRemove, String expected1, String expected2) {
        List<String> inputList = Arrays.asList(input1, input2, input3, input4);
        List<String> expectedList = Arrays.asList(expected1, expected2);
        List<String> actual = BasicJava.removeSpecificName(inputList, toRemove);
        check("removeSpecificName", expectedList.equals(actual));
    }
}