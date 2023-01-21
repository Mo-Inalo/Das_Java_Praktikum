/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: E-Camel
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package ecamel;

/**
 * Definiert ein Kamel.
 * Knoten einer einfach verketteten linearen Liste Karawane.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 14.06.2008
 */
public class Camel {
    /**
     * Maximale Marschgeschwindigkeit unbeladen.
     */
    private final int maxPace;

    /**
     * Anzahl der Ballen Ladung.
     */
    private int load = 0;

    /**
     * Nachfolger in der Karawane.
     */
    private Camel next = null;

    /**
     * Erzeugt ein isoliertes Kamel.
     * @param mp maximale Marschgeschwindigkeit unbeladen
     */
    public Camel(final int mp) {
        maxPace = mp;
    }

    /**
     * Liefert die tatsächliche Geschwindigkeit des Kamels.
     * @return tatsächliche Geschwindigkeit unter Berücksichtigung der Ladung
     */
    public int pace() {
        return Math.max(0, maxPace - load);
    }

    public int getLoad() {
        return load;
    }

    public int getMaxPace() {
        return maxPace;
    }

    public Camel getNext() {
        return next;
    }

    public void setLoad(final int load) {
        this.load = load;
    }

    void setNext(final Camel next) {
        this.next = next;
    }
}
