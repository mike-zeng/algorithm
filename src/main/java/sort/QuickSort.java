package sort;
/**
 * 快速排序
 */
public class QuickSort extends Sort {
    public void sort(Comparable[] a) {

    }

    private void sort(Comparable[] a,int low,int hight){

    }
    private int partition(Comparable[] a,int low,int hight){
        int i=low,j=hight+1;
        Comparable v=a[low];

        while (true){
            //从左边往右找到一个小于v的值
            while (less(a[++i],v)){
                if (i==hight){
                    break;
                }
            }

            //找右往左找到一个而大于v的值
            while (less(v,a[--j])){
                if (j==low){
                    break;
                }
            }
            if (i>=j){
                break;
            }
            exch(a,i,j);
        }
        exch(a,low,j);
        return j;
    }
}
