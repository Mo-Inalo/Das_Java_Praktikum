/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Datumsarithmetik
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package date;

/**
 * Rahmenklasse für Programm zur Wochentagsbestimmung.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 14.06.2008
 */
class Zeller2 {
    /**
     * Berechnet den Wochentag zu einem gegebenen Kalenderdatum.
     * @param args Tag (1..31), Monat (1..12), Jahr  (ccyy)
     * Ausgabe: Wochentag (0 = Sonntag bis 6 = Samstag)
     */
    public static void main(final String[] args) {
        final int d = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        int temp = Integer.parseInt(args[2]); 

        if(m < 3) {
            m += 12;    
            temp--;     
        }
        final int y = temp%100;
        final int c = temp/100;

        final int w = (d + 26*(m + 1)/10 + 5*y/4 + c/4 + 5*c - 1)%7;

        System.out.println(w);
    }
}

