package sort;

public abstract class Sort {

    //排序算法
    public abstract void sort(Comparable[] a);

    //判断前一个值是否小于后一个值
    public boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }

    //交换两个元素
    protected void exch(Comparable[] a,int i,int j){
        Comparable t=a[i];
        a[i]=a[j];
        a[j]=t;
    }

    //打印数组元素
    protected void show(Comparable[] a){
        for (int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

    //判断是否已经排序
    protected boolean isShort(Comparable[] a){
        for (int i=0;i<a.length-1;i++){
            if (!less(a[i],a[i+1])){
                return false;
            }
        }
        return true;
    }
}
