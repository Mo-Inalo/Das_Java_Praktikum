/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Polymorphe Methoden
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package polymorph;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Definiert statische, polymorphe Methoden.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 2008-05-16
 */
public class Util {
    /**
     * Liefert das am weitesten links stehende Argument zurück,
     * das verschieden von null ist.
     * @param args Liste von beliebig vielen Referenzen, einschließlich null.
     * @return Linkestes Argument, das von null verschieden ist.
     * null, wenn es kein solches gibt.
     */
    public static <T> T nonull(final T... args) {
        for(final T element: args)
            if(element != null)
                return element;
        return null;
    }

    /**
     * Liefert das Argument zurück, das der Größe nach in der Mitte steht.
     * Benutzt die "natürliche Ordnung" des Argumenttyps.
     * @param a Referenz, darf nicht null sein.
     * @param b Referenz, darf nicht null sein.
     * @param c Referenz, darf nicht null sein.
     * @return Mittleres Argument.
     */
    public static <T extends Comparable <? super T>> T median(final T a, final T b, final T c) {
        if(a.compareTo(b) < 0)
            if(b.compareTo(c) < 0)
                return b;
            else if(a.compareTo(c) < 0)
                return c;
            else
                return a;
        else if(b.compareTo(c) < 0)
            if(a.compareTo(c) < 0)
                return a;
            else
                return c;
        else
            return b;
    }

    /**
     * Produziert count Clones des Objektes orig und liefert sie in einem Array zurück.
     * Für negative count ist das Array leer.
     * @param orig Objekt, darf nicht null sein.
     * @param count Anzahl Clones.
     * @return Arrays mit count Clones des ersten Argumentes.
     * @throws java.lang.NoSuchMethodException
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.IllegalArgumentException
     * @throws java.lang.reflect.InvocationTargetException
     */
    @SuppressWarnings("unchecked")
    public static <T extends Cloneable> T[] cloneArray(final T orig, final int count) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        final Object[] result = (Object[])Array.newInstance(orig.getClass(), count);
        final Method clone = orig.getClass().getDeclaredMethod("clone");
        for(int i = 0; i < count; i++)
            result[i] = clone.invoke(orig);
        return (T[])result;
    }

    /**
     * Testprogramm.
     * @param args Wird ignoriert
     * @throws java.lang.NoSuchMethodException
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.IllegalArgumentException
     * @throws java.lang.reflect.InvocationTargetException
     */
    @SuppressWarnings("unchecked")
    public static void main(final String[] args) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        System.out.println(nonull(null, 23, null, 27));
        //System.out.println(nonull(null, null));
        //System.out.println(nonull());
        System.out.println(nonull((Object)null));

        System.out.println(median(1, 2, 3));
        System.out.println(median("abra", "ka", "dabra"));

        class Foo implements Cloneable {
            @Override
            public Foo clone() throws CloneNotSupportedException {
                return (Foo)super.clone();
            }
        }

        final Foo[] farry = cloneArray(new Foo(), 3);
        System.out.println(java.util.Arrays.toString(farry));
    }
}
