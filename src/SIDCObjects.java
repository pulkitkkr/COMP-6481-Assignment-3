//______________________________________________________________________________________________________________________
// Programming Assignment 3
// Team Members
// 1. Manish Sehgal - 40165366
// 2. Pulkit Kakkar - 40160971
//
//______________________________________________________________________________________________________________________

abstract class SIDCObjects {

    public abstract int generate();

    public abstract void allKeys();

    public abstract void add(int sidc, String value);

    public abstract void remove(int sidc);

    public abstract String getValues(int sidc);

    public abstract int nextKey(int sidc);

    public abstract int prevKey(int sidc);

    public abstract int rangeKey(int key1, int key2);

    public abstract int[] getItemsInArr();

    public abstract int getSize();
}