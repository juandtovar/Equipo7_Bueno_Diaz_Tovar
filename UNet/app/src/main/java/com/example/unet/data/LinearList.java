package com.example.unet.data;

public interface LinearList<T> {

    boolean isEmpty();

    int size();

    int indexOf(T element);

    void remove(int i);

    void add(T element, int i);

}
