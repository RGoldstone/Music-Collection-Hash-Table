/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package musicklib;

/**
 *
 * @author home
 */
public class MusickLib {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

        
        OpenAddressHashtable openAddress = new OpenAddressHashtable(35);
        ChainedHashtable closedAddress = new ChainedHashtable(35);
        
        System.out.println("** Open address hashtable");
        populateData(openAddress);
        
        System.out.println("\n\n** Chained hashtable");
        populateData(closedAddress);

    }
    
    public static void populateData(MusicCollection collection) {
        String[] songs = new String[]{
            "Alien - Britney Spears",
            "Anticipating - Britney Spears",
            "Big Fat Bass - Britney Spears",
            "Body Ache - Britney Spears",
            "Boys - Britney Spears",
            "Break The Ice - Britney Spears",
            "Brightest Morning Star - Britney Spears",
            "Chillin' With You - Britney Spears",
            "Circus - Britney Spears",
            "Criminal - Britney Spears",
            "Dear Diary - Britney Spears",
            "Don't Cry - Britney Spears",
            "Don't Keep Me Waiting - Britney Spears",
            "Everytime - Britney Spears",
            "Gasoline - Britney Spears",
            "Gimme More - Britney Spears",
            "Heaven On Earth - Britney Spears",
            "Hold It Against Me - Britney Spears",
            "How I Roll - Britney Spears",
            "I Love Rock And Roll - Britney Spears",
            "I Wanna Go - Britney Spears",
            "I'm A Slave 4 U - Britney Spears",
            "I'm Not A Girl, Not Yet A Woman - Britney Spears",
            "If U Seek Amy - Britney Spears",
            "Inside Out - Britney Spears",
            "It Should Be Easy - Britney Spears",
            "Kill the Lights - Britney Spears",
            "Lace and Leather - Britney Spears",
            "Lucky - Britney Spears",
            "Mannequin - Britney Spears",
            "Me Against The Music - Britney Spears"
        };
        
        String[] deleteList = new String[]{
            "Chillin' With You - Britney Spears",
            "Circus - Britney Spears",
            "Criminal - Britney Spears",
            "Dear Diary - Britney Spears",
            "Don't Cry - Britney Spears"
        };        
        
        System.out.println("* Populate data");
        for (int i = 0; i < 15; i++) {
            collection.add(songs[i]);
        }
        collection.showHash();
        
        System.out.println("\n* Delete elements");
        for (int i = 0; i < deleteList.length; i++) {
            System.out.println("Delete '" + deleteList[i] + "'");
            collection.delete(deleteList[i]);
        }
        collection.showHash();
        
        System.out.println("\n* Add more data");
        for (int i = 15; i < 20; i++) {
            collection.add(songs[i]);
        }
        collection.showHash();
        
        boolean found = collection.traceSearch("How I Roll - Britney Spears");
        System.out.println("* Search for 'How I Roll - Britney Spears': " + (found ? "FOUND" : "NOT FOUND"));
        
        found = collection.traceSearch("Happy new year - Britney Spears");
        System.out.println("* Search for 'Happy new year - Britney Spears': " + (found ? "FOUND" : "NOT FOUND"));
    }
    
}
