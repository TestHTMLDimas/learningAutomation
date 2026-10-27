import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
    void testCheckIsPositive() {
        check("isPositive(5)", BasicJava.isPositive(5));
        check("isPositive(10)", BasicJava.isPositive(10));
        check("isPositive(-1)", !BasicJava.isPositive(-1));
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
        check("getGrade(" + score + ")",
                expected.equals(BasicJava.getGrade(score)));
    }

    @Test
    void testBlastOff() {
        check("blastOff(3)",
                "3 2 1 Поехали!".equals(BasicJava.blastOff(3)));

        check("blastOff(1)",
                "1 Поехали!".equals(BasicJava.blastOff(1)));
    }

    @RepeatedTest(5)
    void testSumToNWithRandomInput() {
        int n = ThreadLocalRandom.current().nextInt(-100, 46_341);

        int expected = n < 1
                ? 0
                : (int) ((long) n * (n + 1) / 2);

        check("sumToN(" + n + ")",
                BasicJava.sumToN(n) == expected);
    }

    @Test
    void testHasBug() {
        check("hasBug: Bug найден",
                BasicJava.hasBug(new String[]{"Bug"}));
    }

    @Test
    void testGetEvenInRange() {
        check("getEvenInRange(1, 8)",
                "2 4 6 8".equals(BasicJava.getEvenInRange(1, 8)));

        check("getEvenInRange(3, 3)",
                "".equals(BasicJava.getEvenInRange(3, 3)));
    }

    @Test
    void testFindMax() {
        check("findMax",
                BasicJava.findMax(new int[]{-4, 9, 2, 7}) == 9);
    }

    @Test
    void testReverse() {
        String[] actual = BasicJava.reverse(new String[]{"a", "b", "c"});
        String[] expected = {"c", "b", "a"};

        check("reverse", Arrays.equals(expected, actual));
    }

    @Test
    void testCalcAverage() {
        int actual = BasicJava.calcAverage(Arrays.asList(2, 4, 9));

        check("calcAverage", actual == 5);
    }

    @Test
    void testRemoveSpecificName() {
        List<String> actual = BasicJava.removeSpecificName(
                Arrays.asList("Анна", "Иван", "Анна", "Олег"),
                "Анна"
        );

        List<String> expected = Arrays.asList("Иван", "Олег");
        check("removeSpecificName", expected.equals(actual));
    }
}