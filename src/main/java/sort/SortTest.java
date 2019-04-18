package sort;

public class SortTest {
    public static void main(String[] args) {
        Sort sort=new BubbleSort();
        Comparable[] comparables={13,22,6,7,23,9,43,4,5};
        sort.sort(comparables);
        sort.show(comparables);
        System.out.println(sort.isShort(comparables));
    }
}
