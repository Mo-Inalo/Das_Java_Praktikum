/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Relationen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package relation;

import java.util.*;

/**
 * Repräsentiert eine binäre, homogene Relation R auf einer Menge M.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 27.06.2008
 */
public abstract class Relation<T> {

    /**
     * Liefert genau dann true, wenn diese Relation (a, b) enthält.
     * @param a erstes Element
     * @param b zweites Element
     * @return true, wenn diese Relation (a, b) enthält
     */
    abstract public boolean related(T a, T b);

    /**
     * Liefert die Menge M, auf der diese Relation deniert ist.
     * @return Grundmenge M
     */
    abstract public Set<T> elements();

    /**
     * Ist diese Relation rflexiv?
     * @return
     */
    public boolean isReflexive() {
        for(final T t: elements())
            if(!related(t, t))
                return false;
        return true;
    }

    /**
     * Ist diese Relation symmetrisch?
     * @return
     */
    public boolean isSymmetric() {
        for(final T t: elements())
            for(final T u: elements())
                if(related(t, u) != related(u, t))
                    return false;
        return true;
    }

    /**
     * Ist diese Relation transitiv?
     * @return
     */
    public boolean isTransitive() {
        for(final T t: elements())
            for(final T u: elements())
                for(final T v: elements())
                    if(related(t, u)  && related(u, v)  && !related(t, v))
                        return false;
        return true;
    }
}
