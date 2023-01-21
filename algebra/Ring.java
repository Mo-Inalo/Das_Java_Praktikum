/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Algebraische Strukturen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package algebra;

/**
 * Definiert die algebraische Struktur "Ring".
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public interface Ring<R extends Ring<R>> extends Group<R> {
    /** 
     * Multiplikation.
     * @param other Multiplikator
     * @return Produkt
     */
    R mult(final R other);

    /** 
     * Überprüft, ob neutrales Element der Multiplikation.
     * @return true, wenn neutral bzgl. der Multiplikation
     */
    boolean isOne();
}
