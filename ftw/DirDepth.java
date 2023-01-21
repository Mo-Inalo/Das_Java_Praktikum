/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Filesystemsuche
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package ftw;

import java.io.File;

/**
 * Definiert Filesystem-Durchlauf zur Bestimmung der maximalen Tiefe.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 15.06.2008
 */
public class DirDepth extends FTW {
    /**
     * Bisherige maximale Tiefe.
     */
    private int maxDepth = 0;

    /**
     * Tiefe des gerade besuchten Knotens.
     */
    private int depth = 0;

    @Override
    protected void enterDir(final File d) {
        depth++;
        if(depth > maxDepth)
            maxDepth = depth;
    }

    @Override
    protected void leaveDir(final File d) {
        depth--;
    }

    /**
     * Bestimmt die Höhe des Verzeichnisbaums des aktuellen Verzeichnisses.
     * @param args nicht verwendet.
     */
    public static void main(final String[] args) {
        final DirDepth dd = new DirDepth();
        dd.walk(new File("."));
        System.out.println(dd.maxDepth);
    }

}
