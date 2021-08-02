import java.util.Arrays;

public class CountSort {

    public static void main(String[] args)  {
        var data = new int[]{3,6,5,7,1,8,2,9,0,3};
        countSort(data);
        System.out.println(Arrays.toString(data));
    }

    public static void countSort(int[] data)  {
        var counter = new int[data.length];

        for (int item : data)  {
            counter[item]++;
        }

        int dataIndex = 0;
        for (int i=0; i < counter.length; i++)  {
            for (int k =0; k < counter[i]; k++) {
                data[dataIndex++] = i;
            }

        }

    }
    
}
