/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Chiffren
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package cipher;

import java.util.Arrays;
import java.util.Random;

/**
 * Definiert eine einfache Stromchiffre.
 * 
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public class StreamCipher implements Cipher {
    private final Random keyGenerator = new Random();

    /**
     * Verschlüsselung. 
     * Erzeuge einen pseudo-zufälligen Schlüsselstrom und 
     * verknüpfe ihn per XOR mit dem Klartextstrom.
     * Achtung: Setzt invertierbares Encoding voraus, z.B. Latin-1 (ISO-8859-1),
     *          Standardeinstellung unter Unix, nicht unter Windows.
     */
    public String encrypt(final String plainText, final int key) {
        keyGenerator.setSeed(key);
        final byte[] bytes = plainText.getBytes();
        final Substitution cipher = new XorSubstitution();
        for(int i = 0; i < bytes.length; i++) {
            final int runKey = keyGenerator.nextInt();
            bytes[i] = cipher.encrypt(bytes[i], runKey);
        }
        return new String(bytes);
    }

    /**
     * Entschlüsselung = Verschlüsselung.
     */
    public String decrypt(final String cryptText, final int key) {
        return encrypt(cryptText, key);
    }

    /**
     * Testprogramm für Stromchiffre.
     * @param args Klartext
     */
    public static void main(final String[] args) {
        final String plainText = args[0];
        final int key = Integer.parseInt(args[1]);
        final Cipher cipher = new StreamCipher();
        final String cryptText = cipher.encrypt(plainText, key);
        System.out.println(Arrays.toString(cryptText.getBytes()));
        final String decoded = cipher.decrypt(cryptText, key);
        System.out.println(plainText.equals(decoded));
    }
}
