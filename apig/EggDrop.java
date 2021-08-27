package apig;

public class EggDrop {
    public static int FLOORS = 100;

    public static void main(String[] args) {
        EggDrop solver = new EggDrop();

        assert solver.breakEggs(16, 2) == 0: "got %S".formatted(solver.breakEggs(16, 2) );
    }

    public int breakEggs(int target, int eggs) {
        return breakIt(target, eggs, 0, FLOORS);
    }

    private int breakIt(int target, int eggs, int floorsStart, int floorsEnd) {

        int mid = (floorsEnd + floorsStart) / 2;
        if (mid == target) {
            return eggs;
        }
        if (mid > target) {
            return breakIt(target, eggs - 1, floorsStart, mid);
        } else {
            return breakIt(target, eggs, mid + 1, floorsEnd);
        }
    }
}
