package Equipo7_Bueno_Diaz_Tovar.data.interfaces;

public interface HashTable<K> {

    boolean isEmpty();
    
    int size();
    
    int find(K key);
    
    void put(K key);
    
    K get(K key);
    
    K get(int pos);
    
    void remove(K key);
    
    void rehash();
    
    int probe(int i);
    
}

