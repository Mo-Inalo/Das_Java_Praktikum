/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: XXX
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package ftw;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 */
public class DirSizes extends FTW
{
    protected final Deque<Long> lengths = new ArrayDeque<Long>();

    @Override
    protected void enterDir(final File d)
    {
        lengths.push(0L);
    }

    @Override
    protected void leaveDir(final File d)
    {
        System.out.printf("%s: %d%n", d, lengths.pop());
    }

    @Override
    protected void atFile(final File f)
    {
        lengths.push(lengths.pop() + f.length());
        // lengths.peek() += f.length();  // required variable, found value
    }

    public static void main(final String[] args)
    {
        new DirSizes().walk(new File("."));
    }

}
