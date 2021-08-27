package leo;

public class ReverseInteger {

    public static void main(String[] args) {
        ReverseInteger solver = new ReverseInteger();


        assert solver.reverse(981) == 189;
        assert solver.reverse(1) == 1;
        assert solver.reverse(0) == 0;
        assert solver.reverse(5423123) == 3213245;
        assert solver.reverse(1111111) == 1111111;
        assert solver.reverse(-321) == -123;
    }

    public int reverse(int number) {

        if (Math.abs(number) < 10) {
            return number;
        }

        int tens = 1;
        int reverse = 0;

        while (Math.abs(number / Math.pow(10, tens + 1)) > 1) {
            tens++;
        }

        for (int i = 0; i <= tens; i++) {
            int n = (number / (int) (Math.pow(10, i))) % 10;
            reverse += n * (int) Math.pow(10, tens - i);
        }

        return reverse;
    }
}
