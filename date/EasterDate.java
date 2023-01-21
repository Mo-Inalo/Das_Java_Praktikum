/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Datumsarithmetik
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package date;

/**
 * Rahmenklasse für Programm zur Bestimmung des Osterdatums.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 14.06.2008
 */
class EasterDate {
    /**
     * Berechnet das Osterdatum zu einer Jahreszahl y >= 1582.
     * @param args Jahr
     * Ausgabe: Tag und Monat des Osterdatums
     */
    public static void main(final String[] args) {
        final int y = Integer.parseInt(args[0]);
        final int g = y%19;
        final int c = y/100;
        final int h = (c - c/4 - (8*c + 13)/25 + 19*g + 15)%30;
        final int i = h - (h/28)*(1 - (h/28)*(29/(h + 1))*((21 - g)/11));
        final int j = (y + y/4 + i + 2 - c + c/4)%7;
        final int l = i - j;
        final int m = 3 + (l + 40)/44;
        final int d = l + 28 - 31*(m/4);
        System.out.println(d + "  " + m);
    }

}
