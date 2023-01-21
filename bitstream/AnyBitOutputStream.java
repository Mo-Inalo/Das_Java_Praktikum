/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Bitstreams
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package bitstream;

import java.io.IOException;
import java.io.OutputStream;

/**
 * OutputStream für einzelne Bits.
 * Die Gesamtzahl der Bits ist beliebig.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public class AnyBitOutputStream {
    /**
     * Ausgabestrom.
     */
    private final OutputStream destination;

    /**
     * Puffergröße.
     */
    private final static int MAX_BUFFER = 56;

    /**
     * Anzahl der gültigen Bits im Puffer.
     */
    private int bitcount;

    /**
     * Puffer für die Bits eines gelesenen Bytes.
     * Das erste Byte enthält den Bitzähler (Anzahl der nachfolgenden Bits).
     */
    private long buffer;

    /**
     * Konstruktor für einen neuen 'BitOutputStream', der Bits auf 'output' schreibt.
     * Ausgegebene Bits werden in der Reihenfolge vom niederwertigsten zum
     * höchstwertigsten Bit zu Bytes zusammengefügt.
     * @param output der Ausgabestrom
     */
    public AnyBitOutputStream(OutputStream output) {
        destination = output;
        bitcount = 0;
    }

    /**
     * Fügt das Bit 'bit' ('true' = 1, 'false' = 0) an die Ausgabe an.
     * @param bit das Bit als boolean codiert
     * @throws IOException
     */
    public void write(final boolean bit) throws IOException {
        if(bitcount == MAX_BUFFER)
            emptyBuffer();
        if(bit)
            buffer |= 1L << bitcount;
        bitcount++;
    }

    /**
     * Leert den Puffer und schreibt ihn auf den Ausgabestrom.
     * Wenn die Gesamtzahl der ausgegebenen Bits kein Vielfaches von 56 ist,
     * werden entsprechend viele 0-Bits ergänzt.
     * @throws IOException
     */
    private void emptyBuffer() throws IOException {
        if(bitcount > 0) {
            destination.write(bitcount);
            for(int b = 0; b < 7; b++)
                destination.write((int)((buffer >> 8*b) & 0xFF));
            buffer = 0L;
            bitcount = 0;
        }
    }

    /**
     * Schließt den 'BitOutputStream'.
     * @throws IOException
     */
    public void close() throws IOException {
        emptyBuffer();
        destination.close();
    }

}
