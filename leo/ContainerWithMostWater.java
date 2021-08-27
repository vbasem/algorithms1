package leo;

import java.util.TreeMap;

public class ContainerWithMostWater {

    public static void main(String[] args) {
        ContainerWithMostWater solver = new ContainerWithMostWater();

        assert solver.maxArea(new int[]{1,8,6,2,5,4,8,3,7}) == 49;
        assert solver.maxArea(new int[]{1,1}) == 1;
        assert solver.maxArea(new int[]{1,2,1}) == 2;
        assert solver.maxArea(new int[]{1}) == 0;
        assert solver.maxArea(new int[]{}) == 0;
        assert solver.maxArea(null) == 0;
    }

    public int maxArea(int[] heights) {

        int globalMax =0;

        if (heights == null || heights.length  < 2)  {
            return 0;
        }

        for (int i = 0; i < heights.length; i++) {
            for (int j = heights.length - 1; j > i; j--) {
                int h = (j-i) * Math.min(heights[i], heights[j]);
                globalMax = Math.max(h, globalMax);
            }
        }
        return globalMax;
    }
}
