package Equipo7_Bueno_Diaz_Tovar.data;

import Equipo7_Bueno_Diaz_Tovar.data.interfaces.Queue;

public class LinkedQueue<T> implements Queue<T> {

    protected SingleLinkedListNode<T> front;
    protected SingleLinkedListNode<T> back;

    public LinkedQueue() {
        this.front = this.back = null;
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public T getFrontElement() {
        return isEmpty() ? null : this.front.getElement();
    }

    @Override
    public void put(T theElement) {
        SingleLinkedListNode<T> newNode = new SingleLinkedListNode<>(theElement, null);
        if (this.front == null) {
            this.front = newNode;
        } else {
            this.back.setNext(newNode);
        }
        this.back = newNode;
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            return null;
        }
        T frontElement = this.front.getElement();
        this.front = this.front.getNext();
        if (isEmpty()) {
            back = null;
        }
        return frontElement;
    }
}
