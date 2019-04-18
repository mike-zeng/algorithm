package sort;

/**
 * 插入排序（O(n^2)）
 * 最坏：
 *  比较：n^2/2
 *  交换:n^2/2
 * 最好：
 *  比较：n-1
 *  交换：0
 * 平均：
 *  比较：n^2/4
 *  交换:n^2/4
 */
public class InsertSort extends Sort {
    public void sort(Comparable[] a) {
        int n=a.length;
        for (int i=1;i<n;i++){
            for (int j=i;i>0&&less(a[j],a[j-1]);j--){
                exch(a,j,j-1);
            }
        }
    }
}
