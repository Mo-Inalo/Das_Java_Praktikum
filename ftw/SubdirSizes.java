/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: XXX
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package ftw;

import java.io.File;

/**
 *
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 */
public class SubdirSizes extends DirSizes
{
    @Override
    protected void leaveDir(final File d)
    {
        final long s = lengths.pop();
        System.out.printf("%s: %d%n", d, s);
        if(lengths.size() > 0)
            lengths.push(lengths.peek() + s);
    }

    public static void main(final String[] args)
    {
        new SubdirSizes().walk(new File("."));
    }

}
