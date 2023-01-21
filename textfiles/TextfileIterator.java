package textfiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class TextfileIterator implements Iterable<String>
{
    TextfileIterator(String fn)
    {
    	filename = fn;
    }
    
    public Iterator<String> iterator()
    {
    	return new Iterator<String>()
	{
	    public boolean hasNext()
	    {
	    	return nextLine != null;
	    }

	    public String next()
	    {
	    	try
		{
		    String result = nextLine;
		    nextLine = reader.readLine();
	    	    return result;
		}
		catch(IOException ex)
		{
		    ex.printStackTrace();
		}
		return null;
	    }
	    
	    public void remove()
	    {
	    	throw new UnsupportedOperationException();
	    }
	    
	    public void finalize() throws Throwable
	    {
	    	reader.close();
	    }
	    
	    private BufferedReader reader;
	    
	    private String nextLine;
	    
	    {
	    	try
		{
		    reader = new BufferedReader(new FileReader(filename));
		    nextLine = reader.readLine();
		}
		catch(IOException ex)
		{
		    ex.printStackTrace();
		}
	    }
	};
    }
    
    public static void main(String... args)
    {
    	Iterable<String> is = new TextfileIterator("TextfileIterator.java");
        for(String line: is)
	{
	    System.out.printf("[%s]%n", line);
	    for(String line2: is)
	    	System.out.printf("<%s>", line2);
	}
    }
    
    private final String filename;
}
