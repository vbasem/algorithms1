package igao;

public class MaxPathSum {

    public static void main(String[] args) {
        MaxPathSum solver = new MaxPathSum();
        Node root = new Node(10);
        root.left = new Node(2);
        root.right = new Node(10);
        root.left.left = new Node(20);
        root.left.right = new Node(1);
        root.right.right = new Node(-25);
        root.right.right.left = new Node(3);
        root.right.right.right = new Node(4);
        System.out.println(solver.getMaxSum(root));
    }
    int globalMax = 0;
    public int getMaxSum(Node node) {
        if (node == null)  {
            return 0;
        }

        int maxLeft = Math.max(node.value, node.value + getMaxSum(node.left));
        int maxRight = Math.max(node.value, getMaxSum(node.right) + node.value);
        System.out.print("node " + node.value);
        System.out.println(" left : " + maxLeft + " - right: " + maxRight);
        if (maxLeft+ maxRight > globalMax)  {
            globalMax = maxLeft + maxRight;
        }
        return Math.max(maxRight, maxLeft);
    }



    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int i) {
            this.value = i;
        }
    }
}
