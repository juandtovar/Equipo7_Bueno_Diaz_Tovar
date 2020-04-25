/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoestructuras;
import java.util.*;

public class Chain <T> implements LinearList<T>{
    ChainNode <T> head;
    ChainNode <T> tail;

    public Chain() {
        this.head = null;
        this.tail = null;
    }
    
    @Override
    public boolean isEmpty() {
        return head==null;
    }
    @Override
    public void PushBack(T element) {
        ChainNode <T> newNode=new ChainNode<>(element);
        if(this.isEmpty()){
            this.head= newNode;
            this.tail=newNode;
    }else{
            this.tail.next=newNode;
            tail=newNode;
        }
}
}
