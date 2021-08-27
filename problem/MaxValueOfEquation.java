package problem;

public class MaxValueOfEquation {
    public static void main(String[] args) {
        MaxValueOfEquation solver = new MaxValueOfEquation();
        assert solver.findMaxValue(new int[][]{{1, 3}, {2, 0}, {5, 10}, {6, -10}}, 1) == 4;
        assert solver.findMaxValue(new int[][]{{0, 0}, {3, 0}, {9, 2}}, 3) == 3;
        assert solver.findMaxValue(new int[][]{{0, 0}, {1, 3}, {2, 0}, {5, 10}, {6, 13}, {7, -200}, {8, 11}}, 20) == 26;
    }


    public int findMaxValue(int[][] data, int limit) {
        // init e1,  e2
        // init max

        int e1 = 0;
        int e2 = 0;
        int max = 0;


        for (int i = 1; i < data.length; i++) {
            int e1Difference = data[i][0] - data[e1][0];
            int e2Difference = data[i][0] - data[e2][0];
            if (e1Difference <= limit) {
                int v = calculate(data, e1, i);
                if (v >= max) {
                    max = v;
                }
                if (data[e1][1] <= data[i][1]) {
                    e1 = i;
                }

            } else if (e2Difference <= limit) {
                int v = calculate(data, e2, i);
                if (v >= max) {
                    max = v;
                }
                if (data[e2][1] <= data[i][1]) {
                    e1 = i;
                }

            }
            e2 = i;
        }


        return max;
    }

    public int calculate(int[][] data, int leftIndex, int rightIndex) {
        return data[rightIndex][1] + data[leftIndex][1] + data[rightIndex][0] - data[leftIndex][0];
    }
}
