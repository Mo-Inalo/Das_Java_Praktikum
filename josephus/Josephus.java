/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Josephusring
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package josephus;

/**
 * Rahmenklasse zum Josephusring.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 15.06.2008
 */
public class Josephus {

    /**
     * Bestimmt den begnadigten Gefangenen aus einer vorgegebenen Anzahl Gefangener.
     * Aus der Ringliste der Gefangenen wird durch Abzählen bis zu einer tödlichen Zahl
     * der nächste Gefangene ausgesondert (das Urteil vollstreckt),
     * solange bis nur noch einer übrig bleibt (der begnadigt wird).
     * @param args Anzahl Gefangene; tödliche Zahl
     * Ausgabe: Protokoll des Abzählens und begnadigter Gefangener
     */
    public static void main(final String[] args) {
        final int numPrisoners = Integer.parseInt(args[0]);
        final int fatalNumber = Integer.parseInt(args[1]);

        Prisoner prisoner = new Prisoner();
        for(int n = 1; n < numPrisoners; n++)
            prisoner = prisoner.insertNext(new Prisoner()).next();

        int theNumber = 0;
        while(prisoner.next() != prisoner) {
            if(theNumber == fatalNumber - 1) {
                System.out.printf("%s: \"%d!\" - removed%n", prisoner.next(),
                        fatalNumber);
                prisoner = prisoner.removeNext();
                theNumber = 0;
            } else {
                prisoner = prisoner.next();
                theNumber++;
                System.out.printf("%s: \"%d\"%n", prisoner, theNumber);
            }
        }

        System.out.println(prisoner);
    }

}
