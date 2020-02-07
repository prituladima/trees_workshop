package com.prituladima.trees.entities;

public class Node<T> {

    private T to;
    private Integer cost;

    public Node(T to, Integer cost) {
        this.to = to;
        this.cost = cost;
    }

    public T getTo() {
        return to;
    }

    public Integer getCost() {
        return cost;
    }
}
