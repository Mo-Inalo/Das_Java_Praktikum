/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Josephusring
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package josephus;

/**
 * Repräsentiert einen Gefangenen.
 * Gefangener ist Knoten in einer einfach verketteten Ringliste.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 15.06.2008
 */
public class Prisoner {
    /**
     * Eindeutiges Kennzeichen.
     */
    private final int id;

    /**
     * Nachfolger in der Ringliste.
     */
    private Prisoner next;

    /**
     * Nächste zu vergebene ID.
     */
    private static int nextId = 0;

    public Prisoner() {
        id = nextId++;
        next = this;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }

    public Prisoner next() {
        return next;
    }

    /**
     * Fügt einen neuen Gefangenen hinter "diesen" Gefangenen in die Ringlist ein.
     * @param p der neue Gefangene
     * @return "dieser" Gefangene 
     */
    public Prisoner insertNext(final Prisoner p) {
        p.next = next;
        next = p;
        return this;
    }

    /**
     * Entfernt den Nachfolger dieses Gefangenen aus der Ringliste.
     * @return "dieser" Gefangene
     */
    public Prisoner removeNext() {
        next = next.next;
        return this;
    }

}
