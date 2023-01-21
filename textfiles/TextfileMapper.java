package textfiles;

import textfiles.TextfileIterator;

import java.io.*;

abstract class TextfileMapper
{
    abstract String transform(String line);

    void map(String infile, String outfile) throws IOException
    {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outfile), "ISO8859_1"));
        for(String line: new TextfileIterator(infile))
        {
            String xline = transform(line);
            if(xline != null)
                output.println(xline);
        }
        output.close();
    }

    public static void main(String... args) throws IOException
    {
        new TextfileMapper()
        {
            String transform(String line)
            {
                return line.toUpperCase();
            }
        }.map(args[0], "output-1.txt");

        new TextfileMapper()
        {
            String transform(String line)
            {
                return line.trim().length() == 0?  null:  line;
            }
        }.map(args[0], "output-2.txt");

        new TextfileMapper()
        {
            private String lastline = null;

            String transform(String line)
            {
                if(line.equals(lastline))
                    return null;
                lastline = line;
                return line;
            }
        }.map(args[0], "output-3.txt");
    }
}
