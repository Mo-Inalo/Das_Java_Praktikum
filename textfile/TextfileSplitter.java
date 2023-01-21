/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Textdateien
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package textfile;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * Abstrakte Basisklasse zur Aufteilung einer Textdatei auf mehrere Dateien.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 28.06.2008
 */
abstract class TextfileSplitter {

    /**
     * Entscheidet, ob mit dem String line eine neue Ausgabedatei beginnt.
     * @param line die zu untersuchende Zeile
     * @return true, wenn neue Ausgabedatei beginnt, sonst false
     */
    abstract boolean splitAt(String line);

    /**
     * Liest die Eingabedatei und kopiert sie zeilenweise in mehrere Ausgabedateien.
     * Eine neue Ausgabedatei filename.xxx wird geöffnet,
     * wenn splitAt(line) für die aktuelle Zeile line das Ergebnis true liefert.
     * xxx ist ein Zähler für die Ausgabedateien.
     * @param filename der Name der Eingabedatei
     * @throws IOException bei Lese- oder Schreibfehlern
     */
    public void split(final String filename) throws IOException {
        // Öffnet die erste Ausgabedatei
        int index = 0;
        Writer writer = new FileWriter(String.format("%s.%03d", filename, index++));
        PrintWriter printWriter = new PrintWriter(writer);

        // Iteriert über alle Zeilen der Eingabedatei
        for(final String line: new TextfileLines(filename)) {
            if(splitAt(line)) {
                printWriter.close();
                writer = new FileWriter(String.format("%s.%03d", filename, index++));
                printWriter = new PrintWriter(writer);
            }
            printWriter.println(line);
        }
        printWriter.close();
    }

    /**
     * Testprogramm für TextfileSplitter.
     * @param args nicht verwendet
     * @throws IOException, wenn TextfileSplitter einen Lese- oder Schreibfehler meldet
     */
    public static void main(final String[] args) throws IOException {
        // Legt Objekt einer anonymen, konkreten von TextfileSplitter abgeleiteten Klasse an
        new TextfileSplitter() {
            @Override
            boolean splitAt(final String line) {
                return line.startsWith("s");
            }
        }
        .split("input3.txt");
    }
}
