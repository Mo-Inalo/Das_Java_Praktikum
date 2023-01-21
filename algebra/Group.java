/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Algebraische Strukturen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package algebra;

/**
 * Definiert die algebraische Struktur "Gruppe".
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public interface Group<G extends Group<G>> {
    /**
     * Addition.
     * @param other Summand
     * @return Summe
     */
    G add(final G other);

    /**
     * Subtraktion.
     * @param other Operand
     * @return Differenz
     */
    G sub(final G other);

    /**
     * Überprüft, ob neutrales Element der Addition
     * @return true, wenn neutral bzgl. der Addition
     */
    boolean isZero();
}