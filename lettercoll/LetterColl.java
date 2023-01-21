/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Buchstabensammlungen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package lettercoll;

import java.util.Arrays;

/**
 * Definiert eine unveränderliche, ungeordnete Sammlung von Großbuchstaben.
 * Nur die Häufigkeiten der enthaltenen Buchstaben sind relevant.
 * Konzeptionell ein Bag = Menge mit Wiederholungen.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 15.06.2008
 */
public class LetterColl {
    /**
     * Häufigkeitstabelle aller Großbuchstaben.
     * lettercount[c - 'A'] = Häufigkeit des Buchstabens c
     */
    private final int[] lettercount;

    /**
     * Anzahl der vorkommenden Buchstaben einschließlich Wiederholungen.
     */
    private final int letters;

    /**
     * Verwendetes Alphabet = alle Großbuchstaben.
     */
    private final static char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /**
     * Offset für Index in Häufigkeitstabelle lettercount.
     */
    private final static char FIRST = ALPHABET[0];

    /**
     * Alphabetgröße = Länge der Häufigkeitstabelle.
     */
    private final static int N = ALPHABET.length;

    /**
     * Erzeugt eine neue Sammlung aus einer Liste von Großbuchstaben.
     * @param ls die Liste
     */
    public LetterColl(final char... ls) {
        lettercount = new int[N];
        letters = ls.length;
        for(final char c : ls)
            lettercount[c - FIRST]++;
    }

    /**
     * Erzeugt eine neue Sammlung aus den Buchstaben eines Strings.
     * @param ls der String
     */
    public LetterColl(final String ls) {
        this(ls.toCharArray());
    }

    /**
     * Liefert die Anzahl Buchstaben der Sammlung.
     * @return die Anzahl der Buchstaben
     */
    public int size() {
        return letters;
    }

    /**
     * Liefert die Anzahl Vorkommen des Buchstabens c.
     * @param c der Buchstabe
     * @return die Anzahl
     */
    public int count(final char c) {
        return lettercount[c - FIRST];
    }

    /**
     * Liefert die Anzahl verschiedener Buchstaben in der Sammlung.
     * @return die Anzahl
     */
    public int different() {
        int result = 0;
        for(final char l : ALPHABET)
            if(count(l) > 0)
                result++;
        return result;
    }

    /**
     * Liefert den häufigsten Buchstaben in der Sammlung.
     * Wenn es mehrere Kandidaten gibt, wird irgendeiner davon gewählt.
     * @return der häufigste Buchstabe
     */
    public char top() {
        if(size() == 0)
            throw new java.util.NoSuchElementException("empty collection");
        int max = 0;
        char top = FIRST;
        for(final char l : ALPHABET)
            if(count(l) > max) {
                max = count(l);
                top = l;
            }
        return top;
    }

    @Override
    public String toString() {
        String result = "";
        if(size() == 0)
            result = "()";
        else {
            for(final char l : ALPHABET)
                for(int i = 0; i < count(l); i++)
                    result += ", " + l;
            result = '(' + result.substring(2) + ')';
        }
        return result;
    }

    /**
     * Erzeugt neue Buchstabensammlung aus einer Häufigkeitstabelle.
     * @param lc die Häufigkeitstabelle
     */
    private LetterColl(final int[] lc) {
        lettercount = lc;
        int count = 0;
        for(final int c : lc)
            count += c;
        letters = count;
    }

    /**
     * Liefert eine neue Sammlung mit den Buchstaben, die häufiger als m-mal vorkommen.
     * @param threshold untere Grenze für Auswahl
     * @return die neue Buchstabensammlung
     */
    public LetterColl moreThan(final int threshold) {
        final int[] reducedLettercount = new int[N];
        for(final char l : ALPHABET)
            if(count(l) > threshold)
                reducedLettercount[l - FIRST] = count(l);
        return new LetterColl(reducedLettercount);
    }

    /**
     * Liefert die Differenz dieser Sammlung und einer anderen Sammlung.
     * @param lc die andere Sammlung
     * @return die Differenz
     */
    public LetterColl except(final LetterColl lc) {
        final int[] reducedLettercount = new int[N];
        for(final char l : ALPHABET)
            reducedLettercount[l - FIRST] = Math.max(0, count(l) - lc.count(l));
        return new LetterColl(reducedLettercount);
    }

    @Override
    public boolean equals(final Object obj) {
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        final LetterColl other = (LetterColl) obj;
        return Arrays.equals(lettercount, other.lettercount);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(lettercount);
    }

    /**
     * Testprogramm für Buchstabensammlung.
     * @param args nicht verwendet
     */
    public static void main(final String[] args) {
        final LetterColl lc = new LetterColl('A', 'B', 'R', 'A', 'K', 'A', 'D', 'A', 'B', 'R', 'A');
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
