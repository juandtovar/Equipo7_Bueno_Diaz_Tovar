package Equipo7_Bueno_Diaz_Tovar.data;

public class Pila<T> extends Chain<T> {

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
        add(element);
    }

    public T pop() {
        if (!isEmpty()) {
            return remove(size() - 1);
        } else {
            return null;
        }
    }
}
