package musicklib;

public class OpenAddressHashtable implements MusicCollection {

    private Composition[] table;
    private Composition deleted = new Composition();

    public OpenAddressHashtable(int size) {
        table = new Composition[size];
    }

    @Override
    public void showHash() {
        // iterate through all elements
        for (int i = 0; i < table.length; i++) {
            System.out.print("table[" + i + "] = ");
            
            if (table[i] == null) {
                System.out.println("<empty>");
            } else if (table[i] == deleted) {
                System.out.println("<deleted>");
            } else {
                System.out.println(table[i]);
            }
        }
        
        System.out.println("");
    }
    
    @Override
    public void delete(String composition) {
        // search element
        int idx = search(composition, true);
        
        // if element found
        if (idx != -1) {
            // mark it as deleted
            table[idx] = deleted;
        }
    }
    
    @Override
    public boolean traceSearch(String composition) {
        Composition c = new Composition(composition);
        // koefficient for quadratic function
        int koef = 0;
        // hash function value for this element
        int hash = c.getKey() % table.length;
        
        // start search cycle
        do {
            // get element on "hash" index
            System.out.println("Open address: get element at '" + hash + "'");
            Composition c1 = table[hash];
            
            // check it's not empty or deleted
            if (c1 != null && c1 != deleted) {
                // compare with current entry
                if (c1.equals(c)) {
                    // if equals, return index
                    return true;
                } else {
                    // incremenent koefficient
                    koef++;
                    
                    // if end of table
                    if (koef > table.length) {
                        // element is not found
                        return false;
                    }
                    
                    // and compute new hash
                    hash = calculateNextHash(c, koef);
                }
            } else {
                // element is not found
                return false;
            }
        } while (true);
    }
    
    // search item
    // returns -1 if not found or index of element in table
    @Override
    public int search(String composition, boolean searchDeleted) {       
        Composition c = new Composition(composition);
        // koefficient for quadratic function
        int koef = 0;
        // hash function value for this element
        int hash = c.getKey() % table.length;
        
        // start search cycle
        do {
            // if there is not empty cell
            if (!isEmptyCell(hash, searchDeleted)) {
                // compare with current entry
                if (table[hash].equals(c)) {
                    // if equals, return index
                    return hash;
                } else {
                    // incremenent koefficient
                    koef++;
                    
                    // if end of table
                    if (koef > table.length) {
                        // element is not found
                        return -1;
                    }
                    
                    // and compute new hash
                    hash = calculateNextHash(c, koef);
                }
            } else {
                // element is not found
                return -1;
            }
        } while (true);
    }
    
    private boolean isEmptyCell(int index, boolean searchDeleted) {
        Composition c = table[index];
        return c == null || (c == deleted && !searchDeleted);
    }
    
    private int calculateNextHash(Composition c, int koef) {
        return (c.getKey() + koef * koef) % table.length;
    }

    // add item to table
    @Override
    public void add(String composition) {
        
        // search item in table
        int idx = search(composition, false);

        // create new entry
        Composition c = new Composition(composition);
        
        // if not found
        if (idx == -1) {
            // compute index in table
            int hash = (c.getKey() % table.length);
            int koef = 0;
            // start cycle. we need it if cell with "hash" index is not empty
            do {
                // if our index don't contains entry
                if (isEmptyCell(hash, false)) {
                    // just fill cell
                    table[hash] = c;
                    // exit loop
                    break;
                } else {
                    // incremenent koefficient
                    koef++;   
                    
                    // if end of table
                    if (koef > table.length) {
                        // table is full
                        break;
                    }
                    
                    // print warning
                    System.out.println("Warning: found collision " + c + " = " + table[hash]);
                    
                    // else compute new hash, using quadratic probing
                    hash = calculateNextHash(c, koef);
                }
            } while (true);
        }
    }
}
