package com.prituladima.trees.util;

public class TestCase2D {

    public enum QueryType {
        MODIFY, READ
    }

    private QueryType queryType;

    private int x1, x2;
    private int y1, y2;
    private long expectedValue;


    private int index1, index2;
    private long delta;

    public static TestCase2D getRangeSumTestCase(int x1, int x2, int y1, int y2, long expValue){
        return new TestCase2D(x1, x2, y1, y2, expValue);
    }

    public static TestCase2D modifySingleElementTestCase(int index1, int index2, long delta){
        return new TestCase2D(index1, index2, delta);
    }


    private TestCase2D(int x1, int x2, int y1, int y2, long expValue) {
        this.queryType = QueryType.READ;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.expectedValue = expValue;
    }

    private TestCase2D(int index1, int index2, long m) {
        this.queryType = QueryType.MODIFY;
        this.index1 = index1;
        this.index2 = index2;
        this.delta = m;
    }

    public QueryType getQueryType() {
        return queryType;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public long getExpectedValue() {
        return expectedValue;
    }

    public int getIndex1() {
        return index1;
    }

    public int getIndex2() {
        return index2;
    }

    public long getDelta() {
        return delta;
    }
}
