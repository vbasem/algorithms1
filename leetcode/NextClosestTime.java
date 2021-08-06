package leetcode;

import java.util.Arrays;

public class NextClosestTime {

    public static void main(String[] args) {
        NextClosestTime solver = new NextClosestTime();
        System.out.println(solver.nextClosestTime("23:59"));
    }

    public String nextClosestTime(String time) {
        int[] timeArray = convert(time);
        int[] sortedNumbers = countSort(timeArray);
        int[] max = {9, 5, 9, 2};

        for (int i = 0; i < timeArray.length; i++) {
            for (int k = 0; k < sortedNumbers.length; k++) {
                if (sortedNumbers[k] > timeArray[i]) {
                    if (i == 1 && sortedNumbers[k] > 5 ) {
                        continue;
                    }
                    if (i == 2 && timeArray[3] == 2 && sortedNumbers[k] > 3) {
                        continue;
                    }
                    if (i == 3 && sortedNumbers[k] > 2) {
                        continue;
                    }
                    timeArray[i] = sortedNumbers[k];
                    return convert(timeArray);
                }
            }
        }

        // not found
        return null;
    }

    private String convert(int[] timeArray) {
        StringBuilder time = new StringBuilder();
        for (int i = timeArray.length - 1; i >= 0; i--) {
            if (i == 1) {
                time.append(":");
            }
            time.append(timeArray[i]);
        }
        return time.toString();
    }

    private int[] convert(String time) {
        var timeArray = new int[4];
        int index = 0;
        for (int i = time.length() - 1; i >= 0; i--) {
            if (Character.isDigit(time.charAt(i))) {
                timeArray[index++] = Character.getNumericValue(time.charAt(i));
            }
        }

        return timeArray;
    }

    public int[] countSort(int[] time) {
        int[] counter = new int[10];
        int[] result = new int[4];
        for (int i = 0; i < time.length; i++) {
            counter[time[i]]++;
        }
        for (int i = 1; i < counter.length; i++) {
            counter[i] += counter[i - 1];
        }

        for (int i = time.length - 1; i >= 0; i--) {
            result[--counter[time[i]]] = time[i];
        }

        return result;
    }
}
