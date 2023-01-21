/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: I/O-Filter
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package textfilter;

import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * ReverseInputStream liefert die Bytes der Eingabe in umgekehrter Reihenfolge.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 29.06.2008
 */
public class ReverseInputStream extends FilterInputStream {

    /**
     * Haupspeicherkopie des kompletten Eingabestroms.
     */
    private byte[] image = new byte[0];

    /**
     * Anzahl der noch nicht verarbeiteten Bytes.
     */
    private int remaining = 0;

    /**
     * Erzeugt einen ReverseInputStream aus einem InputStream.
     * @param input der Eingabestrom
     * @throws IOException bei Lesefehler
     */
    public ReverseInputStream(final InputStream input) throws IOException {
        super(input);
        int available = input.available();
        while(available > 0) {
            image = Arrays.copyOf(image, remaining + available);
            remaining += input.read(image, remaining, available);
            available = input.available();
        }
    }

    @Override
    public int read() throws IOException {
        if(remaining == 0)
            return -1;
        remaining--;
        return image[remaining];
    }

    @Override
    public int read(final byte[] buffer, final int start, int count) throws IOException {
        if(remaining == 0)
            return -1;
        if(remaining < count)
            count = remaining;
        for(int i = start; i < start + count; i++)
            buffer[i] = (byte)read();
        return count;
    }

    /**
     * Testprogramm für ReverseInputStream.
     * Gibt einen Eingabestrom in umgekehrter Reihenfolge aus.
     * @param args nicht verwendet
     * @throws IOException bei Lesefehlern
     */
    public static void main(final String[] args) throws IOException {
        InputStream inputStream = new ByteArrayInputStream("Abrakadabra".getBytes());
        inputStream = new ReverseInputStream(inputStream);
        final byte[] buffer = new byte[4];
        for(int n = inputStream.read(buffer, 0, buffer.length);
                n >= 0;
                n = inputStream.read(buffer, 0, buffer.length))
            System.out.print(new String(buffer, 0, n));
        System.out.println();

    }

}
