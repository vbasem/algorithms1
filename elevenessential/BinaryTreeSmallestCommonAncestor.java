package elevenessential;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BinaryTreeSmallestCommonAncestor {
    public static void main(String[] args) {
        BinaryTreeSmallestCommonAncestor solver = new BinaryTreeSmallestCommonAncestor();
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);
        TreeNode eight = new TreeNode(8);
        TreeNode nine = new TreeNode(9);
        five.addChildren(three, four);
        three.addChildren(one, eight);
        four.addChildren(nine, two);
        one.addChildren(six, seven);

        System.out.println(" = " + solver.findUsingDsf(five, 3, 6));
    }

    private TreeNode findUsingDsf(TreeNode root, int first, int second) {
        List<TreeNode> pathToFirst = new ArrayList<>();
        dsf(root, first, pathToFirst);
        List<TreeNode> pathToSecond = new ArrayList<>();
        dsf(root, second, pathToSecond);
        int smallerPath = Math.min(pathToFirst.size(), pathToSecond.size());
        if (pathToFirst.isEmpty() || pathToSecond.isEmpty()) {
            return null;
        }
        int i = pathToFirst.size() - smallerPath;
        int j = pathToSecond.size() - smallerPath;
        for (; i < pathToFirst.size(); i++, j++) {
            if (pathToFirst.get(i) == pathToSecond.get(j)) {
                return pathToFirst.get(i);
            }
        }
        return null;
    }

    private List<TreeNode> dsf(TreeNode current, int target, List<TreeNode> path) {
        if (current == null) {
            return path;
        } else if (current.value == target) {
            path.add(current);
            return path;
        } else {
            if (dsf(current.left, target, path).size() > 0) {
                path.add(current);
                return path;
            } else if (dsf(current.right, target, path).size() > 0) {
                path.add(current);
                return path;
            }
        }

        return path;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TreeNode treeNode = (TreeNode) o;
            return value == treeNode.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        public void addLeftChild(TreeNode v) {
            this.left = v;
        }

        public void addRightChild(TreeNode v) {
            this.right = v;
        }

        public void addChildren(TreeNode left, TreeNode right) {
            addLeftChild(left);
            addRightChild(right);
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "value=" + value +
                    '}';
        }
    }
}
