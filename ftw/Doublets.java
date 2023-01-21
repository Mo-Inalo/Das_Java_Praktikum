/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Filesystemsuche
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package ftw;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Definiert Verzeichnisdurchlauf zur Bestimmung gleicher Verzeichnis-/Dateinamen.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 15.06.2008
 */
public class Doublets extends FTW {
    /**
     * Tabelle der bisher besuchten Files.
     */
    private final Map<String, File> found = new HashMap<String, File>();

    @Override
    protected void enterDir(final File d) {
        check(d);
    }

    @Override
    protected void atFile(final File f) {
        check(f);
    }

    /**
     * Überprüft, ob Filename schon früher gefunden.
     * Wenn ja: Meldung auf Standardausgabe, sonst Aufnahme in Tabelle
     * @param f der zu überprüfende File
     */
    private void check(final File f) {
        final String name = f.getName();
        if(found.get(name) == null)
            found.put(name, f);
        else
            System.out.printf("%s - %s%n", f, found.get(name));
    }

    /**
     * Meldet jeden Namen im gegebenen Verzeichnisbaum, der mehrfach vorkommt.
     * @param args Wurzelverzeichnis
     */
    public static void main(final String[] args) {
        new Doublets().walk(new File(args[0]));
    }

}
