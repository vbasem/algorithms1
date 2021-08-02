package elevenessential;

public class ArrayRotation {

    public static void main(String[] args) {
        ArrayRotation solver = new ArrayRotation();
        var a = new int[] {1,2,3,4,5,6,7};
        var b = new int[] {4,5,6,7,1,3,3};
        System.out.println("solver.isRotation(a, b) = " + solver.isRotation(a, b));
    }
    public boolean isRotation(int[] array1, int[] array2) { 
        if (array1.length != array2.length)  {
            return false;
        }
    
        if (array1.length == 0 && array2.length == 0) { 
            return true;
        }
        
        int first = array1[0];
    
        int indexOfFirstInSecondArray = -1;
        
        for (int i = 0; i <array2.length; i++) { 
            if (array2[i] == first) {
                indexOfFirstInSecondArray  = i;
            }
        }
    
        if (indexOfFirstInSecondArray == -1) {
            return false;
        }
    
        for (int i =0 ; i < array1.length; i++) { 
            int secondIndex = indexOfFirstInSecondArray + i >= array2.length? (indexOfFirstInSecondArray + i  - array2.length) : indexOfFirstInSecondArray + i ;
    
            if (array1[i] != array2[secondIndex ]) { 
                return false;
            }
        }
    
        return true;
    
    }
    
    
}
