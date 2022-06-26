package com.dl630.casus;

public interface AbstractFactory<T> {
    T create(String type);
}
