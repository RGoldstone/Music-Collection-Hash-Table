package musicklib;

public class Composition {
    private String compositionName;

    public Composition() {}
    
    public Composition(String compositionName) {
        this.compositionName = compositionName;
    }

    // get entry value
    public String getCompositionName() {
        return compositionName;
    }

    // set entry value
    public void setCompositionName(String compositionName) {
        this.compositionName = compositionName;
    }

    // key of this entry. count it from first two chars in name
    public int getKey() {
        if (this.compositionName != null) {
            // return number value of key
            return this.compositionName.charAt(0) + this.compositionName.charAt(1);
        } else {
            // this is for special deleted value
            return -1;
        }
    }
    
    public boolean equals(Composition c) {
        return c.getCompositionName().equals(getCompositionName());
    }
    
    public String toString() {
        return "<" + getKey() + ":" + getCompositionName() + ">";
    }
}
