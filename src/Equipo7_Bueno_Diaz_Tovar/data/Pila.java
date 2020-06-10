package Equipo7_Bueno_Diaz_Tovar.data;

import java.io.Serializable;

public class Pila<T> extends Chain<T> implements Serializable {

    public Pila() {
        super();
    }

    public T peek() {
        if (!isEmpty()) {
            return getTail().getElement();
        } else {
            return null;
        }
    }

    public void push(T element) {
        add(element, 0);
    }

    public T pop() {
        if (!isEmpty()) {
            return remove(0);
        } else {
            return null;
        }
    }

}
