/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Textdateien
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package textfile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

/**
 * Zeilenweise iterierbare Textdatei.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 28.06.2008
 */
public class TextfileLines implements Iterable<String> {
    private final String filename;

    /**
     * Erzeugt eine zeilenweise iterierbare Textdatei.
     * @param filename Name der Textdatei
     */
    public TextfileLines(final String filename) {
        this.filename = filename;
    }

    /**
     * Liefert einen neuen Iterator.
     */
    public Iterator<String> iterator() {
        return new TextfileIterator();
    }

    /**
     * Iterator über die Zeilen der Textdatei.
     *
     * @author Klaus Köhler, koehler@hm.edu
     * @author Reinhard Schiedermeier, rs@cs.hm.edu
     * @version 28.06.2008
     */
    private class TextfileIterator implements Iterator<String> {

        /**
         * Puffer für die nächste Zeile.
         */
        private String lookahead;

        /**
         * Reader für zeilenweises Lesen der Datei.
         */
        private final BufferedReader bufferedReader;

        /**
         * Erzeugt einen Iterator über die Zeilen der Textdatei.
         * Liest die erste Zeile voraus.
         */
        private TextfileIterator() {
            try {
                final Reader reader = new FileReader(filename);
                bufferedReader = new BufferedReader(reader);
                lookahead = bufferedReader.readLine();
            }
            catch (final IOException ex) {
                throw new IllegalStateException(ex);
            }
        }

        /**
         * remove wird nicht unterstützt.
         */
        public void remove() {
            throw new UnsupportedOperationException("cannot modify file");
        }

        public boolean hasNext() {
            return lookahead != null;
        }

        public String next() {
            final String result = lookahead;
            try {
                lookahead = bufferedReader.readLine();
            }
            catch (final IOException ex) {
                throw new IllegalStateException(ex);
            }
            return result;
        }

        @Override
        public void finalize() throws Throwable {
            try
            {
                bufferedReader.close();
            }
            finally
            {
                super.finalize();
            }
        }
    }

    /**
     * Testprogramm für TextfileLines.
     * @param args nicht verwendet
     */
    public static void main(final String[] args) {
        for(final String line: new TextfileLines("input1.txt"))
            System.out.println(line);
        final TextfileLines textfileLines = new TextfileLines("input1.txt");
        final Iterator<String> iterator1 = textfileLines.iterator();
        while(iterator1.hasNext()) {
            System.out.println("* " + iterator1.next());
            final Iterator<String> iterator2 = textfileLines.iterator();
            while(iterator2.hasNext())
                System.out.println("\t** " + iterator2.next());
        }
    }

}
