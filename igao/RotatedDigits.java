package igao;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class RotatedDigits {

    Set<Integer> good = new HashSet<>() {{
        add(2);
        add(5);
        add(6);
        add(9);
    }};
    Set<Integer> neutral = new HashSet<>() {
        {
            add(0);
            add(1);
            add(8);
        }
    };

    public static void main(String[] args) {
        RotatedDigits solver = new RotatedDigits();

        assert solver.solve(1) == 0;
        assert solver.solve(2) == 1;
        assert solver.solve(12) == 5;
        assert solver.solve(21) == 10;
    }

    private int solve(int number) {
        int total = 0;
        for (int i = 1; i <= number; i++) {
            if (i < 10 && good.contains(i)) {
                total++;
            } else if (isGoodNumber(i)) {
                total++;
            }
        }
        return total;
    }

    private boolean isGoodNumber(int i) {
        int n = i;
        boolean hasGoodNumber = false;
        boolean hasBadNumber = false;
        while (n > 0) {
            int digit = n % 10;
            if (good.contains(digit)) {
                hasGoodNumber = true;
            } else if (!neutral.contains(digit)) {
                hasBadNumber = true;
            }
            n = n / 10;
        }

        return hasGoodNumber && !hasBadNumber;
    }


}
