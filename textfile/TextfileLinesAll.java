/* Übungsbuch Java, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: XXX
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package textfile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 */
public class TextfileLinesAll implements Iterable<String>
{
    private final List<String> linelist = new ArrayList<String>();

    public TextfileLinesAll(final String filename) throws FileNotFoundException, IOException
    {
        final BufferedReader reader = new BufferedReader(new FileReader(filename));
        for(String line = reader.readLine(); line != null; line = reader.readLine())
            linelist.add(line);
        reader.close();
    }

    public Iterator<String> iterator()
    {
        return linelist.iterator();
    }

}
