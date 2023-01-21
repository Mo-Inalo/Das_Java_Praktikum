/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Chiffren
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package cipher;

import java.util.Arrays;

/**
 * Definiert eine additive Substitutionschiffre.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public class AdditiveSubstitution extends Substitution {
    @Override
    public byte encrypt(final byte c, final int key) {
        return (byte)(c + key);
    }

    @Override
    public byte decrypt(final byte c, final int key) {
        return (byte)(c - key);
    }

    /**
     * Testprogramm zur Ver- und Entschlüsselung mittels additiver Substitution.
     * @param args Klartext
     */
    public static void main(final String[] args) {
        final String plainText = args[0];
        final int key = Integer.parseInt(args[1]);
        final Cipher cipher = new AdditiveSubstitution();
        final String cryptText = cipher.encrypt(plainText, key);
        System.out.println(Arrays.toString(cryptText.getBytes()));
        final String decoded = cipher.decrypt(cryptText, key);
        System.out.println(plainText.equals(decoded));
    }
}
