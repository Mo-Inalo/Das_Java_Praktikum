/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Bäume
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package tree;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Knoten in einem Baum mit beliebigen Verzweigungsgrad.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 29.06.2008
 */
public class Node<T> implements Iterable<Node<T>> {
    /**
     * Liste der Kindknoten.
     */
    private final Node<T>[] children;

    /**
     * Nutzinformation.
     */
    private final T info;

    /**
     * Erzeugt einen neuen Knoten mit gegebener Information und gegebenen Kindknoten.
     *
     * @param i  die Information (nicht null)
     * @param ch die Kindknoten  (nicht null)
     */
    public Node(final T i, final Node<T>... ch) {
        if(i == null)
            throw new IllegalArgumentException("null info not allowed");
        for(final Node<T> child: ch)
            if(child == null)
                throw new IllegalArgumentException("null child not allowed");
        children = ch;
        info = i;
    }

    public T info() {
        return info;
    }

    /**
     * Liefert die Anzahl der Kindknoten (0 bei Blättern).
     * @return
     */
    public int size() {
        return children.length;
    }

    @Override
    public Iterator<Node<T>> iterator() {
        return new ChildArrayIterator();
    }

    /**
     * Iterator über die Kindknoten.
     *
     * @author Klaus Köhler, koehler@hm.edu
     * @author Reinhard Schiedermeier, rs@cs.hm.edu
     * @version 29.06.2008
     */
    private class ChildArrayIterator implements Iterator<Node<T>> {
        
        /**
         * Index des nächsten Kindknoten.
         */
        private int at = 0;

        @Override
        public boolean hasNext() {
            return at < children.length;
        }

        @Override
        public Node<T> next() {
            return children[at++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("nodes are immutable");
        }

    }

    /**
     * Liefert die Höhe des Baums mit diesem Knoten als Wurzel, 
     * das heißt die maximale Anzahl Knotenebenen unter diesem Knoten.
     * @return die Höhe
     */
    public int height() {
        int result = 0;
        for(final Node<T> ch: this)
            result = Math.max(1 + ch.height(), result);
        return result;
    }

    /**
     * liefert eine Textform des Baums, an dessen Wurzel dieser Knoten steht. 
     * Ein Knoten wird in der folgenden Form abgebildet: information(child1, child2, ...)
     */
    @Override
    public String toString() {
        if(size() == 0)
            return info.toString();
        String result = "";
        for(final Node<T> ch: this)
            result += ", " + ch.toString();
        return info + "(" + result.substring(2) + ")";
    }

    @Override
    public boolean equals(final Object other) {
        if(other == null)
            return false;
        if(other.getClass() != getClass())
            return false;
        @SuppressWarnings("unchecked")
        final Node<T> that = (Node<T>)other;
        if(!info().equals(that.info()))
            return false;

        if(children.length != that.children.length)
            return false;
        for(int i = 0; i < children.length; i++)
            if(!children[i].equals(that.children[i]))
                return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        if(info != null)
            hash = 37*hash + info.hashCode();
        hash = 37*hash + Arrays.hashCode(children);
        return hash;
    }

    /**
     * Liefert einen neuen Baum, in dem alle Vorkommen eines gegebenen
     * Teilbaums old durch einen anderen Teilbaum nu ersetzt sind. 
     * Sollte old selbst innerhalb von nu vorkommen, so wird old dort nicht ersetzt.
     * @param old zu ersetzender Baum
     * @param nu  Ersatzbaum
     * @return der ersetzte Baum
     */
    @SuppressWarnings("unchecked")
    public Node<T> subst(final Node<T> old, final Node<T> nu) {
        if(equals(old))
            return nu;
        final Node<T>[] newchildren = new Node[size()];
        int index = 0;
        for(final Node<T> child: this)
            newchildren[index++] = child.subst(old, nu);
        return new Node<T>(info(), newchildren);
    }

    /**
     * Testprogramm für Bäume.
     * @param args nicht verwendet
     */
    @SuppressWarnings("unchecked")
    public static void main(final String[] args) {
        final Node<String> t = new Node<String>("Abra",
                                          new Node<String>("ka"),
                                          new Node<String>("da", new Node<String>("bra"), new Node<String>("sim")),
                                          new Node<String>("sa", new Node<String>("la", new Node<String>("bim"))));
        System.out.println(t);
        System.out.println(t.height());
        final Node<String> u = t.subst(new Node<String>("la", new Node<String>("bim")),
                                 new Node<String>("trala", new Node<String>("la")));
        System.out.println(u);
        System.out.println(u.hashCode());
    }

}
