package problem;

import java.util.Arrays;

public class MaxNumberVisiblePoints {

    public static void main(String[] args) {
        MaxNumberVisiblePoints solver = new MaxNumberVisiblePoints();
        solver.findMaxSortWindow(new int[]{1,1}, new int[][]  {{2,1},{2,2},{3,3}}, 90);
        assert solver.findMax(new int[]{1,1}, new int[][]  {{2,1},{2,2},{3,3}}, 90) == solver.findMaxSortWindow(new int[]{1,1}, new int[][]  {{2,1},{2,2},{3,3}}, 90);
        assert solver.findMax(new int[]{1,1}, new int[][]  {{2,1},{2,2},{3,4}, {1,1}}, 90) == solver.findMaxSortWindow(new int[]{1,1}, new int[][]  {{2,1},{2,2},{3,4}, {1,1}}, 90) ;
        assert solver.findMax(new int[]{1,1}, new int[][]  {{1,0},{2,1}}, 13) == solver.findMaxSortWindow(new int[]{1,1}, new int[][]  {{1,0},{2,1}}, 13);
    }

    public int findMaxSortWindow(int[] pos, int[][] points, int field)  {
        double[] angles = getAngles(pos, points);
        Arrays.sort(angles);
        int max = 0 ;
        int count = 0;
        int j = 0;
        for (int i = 0; i < angles.length; i++) {
            while (angles[i]- angles[j] > field && j < i)  {
                j++;
                count--;
            }
            if (angles[i]- angles[j] <= field)  {
                count++;
            }

            if (count > max)  {
                max = count;
            }
        }

        return max;
    }
    public int findMax(int[] pos, int[][] points, int field) {

        double[] angles = getAngles(pos, points);


        int leftField = field / 2;
        int rightField = 360 - leftField;
        int max = 0;
        for (int rotation = 0; rotation < 360; rotation++) {
            int count = 0;
            leftField = adjust(leftField, rotation);
            rightField = adjust(rightField, rotation);
            for (int i = 0; i < angles.length; i++) {
                if (angles[i] == 0) {
                    count++;
                } else if (isInBetween(angles[i], leftField, rightField)) {
                    count++;
                }
                if (count > max) {
                    max = count;
                }
            }
        }
        System.out.println(max);
        return max;
    }

    private double[] getAngles(int[] pos, int[][] points) {
        double[] angles = new double[points.length];

        for (int i = 0; i < angles.length; i++) {
            angles[i] = getAngle(pos, points[i]);
        }
        return angles;
    }


    private boolean isInBetween(double angle, int leftField, int rightField) {
        if (leftField < rightField) {
            return angle <= leftField || angle >= rightField;
        } else {
            return angle <= leftField && angle >= rightField;
        }
    }

    private int adjust(int current, int rotation) {
        int temp = current + rotation;
        if (temp > 359) {
            return temp - 360;
        }
        return temp;
    }

    private double getAngle(int[] p1, int[] p2) {
        double determinant = (p1[0] * p2[1]) - (p1[1] * p2[0]);
        double dotProduct = (p1[0] * p2[0]) + (p1[1] * p2[1]);
//        System.out.println(Math.atan2(determinant, dotProduct));
//        System.out.println(Math.atan2(determinant, dotProduct) * 180 / Math.PI);
        double angle = Math.toDegrees(Math.atan2(determinant, dotProduct));
//        double angle = Math.atan2(determinant, dotProduct) * 180 / Math.PI;
//        if (angle < 0) {
//            return 360 + angle;
//        } else {
//            return angle;
//        }
        return angle + 360;
    }
}
