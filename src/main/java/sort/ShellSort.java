package sort;

/**
 * 希尔排序
 */
public class ShellSort extends Sort{

    public void sort(Comparable[] a) {
        int h=1;
        int n=a.length;
        while (h<n/3){
            h=3*h+1;
        }
        while (h>=1){
            for (int i=h;i<n;i++){
                for (int j=i;j>=h&&less(a[j],a[j-h]);j-=h){
                    exch(a,j,j-h);
                }
            }
            h=h/3;
        }
    }
}
