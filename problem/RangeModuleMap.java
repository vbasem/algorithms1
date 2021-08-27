package problem;

import java.util.Map;
import java.util.TreeMap;

public class RangeModuleMap {
    TreeMap<Integer, Range> data;


    public RangeModuleMap() {
        this.data = new TreeMap<>();
        data.put(0, Range.END);
    }

    public static void main(String[] args) {
        RangeModuleMap rangeModule = new RangeModuleMap();
        rangeModule.addRange(10, 20);
        rangeModule.removeRange(14, 16);
        assert rangeModule.queryRange(10, 14);
        assert !rangeModule.queryRange(13, 15);
        assert rangeModule.queryRange(16, 17);
    }

    public boolean queryRange(int left, int right) {
        Map.Entry<Integer, Range> from = data.lowerEntry(right);

        return from != null && from.getKey() <= left && from.getValue() == Range.START;
    }

    public void addRange(int left, int right) {
        Map.Entry<Integer, Range> from = data.lowerEntry(left);
        Map.Entry<Integer, Range> to = data.floorEntry(right);

        data.subMap(left, true, right, true).clear();
        if (from.getValue() == Range.END) {
            data.put(left, Range.START);
        }
        if (to.getValue() == Range.END) {
            data.put(right, Range.END);
        }
    }

    public void removeRange(int left, int right) {
        Map.Entry<Integer, Range> from = data.lowerEntry(left);
        Map.Entry<Integer, Range> to = data.floorEntry(right);

        data.subMap(left, true, right, true).clear();
        if (from.getValue() == Range.START) {
            data.put(left, Range.END);
        }
        if (to.getValue() == Range.START) {
            data.put(right, Range.START);
        }
    }

    enum Range {
        START,
        END
    }
}


