package igao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static void main(String[] args) {
        double[][] p = {{2.5, 3}, {3, 3}, {1, 1}, {1, 2}, {2, 1}, {2, 2}};
        ClosestPair solver = new ClosestPair();
        double[][] points = solver.closestPair(p);
        System.out.println(solver.calculateDelta(points, new int[]{0, 1}));
    }

    public double[][] closestPair(double[][] points) {

        double[][] ySorted = Arrays.stream(points).map(double[]::clone).toArray(double[][]::new);

        Arrays.sort(points, Comparator.comparingDouble(x -> x[0]));
        Arrays.sort(ySorted, Comparator.comparingDouble(x -> x[1]));

        int[] p = helper(points, ySorted, 0, points.length - 1);

        double[][] result = new double[2][2];
        result[0] = points[p[0]];
        result[1] = points[p[1]];

        return result;
    }

    private int[] helper(double[][] points, double[][] pointsYsorted, int start, int end) {
        if (end - start < 3) {
            return bruteForce(points, start, end);
        }
        int mid = (start + end) / 2;
        double[][] yLeft = Arrays.stream(pointsYsorted).filter(p -> p[0] <= points[mid][0]).map(double[]::clone).toArray(double[][]::new);
        double[][] yRight = Arrays.stream(pointsYsorted).filter(p -> p[0] > points[mid][0]).map(double[]::clone).toArray(double[][]::new);

        int[] left = helper(points, yLeft, start, mid);
        int[] right = helper(points, yRight, mid + 1, end);
        double delta = getLowest(points, left, right);
        ArrayList<double[]> strip = new ArrayList<>();
        for (int i = 0; i < pointsYsorted.length; i++) {
            if (Math.abs(pointsYsorted[i][0] - points[mid][0]) < delta) {
                strip.add(pointsYsorted[i]);
            }
        }

        return checkBoundary(points, strip.toArray(new double[0][]), mid, left, right);
    }

    private double getLowest(double[][] points, int[] left, int[] right) {
        double leftDelta = calculateDelta(points, left);
        double rightDelta = calculateDelta(points, right);
        return Math.min(leftDelta, rightDelta);
    }

    private int[] bruteForce(double[][] points, int start, int end) {
        double delta = Double.MAX_VALUE;
        int[] result = new int[]{};
        for (int i = start; i < end; i++) {
            for (int j = i + 1; j <= end; j++) {
                double d = calculateDelta(points, new int[]{i, j});
                if (d < delta) {
                    delta = d;
                    result = new int[]{i , j };
                }
            }
        }

        return result;
    }

    private int[] checkBoundary(double[][] points, double[][] strip, int mid, int[] left, int[] right) {
        double leftDelta = calculateDelta(points, left);
        double rightDelta = calculateDelta(points, right);
        double delta;
        int[] result;
        if (leftDelta < rightDelta) {
            result = left;
            delta = leftDelta;
        } else {
            result = right;
            delta = rightDelta;
        }
        for (int i = 0; i < strip.length; i++) {
            for (int j = 1 + i; j < strip.length && (strip[j][1] - strip[i][1]) < delta; j++) {
                double d = calculateDelta(strip, new int[]{i, j});
                if (d < delta) {
                    delta = d;
                    result = new int[]{mid - i, mid + j};
                }
            }
        }

        return result;
    }


    private double calculateDelta(double[][] points, int[] indices) {
        double x = Math.pow(points[indices[1]][0] - points[indices[0]][0], 2);
        double y = Math.pow(points[indices[1]][1] - points[indices[0]][1], 2);
        return Math.sqrt(x + y);
    }
}
