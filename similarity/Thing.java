class Thing implements Similarity<Thing> {
    Thing(String s) {
    	string = s;
    }
    
    public boolean isSimilarTo(Thing i) {
    	return string.startsWith(i.string)  ||  i.string.startsWith(string);
    }
    
    public String toString()
    {
    	return string;
    }
    
    private final String string;
}
