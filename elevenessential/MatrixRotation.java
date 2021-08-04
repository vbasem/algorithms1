package elevenessential;

import java.util.Arrays;

public class MatrixRotation {


    // 1,2,3  ->  7,4,1
    // 4,5,6  ->  8,5,2
    // 7,8,9  ->  9,6,3
    //

    public static void main(String[] args) {
        MatrixRotation solver = new MatrixRotation();

        int[][] input =  {{1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12},
        {13, 14, 15, 16}};

        
        for (int[] item: input)  {
            System.out.println(Arrays.toString(item));
        }


        // int[][] result = solver.rotateCopy(input, input.length);
        solver.rotateInPlace(input, input.length);
        System.out.println("");
        for (int[] item: input)  {
            System.out.println(Arrays.toString(item));
        }

        
    }

    public int[][] rotateCopy(int[][] input, int size) { 
        int[][] result = new int[size][size];

        for (int i = 0 ; i < size; i++) { 
            for (int k = 0 ; k < size; k++)  {
                result[k][size -1 - i] =input[i][k];
            }
        }

        return result;
    }

    public int[][] rotateInPlace(int[][] input, int size) {
        for (int i = 0 ; i < size /2; i++) { 
            for (int k = i ; k < size - 1 - i; k++)  {
                int row = k ;
                int col = size -1 - i;
                while (row != i || col != k) {
                    int temp = input[row][col];
                    input[row][col] = input[i][k];
                    input[i][k] = temp;
                    int tempCol = col;
                    col = size - 1 - row;
                    row = tempCol;  
                }
            }
        }

        return input;
     }
    
}
