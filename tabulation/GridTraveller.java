package tabulation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GridTraveller {
 

    public static void main(String[] args) { 
        GridTraveller solver = new GridTraveller();
        System.out.println(solver.gridTraveller(9, 9, new HashMap<>()));
        System.out.println(solver.gridTravellerTabular(9, 9));
    }

    public int gridTravellerTabular(int m, int n) {
        int[][] tab = new int[m + 1][n +1];
        Arrays.fill(tab, new int[n+1]);

        tab[1][1] = 1;
        for (int i = 1; i <= m; i++)  {
            for (int j = 1; j  <= n ; j++) {
                // if (i == 1 || j == 1) { 
                    // tab[i][j] = 1;
                // } else {
                    tab[i][j]= tab[i-1][j] + tab[i][j-1];
                // }
            }
        }

        return tab[m][n];
    }
    public int gridTraveller(int m, int n, Map<String, Integer> memo)  {
        String key = "m"+":"+"n";
        if (m == 0 || n == 0) {
            return 0;
        } else if (m ==1 || n ==1 ) { 
            return 1;
        } else if (memo.containsKey(key)) { 
            return memo.get(key);
        } else {
    
            return gridTraveller(m, n-1, memo) + gridTraveller(m -1 , n, memo);
    
        }
    
    }
    
}
