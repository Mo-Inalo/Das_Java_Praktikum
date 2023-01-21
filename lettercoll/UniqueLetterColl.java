/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Buchstabensammlungen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package lettercoll;

/**
 * Definiert eine unveränderliche, ungeordnete Sammlung von Großbuchstaben.
 * Gleiche Buchstaben werden nur einmal gezählt.
 * Konzeptionell eine Menge ohne Wiederholungen.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 15.06.2008
 */
public class UniqueLetterColl extends LetterColl {

    /**
     * Erzeugt eine Menge aus einer Liste von Großbuchstaben.
     * @param ls die Buchstabenliste
     */
    public UniqueLetterColl(final char... ls) {
        super(uniqueLetters(ls));
    }

    /**
     * Wandelt eine Buchstabenliste in einen String ohne Wiederholungen um.
     * @param ls die Buchstabenliste
     * @return der String mit allen Buchstaben der Liste ohne Wiederholungen
     */
    private static String uniqueLetters(final char... ls) {
        String result = "";
        for(final char c: ls)
            if(result.indexOf(c) < 0)
                result += c;
        return result;
    }

    /**
     * Erzeugt eine Menge aus einem String von Großbuchstaben.
     * @param ls der String
     */
    public UniqueLetterColl(final String ls) {
        this(ls.toCharArray());
    }

    /**
     * Testprogramm für Buchstabensammlung.
     * @param args nicht verwendet
     */
    public static void main(final String[] args) {
        final LetterColl lc = new UniqueLetterColl('A', 'B', 'R', 'A', 'K', 'A', 'D', 'A', 'B', 'R', 'A');
        System.out.println(lc.size());
        System.out.println(lc.count('R'));
        System.out.println(lc.count('X'));
        System.out.println(lc.different());
        System.out.println(lc.top());
        System.out.println(lc);
        System.out.println(lc.moreThan(1));
        System.out.println(lc.except(new LetterColl("ABRAXAS")));
    }

}
