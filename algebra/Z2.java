/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Algebraische Strukturen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package algebra;

/**
 * Definiert den Körper mit 2 Elementen 0, 1.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public class Z2 implements Field<Z2> {
    public final static Z2 ZERO = new Z2(0);
    public final static Z2 ONE = new Z2(1);
    
    private final int value;

    public Z2(int i) {
        value = Math.abs(i % 2);
    }

    public boolean isZero() {
        return this.equals(ZERO);
    }

    public boolean isOne() {
        return this.equals(ONE);
    }

    @Override
    public boolean equals(final Object x) {
        if (x == null)
            return false;
        if (x.getClass() != getClass())
            return false;
        final Z2 other = (Z2) x;
        return other.value == value;
    }

    public Z2 add(final Z2 other) {
        return new Z2(value + other.value);
    }

    public Z2 sub(final Z2 other) {
        return add(other);
    }

    public Z2 mult(final Z2 other) {
        return new Z2(value * other.value);
    }

    public Z2 div(final Z2 other) {
        if (other.isZero())
            throw new ArithmeticException("Division by zero");
        return mult(other);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public int hashCode() {
        return value;
    }

    /** 
     * Testprogramm.
     * @param args Wird ignoriert
     */
    public static void main(final String[] args) {
        final Z2 i1 = new Z2(0);
        final Z2 i2 = new Z2(-1);
        System.out.println(i1 + " - " + i2 + " = " + i1.sub(i2));
        System.out.println(i1 + "/" + i2 + " = " + i1.div(i2));
        System.out.println(i2 + "/" + i1 + " = " + i2.div(i1));
    }

}
