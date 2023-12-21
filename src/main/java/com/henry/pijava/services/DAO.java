package com.henry.pijava.services;

import java.util.List;

public interface DAO <T, K> {
    T read(K key);
    List<T> readAll();
    void insert(T e);
    void update(T e, K k);
    T delete(int e);
}
