package Equipo7_Bueno_Diaz_Tovar.data;

import java.io.Serializable;

public class SingleLinkedListNode<T> implements Serializable {

    private T element;
    private SingleLinkedListNode<T> next;

    public SingleLinkedListNode() {
        this.element = null;
        this.next = null;
    }

    public SingleLinkedListNode(T element) {
        this.element = element;
        this.next = null;
    }

    public SingleLinkedListNode(T element, SingleLinkedListNode<T> next) {
        this.element = element;
        this.next = next;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public SingleLinkedListNode<T> getNext() {
        return next;
    }

    public void setNext(SingleLinkedListNode<T> next) {
        this.next = next;
    }

}
