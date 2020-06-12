package Equipo7_Bueno_Diaz_Tovar.data.interfaces;

public interface LinearList<T> {

    boolean isEmpty();

    int size();

    T get(int i);
    
    int indexOf(T element);

    T remove(int i);

    void add(T element, int i);
    
    void add(T element);

}
