package Equipo7_Bueno_Diaz_Tovar.data;

public interface LinearList<T> {

    boolean isEmpty();

    int size();

    int indexOf(T element);

    T remove(int i);

    void add(T element, int i);

    T find(int i);
    
    boolean contains(T element);

}
