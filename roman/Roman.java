/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Römische Zahlen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package roman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Römische Zahlen in vereinfachter Darstellung.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 28.06.2008
 */
class Roman implements Comparable<Roman> {

    /**
     * Numerischer Wert.
     */
    private final int numeral;

    /**
     * Normierte String-Darstellung.
     */
    private final String literal;

    /**
     * Zuordnung von römischen Ziffernzeichen zu numerischen Werten.
     */
    private static Map<Character, Integer> digitValue = new HashMap<Character, Integer>();

    /**
     * Statischer Initialisierer für die Zuordnungstabelle.
     */
    static {
        digitValue.put('I', 1);
        digitValue.put('V', 5);
        digitValue.put('X', 10);
        digitValue.put('L', 50);
        digitValue.put('C', 100);
        digitValue.put('D', 500);
        digitValue.put('M', 1000);
    }

    /**
     * Erzeugt eine römische Zahl aus ihrer String-Darstellung.
     * Normiert die String-Darstellung.
     * @param s die String-Darstellung
     */
    public Roman(final String s) {
        numeral = toNumeral(s);
        literal = toLiteral(numeral);
    }

    /**
     * Erzeugt eine römische Zahl aus ihrem numerischen Wert.
     * @param n der numerische Wert
     */
    public Roman(final int n) {
        if(n <= 0)
            throw new IllegalArgumentException("non-positive numeral");
        literal = toLiteral(n);
        numeral = n;
    }

   /**
     * Berechnet den Wert zu einer String-Darstellung.
     * @param s die String-Darstellung
     * @return  numerischer Wert
     */
    private static int toNumeral(final String s) {
        int last = 0;
        int result = 0;
        for(int i = 0; i < s.length(); i++) {
            final Integer d = digitValue.get(s.charAt(i));
            if(d == null)
                throw new IllegalArgumentException("invalid token: " + s.charAt(i));
            if(d > last)
                result -= last;
            else
                result += last;
            last = d;
        }
        return result + last;
    }

    @Override
    public String toString() {
        return literal;
    }

    /**
     * Zuordnung Werte --> Ziffernzeichen und Ziffernzeichenkombinationen.
     * Geordnet nach fallender Größe der Werte.
     * Ziffernzeichenkombination = Ziffernzeichen mit einem nachfolgenden größeren Ziffernzeichen.
     */
    private static Map<Integer, String> restLiteral = new TreeMap<Integer, String>(new Comparator<Integer>() {
        public int compare(final Integer i0, final Integer i1) {
            return i1 - i0;
        }
    });

    /**
     * Statischer Initialisierer für die Zuordnungstabelle.
     */
    static {
        restLiteral.put(1000, "M");
        restLiteral.put(900, "CM");
        restLiteral.put(500, "D");
        restLiteral.put(400, "CD");
        restLiteral.put(100, "C");
        restLiteral.put(90, "XC");
        restLiteral.put(50, "L");
        restLiteral.put(40, "XL");
        restLiteral.put(10, "X");
        restLiteral.put(9, "IX");
        restLiteral.put(5, "V");
        restLiteral.put(4, "IV");
        restLiteral.put(1, "I");
    }

    /**
     * Konvertiert numerischen Wert in String-Darstellung.
     * @param n numerischer Wert
     * @return String-Darstellung
     */
    private static String toLiteral(int n) {
        String result = "";
        for(final int rest: restLiteral.keySet())
            while(n >= rest) {
                result += restLiteral.get(rest);
                n -= rest;
            }
        return result;
    }

    /**
     * Addiert eine andere römische Zahl zu dieser Zahl.
     * @param r die andere römische Zahl
     * @return die Summe als neues Objekt
     */
    public Roman add(final Roman r) {
        return new Roman(numeral + r.numeral);
    }

    /**
     * Multipliziert diese Zahl mit einer anderen römischen Zahl.
     * @param r die andere römische Zahl
     * @return das Produkt als neues Objekt
     */
    public Roman mult(final Roman r) {
        return new Roman(numeral*r.numeral);
    }

    /**
     * Subtrahiert eine andere römische Zahl von dieser Zahl.
     * @param r die andere römische Zahl
     * @return die Differenz als neues Objekt
     */
    public Roman sub(final Roman r) {
        return new Roman(numeral - r.numeral);
    }

    /**
     * Dividiert diese Zahl durch eine andere römische Zahl.
     * @param r die andere römische Zahl
     * @return der Quotient als neues Objekt
     */
    public Roman div(final Roman r) {
        return new Roman(numeral/r.numeral);
    }

    @Override
    public boolean equals(final Object x) {
        if(x == null)
            return false;
        if(getClass() != x.getClass())
            return false;
        final Roman r = (Roman)x;
        return numeral == r.numeral;
    }

    @Override
    public int hashCode() {
        return numeral;
    }

    /**
     * Vergleicht diese Zahl mit einer anderen römischen Zahl.
     * @return  <0, wenn diese Zahl kleiner als die andere römische Zahl ist
     *          =0, wenn beide Zahlen denselben Wert haben
     *          >0, wenn diese Zahl größer als die andere römische Zahl ist
     */
    public int compareTo(final Roman r) {
        return numeral - r.numeral;
    }

    /**
     * Comparator bzgl. der Länge der normierten String-Darstellung.
     */
    public final static Comparator<Roman> LengthComparator = new Comparator<Roman>() {
        public int compare(final Roman r0, final Roman r1) {
            return r0.literal.length() - r1.literal.length();
        }

    };

    /**
     * Comparator bzgl. der lexikographischen Ordnung der normierten String-Darstellung.
     */
    public final static Comparator<Roman> StringComparator = new Comparator<Roman>() {
        public int compare(final Roman r0, final Roman r1) {
            return r0.literal.compareTo(r1.literal);
        }

    };

    /**
     * Testprogramm für römische Zahlen.
     * @param args nicht verwendet
     */
    public static void main(final String[] args) {
        {
            final Roman r1 = new Roman("LXIX");
            final Roman r2 = new Roman(26);

            Set<Roman> sr = new TreeSet<Roman>();
            sr.add(r1);
            sr.add(r2);
            sr.add(r1.sub(r2));
            sr.add(r1.add(r2));
            System.out.println(sr);

            sr = new TreeSet<Roman>(Roman.LengthComparator);
            sr.add(r1);
            sr.add(r2);
            sr.add(r1.sub(r2));
            sr.add(r1.add(r2));
            System.out.println(sr);

            final List<Roman> lr = new ArrayList<Roman>();
            lr.add(r1);
            lr.add(r2);
            lr.add(r1.sub(r2));
            lr.add(r1.add(r2));
            Collections.sort(lr);
            System.out.println(lr);
            Collections.sort(lr, Roman.LengthComparator);
            System.out.println(lr);
            Collections.sort(lr, Roman.StringComparator);
            System.out.println(lr);

            final Roman r = new Roman(24);
            System.out.println(r.add(new Roman("XIX")));
        }
    }

}

