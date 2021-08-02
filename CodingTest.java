
public class CodingTest {
    public boolean testNode() {
        Node baseNode = new Node(1);
        Node afterNode = new Node(2);
        Node beforeNode = new Node(0);

        baseNode.addAfter(afterNode);
        baseNode.addBefore(beforeNode);

        if (baseNode.getValue() != 1) {
            return false;
        }

        if (baseNode.getAfterNode().getValue() != 2) {
            return false;
        }

        
        if (baseNode.getBeforeNode().getValue() != 0) {
            return false;
        }

        return true;
    }
    
}
