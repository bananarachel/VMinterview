import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Random;
import java.util.TreeSet;

/**
 *  Test of class Merge collection
 *  Test 1 tests merging 2
 */
public class TestMergeCollection {

    /**
     * Test sorted set
     * @throws Exception
     */
    @Test
    public void testSet() throws Exception
    {
        /*

         */
        Random random = new Random(15);
        int range = 30;
        TreeSet<Integer> treeSet1 = new TreeSet<>();
        TreeSet<Integer> treeSet2 = new TreeSet<>();
        for (int i = 0; i < 5; i++) {
            treeSet1.add(random.nextInt(range));
            treeSet2.add(random.nextInt(range));
        }
        System.out.println("tree1 is");
        treeSet1.stream().forEach(System.out::println);
        System.out.println("tree2 is");
        treeSet2.stream().forEach(System.out::println);
        MergeCollection<Integer> testMerge = new MergeCollection(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return Integer.compare((Integer) o1, (Integer) o2);
            }
        });
        System.out.println("merged tree is");
        Collection<Integer> result = testMerge.mergeSortedCollections(treeSet1,treeSet2);
        result.stream().forEach(System.out::println);
        //merged size might be smaller.
        Assert.assertTrue(treeSet1.size() + treeSet2.size() >= result.size());
        //no loss of elements.
        Assert.assertTrue(result.containsAll(treeSet1));
        Assert.assertTrue(result.containsAll(treeSet2));
    }

    /**
     * Test merging 2 array list
     *
     * @throws Exception
     */
    @Test
    public void testList() throws Exception
    {
        Random random = new Random(15);
        int range = 30;
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {

            arrayList1.add(random.nextInt(range));
            arrayList2.add(random.nextInt(range));
        }
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return Integer.compare((Integer)o1, (Integer)o2);
            }
        };
        arrayList1.sort(comparator);
        arrayList2.sort(comparator);
        System.out.println("ArrayList1 is");
        arrayList1.stream().forEach(System.out::println);
        System.out.println("ArrayList2 is");
        arrayList2.stream().forEach(System.out::println);
        MergeCollection<Integer> testMerge = new MergeCollection(comparator);
        System.out.println("Merged list is");
        Collection<Integer> result =  testMerge.mergeSortedCollections(arrayList1, arrayList2);
        result.stream().forEach(System.out::println);

        //merged size = sum of size of 2 list
        Assert.assertEquals(arrayList1.size() + arrayList2.size(), result.size());
        //no loss of elements
        Assert.assertTrue(result.containsAll(arrayList1));
        Assert.assertTrue(result.removeAll(arrayList1));
        Assert.assertTrue(result.containsAll(arrayList2));
    }
}
