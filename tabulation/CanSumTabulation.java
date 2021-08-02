package tabulation;

import java.util.Arrays;

public class CanSumTabulation {
    

    public static void main(String[] args)  {

        CanSumTabulation solver = new CanSumTabulation();
        var nums  = new int[]{3, 4, 2, 1};
        System.out.println(solver.canSum(5, nums));
    }
    public boolean canSum(int target, int[] nums) { 
        boolean[] tab = new boolean[target + 1];
    
        tab[0] = true;
    
        for (int i = 0; i < tab.length; i++)  {
            if (tab[i]) {
                for (int j : nums) {
                    if (j + i  < tab.length)  {
                        tab[i+j] = true;
                    }
                }
            
            }
            System.out.println(Arrays.toString(tab));
        }
    
        return tab[target];
    
    }
    
}
