/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Kommentar-Zapper
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package commentx;

/**
 * Definiert den Zustandsübergang in einem endlichen Automaten.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 14.06.2008
 */
public class Transition {
    /**
     * Nachfolgezustand
     */
    private final State next;

    /**
     * Ausgabe bei Zustandsübergang
     */
    private final char[] output;

    public Transition(final State next, final char... output) {
        this.next = next;
        this.output = output;
    }

    public State next() {
        return next;
    }

    public char[] output() {
        return output;
    }

}
