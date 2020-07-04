package Equipo7_Bueno_Diaz_Tovar.data;

import Equipo7_Bueno_Diaz_Tovar.data.interfaces.*;
import java.io.Serializable;

public class Set<K> implements HashTable<K>, Serializable {

    private int m;
    private K[] table;
    private boolean[] neverUsed;
    private int size, neverUsedSize, col;

    public Set(int m) {
        this.m = m;
        this.table = (K[]) new Object[m];
        this.neverUsed = new boolean[m];
        for (int i = 0; i < m; i++) {
            this.neverUsed[i] = true;
        }
        this.size = this.neverUsedSize = this.col = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int find(K key) {
        int hash = key.hashCode() % this.m;
        int i = 0;
        int pos = hash;
        do {
            if (this.neverUsed[pos]) {
                return -1;
            } else if (this.table[pos] != null && this.table[pos].equals(key)) {
                return pos;
            }
            i++;
            pos = (hash + probe(i)) % this.m;
        } while (pos != hash);
        return -1;
    }

    @Override
    public void put(K key) {
        int pos = find(key);
        if (pos == -1) {
            if (2 * (this.neverUsedSize + 1) > this.m) {
                rehash();
            }
            int i = 0;
            int hash = key.hashCode() % this.m;
            pos = hash;
            do {
                if (this.neverUsed[pos]) {
                    this.table[pos] = key;
                    this.neverUsed[pos] = false;
                    this.neverUsedSize++;
                    this.size++;
                    return;
                } else if (this.table[pos] == null) {
                    this.table[pos] = key;
                    this.size++;
                    return;
                } else {
                    i++;
                    pos = (hash + probe(i)) % this.m;
                    this.col++;
                }
            } while (pos != hash);
        }
    }

    @Override
    public K get(K key) {
        return key;
    }

    @Override
    public K get(int pos) {
        return this.table[pos];
    }

    @Override
    public void remove(K key) {
        int pos = find(key);
        if (pos != -1) {
            this.table[pos] = null;
            this.size--;
        }
    }

    @Override
    public void rehash() {
        K[] old = table;
        this.m *= 2;
        this.table = (K[]) new Object[m];
        this.neverUsed = new boolean[this.m];
        for (int i = 0; i < m; i++) {
            this.neverUsed[i] = true;
        }
        this.size = this.neverUsedSize = 0;
        for (int i = 0; i < this.m / 2; i++) {
            if (old[i] != null) {
                put(old[i]);
            }
        }
    }

    @Override
    public int probe(int i) {
        return i * i;
    }

    public K[] getTable() {
        return table;
    }

    public void setTable(K[] table) {
        this.table = table;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String fullToString() {
        String s = "";
        for (int i = 0; i < this.table.length; i++) {
            s += i + " " + this.table[i] + "\n";
        }
        return s;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < this.table.length; i++) {
            if (this.table[i] != null) {
                s += i + " " + this.table[i] + "\n";
            }
        }
        return s;
    }

}
