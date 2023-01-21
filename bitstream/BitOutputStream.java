/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Bitstreams
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package bitstream;

import java.io.IOException;
import java.io.OutputStream;

/**
 * OutputStream f�r einzelne Bits.
 * Die Gesamtzahl der Bits ist ein ganzzahliges Vielfaches von 8.
 *
 * @author Klaus K�hler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public class BitOutputStream {
    /**
     * Ausgabestrom.
     */
    private final OutputStream destination;

    /**
     * Puffergr��e.
     */
    private final static int MAX_BUFFER = 8;

    /**
     * Anzahl der g�ltigen Bits im Puffer.
     */
    private int bitcount;

    /**
     * Puffer f�r ein Byte.
     */
    private int buffer;

    /**
     * Konstruktor f�r einen neuen 'BitOutputStream', der Bits auf 'output' schreibt.
     * Ausgegebene Bits werden in der Reihenfolge vom niederwertigsten zum
     * h�chstwertigsten Bit zu Bytes zusammengef�gt.
     * @param output der Ausgabestrom
     */
    public BitOutputStream(final OutputStream output) {
        destination = output;
        bitcount = 0;
    }

    /**
     * F�gt das Bit 'bit' ('true' = 1, 'false' = 0) an die Ausgabe an.
     * @param bit das Bit als boolean codiert
     * @throws IOException
     */
    public void write(final boolean bit) throws IOException {
        if(bitcount == MAX_BUFFER)
            emptyBuffer();
        if(bit)
            buffer |= 1 << bitcount;
        bitcount++;
    }

    /**
     * Leert den Puffer und schreibt ihn auf den Ausgabestrom.
     * Wenn die Gesamtzahl der ausgegebenen Bits kein Vielfaches von acht ist,
     * werden entsprechend viele 0-Bits erg�nzt.
     * @throws IOException
     */
    private void emptyBuffer() throws IOException {
        if(bitcount > 0) {
            destination.write(buffer);
            buffer = 0;
            bitcount = 0;
        }
    }

    /**
     * Schlie�t den 'BitOutputStream'.
     * @throws IOException
     */
    public void close() throws IOException {
        emptyBuffer();
        destination.close();
    }
}
