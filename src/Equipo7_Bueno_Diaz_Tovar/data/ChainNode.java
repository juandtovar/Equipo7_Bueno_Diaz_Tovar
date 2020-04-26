package Equipo7_Bueno_Diaz_Tovar.data;

public class ChainNode <T>{
    private T element;
    private ChainNode<T> next;

    public ChainNode () {
        this.element = null;
        this.next = null;
    }

    public ChainNode(T element) {
        this.element = element;
        this.next=null;
    }
    
    public ChainNode(T element, ChainNode<T> next) {
        this.element = element;
        this.next=next;
    }   

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public ChainNode<T> getNext() {
        return next;
    }

    public void setNext(ChainNode<T> next) {
        this.next = next;
    }
       
}
