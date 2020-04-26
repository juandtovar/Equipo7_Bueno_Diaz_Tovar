package Equipo7_Bueno_Diaz_Tovar.data;

public class Chain<T> implements LinearList<T> {
    
    private ChainNode<T> head;
    private ChainNode<T> tail;
    
    public Chain() {
        this.head = null;
        this.tail = null;
    }
    
    @Override
    public boolean isEmpty() {
        return head == null;
    }
    
    @Override
    public void PushBack(T element) {
        ChainNode<T> newNode = new ChainNode<>(element);
        if (this.isEmpty()) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.setNext(newNode);
            tail = newNode;
        }
    }
    
    public ChainNode<T> getHead() {
        return head;
    }
    
    public void setHead(ChainNode<T> head) {
        this.head = head;
    }
    
    public ChainNode<T> getTail() {
        return tail;
    }
    
    public void setTail(ChainNode<T> tail) {
        this.tail = tail;
    }
    
}
