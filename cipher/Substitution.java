/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Chiffren
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package cipher;

/**
 * Definiert abstrakt eine einfache Substitutionschiffre.
 *
 * @author Klaus K�hler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public abstract class Substitution implements Cipher {
    /**
     * Verschl�sselt ein byte.
     * @param c zu verschl�sselndes byte
     * @param key Schl�ssel
     * @return verschl�sseltes byte
     */
    abstract public byte encrypt(final byte c, final int key);

    /**
     * Entschl�sselt ein byte.
     * @param c zu entschl�sselndes byte
     * @param key Schl�ssel
     * @return entschl�sseltes byte
     */
    abstract public byte decrypt(final byte c, final int key);

    /**
     * Verschl�sselt einen Text durch Verschl�sselung der Einzelzeichen.
     * @param plainText zu verschl�sselnder Klartext
     * @param key Schl�ssel
     * @return Chiffretext
     */
    public String encrypt(final String plainText, final int key) {
        final byte[] bytes = plainText.getBytes();
        for(int i = 0; i < bytes.length; i++)
            bytes[i] = encrypt(bytes[i], key);
        return new String(bytes);
    }

    /**
     * Entschl�sselt einen Text durch Entschl�sselung der Einzelzeichen.
     * @param cryptText zu entschl�sselnder Chiffretext
     * @param key Schl�ssel
     * @return Klartext
     */
    public String decrypt(final String cryptText, final int key) {
        final byte[] bytes = cryptText.getBytes();
        for(int i = 0; i < bytes.length; i++)
            bytes[i] = decrypt(bytes[i], key);
        return new String(bytes);
    }
}
