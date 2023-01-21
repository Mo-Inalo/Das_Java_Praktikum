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
 * Abstrakte Basisklasse zur zeilenweisen Transformation einer Textdatei.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 28.06.2008
 */
abstract class TextfileMapper {

    /**
     * Transformiert eine Zeile.
     * @param line die Zeile
     * @return die transformierte Zeile; null, wenn die Zeile ignoriert werden soll
     */
    abstract String transform(String line);

    /**
     * Wandelt den Inhalt einer Textdatei zeilenweise um
     * und schreibt das Resultat auf eine neue Textdatei.
     * @param infile Name der Eingabedatei
     * @param outfile Name der Ausgabedatei
     * @throws IOException bei Lese- oder Scheibfehler
     */
    public void map(final String infile, final String outfile) throws IOException {
        final Writer writer = new FileWriter(outfile);
        final PrintWriter printWriter = new PrintWriter(writer);
        for(final String line: new TextfileLines(infile)) {
            final String transformedLine = transform(line);
            if(transformedLine != null)
                printWriter.println(transformedLine);
        }
        printWriter.close();
    }

    /**
     * Testprogramm für TextfileMapper.
     * Definiert anonyme, konkrete abgeleitete Klassen von TextfileMapper,
     * legt Objekte davon an und startet die Dateitransformation durch Aufruf von map.
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        // ersetzt alle kleinen durch große Buchstaben
        new TextfileMapper() {
            @Override
            String transform(final String line) {
                return line.toUpperCase();
            }
        }
        .map("input2.txt", "output21.txt");

        // löscht alle leeren Zeilen
        new TextfileMapper() {
            @Override
            String transform(final String line) {
                return line.isEmpty()?  null:  line;
            }
        }
        .map("input2.txt", "output22.txt");

        // fasst gleiche, direkt aufeinander folgende Zeilen zu einer einzigen zusammen
        new TextfileMapper() {
            private String lastline = null;
            @Override
            String transform(final String line) {
                if(line.equals(lastline))
                    return null;
                lastline = line;
                return line;
            }
        }
        .map("input2.txt", "output23.txt");
    }

}
