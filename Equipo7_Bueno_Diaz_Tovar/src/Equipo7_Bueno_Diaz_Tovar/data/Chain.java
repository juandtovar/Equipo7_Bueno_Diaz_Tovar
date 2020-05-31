package Equipo7_Bueno_Diaz_Tovar.data;

public class Chain<T> implements LinearList<T> {

    private ChainNode<T> head;
    private ChainNode<T> tail;
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
            ChainNode<T> newHead = new ChainNode<>(element, this.head);
            this.head = newHead;
            if (this.size == 0) {
                this.tail = this.head;
            }
        } else {
            if (i == this.size) {
                this.tail.setNext(new ChainNode<>(element));
                this.tail = this.tail.getNext();
                return;
            }
            ChainNode<T> temp = this.head;
            for (int j = 0; j < i - 1; j++) {
                temp = temp.getNext();
            }
            ChainNode node = new ChainNode<>(element, temp.getNext());
            node.setNext(temp.getNext());
            temp.setNext(node);
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

    public ChainNode<T> getTail() {
        return tail;
    }

    public void setTail(ChainNode<T> tail) {
        this.tail = tail;
    }

}
