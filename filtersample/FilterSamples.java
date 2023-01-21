/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Messwerte
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package filtersample;

import java.util.*;

/**
 * Rahmenklasse für Filterprogramm.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 15.06.2008
 */
class FilterSamples {

    /**
     * Filter für Folgen ganzer Zahlen.
     * 1. Negative Werte werden gelöscht.
     * 2. Dann werden alle Gruppen von zwei oder mehr unmittelbar
     *    aufeinander folgenden Nullen zu einer einzigen 0 zusammengefasst.
     * 3. Die dann übrigen Werte werden ausgegeben.
     * Eingabe von Standardeingabe
     * Ausgabe auf Standardausgabe
     * @param args wird ignoriert.
     */
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int STOPPER = -1;
        int stoppers = 0;
        boolean nonZero = true;
        while(stoppers < 3) {
            final int n = scanner.nextInt();
            if(n > 0) {
                stoppers = 0;
                nonZero = true;
                System.out.printf("%d ", n);
            }
            else if(n == 0) {
                stoppers = 0;
                if(nonZero)
                    System.out.print("0 ");
                nonZero = false;
            }
            else
                if(n == STOPPER)
                    stoppers++;
                else
                    stoppers = 0;
        }
    }

}

