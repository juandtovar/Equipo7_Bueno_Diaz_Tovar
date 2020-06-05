package Equipo7_Bueno_Diaz_Tovar.data;

public class Chain<T> implements LinearList<T> {

    private ChainNode<T> head;
    private ChainNode<T> tail;
    private int size;

    public Chain() {
        this.head = this.tail = null;
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
            if (this.size == 0) {
                this.tail = this.head;
            }
        } else {
            ChainNode<T> temp = this.head;
            for (int j = 0; j < i - 1; j++) {
                temp = temp.getNext();
            }
            ChainNode<T> node = new ChainNode<T>(element, temp.getNext());
            temp.setNext(node);
            if (i == this.size) {
                this.setTail(node);
            }
        }
        size++;
    }

    public void add(T element) {
        add(element, this.getSize());
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
    public T remove(int i) {
        T removedElement;
        if (i == 0) {
            removedElement = this.head.getElement();
            this.head = this.head.getNext();
        } else {
            ChainNode<T> temp = this.head;
            for (int j = 0; j < i - 1; j++) {
                temp = temp.getNext();
            }
            removedElement = temp.getNext().getElement();
            temp.setNext(temp.getNext().getNext());
        }
        if(i == this.size) {
            this.setTail(this.tail.getNext());
        }
        setSize(this.size - 1);
        return removedElement;
    }

    @Override
    public T find(T element) {
        ChainNode<T> currentNode = this.head;
        while (currentNode != null && !currentNode.getElement().equals(element)) {
            currentNode = currentNode.getNext();
        }
        return currentNode.getElement();
    }

    @Override
    public T find(int i) {
        if (i >= this.size || i < 0) {
            return null;
        } else {
            ChainNode<T> currentNode = this.head;
            for(int j = 0; j < i; j++) {
                currentNode = currentNode.getNext();
            }
            return currentNode.getElement();
        }
    }

    public ChainNode<T> getHead() {
        return head;
    }

    public void setHead(ChainNode<T> head) {
        this.head = head;
    }

    public int getSize() {
        return this.size;
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
