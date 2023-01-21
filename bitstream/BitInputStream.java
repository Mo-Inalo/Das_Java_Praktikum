/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Bitstreams
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package bitstream;

import java.io.IOException;
import java.io.InputStream;

/**
 * InputStream für einzelne Bits.
 * Die Gesamtzahl der Bits ist ein ganzzahliges Vielfaches von 8. 
 * 
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public class BitInputStream {
    /**
     * Speisender Strom.
     */
    private final InputStream source;

    /**
     * Puffergröße.
     */
    private final static int MAX_BUFFER = 8;

    /**
     * Aktueller Pufferindex.
     */
    private int bitpos;

    /**
     * Puffer für die Bits eines gelesenen Bytes.
     */
    private int buffer;

    /**
     * Konstruktor für einen neuen 'BitInputStream', der Daten von 'input' holt.
     * Jedes Byte von 'input' wird in der Reihenfolge vom niederwertigsten
     * zum höchstwertigsten Bit verarbeitet.
     * @param input der InputStream
     * @throws IOException
     */
    public BitInputStream(InputStream input) throws IOException {
        source = input;
        fillBuffer();
    }

    /**
     * Holt ein Byte von der Datenquelle und speichert es in der Instanzvariablen 'buffer'.
     * @throws IOException
     */
    private void fillBuffer() throws IOException {
        buffer = source.read();
        bitpos = 0;
    }

    /**
     * Gibt Auskunft ob noch wenigstens ein weiteres Bit gelesen werden kann
     * oder ob die Eingabe erschöpft ist.
     * @return true, wenn noch ein Bit gelesen werden kann
     * @throws IOException
     */
    public boolean eof() throws IOException {
        return buffer < 0;
    }

    /**
     * Liest ein Bit und liefert es als 'boolean'-Wert zurück.
     * @return 'true', wenn das Bit = 1 ist, sonst 'false'
     * @throws IOException
     */
    public boolean read() throws IOException {
        final boolean result = (buffer & (1 << bitpos)) != 0;
        bitpos++;
        if(bitpos >= MAX_BUFFER)
            fillBuffer();
        return result;
    }

    /**
     * Schließt den 'BitInputStream'.
     * @throws IOException
     */
    public void close() throws IOException {
        source.close();
    }

}
