package musicklib;

public interface MusicCollection {

    // add item to table
    void add(String composition);

    void delete(String composition);

    // search item
    // returns -1 if not found or index of element in table
    int search(String composition, boolean searchDeleted);

    void showHash();

    boolean traceSearch(String value);
}
