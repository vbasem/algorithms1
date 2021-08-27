package sandbox;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapBox {
    public static void main(String[] args) {

        Map<Integer, Boolean> map = new HashMap<>();
        TreeMap<Integer, Boolean> tree = new TreeMap<>();
        tree.put(3, true);
        tree.put(10, false);
        tree.subMap(2, true, 20, true).forEach((integer, aBoolean) -> System.out.println("""
                %s -> %s""".formatted(integer, aBoolean)));
        tree.merge(1, false, (aBoolean, aBoolean2) -> false);
        System.out.println(tree.get(1));
        tree.merge(1, false, (aBoolean, aBoolean2) -> true);
        System.out.println(tree.get(1));


        System.out.println("abc".charAt(0));
    }
}
