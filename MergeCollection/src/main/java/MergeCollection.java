import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Problem 1
 * Collections:
 * Write a java merge class that will merge two sorted collections of the same kind into a single sorted collection.
 * You need to think of how to design this class in a generic form and efficiently.
 *
 * @param <T>
 */

public class MergeCollection<T> {
    /**
     * comparator for sorting
     */
    Comparator<T> comparator;

    /**
     *
     * @param comparator
     */
    public MergeCollection(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * set the comparator of sorting
     * @param comparator
     */
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * Given 2 sorted collections, merge them based on the comparator
     * @param c1 sorted collection
     * @param c2 sorted collection
     * @return merged collection, remains sorted.
     */
    public Collection<T> mergeSortedCollections(Collection<T> c1, Collection<T> c2) {
        if (comparator == null) {
            System.out.println("Unsupported merge: comparator is null");
            return null;
        }
        return mergeCollections(c1, c2);
    }

    /**
     * inner call of merging
     * @param c1 sorted collection
     * @param c2 sorted collection
     * @return merged sorted collection
     */
    private Collection<T> mergeCollections(Collection<T> c1, Collection<T> c2) {
        Iterator<T> i1 = c1.iterator();
        Iterator<T> i2 = c2.iterator();
        if (!i1.hasNext()) return c2;
        if (!i2.hasNext()) return c1;
        T n1 = i1.next(), n2 = i2.next();
        List<T> result = new ArrayList<>(c1.size() + c2.size());
        // iterated adding smaller element, based on comparator, into result collection
        while (n1 != null && n2 != null) {
            if (comparator.compare(n1, n2) >= 0) {
                result.add(n2);
                if (i2.hasNext())
                    n2 = i2.next();
                else
                    break;
            } else {
                result.add(n1);
                if (i1.hasNext())
                    n1 = i1.next();
                else
                    break;
            }
        }
        // finish adding the tail part of the remaining collection
        if (i1.hasNext()) {
            result.add(n1);
            return fillRest(result, i1);
        }
        result.add(n2);
        return fillRest(result, i2);
    }

    /**
     * Add remaining part into target collection
     * @param tar target collection, merged collection
     * @param iter collection that has remaining elements
     * @return merged collection with all the elements
     */
    private Collection<T> fillRest(Collection<T> tar, Iterator<T> iter) {
        while (iter.hasNext()) {
            tar.add(iter.next());
        }
        return tar;
    }
}
