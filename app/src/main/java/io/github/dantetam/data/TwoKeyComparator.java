package io.github.dantetam.data;

import java.util.Comparator;

interface TwoWayComparator<T> extends Comparator<T> {

    //Install a 'natural ordering' by chaining x and y compare
    public int compare(T a, T b) ;
    /*{
        int dy = compareY(a, b);
        if (dy != 0) {
            return dy;
        }
        return compareX(a, b);
    }*/

    public boolean equals(Object a);

    public int compareX(T a, T b);
    public int compareY(T a, T b);

    public T[] createChildren();
    /**
     * @param a - First item T ("this")
     * @param b - Second item T ("other")
     * @return the default quadrant of b with respect to a,
     * where mathematical quadrants are mapped respectively as
     * 2 1 --> 0 1
     * 3 4 --> 2 3
     */
    public int getCompareScores(T a, T b);
    /*{
        int dy = compareY(a, b);
        int dx = compareX(a, b);
        if (dy == dx && dx == 0) {
            throw new RuntimeException("a and b should not be equal");
        }
        if (dy > 1 && dx > 1) return 3;
        if (dy > 1 && dx < 1) return 2;
        if (dx > 1) return 1;
        return 0;
    }*/

}