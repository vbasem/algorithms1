package igao;

import java.util.*;

public class SnapshotArrayList {
    Map<Integer, Integer>[] data;
    int size;
    int currentSnapId;

    public SnapshotArrayList(int size) {
        this.data = new Map[size];
        for (int i =0;i < size; i++) {
            this.data[i] = new HashMap<>();
        }
        size = 0;
        currentSnapId = 0;
    }

    public static void main(String[] args) {

        SnapshotArrayList snapshotArr = new SnapshotArrayList(3);
        snapshotArr.set(0,5);  // Set array[0] = 5
        snapshotArr.snap();  // Take a snapshot, return snap_id = 0
        snapshotArr.set(0,6);
        assert snapshotArr.get(0,0) == 5;
    }

    public void set(int index, int val) {
        this.data[index].put(currentSnapId,val);
    }

    public int snap() {
        return currentSnapId++;
    }

    public int get(int index, int snapId) {
        return this.data[index].getOrDefault(snapId ,0);
    }
}
