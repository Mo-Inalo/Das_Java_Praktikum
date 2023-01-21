/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: I/O-Filter
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package textfilter;

//import java.io.FileReader;
import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * PositionReader führt beim Lesen Buch über die aktuelle Zeilen- und Spaltenposition.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 29.06.2008
 */
public class PositionReader extends FilterReader {

    /**
     * Standardtabulatorweite.
     */
    public final static int TAB = 8;

    /**
     * Aktuelle Zeilennummer.
     */
    private int line = 1;

    /**
     * Aktuelle Spaltennummer.
     */
    private int column = 0;

    /**
     * Verwendetes Betriebssystem.
     */
    private final static boolean unixSystem = 
        System.getProperty("line.separator").charAt(0) == '\n';

    public PositionReader(final Reader in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        return trackPosition(super.read());
    }

    @Override
    public int read(final char[] buffer, final int start, final int want) throws IOException {
        final int have = super.read(buffer, start, want);
        for(int i = start; i < start + have; i++)
            trackPosition(buffer[i]);
        return have;
    }

    /**
     * Aanalysiert Zeichen und bestimmt die Zeilen- und Spaltenposition.
     * @param chr das Zeichen
     * @return das unveränderte Zeichen
     */
    private int trackPosition(int chr) {
        if(chr < 0)             // Fluchtwert am Ende der Eingabe
            ;       
        else if(chr == '\n') {
            // Unix: Anfang der nächsten Zeile
            // Windows: nächste Zeile, gleiche Spalte
            line++;
            if(unixSystem)
                column = 0;
        }
        else if(chr == '\r')
            column = 0;         // Anfang der gleichen Zeile
        else if(chr == '\t')
            column = (column/TAB + 1)*TAB;
        else
            column++;
        return chr;
    }

    /**
     * Liefert die Nummer der aktuellen Zeile ab 1.
     * @return Zeilennummer
     */
    public int line() {
        return line;
    }

    /**
     * Liefert die Spaltenposition in der aktuellen Zeile ab 0.
     * @return Spaltenposition
     */
    public int column() {
        return column;
    }

    /**
     * Testprogramm für PositionReader.
     * Liest einen Text und gibt seine Zeichen zusammen mit der Position aus.
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        final String string = String.format("Zeile 1%n\t...%n*\t**\t*%n\r\nEnde%n");
        final Reader reader = new StringReader(string);
        final PositionReader positionReader = new PositionReader(reader);
        for(int chr = positionReader.read(); chr >= 0; chr = positionReader.read())
            System.out.printf("%c(%d, %d)", (char)chr, positionReader.line(), positionReader.column());
        positionReader.close();

    }

}
