package Equipo7_Bueno_Diaz_Tovar.data;

import Equipo7_Bueno_Diaz_Tovar.data.interfaces.Stack;
import java.io.Serializable;

public class LinkedStack<T> implements Stack<T>, Serializable {

    private SingleLinkedListNode<T> top;

    public LinkedStack() {

    }

    @Override
    public boolean isEmpty() {
        return this.top == null;
    }

    @Override
    public T peek() {
        if (!isEmpty()) {
            return this.top.getElement();
        } else {
            return null;
        }
    }

    @Override
    public void push(T element) {
        this.top = new SingleLinkedListNode<>(element, this.top);
    }

    @Override
    public T pop() {
        if (!isEmpty()) {
            T removed = this.top.getElement();
            this.top = this.top.getNext();
            return removed;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        String s = "{";
        SingleLinkedListNode<T> temp = this.top;
        while(temp != null) {
            s += temp.getElement().toString();
            if(temp.getNext() != null){
                s += ", ";
            }
            temp = temp.getNext();
        }
        return s + "}";
    }

    
}
