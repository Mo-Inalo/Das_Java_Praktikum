/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Relationen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package relation;

import java.util.Set;

/**
 * Verkettete Relation.
 * Verkettete Relation V entsteht aus den Relationen R und S:
 *  (a,b) in R und (b,c) in S ==> (a,c) in V.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 27.06.2008
 */
public class Composed<T> extends Relation<T> {
    private final Relation<T> first;

    private final Relation<T> second;

    /**
     * Erzeugt eine verkettete Relation aus zwei Relationen.
     * @param fst erste Relation
     * @param snd zweite Relation
     */
    public Composed(final Relation<T> fst, final Relation<T> snd) {
        first = fst;
        second = snd;
    }

    @Override
    public boolean related(final T a, final T b) {
        for(final T x: elements())
            if(first.related(a, x)  &&  second.related(x, b))
                return true;
        return false;
    }

    @Override
    public Set<T> elements() {
        return first.elements();
    }

    /**
     * Testprogramm für verkettete Relation.
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
        final Relation<Integer> s = new Simple<Integer>(
            new Integer[] {0, 2, 3, 4},
            0, 0,
            2, 2,
            2, 3,
            3, 2,
            3, 3,
            4, 4);

        final Relation<Integer> rs = new Composed<Integer>(r, s);
        System.out.println(rs.related(0, 2));   // true
        System.out.println(rs.related(2, 0));   // false

        final Relation<Integer> rsr = new Composed<Integer>(rs, r);
        System.out.println(rsr.related(0, 2));  // false
        System.out.println(rsr.related(2, 0));  // true
    }
}
