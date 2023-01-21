/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Teppiche
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package carpet;

/**
 * Definiert die main-Methode.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu 
 * @version 2008-05-16
 */
public class Rhombs {
    /**
     * Gibt einen "Teppich" aus Textzeichen auf der Konsole aus. 
     * @param args Kommandozeilenargumente:
     * <ol><li>Kantenlänge einer Raute (Anzahl Zeilen bzw. Spalten) als ganze, ungerade Zahl.
     * <li>Größe des Teppichs (Anzahl Rauten senkrecht bzw. quer) als ganze, positive Zahl.
     * </ol>
     */
    public static void main(final String[] args) {
        final int m = Integer.parseInt(args[0]); 
        final int n = Integer.parseInt(args[1]); 

        for(int r = 0; r < n; r++) {
            if(r > 0) {
                for(int c = 0; c < m*n + n - 1; c++)
                    System.out.print('.');
                System.out.println();
            }
            for(int l = 0; l < m; l++) {
                for(int c = 0; c < n; c++) { 
                    if(c > 0)
                        System.out.print('.');
                    for(int d = 0; d < m; d++)
                        System.out.print(Math.abs(m/2 - l) + Math.abs(m/2 - d) <= m/2?  'O':  '.');
                }
                System.out.println();
            }
        }
    }
}
