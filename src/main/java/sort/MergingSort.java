package sort;

/**
 * 归并排序
 */
public class MergingSort extends Sort {
    private Comparable[] aux;
    public void sort(Comparable[] a) {
        aux=new Comparable[a.length];
        sort(a,0,a.length-1);
    }

    private void sort(Comparable[] a,int low,int hight){
        if (hight<=low){
            return;
        }
        int mid=low+(hight-low)/2;
        sort(a,low,mid);
        sort(a,mid+1,hight);
        merge(a,low,mid,hight);
    }
    private void merge(Comparable[] a,int low,int mid,int hight){
        int i=low,j=mid+1;
        for (int k=low;k<=hight;k++){
            aux[k]=a[k];
        }

        for (int k=low;k<=hight;k++){
            if (i>mid){
                a[k]=aux[j++];
            }else if (j>hight){
                a[k]=aux[i++];
            }else if (less(aux[j],aux[i])){
                a[k]=aux[j++];
            }else {
                a[k]=aux[i++];
            }
        }
    }
}
