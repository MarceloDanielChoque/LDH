package es.ull.esit.utilities;

import java.util.BitSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.NoSuchElementException; //linea nueva agregada para corregir error sonarQube

// Sirve para calcular todos los subconjuntos de un conjunto dado
public class PowerSet<E> implements Iterable<Set<E>> {

    private E[] arr = null;
    private BitSet bset = null;

    @SuppressWarnings("unchecked")
    public PowerSet(Set<E> set) {
        this.arr = (E[]) set.toArray();
        this.bset = new BitSet(this.arr.length + 1);
    }

    private class PowerSetIterator implements Iterator<Set<E>> {
        private final BitSet localBset = (BitSet) bset.clone();

        @Override
        public boolean hasNext() {
            return !localBset.get(arr.length);
        }

        @Override
        public Set<E> next() {
            Set<E> returnSet = new TreeSet<>();
            for (int i = 0; i < arr.length; i++) {
                if (localBset.get(i)) {
                    returnSet.add(arr[i]);
                }
            }
            for (int i = 0; i < localBset.size(); i++) {
                if (!localBset.get(i)) {
                    localBset.set(i);
                    break;
                } else {
                    localBset.clear(i);
                }
            }
            return returnSet;
        }
    }

    @Override
    public Iterator<Set<E>> iterator() {
        return new PowerSetIterator();
    }
}