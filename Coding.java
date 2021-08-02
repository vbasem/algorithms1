
public class Coding {
    public static void main(String[] args) {
        System.out.println("hellp");
        CodingTest test = new CodingTest();
        System.out.println("test results: " + test.testNode());
    }
}

class Node {
    private int num = Integer.MIN_VALUE;
    private Node before;
    private Node after;

    public Node(int nodeValue) {
        this.num = nodeValue;
    }

    public Node addBefore(Node beforeNode) {
        this.before = beforeNode;
        beforeNode.after = this;
        
        return this;
    }

    public Node addAfter(Node afterNode) {
        this.after = afterNode;
        afterNode.addBefore(this);
        
        return this;
    }

    public Node getAfterNode() {
        return this.after;
    }

    public Node getBeforeNode() {
        return this.before;
    }

    public int getValue() {
        return this.num;
    }

}
