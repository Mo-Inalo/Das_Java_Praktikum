/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Bitstreams
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package bitstream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Klassenrahmen für main-Programm zum Testen von AnyBitStreams.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public class AnyBitStreamMain {
    /**
     * Testprogramm für AnyBitStreams.
     * @param args nicht verwendet
     * @throws IOException
     */
    public static void main(final String[] args) throws FileNotFoundException, IOException {
        final OutputStream os = new FileOutputStream("39bits");
        final AnyBitOutputStream abo = new AnyBitOutputStream(os);
        for(int i = 0; i < 39; i++)
            abo.write(i%3 == 0);
        abo.close();
        
        final InputStream is = new FileInputStream("39bits");
        final AnyBitInputStream abi = new AnyBitInputStream(is);
        while(!abi.eof())
            System.out.print(abi.read()?  '1':  '0');
        System.out.println();
        abi.close();
    }
}
