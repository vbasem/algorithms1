package igao;

public class TowerOfHanoi {


    public static void main(String[] args) {
        TowerOfHanoi solver = new TowerOfHanoi();
        solver.solve(3);
    }

    private void solve(int disks) {

        helper(disks, 1, 2, 3);
    }

    private void helper(int disks, int a, int b, int c) {
        if (disks > 0) {
            helper(disks - 1,a,c, b);
            System.out.println("moved " + disks + " from " + a + " to " + c);
            helper(disks - 1,b,a, c);
        }
    }
}
