package igao;

public class DominoFlip {

    public static void main(String[] args) {
        DominoFlip solver = new DominoFlip();

        assert solver.solve(new int[]{2, 2, 2, 4, 2, 2}, new int[]{5, 2, 6, 2, 3, 2}) == 1;
        assert solver.solve(new int[]{2, 1, 2, 4, 2, 2}, new int[]{5, 2, 6, 2, 3, 2}) == 2;
        assert solver.solve(new int[]{3, 5, 1, 2, 3}, new int[]{3, 6, 3, 3, 4}) == -1;

    }

    private int solve(int[] a, int[] b) {
        int length = a.length;
        int[] ta = new int[7];
        int[] tb = new int[7];
        int candidateA = 0;
        int[] diff = new int[7];
        for (int i = 0; i < length; i++) {
            ta[a[i]]++;
            tb[b[i]]++;

            if (a[i] == b[i]) {
                diff[a[i]]++;
            }
            if (ta[a[i]] + ta[b[i]] - diff[a[i]] >= a.length) {
                candidateA = a[i];
            }
        }

        if (candidateA == 0) {
            return -1;
        }
        int ca = 0;
        for (int i = 1; i < 7; i++) {
            if ((ta[i] + tb[i]) - diff[i] > ca) {
                ca = Math.min(ta[i], tb[i]) - diff[i];
            }
        }
        return ca;
    }


}
