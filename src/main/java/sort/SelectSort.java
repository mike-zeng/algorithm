package sort;

/**
 * 选择排序
 * 交换次数：n次
 * 比较次数：(n-1)+(n-2)+...1=(n-1+1)(n-1)/2=n^2/2-n/2
 * 时间复杂度: O(n^2)
 *
 * @author zeng
 */
public class SelectSort extends Sort {
    public void sort(Comparable[] a) {
        int n=a.length;
        int min=0;
        for (int i=0;i<n;i++) {
            min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j],a[min])){
                    min=j;
                }
            }
        }
    }

}