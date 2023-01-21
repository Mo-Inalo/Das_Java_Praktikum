/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Chiffren
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package cipher;

/**
 * Definiert abstrakt eine einfache Substitutionschiffre.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public abstract class Substitution implements Cipher {
    /**
     * Verschlüsselt ein byte.
     * @param c zu verschlüsselndes byte
     * @param key Schlüssel
     * @return verschlüsseltes byte
     */
    abstract public byte encrypt(final byte c, final int key);

    /**
     * Entschlüsselt ein byte.
     * @param c zu entschlüsselndes byte
     * @param key Schlüssel
     * @return entschlüsseltes byte
     */
    abstract public byte decrypt(final byte c, final int key);

    /**
     * Verschlüsselt einen Text durch Verschlüsselung der Einzelzeichen.
     * @param plainText zu verschlüsselnder Klartext
     * @param key Schlüssel
     * @return Chiffretext
     */
    public String encrypt(final String plainText, final int key) {
        final byte[] bytes = plainText.getBytes();
        for(int i = 0; i < bytes.length; i++)
            bytes[i] = encrypt(bytes[i], key);
        return new String(bytes);
    }

    /**
     * Entschlüsselt einen Text durch Entschlüsselung der Einzelzeichen.
     * @param cryptText zu entschlüsselnder Chiffretext
     * @param key Schlüssel
     * @return Klartext
     */
    public String decrypt(final String cryptText, final int key) {
        final byte[] bytes = cryptText.getBytes();
        for(int i = 0; i < bytes.length; i++)
            bytes[i] = decrypt(bytes[i], key);
        return new String(bytes);
    }
}
