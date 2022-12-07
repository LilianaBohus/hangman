package org.progmatic.hangman.model;

public abstract class State<T, V> {

    T pool;
    V representativeState;

    public abstract void setPool(T object);

    public abstract void updateState();

    public V getRepresentativeState() {
        return this.representativeState;
    }
}
