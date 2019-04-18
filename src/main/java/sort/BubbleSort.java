package sort;

public class BubbleSort extends Sort{
    public void sort(Comparable[] a) {
        sort5(a);
    }

    //1.最基本的冒泡排序
    private void sort1(Comparable[] a){
        for (int i=1;i<a.length;i++){
            for (int j=0;j<a.length-i;j++){
                if (!less(a[j],a[j+1])){
                    exch(a,j,j+1);
                }
            }
        }
    }
    //2.冒泡排序 优化方式1
    private void sort2(Comparable[] a){
        boolean flag=false;
        for (int i=1;i<a.length;i++){
            for (int j=0;j<a.length-i;j++){
                if (!less(a[j],a[j+1])){
                    exch(a,j,j+1);
                    flag=true;
                }
            }
            if (!flag){
                break;
            }
        }
    }
    //3.冒泡排序 优化方式2
    private void sort3(Comparable[] a){
        int border=a.length-1;
        int lastIndex=a.length;
        boolean isSort=true;
        for (int i=1;i<a.length;i++){
            for (int j=0;j<border;j++){
                if (!less(a[j],a[j+1])){
                    exch(a,j,j+1);
                    lastIndex=j;
                    isSort=false;
                }
            }
            border=lastIndex;
            if (isSort){
                break;
            }
        }
    }

    //4.冒泡排序 优化方式3(鸡尾酒排序)
    private void sort4(Comparable[] a){

        for (int i=0;i<a.length/2;i++){
            for (int j=0;j<a.length-i-1;j++){
                if (!less(a[j],a[j+1])){
                    exch(a,j,j+1);
                }
            }
            for (int j=a.length-i-2;j>i;j--){
                if (less(a[j],a[j-1])){
                    exch(a,j,j-1);
                }
            }
        }
    }
    //5. 鸡尾酒排序 优化后

    private void sort5(Comparable[] a){
        int lastMax=a.length-1;
        int lastMin=0;
        boolean isSort=true;

        int leftBorder=0,rightBorder=a.length-1;

        while (rightBorder-leftBorder>1){
            for (int j=leftBorder;j<rightBorder;j++){
                if (!less(a[j],a[j+1])){
                    exch(a,j,j+1);
                    lastMax=j;
                    isSort=false;
                }
            }
            rightBorder=lastMax;

            for (int j=rightBorder;j>leftBorder;j--){
                if (less(a[j],a[j-1])){
                    exch(a,j,j-1);
                    lastMin=j;
                    isSort=false;
                }
            }
            leftBorder=lastMin;
            if (isSort){
                break;
            }
        }
    }

}
