package elevenessential;

public class NthFromNLinkedList {

    public static void main(String[] args) {
        NthFromNLinkedList solver = new NthFromNLinkedList();
        Node current = new Node(1, null);
        for (int i = 2; i < 8; i++) {
            current = new Node(i, current);
        }
        Node head = current;
        // head = 7 -> 6 -> 5 -> 4 -> 3 -> 2 -> 1 -> (null)

        Node current2 = new Node(4, null);
        for (int i = 3; i > 0; i--) {
            current2 = new Node(i, current2);
        }
        Node head2 = current2;
        // head2 = 1 -> 2 -> 3 -> 4 -> (null)

        assert solver.nthFromLast(head, 1).value == 1: "got " + solver.nthFromLast(head, 1);
        assert solver.nthFromLast(head, 5).value == 5;
        assert solver.nthFromLast(head2, 2).value == 3;
        assert solver.nthFromLast(head2, 4).value == 1;
        assert solver.nthFromLast(head2, 5) == null;
        assert solver.nthFromLast(null, 1) == null;

    }

    // 4,1,2,3,3-> null -> 3 -> 2, 1-> 3, 2-> 3, 4 -> 1, 10th -> null
    public Node nthFromLast(Node head, int n) {

        if (head == null) {
            return null;
        }
        Node[] cache = new Node[n];

        int counter = 0;
        Node e = head;

        while (e != null) {
            cache[counter] = e;
            e = e.child;
            if (++counter == n)  {
                counter = 0;
            }
        }

        return cache[counter];
    }

    static class Node {
        public Node(int i, Node child) {
            this.child = child;
            this.value = i;
        }

        int value;
        Node child;
    }

}
