/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Chiffren
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package cipher;

/**
 * Definiert eine Chiffre.
 * 
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public interface Cipher {
    /**
     * Verschlüsselt Klartext zu Chiffretext.
     * @param plainText Klartext
     * @param key Schlüssel
     * @return Chiffretext
     */
    String encrypt(final String plainText, int key);

    /**
     * Entschlüsselt Chiffretext zu Klartext.
     * @param cryptText Chiffretext
     * @param key Schlüssel
     * @return Klartext
     */
    String decrypt(final String cryptText, int key);
}
