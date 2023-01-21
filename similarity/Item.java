class Item implements Similarity<Item>
{
    Item(int n)
    {
    	this.n = n;
    }
    
    public boolean isSimilarTo(Item i)
    {
    	return Math.abs(n - i.n) < 2;
    }
    
    private int n;
}
