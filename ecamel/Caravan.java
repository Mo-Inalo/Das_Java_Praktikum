/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: E-Camel
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package ecamel;

/**
 * Definiert eine Karawane aus Kamelen.
 * Eine Karawane ist eine einfach verkettete lineare Liste aus Kamelen.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 14.06.2008
 */
public class Caravan {
    /**
     * Leitkamel = Startknoten.
     */
    private Camel first = null;

    /**
     * Liefert die Reisegeschwindigkeit dieser Karawane.
     * Wird vom langsamsten Kamel bestimmt.
     * @return Reisegeschwindigkeit
     */
    public int pace() {
        int pace = Integer.MAX_VALUE;
        for(Camel c = first; c != null; c = c.getNext())
            pace = Math.min(pace, c.pace());
        return pace;
    }

    /**
     * Fügt das Kamel in diese Karawane ein.
     * @param c Kamel
     */
    public void addCamel(final Camel c) {
        c.setNext(first);
        first = c;
    }

    /**
     * Nimmt das Kamel c aus dieser Karawane heraus.
     * @param x
     */
    public void removeCamel(final Camel x) {
        if(x == first)
            first = x.getNext();
        else {
            Camel c = first;
            while(c.getNext() != x)
                c = c.getNext();
            c.setNext(x.getNext());
        }
    }

    /**
     * Bestimmt das aktuell schnellste Kamel der Karawane.
     * @return schnellstes Kamel
     */
    private Camel findFastestCamel() {
        Camel fastest = first;
        for(Camel c = first; c != null; c = c.getNext())
            if(c.pace() > fastest.pace())
                fastest = c;
        return fastest;
    }

    /**
     * Verteilt zusätzliche Ladung auf die Kamele dieser Karawane,
     * sodass die Reisegeschwindigkeit möglichst hoch bleibt.
     * @param l  Anzahl Ballen Ladung
     */
    public void addLoad(final int l) {
        int remaining = l;
        while(remaining > 0) {
            final Camel fastest = findFastestCamel();
            fastest.setLoad(fastest.getLoad() + 1);
            remaining--;
        }
    }

    /**
     * Testprogramm für Karawane.
     * @param args nicht verwendet
     */
    public static void main(final String[] args) {
        final Camel ataAllah = new Camel(8);
        final Camel desertWind = new Camel(7);

        final Caravan saharaExpress = new Caravan();
        saharaExpress.addCamel(ataAllah);
        saharaExpress.addCamel(desertWind);
        System.out.println(saharaExpress.pace());
        saharaExpress.addLoad(5);
        System.out.println(saharaExpress.pace());
    }

}
