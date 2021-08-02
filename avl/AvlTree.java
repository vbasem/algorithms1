// package avl;

// import java.util.Deque;
// import java.util.LinkedList;

// public class AvlTree {
//     private Node root;

//     public static void main(String[] args) {
//         AvlTree tree = new AvlTree();
//         tree.testAvl();
//     }

//     public void testAvl() {
//         this.add(30);
//         this.add(20);
//         this.add(10);
//         this.printTree();
//     }

//     public void add(int value) {
//         Node node = new Node(value);
//         if (this.root == null) {
//             this.root = node;
//         } else {
//             root = this.add(node, root);
//         }
//     }

    
//     public void printTree() { 
//         Deque<Node> queue = new LinkedList<>();
//         queue.add(this.root);

//         while (queue.size() > 0) {
//             Node current = queue.removeFirst();
//             System.out.print( current.value+ ",");
//             current.bsf(queue);
//         }
//         System.out.println("\n------");

//     }

// }

// class Node {
//     int value;
//     Node left;
//     Node right;
//     int bf = 0;;

//     public Node(int value) {
//         this.value = value;
//     }

//     public void bsf(Deque queue) {
//         if (left != null) {
//             queue.add(this.left);

//         }
//         if (right !=  null) {
//             queue.add(this.right);
//         }
//     }

//     public Node add(Node node, Node parent) {

//         // add left or right
//         // add balance shift of child to own balance
//         // if balance > 1 or < 1
//         // if (tell child to prepare for LL or RR by fixing LR or RL turns)
//         // fo L or R turn
//         // replace parent right or left with new child
//         // update node and child balances
//         // perform RR or LL turns
//         // change self to child L or R (-2 or +2)
//         // change child to be child of grandpa
//         // update childs weight with own balance
//         // return balance shift
//         if (node.value > this.value) {
//             this.addRightChild(node, parent);

//         } else {
//             this.addLeftChild(node, parent);
//         }
//         Node newParent = rebalanceIfNeeded(parent);
//         updateBalance();

//         return newParent;

//     }

//     protected Node rebalanceIfNeeded(Node parent) {
//         if (this.bf > 1) {
//             if (this.right.bf < 0) {
//                 this.right.rightTurn(this);
//             }

//             return leftTurn(parent);
//         } else if (this.bf < 1) {
//             if (this.left.bf > 0) {
//                 this.left.leftTurn(this);
//             }

//             return rightTurn(parent);
//         }

//         return parent;
//     }

//     protected Node rightTurn(Node parent) {
//         Node newParent = this.left;
//         if (parent.value != this.value) {
//             parent.right = newParent;
//         }
//         this.left = null;
//         newParent.right = this;
//         this.updateBalance();
//         newParent.updateBalance();
//         return newParent;
//     }

//     protected Node leftTurn(Node parent) {
//         Node newParent = this.right;
//         if (parent.value != this.value) {
//             parent.left = newParent;
//         }
//         this.right = null;
//         newParent.left = this;
//         this.updateBalance();
//         newParent.updateBalance();

//         return newParent;

//     }

// protected void updateBalance() { 
// 	this.bf = (this.left == null ? 0 : this.left.bf) + (this.right == null ? 0 : this.right.bf);
// }

//     protected void addLeftChild(Node node, Node parent) {
//         if (this.left == null) {
//             this.left = node;
//         } else {
//             this.left.add(node, parent);
//         }

//     }

//     protected void addRightChild(Node node, Node parent) {
//         if (this.right == null) {
//             this.right = node;
//         } else {
//             this.right.add(node, parent);
//         }

//     }
// }
