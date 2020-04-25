/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoestructuras;

/**
 *
 * @author usuario
 */
public class ChainNode <T>{
    T element;
    ChainNode<T> next;

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
    public ChainNode<T> getNext() {
        return next;
    }
    
    
    
    
    

    
    
}
