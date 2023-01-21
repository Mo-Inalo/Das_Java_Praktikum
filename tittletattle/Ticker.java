/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Tittle-Tattle
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package tittletattle;

/**
 * Ticker wartet auf das Eintreten eines Ereignisses.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 29.06.2008
 */
public interface Ticker {
    /**
     * Wartet auf das nächste Ereignis und kehrt zurück, sobald es eingetreten ist.
     * Der Aufruf blockiert so lange, bis ein Ereignis eintritt.
     * @return ganze Zahl
     */
    int tick();
}
