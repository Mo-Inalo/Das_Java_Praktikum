/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: 39. Mustervergleich
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package stringmatch;

/**
 * Klassenrahmen für Methoden zum Mustervergleich von Strings.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 26.05.2008
 */
public class StringMatch {
    /**
     * JOKER passt auf jedes Einzelzeichen.
     */
    public final static char JOKER = '?';

    /**
     * Prüft, ob eine Maske zum String passt.
     * @param pattern die Maske
     * @param string der Vergleichs-String
     * @return true wenn die Maske zum String passt, sonst false.
     */
    public static boolean match(final String pattern, final String string) {
        if(pattern.isEmpty())
            return string.isEmpty();
        if(string.isEmpty())
            return false;
        if(pattern.charAt(0) == JOKER  || pattern.charAt(0) == string.charAt(0))
            return match(pattern.substring(1), string.substring(1));
        else
            return false;
    }

    /**
     * SUPER_JOKER passt auf jeden String.
     */
    public final static char SUPER_JOKER = '*';

    /**
     * Prüft, ob eine Maske zum String passt.
     * @param pattern die Maske
     * @param string der Vergleichs-String
     * @return true wenn die Maske zum String passt, sonst false.
     */
    public static boolean superMatch(final String pattern, final String string) {
        if(pattern.isEmpty())
            return string.isEmpty();
        if(pattern.charAt(0) == SUPER_JOKER) {
            if(superMatch(pattern.substring(1), string))
                return true;
            if(string.isEmpty())
                return false;
            return superMatch(pattern, string.substring(1));
        }
        if(string.isEmpty())
            return false;
        if(pattern.charAt(0) == JOKER  || pattern.charAt(0) == string.charAt(0))
            return superMatch(pattern.substring(1), string.substring(1));
        return false;
    }

    /**
     * Testprogramm zum Mustervergleich.
     * @param args wird ignoriert
     */
    public static void main(final String[] args) {
        System.out.println(match("1?3?", "1234"));          // true
        System.out.println(match("1?3?", "1324"));          // false
        System.out.println(match("1?3?", "123"));           // false
        System.out.println(superMatch("*3*2*", "311245"));  // true
        System.out.println(superMatch("*3*2*", "32"));      // true
        System.out.println(superMatch("*3*2*", "1234"));    // false
        System.out.println(superMatch("*3*2*", "3"));       // false
    }
}
