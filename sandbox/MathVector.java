package sandbox;

public class MathVector {

    public static void main(String[] args) {
        int[] p1 = new int[]{1,0};
        int[] p2 = new int[]{1, 0};

        double dotProduct = (p1[0] * p2[0]) +(p1[1] * p2[1]);
        double determinent =  (p1[0] * p2[1] ) - (p1[1] * p2[0]);
        double lengthP1 = Math.sqrt(p1[0]* p1[0 ]+  p1[1] * p1[1]);
        double lengthP2 = Math.sqrt(p2[0]* p2[0 ]+  p2[1] * p2[1]);
        System.out.println(dotProduct);
        System.out.println(Math.toDegrees(Math.acos(dotProduct/(lengthP1 * lengthP2))));
        System.out.println(Math.toDegrees(Math.atan2(determinent, dotProduct)));
    }
}
