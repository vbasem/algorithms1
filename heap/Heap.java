package heap;

import java.util.Arrays;

public class Heap {

    public static void main(String[] args) {
        Heap heap = new Heap(20);

        heap.add(20);
        heap.add(30);
        heap.add(40);
        heap.add(50);
        heap.add(60);
        heap.add(10);
        heap.printTree();
        heap.delete();
        heap.delete();
        heap.delete();
        heap.delete();
        heap.delete();
        heap.delete();
        heap.printTree();
    }

    int[] data;
    int size = 0;

    public Heap(int size) {
        this.data = new int[size];
    }

    public void add(int value) {

        // empty -> add
        this.data[size] = value;
        // add to last place in array
        replaceUpward(size);

        // replace upward

        size++;
    }

    public void replaceUpward(int index) {

        // find parent index using floor (n-1)/2
        // check current value with parent,
        // if larger than parent, put parent in slot, remember parent slot, and repeat
        // until root or smaller
        // put the value in the remembered index

        int newNodeValue = data[index];
        int replacementIndex = index;
        int parentIndex = getParentIndex(replacementIndex);

        while (replacementIndex > 0 && newNodeValue > data[parentIndex]) {
            data[replacementIndex] = data[parentIndex];
            replacementIndex = parentIndex;
            parentIndex = getParentIndex(replacementIndex);
        }

        data[replacementIndex] = newNodeValue;

    }

    public int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    public int delete() {
        return delete(0);
    }

    public int delete(int index) {
        // size = 1
        // replace index with last index value (can store this index value in that
        // spot);
        // either replace downward or upward
        // get bigger of the 2 children
        // if value < of the bigger one, swap downward (upto size - 1)
        // if value > parent -> replaceupward

        // size --

        if (size == 0) {
            return 0;
        } else if (size == 1) {
            return data[--size];
        } else {
            int deletedValue = data[index];
            data[index] = data[size - 1];
            data[--size] = deletedValue;
            heapify(index);
            return deletedValue;
        }

    }

    public void heapify(int index) {
        int childIndex = getLargestChildIndex(index);
        if (childIndex != -1) {
            if (data[index] < data[childIndex]) {
                replaceDownward(index, childIndex);
            }
        } else if (index > 0 && data[index] > data[getParentIndex(index)]) {
            replaceUpward(index);
        }
    }

    public void replaceDownward(int index, int childIndex) {

        if (childIndex >= size) {
            return;
        } else {
            int childValue = data[childIndex];
            data[childIndex] = data[index];
            data[index] = childValue;

            int newChildIndex = getLargestChildIndex(childIndex);
            if (newChildIndex != -1) {
                if (data[childIndex] < data[newChildIndex]) {
                    replaceDownward(childIndex, newChildIndex);
                }
            }
        }
    }

    public int getLargestChildIndex(int index) {
        int leftChild = (index * 2) + 1;
        int rightChild = (index * 2) + 2;

        if (leftChild >= size) {
            return -1;
        } else {
            if (rightChild < size) {
                return data[leftChild] < data[rightChild] ? rightChild : leftChild;
            } else {
                return leftChild;
            }
        }
    }

    public void printTree() {

        System.out.println(Arrays.toString(this.data));
    }

}
