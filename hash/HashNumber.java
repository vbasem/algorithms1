package hash;

import java.util.Arrays;

public class HashNumber  {


    public static void main(String[] args) {
        var numsToAdd = new int[]{ 59382,43,6894,500,99,-58};
        var nums = new int[10];

        for (int a = 0; a < numsToAdd.length; a++) {
            nums[hashNum(numsToAdd[a])] = numsToAdd[a];
        }

        Arrays.stream(nums).forEach(System.out::println);
    }


    public static int hashNum(int number) {
        int baseModulo = 10;
        int mod = Math.abs(number % baseModulo);
         while (mod  > 10) {
             baseModulo*= 10;
             mod  = Math.abs(number % baseModulo);
         }

         return mod;
    }
}