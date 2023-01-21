/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Algebraische Strukturen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package algebra;

/**
 * Definiert die algebraische Struktur "K�rper".
 *
 * @author Klaus K�hler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public interface Field<F extends Field<F>> extends Ring<F> {
    /**
     * Division.
     * @param other Divisor
     * @return Quotient
     */
    F div(final F other);
}
