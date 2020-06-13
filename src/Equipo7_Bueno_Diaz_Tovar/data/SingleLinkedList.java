package Equipo7_Bueno_Diaz_Tovar.data;

import Equipo7_Bueno_Diaz_Tovar.data.interfaces.LinearList;
import java.io.Serializable;

public class SingleLinkedList<T> implements LinearList<T>, Serializable {

    private SingleLinkedListNode<T> head;
    private SingleLinkedListNode<T> tail;
    private int size;

    public SingleLinkedList() {
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
            SingleLinkedListNode<T> currentNode = this.head;
            for (int j = 0; j < i; j++) {
                currentNode = currentNode.getNext();
            }
            return currentNode.getElement();
        }
    }

    @Override
    public int indexOf(T element) {
        SingleLinkedListNode<T> currentNode = this.head;
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
            SingleLinkedListNode<T> temp = this.head;
            for (int j = 0; j < i - 1; j++) {
                temp = temp.getNext();
            }
            if(i == this.size() - 1) {
                this.tail = temp;
            }
            removedElement = temp.getNext().getElement();
            temp.setNext(temp.getNext().getNext());
        }
        this.setSize(this.size - 1);
        return removedElement;
    }

    @Override
    public void add(T element, int i) {
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (i == this.size()) {
            if (this.size() == 0) {
                this.head = new SingleLinkedListNode<>(element, this.head);
                this.tail = this.head;
            } else {
                SingleLinkedListNode<T> node = new SingleLinkedListNode<>(element, null);
                this.tail.setNext(node);
                this.tail = this.tail.getNext();
            }
        } else if (i == 0) {
            this.head = new SingleLinkedListNode<>(element, this.head);
        } else {
            SingleLinkedListNode<T> temp = this.head;
            for (int j = 0; j < i - 1; j++) {
                temp = temp.getNext();
            }
            SingleLinkedListNode<T> node = new SingleLinkedListNode<>(element, temp.getNext());
            temp.setNext(node);
        }
        this.size++;
    }

    @Override
    public void add(T element) {
        add(element, this.size());
    }

    public SingleLinkedListNode<T> getHead() {
        return head;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        SingleLinkedListNode<T> currentNode = this.head;
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
