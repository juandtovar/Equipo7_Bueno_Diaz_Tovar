package Equipo7_Bueno_Diaz_Tovar.data;

public class Chain<T> implements LinearList<T> {

    private ChainNode<T> head;
    private int size;

    public Chain() {
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void add(T element, int i) {
        if (i == 0) {
            this.head = new ChainNode<>(element, this.head);
        } else {
            ChainNode<T> temp = this.head;
            for (int j = 0; j < i - 1; j++) {
                temp = temp.getNext();
            }
            temp.setNext(new ChainNode<>(element, temp.getNext()));
        }
        size++;
    }

    @Override
    public int indexOf(T element) {
        ChainNode<T> currentNode = this.head;
        int i = 0;
        while (currentNode != null && !currentNode.getElement().equals(element)) {
            currentNode = currentNode.getNext();
            i++;
        }
        if (currentNode == null) {
            return -1;
        } else {
            return i;
        }
    }

    @Override
    public void remove(int i) {
        if (i == 0) {
            this.head = this.head.getNext();
        } else {
            ChainNode<T> temp = this.head;
            for (int j = 0; j < i - 1; j++) {
                temp = temp.getNext();
            }
            T removedElement = temp.getNext().getElement();
            temp.setNext(temp.getNext().getNext());
        }
        this.size--;
    }

    public ChainNode<T> getHead() {
        return head;
    }

    public void setHead(ChainNode<T> head) {
        this.head = head;
    }
    
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
