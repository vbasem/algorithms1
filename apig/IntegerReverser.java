package apig;

public class IntegerReverser {
    public static void main(String[] args) {
        IntegerReverser solver = new IntegerReverser();
        assert solver.reverse(123) ==  321;
        assert solver.reverse(3) ==  3;
        assert solver.reverse(12) ==  21;
        assert solver.reverse(0) ==  0;
    }

    public int reverse(int in)  {
        int num = in;
        int result =0 ;
        do {
            result = result * 10 + (num % 10);
        } while((num  = num / 10) > 0);

        return result;
    }
}
