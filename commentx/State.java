/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Kommentar-Zapper
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package commentx;

/**
 * Repräsentiert den Zustand des endlichen Automaten für den Kommentar-Zapper.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 14.06.2008
 */
public enum State {
    Source, Into, Comment, Outof, Line
}
