package textfiles;

import textfiles.TextfileIterator;

import java.io.*;

abstract class TextfileSplitter
{
    abstract boolean splitAt(String line);

    void split(String filename) throws IOException
    {
    	int index = 0;
    	Writer w = new FileWriter(String.format("%s.%03d", filename, index++));
    	PrintWriter p = new PrintWriter(w);
	
    	for(String line: new TextfileIterator(filename)) {
	    if(splitAt(line))
	    {
	    	p.close();
    	    	w = new FileWriter(String.format("%s.%03d", filename, index++));
    	    	p = new PrintWriter(w);
	    }
	    p.println(line);
	}
	p.close();
    }

    public static void main(String... args) throws IOException
    {
        new TextfileSplitter()
        {
            boolean splitAt(String line)
            {
                return line.startsWith("s");
            }
        }
        .split(args[0]);
    }
}
