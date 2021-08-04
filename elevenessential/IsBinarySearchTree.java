package elevenessential;


import java.util.Deque;
import java.util.LinkedList;

public class IsBinarySearchTree {

    public static void main(String[] args) {
        IsBinarySearchTree solver = new IsBinarySearchTree();
        TreeNode root = new TreeNode(0);
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        root.left = one;
        root.right = two;
        one.left = three;
        one.right = four;
        two.left = five;
        two.right = six;

        assert !solver.isBinarySearchTree(root) : "wrong answer";

    }

    public boolean isBinarySearchTree(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode next;
        while ((next = queue.poll()) != null) {
            if (next.left != null) {
                if (next.left.value > next.value) {
                    return false;
                }
                if (next.left.right != null) {
                    if (next.left.right.value > next.value) {
                        return false;
                    }
                }
                queue.add(next.left);
            }
            if (next.right != null) {
                if (next.right.value < next.value) {
                    return false;
                }
                if (next.right.left != null) {
                    if (next.right.left.value < next.value) {
                        return false;
                    }
                }
                queue.add(next.right);
            }
        }

        return true;
    }

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value, TreeNode left, TreeNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public TreeNode(int value) {
            this.value = value;
        }

        public void addLeftChild(int v) {
            this.left = new TreeNode(v, null, null);
        }

        public void addRightChild(int v) {
            this.right = new TreeNode(v, null, null);
        }
    }
}
