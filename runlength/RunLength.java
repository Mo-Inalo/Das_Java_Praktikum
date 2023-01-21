/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Kompression
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package runlength;

/**
 * Klassenrahmen f�r Laufl�ngencodierung.
 *
 * @author Klaus K�hler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 27.06.2008
 */
public class RunLength {

    /**
     * Komprimiert einen String mittels Laufl�ngencodierung.
     * @param args zu komprimierender String
     */
    public static void main(final String[] args) {
        // Im Text nicht vorkommendes Zeichen
        final char IMPOSSIBLE = '\0';
        // Kennzeichen f�r codierte L�ufe
        final char marker = 'Z';
        // Zu komprimierender String
        final String input = args[0];
        // Letztes gelesenes Zeichen
        char last = IMPOSSIBLE;
        // Laufl�ngenz�hler
        int count = 0;
        // Codiere nur L�ufe, die l�nger als 3 sind.
        final int compressAbove = 3;

        for(int i = 0; i <= input.length(); i++) {
            final char chr = i < input.length()?  input.charAt(i):  IMPOSSIBLE;
            if(chr != last  || count == 9) {
                if(count > compressAbove  || last == marker)
                    System.out.printf("%c%d%c", marker, count, last);
                else
                    for(int j = 0; j < count; j++)
                        System.out.print(last);
                count = 0;
            }
            last = chr;
            count++;
        }
    }

}
