/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Filesystemsuche
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package ftw;

import java.io.File;

/**
 * Definiert einen rekursiven Durchlauf eines hierarchischen Filesystems.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 15.06.2008
 */
public abstract class FTW {
    /**
     * Preprocessing beim Eintritt in Verzeichnis.
     * @param d das Verzeichnis
     */
    protected void enterDir(final File d) {}

    /**
     * Postprocessing beim Verlassen des Verzeichnisses.
     * @param d das Verzeichnis
     */
    protected void leaveDir(final File d) {}

    /**
     * Processing für Datei.
     * @param f die Datei
     */
    protected void atFile(final File f) {}

    /**
     * Durchläuft rekursiv den Verzeichnisbaum beginnend bei der Wurzel x.
     * @param x die Wurzel des Verzeichnisbaums
     */
    protected void walk(final File x) {
        if(x.isFile())
            atFile(x);
        else if(x.isDirectory()) {
            enterDir(x);
            final File[] content = x.listFiles();
            if(content != null)
                for(final File y : content)
                    walk(y);
            leaveDir(x);
        }
    }
}
