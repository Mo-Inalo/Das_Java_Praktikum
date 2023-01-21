/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Algebraische Strukturen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package algebra;

/**
 * Definiert den Körper mit 2 Elementen 0, 1.
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public enum Z2e implements Field<Z2e> {
    ZERO, ONE;

    public Z2e add(final Z2e other) {
        return this == other? ZERO: ONE;
    }

    public Z2e sub(final Z2e other) {
        return add(other);
    }

    public boolean isZero() {
        return this == ZERO;
    }

    public boolean isOne() {
        return this == ONE;
    }

    public Z2e mult(final Z2e other) {
        return this == ZERO || other == ZERO? ZERO: ONE;
    }

    public Z2e div(final Z2e other) {
        if(other == ZERO)
            throw new ArithmeticException("division by zero");
        return mult(other);
    }

    /**
     * Testprogramm.
     * @param args wird ignoriert
     */
    public static void main(final String[] args) {
        final Z2e i1 = Z2e.ZERO;
        final Z2e i2 = Z2e.ONE;
        System.out.println(i1 + " - " + i2 + " = " + i1.sub(i2));
        System.out.println(i1 + "/" + i2 + " = " + i1.div(i2));
        System.out.println(i2 + "/" + i1 + " = " + i2.div(i1));
    }

}
