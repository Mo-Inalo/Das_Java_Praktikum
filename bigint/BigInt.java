/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Große Ganzzahlen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package bigint;

/**
 * Definiert beliebig große nicht-negative ganze Zahlen.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public class BigInt {
    /**
     * Dezimalzifferndarstellung der Zahl.
     */
    private final String digits;

    /**
     * Erzeugt eine Zahl, deren Dezimaldarstellung in 's' gegeben ist.
     * 's' besteht ausschließlich aus Dezimalziffern.
     * @param s die Dezimaldarstellung
     */
    public BigInt(final String s) {
        digits = s;
    }

    /**
     * Erzeugt eine Zahl mit dem Wert von 'n' >=0.
     * @param n
     */
    public BigInt(int n) {
        if(n == 0)
            digits = "0";
        else {
            String tmp = "";
            while(n > 0) {
                tmp = n%10 + tmp;
                n /= 10;
            }
            digits = tmp;
        }
    }

    /**
     * Getter für die p-te Dezimalziffer dieser Zahl.
     * @param p Index
     * @return p-te Dezimalziffer dieser Zahl
     */
    private int digit(final int p) {
        if(p >= digits.length())
            return 0;
        return digits.charAt(digits.length() - p - 1) - '0';
    }

    /**
     * Liefert 'true', wenn  diese Zahl kleiner als der Parameter 'b' ist
     * @param b die Vergleichszahl
     * @return true, wenn diese Zahl kleiner als der Parameter 'b' ist
     */
    public boolean less(final BigInt b) {
        int p = Math.max(digits.length(), b.toString().length()) - 1;
        while(p >= 0)
            if(digit(p) > b.digit(p))
                return false;
            else if(digit(p) < b.digit(p))
                return true;
            else
                p--;
        return false;
    }

    /**
     * Liefert eine neue Zahl mit der Summe dieser Zahl und dem Parameter 'b'.
     * @param b der Summand
     * @return die Summe
     */
    public BigInt add(final BigInt b) {
        final int max = Math.max(digits.length(), b.toString().length());
        String tmp = "";
        int p = 0;
        int carry = 0;
        while(p < max) {
            final int n = digit(p) + b.digit(p) + carry;
            tmp = n%10 + tmp;
            carry = n/10;
            p++;
        }
        if(carry > 0)
            tmp = carry + tmp;
        return new BigInt(tmp);
    }

    @Override
    public String toString() {
        return digits;
    }

    /**
     * Testprogramm für BigInt, berechnet die Fibonacci-Zahl f(10000).
     * @param args nicht verwendet
     */
    public static void main(final String[] args) {
        final BigInt n = new BigInt(10000);
        final BigInt one = new BigInt(1);
        BigInt last = one;
        BigInt butlast = one;
        BigInt i = one;
        while(i.less(n)) {
            final BigInt tmp = last.add(butlast);
            butlast = last;
            last = tmp;
            i = i.add(one);
        }
        System.out.println(last);
    }

}

