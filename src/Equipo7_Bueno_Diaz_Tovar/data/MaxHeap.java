package Equipo7_Bueno_Diaz_Tovar.data;

import Equipo7_Bueno_Diaz_Tovar.data.interfaces.PriorityQueue;
import java.util.Objects;

public class MaxHeap<T extends Comparable<T>> implements PriorityQueue<T> {

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
    public void add(T x) {
        System.out.println(this.toString());
        if (this.size == this.heap.length - 1) {
            T[] old = this.heap;
            this.heap = (T[]) new Comparable[2 * (this.size + 1)];
            System.arraycopy(old, 0, this.heap, 0, this.size + 1);
        }
        int temp = ++this.size;
        while (temp != 1 && this.heap[temp / 2].compareTo(x) < 0) {
            System.out.print(temp + " ");
            System.out.println(this);
            this.heap[temp] = this.heap[temp / 2];
            temp /= 2;
        }
        this.heap[temp] = x;
        System.out.println(this.toString());
    }

    @Override
    public T getMax() {
        return this.heap[1];
    }

    @Override
    public T removeMax() {
        if (this.size == 0) {
            return null;
        }
        T max = this.heap[1];
        T last = this.heap[this.size--];
        int temp = 1;
        int child = 2;
        while (child <= this.size) {
            if (child < this.size && this.heap[child].compareTo(this.heap[child + 1]) < 0) {
                System.out.println("++");
                child++;
            }
            if (last.compareTo(this.heap[child]) >= 0) {
                System.out.println("E");
                break;
            }
            this.heap[temp] = this.heap[child];
            temp = child;
            child *= 2;
        }
        this.heap[temp] = last;
        return max;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append('[');
        if (this.size > 0) {
            s.append(this.heap[1]);
            for (int i = 2; i <= size; i++) {
                s.append(", ").append(Objects.toString(this.heap[i]));
            }
        }
        s.append("]");
        return new String(s);
    }
}
