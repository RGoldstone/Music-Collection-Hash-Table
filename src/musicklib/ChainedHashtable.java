package musicklib;

import java.util.LinkedList;

public class ChainedHashtable implements MusicCollection {

    private LinkedList<Composition>[] table;

    public ChainedHashtable(int size) {
        table = new LinkedList[size];
    }

    @Override
    public void showHash() {
        // iterate through all elements
        for (int i = 0; i < table.length; i++) {
            System.out.print("table[" + i + "] = ");
            
            if (table[i] != null && table[i].size() > 0) {
                System.out.println(table[i]);
            } else {
                System.out.println("[]");
            }
        }
    }
    
    @Override
    public void delete(String composition) {
        // search element
        int idx = search(composition, true);
        
        // if element found
        if (idx != -1) {
            // mark it as deleted
            table[calculateHash(composition)].remove(idx);
        }
    }
    
    // calculate index for composition
    private int calculateHash(String composition) {
        Composition c = new Composition(composition);
        return c.getKey() % table.length;
    }
    
    @Override
    public boolean traceSearch(String composition) {
        // create new element
        Composition c = new Composition(composition);
        // hash function value for this element
        int hash = calculateHash(composition);        
        
        System.out.println("Closed address: get list at '" + hash + "'");
        
        LinkedList<Composition> list = table[hash];
        
        if (list != null) {
            // compare all items in list with current
            for (int i = 0; i < list.size(); i++) {
                System.out.println("Closed address: get list at '" + hash + ":" + i + "'");
                // element found
                if (list.get(i).equals(c)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    // search item
    // returns -1 if not found or index of element in table
    @Override
    public int search(String composition, boolean searchDeleted) {       
        Composition c = new Composition(composition);
        // hash function value for this element
        int hash = calculateHash(composition);
        
        // get all values with current hash
        LinkedList<Composition> list = table[hash];
        
        // if there are elements
        if (list != null) {
            // compare every element with current
            for (int i = 0; i < list.size(); i++) {
                // if equals
                if (list.get(i).equals(c)) {
                    // return index
                    return i;
                }
            }
        }
        // not found
        return -1;
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
            int hash = calculateHash(composition);
            // create new LinkedList if needed
            if (table[hash] == null) {
                table[hash] = new LinkedList<Composition>();
            } else {
                System.out.println("Warning: found collision " + c + " = " + table[hash]);
            }
            
            table[hash].add(c);
        }
    }
}
