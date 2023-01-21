/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Chiffren
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package cipher;

/**
 * Definiert eine Chiffre.
 * 
 * @author Klaus K�hler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public interface Cipher {
    /**
     * Verschl�sselt Klartext zu Chiffretext.
     * @param plainText Klartext
     * @param key Schl�ssel
     * @return Chiffretext
     */
    String encrypt(final String plainText, int key);

    /**
     * Entschl�sselt Chiffretext zu Klartext.
     * @param cryptText Chiffretext
     * @param key Schl�ssel
     * @return Klartext
     */
    String decrypt(final String cryptText, int key);
}
