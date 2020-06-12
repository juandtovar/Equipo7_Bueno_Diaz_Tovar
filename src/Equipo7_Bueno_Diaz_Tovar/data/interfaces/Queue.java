package Equipo7_Bueno_Diaz_Tovar.data.interfaces;

public interface Queue<T> {

    boolean isEmpty();

    T getFrontElement();

    void put(T theObject);

    T remove();

}
