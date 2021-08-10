package igao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SnapshotArray {
    int[] data;
    int size;
    Map<Integer, List<int[]>> snaps;
    int currentSnapId;

    public SnapshotArray(int size) {
        this.data = new int[size];
        size = 0;
        snaps = new HashMap<>();
        currentSnapId = 0;
    }

    public static void main(String[] args) {

        SnapshotArray snapshotArr = new SnapshotArray(3);
        snapshotArr.set(0,5);  // Set array[0] = 5
        snapshotArr.snap();  // Take a snapshot, return snap_id = 0
        snapshotArr.set(0,6);
        assert snapshotArr.get(0,0) == 5;
    }

    public void set(int index, int val) {
        this.data[index] = val;
        this.snaps.computeIfAbsent(currentSnapId, integer -> new ArrayList<>()).add(new int[]{index, val});
    }

    public int snap() {
        this.snaps.put(currentSnapId + 1, new ArrayList<>());
        return currentSnapId++;
    }

    public int get(int index, int snapId) {
        if (currentSnapId == snapId) {
            return data[index];
        }

        int value = 0;
        for (int i = 0; i <= snapId; i++) {
            for (int[] action : this.snaps.get(i)) {
                if (action[0] == index) {
                    value = action[1];
                }
            }
        }

        return value;
    }
}
