import java.util.ArrayList;
import java.util.List;

public class BasicJava {

    //    Задача 1:
    public static boolean isEven(int n) {
        return (n % 2 == 0) ? true : false;
    }

    //    Задача 2:
    public static String checkAccess(int age) {
        return (age > 18) ? "Allowed" : "Denied";
    }

    //    Задача 3:
    public static boolean isPositive(int n) {
        return (n >= 0) ? true : false;
    }

    //    Задача 4:
    public static String getGrade(int score) {
        if (score < 0 || score > 100) {
            return "Error";
        }
        if (score <= 20) {
            return "E";
        } else if (score <= 40) {
            return "D";
        } else if (score <= 60) {
            return "C";
        } else if (score <= 80) {
            return "B";
        } else {
            return "A";
        }
    }

    //    Задача 5:
    public static String blastOff(int start) {
        StringBuilder result = new StringBuilder();
        for (int i = start; i >= 1; i--) {
            if (i < start) {
                result.append(" ");
            }
            result.append(i);
        }
        result.append(" Поехали!");
        return result.toString();
    }

    //    Задача 6:
    public static int sumToN(int n) {
        if (n < 1) {
            return 0;
        }
        return n * (n + 1) / 2;
    }

    //    Задача 7:
    public static boolean hasBug(String[] messages) {
        for (String message : messages) {
            if (message != null && "Bug".equalsIgnoreCase(message)) {
                return true;
            }
        }
        return false;
    }

    //    Задача 8:
    public static String getEvenInRange(int start, int end) {
        StringBuilder result = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {
                if (result.length() > 0) {
                    result.append(" ");
                }
                result.append(i);
            }
        }
        return result.toString();
    }

    //    Задача 9:
    public static int findMax(int[] arr) {

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    //    Задача 10:
    public static String[] reverse(String[] arr) {

        String[] reversed = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            reversed[i] = arr[arr.length - 1 - i];
        }
        return reversed;
    }

    //    Задача 11:
    public static Integer calcAverage(List<Integer> list) {

        Integer sum = 0;
        for (Object obj : list) {
            if (obj instanceof Number) {
                sum += ((Number) obj).intValue();
            }
        }
        return sum / list.size();
    }

    //    Задача 12:
    public static List<String> removeSpecificName(List<String> list, String nameToRemove) {

        List<String> result = new ArrayList<>();
        for (String name : list) {
            if (!nameToRemove.equals(name)) {
                result.add(name);
            }
        }
        return result;
    }
}
