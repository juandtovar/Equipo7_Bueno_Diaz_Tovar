package com.example.unet.data;

public class Pila {

    private final int size = 50;
    private int[] pila = new int[this.size];
    private int pos = -1;

    public Pila() {
        for(int i = 0; i < this.size; i++) {
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

