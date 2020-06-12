package Equipo7_Bueno_Diaz_Tovar.data.interfaces;

public interface PriorityQueue<T extends Comparable<T>> {

    boolean isEmpty();

    void add(T x);

    T getMax();

    T removeMax();
    
    
}
