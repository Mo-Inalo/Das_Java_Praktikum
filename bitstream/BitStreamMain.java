/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Bitstreams
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package bitstream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Klassenrahmen zum Testen von BitStreams.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public class BitStreamMain {
    /**
     * Testprogramm für BitStreams.
     * @param args nicht verwendet
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        {
            final InputStream is = new ByteArrayInputStream("ABC".getBytes());
            final BitInputStream bi = new BitInputStream(is);
            while(!bi.eof())
                System.out.print(bi.read()?  '1':  '0');
            bi.close();
            System.out.println();
        }
        {
            final ByteArrayOutputStream os = new ByteArrayOutputStream();
            final BitOutputStream bo = new BitOutputStream(os);
            final InputStream is = new ByteArrayInputStream("ABC".getBytes());
            final BitInputStream bi = new BitInputStream(is);
            while(!bi.eof())
                bo.write(bi.read());
            bi.close();
            bo.close();
            System.out.println(new String(os.toByteArray()));
        }
    }
}
