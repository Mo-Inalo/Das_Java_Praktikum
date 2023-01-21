/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Kommentar-Zapper
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package commentx;

import java.util.HashMap;
import java.util.Map;

/**
 * Definiert einen zweidimensionalen Assoziativspeicher.
 * D.h. eine Matrix, die durch Zeilen- und Spaltenindizes generischen Typs adressiert wird.
 * U Typ des Zeilenindex
 * V Typ des Spaltenindex
 * W Elementtyp der Matrix
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 14.06.2008
 */
public class Field<U, V, W> {
    /**
     * Matrix W[][] mit Zeilenindex vom Typ U und Spaltenindex vom Typ V
     */
    private final Map<U, Map<V, W>> map = new HashMap<U, Map<V, W>>();

    /**
     * Fügt Element w in der u-ten Zeile und v-ten Spalte ein.
     * @param u Zeilenindex
     * @param v Spaltenindex
     * @param w Wert
     */
    void put(final U u, final V v, final W w) {
        Map<V, W> tmp = map.get(u);
        if(tmp == null) {
            tmp = new HashMap<V, W>();
            map.put(u, tmp);
        }
        tmp.put(v, w);
    }

    /**
     * Holt Element an der Stelle (u,v).
     * @param u Zeilenindex
     * @param v Spaltenindex
     * @return Element
     */
    W get(final U u, final V v) {
        final Map<V, W> tmp = map.get(u);
        if(tmp == null)
            return null;
        return tmp.get(v);
    }

}
