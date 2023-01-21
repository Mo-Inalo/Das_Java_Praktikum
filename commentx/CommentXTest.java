/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Kommentar-Zapper
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package commentx;

/**
 * Quelltext als Eingabebeispiel für Kommentar-Zapper.
 * Nicht formatieren!!!
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 14.06.2008
 */
public class CommentXTest {
    public/* comment */static/**/void main(String[] args) {
        System.out.println(5/5);
        System.out.println(5/* comment ***//5);
        System.out.println(1/*/* //** comment */*1);
        System.out.println(5/* comment *
*/ - 4);
        System.out.println(5/* comment *
/*/ - 4);
    }
}
