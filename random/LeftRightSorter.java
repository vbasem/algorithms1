package random;

import java.util.Arrays;

public class LeftRightSorter {


    public static void main(String[] args) {
        var numbers = new int[]{2,1,3};

        var mid = 1;
        int i = 0;
        int j = numbers.length - 1;
        int midValue = numbers[mid];
        System.out.println(midValue);
        while (i <= j) {
            while (numbers[i] < midValue) {
                i++;
            }
            while (numbers[j] > midValue) {
                j--;
            }


            if (i <= j) {
                System.out.println("swappint " + numbers[i] + " with  " + numbers[j]);
                int tmp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = tmp;
                i++;
                j--;
            }
        }
        System.out.println("mid: " + mid + " right: " + j + " left: " + i);
        System.out.println(Arrays.toString(numbers));
    }
}
