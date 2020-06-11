package Equipo7_Bueno_Diaz_Tovar.data;

import java.io.Serializable;

public class Chain<T> implements LinearList<T>, Serializable {

    private ChainNode<T> head;
    private int size;

    public Chain() {
        this.head = null;
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
    public T get(int i) {
        if (i >= this.size || i < 0) {
            return null;
        } else {
            ChainNode<T> currentNode = this.head;
            for (int j = 0; j < i; j++) {
                currentNode = currentNode.getNext();
            }
            return currentNode.getElement();
        }
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
        this.setSize(this.size - 1);
        return removedElement;
    }
    
    @Override
    public T remove(T element) {
        T removedElement;
        int i = indexOf(element);
        if(i == -1){
            return null;
        }
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
        this.setSize(this.size - 1);
        return removedElement;
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
            ChainNode<T> node = new ChainNode<>(element, temp.getNext());
            temp.setNext(node);
        }
        this.size++;
    }

    @Override
    public void add(T element) {
        add(element, this.size());
    }

    public ChainNode<T> getHead() {
        return head;
    }

    public void setHead(ChainNode<T> head) {
        this.head = head;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        ChainNode<T> currentNode = this.head;
        String s = "[";
        int i = 0;
        while (currentNode != null) {
            if (i != this.size - 1) {
                s += currentNode.getElement().toString() + ", ";
            } else {
                s += currentNode.getElement().toString();
            }
            currentNode = currentNode.getNext();
            i++;
        }
        return s + "]";
    }
}
