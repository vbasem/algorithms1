package bucket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BucketSort {
    

    public static void main(String args[]) { 
        int[] data = {54, 46, 83, 66, 95, 92, 43};
        bucketSort(data);

        System.out.println("RESULT-----");
        Arrays.stream(data).forEach(n -> System.out.print(n + ","));
        System.out.println("\n-----");

    }

    public static void bucketSort(int[] data) {
        List<Integer>[] buckets = new ArrayList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        // go through each, hash value with % 10 put into bucket, 
        // insert sort each bucket
        // go through buckets and insert 1 by 1
        for (int i = 0; i< data.length; i++) {
            buckets[data[i] / 10].add(data[i]);
        }
        System.out.println("done1");
        int dataIndex=0;
        for (int i = 0; i < buckets.length; i++) {
            insertSort(buckets[i]);
            for (int k = 0; k <buckets[i].size(); k++) {
                data[dataIndex++] = buckets[i].get(k);
            }
        }
            System.out.println("done");
        
    }

    private static void insertSort(List<Integer> data) {
        
        for (int i = 1; i < data.size(); i++) {
            int currentValue = data.get(i);
            int currentIndex = i;
            for (int k = i -1; k >= 0; k--) {
                if (data.get(k ) > currentValue) {
                    data.set(currentIndex, data.get(k));
                    data.set(k, currentValue);
                    currentIndex = k;
                }
            }
        }

        data.forEach(n -> System.out.print(n + ","));
        System.out.println("\n-----");


    }
}
