package problem;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class RangeModule {
    public static void main(String[] args) {
        RangeModule rangeModule = new RangeModule();
        rangeModule.addRange(10, 20);
        rangeModule.removeRange(14, 16);
        assert rangeModule.queryRange(10, 14);
        assert !rangeModule.queryRange(13, 15);
        assert rangeModule.queryRange(16, 17);
    }
    TreeMap<Integer, Integer> data;

    public RangeModule() {
        this.data = new TreeMap<>();
    }

    public boolean queryRange(int left, int right) {
        for (Integer key : data.keySet()) {
            if (key <= left && data.get(key) >= right) {
                return true;
            }
        }

        return false;
    }

    public void addRange(int left, int right) {
        List<Integer> chunks = new ArrayList<>();
        for (Integer key : data.keySet()) {

            int currentRight = data.get(key);
            if (key <= left && currentRight >= right) {
                return;
            }
            if (right >= key && right <= currentRight) {
                chunks.add(key);
            } else if (left >= key && left <= currentRight) {
                chunks.add(key);
            }
        }

        mergeChunks(chunks, left, right);
    }

    private void mergeChunks(List<Integer> keys, int left, int right) {
        // chuncks is empty
        int newLeft = left;
        int newRight = right;
        if (!keys.isEmpty()) {
            for (Integer item : keys) {
                newLeft = Math.min(item, newLeft);
                newRight = Math.max(data.remove(item), newRight);
            }
        }
        this.data.put(newLeft, newRight);
    }

    public void removeRange(int left, int right) {
        List<Integer> chunks = new ArrayList<>();
        for (Integer key : data.keySet()) {
            int currentRight = data.get(key);
            if (key <= left && currentRight >= right) {
                chunks.add(key);
                break;
            }
            if (right > key && right <= currentRight) {
                chunks.add(key);
            } else if (left >= key && left <= currentRight) {
                chunks.add(key);
            }
        }
        removeChunks(chunks, left, right);
    }

    private void removeChunks(List<Integer> chunks, int left, int right) {
        if (chunks.isEmpty()) {
            return;
        }
        for (Integer item : chunks) {
            int cLeft = item;
            int cRight = data.remove(item);
            if (cLeft == left && cRight == right) continue;
            if (left > cLeft && right < cRight) {
                int cRight1 = left;
                int cLeft2 = right;
                data.put(cLeft, cRight1);
                data.put(cLeft2, cRight);
            } else if (cLeft < left) {
                cLeft = right;
                data.put(cLeft, cRight);
            } else {
                cRight = left;
                data.put(cLeft, cRight);
            }
        }
    }
}


