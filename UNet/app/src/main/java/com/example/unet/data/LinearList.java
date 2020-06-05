package com.example.unet.data;

public interface LinearList<T> {

    boolean isEmpty();

    int size();

    int indexOf(T element);

    T remove(int i);

    void add(T element, int i);

    T find(T element);

    T find(int i);

}
