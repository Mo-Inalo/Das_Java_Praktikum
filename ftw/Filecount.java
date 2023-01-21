/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Filesystemsuche
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package ftw;

import java.io.File;

/**
 * Verzeichnisdurchlauf zur Bestimmung der Anzahl enthaltener Dateien.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 15.06.2008
 */
public class Filecount extends FTW {
    private int count = 0;

    @Override
    protected void atFile(final File f) {
        count++;
    }

    /**
     * Zählt die Dateien des aktuellen Verzeichnisbaums.
     * @param args nicht verwendet
     */
    public static void main(final String[] args) {
        final Filecount fc = new Filecount();
        fc.walk(new File("."));
        System.out.println(fc.count);
    }
}
