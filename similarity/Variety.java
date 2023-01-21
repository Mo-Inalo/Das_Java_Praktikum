import java.util.*;

class Variety<T extends Similarity<T>> extends ArrayList<T>
{
    public boolean add(T t) 
    {
        for(T x: this)
            if(x.isSimilarTo(t))
                return false;
        super.add(t);
	return true;
    }
    
    public static void main(String... args)
    {
        Variety<Thing> vs = new Variety<Thing>();
	vs.add(new Thing("abrak"));
	vs.add(new Thing("ad"));
	vs.add(new Thing("abra"));
	for(Thing th: vs)
	    System.out.println(th);
    }
}
