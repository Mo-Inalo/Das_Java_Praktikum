/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Chiffren
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package cipher;

import java.util.Arrays;
import java.util.Random;

import static power.Power.pow;

/**
 * Definiert eine Diffie-Hellman-Chiffre. Mittels asymmetrischem
 * Diffie-Hellman-Schl�sselaustausch wird ein symmetrischer Schl�ssel vereinbart
 * und zur Verschl�sselung mit einer Stromchiffre verwendet.
 * 
 * @author Klaus K�hler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public class DHCipher implements Cipher {
    /**
     * Gemeinsam verwendete Primzahl, nicht geheim.
     */
    private final int prime;
    /**
     * Geheimer Exponent.
     */
    private final int secretKey;
    /**
     * �ffentlicher Exponent.
     */
    private final int publicKey;

    /**
     * Konstruiert ein Schl�sselpaar.
     * @param n gemeinsam genutzte Primzahl
     * @param g Generator (= erzeugendes Element der multiplikativen Gruppe Zp*)
     */
    public DHCipher(int n, int g) {
        prime = n;
        secretKey = new Random().nextInt(prime - 1) + 1;
        publicKey = pow(g, secretKey, prime);
    }

    public int getPublicKey() {
        return publicKey;
    }

    /**
     * Berechnet den gemeinsam genutzten (geheimen) symmetrischen Schl�ssel.
     * @param otherKey �ffentlicher Schl�ssel des Partners
     * @return gemeinsamer symmetrischer Schl�ssel
     */
    private int sharedKey(final int otherKey) {
        return pow(otherKey, secretKey, prime);
    }

    public String encrypt(final String plainText, final int otherKey) {
        final int key = sharedKey(otherKey);
        final StreamCipher cipher = new StreamCipher();
        return cipher.encrypt(plainText, key);
    }

    public String decrypt(final String cryptText, final int otherKey) {
        final int key = sharedKey(otherKey);
        final StreamCipher cipher = new StreamCipher();
        return cipher.decrypt(cryptText, key);
    }

    /**
     * Testprogramm f�r Diffie-Hellman-Chiffre.
     * @param args Klartext
     */
    public static void main(final String[] args) {
        final String plainText = args[0];
        final int prime = Integer.MAX_VALUE;
        final int generator = 2;
        final DHCipher alice = new DHCipher(prime, generator);
        final DHCipher bob = new DHCipher(prime, generator);
        final String cryptText = alice.encrypt(plainText, bob.getPublicKey());
        System.out.println(Arrays.toString(cryptText.getBytes()));
        final String decoded = bob.decrypt(cryptText, alice.getPublicKey());
        System.out.println(plainText.equals(decoded));
    }
}
