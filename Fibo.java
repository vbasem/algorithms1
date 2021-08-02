import javax.swing.Painter;

public class Fibo {
 
    public static void main(String[] args) {
        int result = new Fibo().solve(8);
        System.out.println(result);
    }

    public int solve(int num)  {
        if (num == 0) {
            return 0;
        
        }

        if  (num == 1) {
            return 1;
        }

        return solve(num -1) + solve (num -2);


    }

}