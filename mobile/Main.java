/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Mobiles
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package mobile;

/**
 * Rahmenklasse für Mobile-Testprogramm.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 15.06.2008
 */
public class Main {
    
    /**
     * Konstruiert ein Mobile, balanciert es aus und gibt es aus.
     * @param args nicht verwendet
     */
    public static void main(final String[] args) {
        final Star a = new Star(2);
        final Star b = new Star(4);
//        final GlitterStar c = new GlitterStar(9);
        final Star c = new Star(9);
        final Wire ab = new Wire(a, b, 9);
        final Wire abc = new Wire(ab, c, 10);

        abc.balance();
        System.out.println(abc);

//        c.decorate();
//        c.decorate();
//        c.decorate();
        abc.balance();
        System.out.println(abc);
    }

}
