package Equipo7_Bueno_Diaz_Tovar.data;

import Equipo7_Bueno_Diaz_Tovar.data.interfaces.PriorityQueue;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class MaxHeap<T extends Comparable<T>> implements PriorityQueue<T>, Serializable {

    public T[] heap;
    int size;

    public MaxHeap(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException();
        }
        this.heap = (T[]) new Comparable[initialCapacity + 1];
        this.size = 0;
    }

    public MaxHeap() {
        this(10);
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public T getMax() {
        return this.heap[1];
    }

    @Override
    public void add(T x) {
        if (this.size == this.heap.length - 1) {
            T[] old = this.heap;
            this.heap = (T[]) new Comparable[2 * (this.size + 1)];
            System.arraycopy(old, 0, this.heap, 0, this.size + 1);
        }
        this.heap[++this.size] = x;
        siftUp(indexOf(x));
    }

    @Override
    public T removeMax() {
        if (this.size == 0) {
            return null;
        }
        T max = this.heap[1];
        this.heap[1] = this.heap[this.size--];
        siftDown(1);
        return max;
    }

    public int indexOf(T element) {
        return java.util.Arrays.asList(this.heap).indexOf(element);
    }

    public void siftUp(int i) {
        if (i > 1) {
            while (i > 1 && this.heap[i / 2].compareTo(this.heap[i]) < 0) {
                T temp = this.heap[i / 2];
                this.heap[i / 2] = this.heap[i];
                this.heap[i] = temp;
                i /= 2;
            }
        }
    }

    private void siftDown(int i) {
        for (int childIndex = 2 * i; childIndex <= this.size; childIndex *= 2) {
            if (childIndex < this.size && this.heap[childIndex].compareTo(this.heap[childIndex + 1]) < 0) {
                childIndex++;
            }
            if (this.heap[i].compareTo(this.heap[childIndex]) < 0) {
                T temp = this.heap[i];
                this.heap[i] = this.heap[childIndex];
                this.heap[childIndex] = temp;
                i = childIndex;
            }
        }
    }

    public void siftUp(T element) {
        siftUp(indexOf(element));
    }

    public void siftDown(T element) {
        siftDown(indexOf(element));
    }

    public T remove(T element) {
        siftUp(indexOf(element));
        return removeMax();
    }

    public T[] getHeap() {
        return heap;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append('[');
        if (this.size > 0) {
            s.append("(").append(this.heap[1]).append(")");
            for (int i = 2; i <= this.size; i++) {
                s.append(", ").append("(").append(Objects.toString(this.heap[i])).append(")");
            }
        }
        s.append("]");
        return new String(s);
    }
}
