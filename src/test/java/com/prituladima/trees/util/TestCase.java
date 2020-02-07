package com.prituladima.trees.util;

public
class TestCase {
    public enum QueryType {
        MODIFY, READ
    }

    private QueryType queryType;

    private int L;
    private int R;
    private long expectedValue;


    private int index;
    private long delta;

    public static TestCase getRangeSumTestCase(int l, int r, long expValue){
        return new TestCase(l, r, expValue);
    }

    public static TestCase modifySingleElementTestCase(int index, long delta){
        return new TestCase(index, delta);
    }


    private TestCase(int l, int r, long expValue) {
        queryType = QueryType.READ;
        L = l;
        R = r;
        expectedValue = expValue;
    }

    public TestCase(int i, long m) {
        queryType = QueryType.MODIFY;
        index = i;
        delta = m;
    }

    public QueryType getQueryType() {
        return queryType;
    }

    public int getL() {
        return L;
    }

    public int getR() {
        return R;
    }

    public long getExpectedValue() {
        return expectedValue;
    }

    public int getIndex() {
        return index;
    }

    public long getDelta() {
        return delta;
    }

}