package Equipo7_Bueno_Diaz_Tovar.data;

public class Pila {

    int[] pila = new int[50];
    int pos = -1;

    public Pila() {
        for(int i = 0; i < 50; i++) {
            pila[i] = 0;
        }
    }

    public boolean isEmpty() {
        return pos == -1;
    }

    public int top() {
        return this.pila[pos];
    }

    public void push(int x) {
        pila[pos++] = x;
    }

    public int pop() {
        int x = pila[pos];
        pila[pos] = 0;
        pos--;
        return x;
    }

}

