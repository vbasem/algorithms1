package sort;

public class LinkedListSort { 

    public static void main(String[] args){ 
            List list = new List();
            list.insertSorted(5);
            
            list.print();
            list.insertSorted(3);
            
            list.print();
            list.insertSorted(2);
            
            list.print();
            list.insertSorted(7);
            
            list.print();
            list.insertSorted(8);

            list.print();

    }

}

class List {
    Node head;

    public void print()  {
        Node current = this.head;
        while (current != null) {
            System.out.print(current.value);
            System.out.print(" --> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public void insertSorted(int value ) {
        if (this.head == null) {
            this.head = new Node(value);
            return;
        }

        insert(value);
    }

    private void insert(int value) {
        Node insertedNode = new Node(value);
        Node current = head;
        Node previosNode = null;
        while(current != null) {
            if (current.value > value) {
                replace(insertedNode, previosNode, current);
                break;
            } else {
                current = current.next;
            }
        }
    }

    private void replace(Node inserted, Node previous, Node current)  {
        inserted.next = current;
        if (previous == null)  {
            this.head = inserted;
        } else {
            previous.next = inserted;
        }

    }
}


class Node { 
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
    }
}