package bst;

import java.util.Deque;
import java.util.LinkedList;

public class BinaryTree {

    public static void main(String[] args) {
        var tree = new BinaryTree();
        tree.add(50);
        tree.add(30);
        tree.add(40);
        tree.add(60);
        tree.add(55);
        tree.add(65);
        tree.add(75);
        tree.add(15);
        tree.add(25);
        tree.add(35);
        tree.add(37);

        System.out.println("---BSF---");
        tree.printBsf();
        System.out.println("\n----Dsf InOrder--");
        tree.printDsfInOrder();
        System.out.println("\n---Dsf PreOrder---");
        tree.printDsfPreOrder();
        System.out.println("\n---Dsf PostOrder---");
        tree.printDsfPostOrder();
        System.out.println("\n--------------");

        System.out.println("Node found : " + tree.find(60));
        System.out.println("Node found : " + tree.find(63));
        System.out.println("Max found : " + tree.findMax());
        System.out.println("Min found : " + tree.findMin());

        System.out.println("Deleted : " + 30);
        tree.delete(30);
        System.out.println("\n----Dsf InOrder--");
        tree.printDsfInOrder();
    }

    Node root;

    public Node add(int value) {
        var node = new Node(value);
        if (root == null) {
            this.root = node;
        } else {
            this.root.add(node);
        }

        return node;
    }

    public void printBsf() {
        if (this.root == null) {
            System.out.println("empty");
        } else {
            Deque<Node> queue = new LinkedList<>();
            queue.add(this.root);
            while (queue.peek() != null) {
                queue.poll().printBsf(queue);
            }
        }
    }

    public void printDsfInOrder() {
        if (this.root == null) {
            System.out.println("empty");
        } else {
            this.root.printInOrder();
        }
    }

    public void printDsfPreOrder() {
        if (this.root == null) {
            System.out.println("empty");
        } else {
            this.root.printDsfPreOrder();
        }
    }

    public void printDsfPostOrder() {
        if (this.root == null) {
            System.out.println("empty");
        } else {
            this.root.printDsfPostOrder();
        }
    }

    public Node find(int value) {
        if (this.root == null) {
            return null;
        } else {
            return this.root.find(value);
        }
    }

    public Node findMax() {
        if (this.root == null) {
            return null;
        } else {
            return this.root.findMax();
        }
    }

    public Node findMin() {
        if (this.root == null) {
            return null;
        } else {
            return this.root.findMin();
        }
    }

    public void delete(int value) {
        root = delete(root, value);
    }

    private Node delete(Node subTree, int value) {
        if (subTree == null) {
            return subTree;
        }

        if (subTree.value == value) {
            if (subTree.left == null) {
                return subTree.right;
            } else if (subTree.right == null) {
                return subTree.left;
            } else {
                Node min = subTree.right.findMin();
                subTree.value = min.value;
                subTree.right =  delete(subTree.right, min.value);
                return subTree;
            }
        } else {
            if (subTree.value < value) {
                subTree.right = delete(subTree.right, value);
                return subTree;
            } else {
                subTree.left = delete(subTree.left, value);
                return subTree;
            }
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public void add(Node node) {
        if (this.value > node.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    public void printBsf(Deque<Node> queue) {
        System.out.print(this.value);
        System.out.print(",");
        if (this.left != null) {
            queue.offer(this.left);
        }
        if (this.right != null) {
            queue.offer(this.right);
        }
    }

    public void printInOrder() {
        if (this.left != null) {
            this.left.printInOrder();
        }
        System.out.print(this.value);
        System.out.print(",");
        if (this.right != null) {
            this.right.printInOrder();
        }
    }

    public void printDsfPreOrder() {
        System.out.print(this.value);
        System.out.print(",");
        if (this.left != null) {
            this.left.printDsfPreOrder();
        }

        if (this.right != null) {
            this.right.printDsfPreOrder();
        }
    }

    public void printDsfPostOrder() {
        if (this.left != null) {
            this.left.printDsfPostOrder();
        }

        if (this.right != null) {
            this.right.printDsfPostOrder();
        }

        System.out.print(this.value);
        System.out.print(",");
    }

    public Node find(int value) {
        if (this.value == value) {
            return this;
        } else if (this.value > value) {
            if (hasLeftChild()) {
                return this.left.find(value);
            }
        } else if (this.value < value) {
            if (hasRightChild()) {
                return this.right.find(value);
            }
        }

        return null;

    }

    public Node findMax() {
        if (this.right != null) {
            return this.right.findMax();
        } else {
            return this;
        }
    }

    public Node findMin() {
        if (this.left != null) {
            return this.left.findMin();
        } else {
            return this;
        }
    }

    // ------

    public boolean hasLeftChild() {
        return this.left != null;
    }

    public boolean hasRightChild() {
        return this.right != null;
    }

    @Override
    public String toString() {
        return this.value + "";
    }
}
