/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Relationen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package relation;

import java.util.*;

/**
 * Relation mit aufgezählten Elementen.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 27.06.2008
 */
public class Simple<T> extends Relation<T> {

    /**
     * Grundmenge M und Menge der Paare der Relation R.
     * Repräsentiert durch Abbildung r: M --> Potenzmenge(M),
     * (a,b) in R <==> b in r(a), also a --> {..., b, ...}
     * Die Schlüssel der Map sind die Elemente der Grundmenge M.
     * Der zu einem Schlüssel gehörige Wert ist die Menge aller Elemente,
     * die mit dem Schlüsselelement in Relation stehen
     */
    private final Map<T, Set<T>> relatedTo = new HashMap<T, Set<T>>();

    /**
     * Erzeugt eine aufgezählte Relation.
     * @param e Array mit den Elementen der Grundmenge
     * @param p Folge der Paare a,b der Relation
     */
    public Simple(final T[] e, final T... p) {
        for(final T t : e) {
            relatedTo.put(t, new HashSet<T>());
        }
        for(int i = 0; i < p.length; i += 2)
            relatedTo.get(p[i]).add(p[i + 1]);
    }

    @Override
    public boolean related(final T a, final T b) {
        return relatedTo.get(a).contains(b);
    }

    @Override
    public Set<T> elements() {
        return relatedTo.keySet();
    }

    /**
     * Testprogramm für aufgezählte Relation Simple.
     * @param args nicht verwendet
     */
    public static void main(final String[] args) {
        final Relation<Integer> r = new Simple<Integer>(
            new Integer[] {0, 2, 3, 4},
            0, 2,
            0, 4,
            2, 4,
            2, 3,
            3, 4,
            3, 0);
        System.out.println(r.related(0, 2));    // true
        System.out.println(r.related(2, 0));    // false
    }
}
