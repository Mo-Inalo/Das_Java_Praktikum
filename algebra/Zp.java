/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Algebraische Strukturen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package algebra;

import static prime.Fermat.isPrime;
import static power.Power.pow;

/**
 * Definiert den endlichen Körper mit p Elementen.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public class Zp implements Field<Zp> {
    private final int prime;
    private final int value;

    public Zp(int p, int i) {
        if(!isPrime(p))
            throw new IllegalArgumentException(p + " is not a prime");
        prime = p;
        value = (i%p + p)%p;
    }

    public Zp add(final Zp f) {
        if(prime != f.prime)
            throw new IllegalArgumentException("Different field");
        return new Zp(prime, value + f.value);
    }

    public Zp sub(final Zp f) {
        if(prime != f.prime)
            throw new IllegalArgumentException("Different field");
        return new Zp(prime, value - f.value);
    }

    public Zp mult(final Zp f) {
        if(prime != f.prime)
            throw new IllegalArgumentException("Different field");
        return new Zp(prime, value*f.value);
    }

    public Zp div(final Zp f) {
        if(f.isZero())
            throw new ArithmeticException("Division by zero");
        return mult(f.inverse());
    }

    public Zp inverse() {
        if(isZero())
            throw new ArithmeticException("Division by zero");
        return new Zp(prime, pow(value, prime - 2, prime));
    }

    public boolean isZero() {
        return value == 0;
    }

    public boolean isOne() {
        return value == 1;
    }

    @Override
    public boolean equals(final Object x) {
        if(x == null)
            return false;
        if(x.getClass() != getClass())
            return false;
        final Zp other = (Zp)x;
        return other.value == value  && other.prime == prime;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 53*hash + prime;
        hash = 53*hash + value;
        return hash;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    /**
     * Testprogramm.
     * @param args wird ignoriert
     */
    public static void main(final String[] args) {
        final int p = 5;
        final Zp i1 = new Zp(p, 3);
        final Zp i2 = new Zp(p, 2);
        System.out.println("Operations in GF(" + p + "):");
        System.out.println(i1 + " - " + i2 + " = " + i1.sub(i2));
        System.out.println(i1 + "/" + i2 + " = " + i1.div(i2));
        System.out.println(i2 + "/" + i1 + " = " + i2.div(i1));
    }

}
