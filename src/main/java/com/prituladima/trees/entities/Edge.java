package com.prituladima.trees.entities;

import java.util.Objects;

public class Edge<T> {
    private T from;
    private T to;
    private int cost;

    public Edge(T from, T to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public T getFrom() {
        return from;
    }

    public T getTo() {
        return to;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return cost == edge.cost &&
                Objects.equals(from, edge.from) &&
                Objects.equals(to, edge.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, cost);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", cost=" + cost +
                '}';
    }
}
