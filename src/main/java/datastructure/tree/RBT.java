package datastructure.tree;

import java.util.Comparator;

public class RBT<K extends Comparable,V>{
    public static final int BLACK=0,RED=1;
    private Entry root;
    private Comparator comparator;
    private int size;
    private class Entry<K,V>{
        K key;
        V value;
        int color;
        Entry<K,V> parent;
        Entry<K,V> next;
        Entry<K,V> left,right;
        public Entry(K key, V value,int color,Entry<K,V> parent) {
            this.key = key;
            this.value = value;
            this.color=color;
            this.parent=parent;
        }
    }

    public RBT() {
    }

    public RBT(Comparator comparator) {
        this.comparator = comparator;
    }

    private int compare(K key1,K key2){
        if (comparator!=null){
            return comparator.compare(key1,key2);
        }
        return key1.compareTo(key2);
    }

    public V get(K key){
        Entry<K,V> e=getEntry(key);
        return e==null?null:e.value;
    }

    public V put(K key,V value){
        Entry<K,V> e=putEntry(key,value);
        if (e==null){
            return null;
        }else {
            size++;
            return e.value;
        }
    }

    private Entry<K,V> putEntry(K key,V value){
        Entry<K,V> p=root;
        if (p==null){
            root=new Entry(key,value,BLACK,null);
            return root;
        }
        Entry<K,V> e=null;
        while (p!=null){
            int c=compare(key,p.key);
            if (c==0){
                p.value=value;//修改值
                return null;
            }else if (c<0){
                if (p.left==null){
                    e=new Entry<K, V>(key,value,RED,p);
                    p.left=e;
                    break;
                }
                p=p.left;
            }else {
                if (p.right==null){
                    e=new Entry<K, V>(key,value,RED,p);
                    p.right=e;
                    break;
                }
                p=p.right;
            }
        }
        //插入后节点的修复
        fixAfterInsertion(e);
        return e;
    }

    private void fixAfterInsertion(Entry<K,V> e){
        //修复的节点为null或者root节点，或者其父节点为黑色则不需要继续修复了
        while (e!=null&&e!=root&&e.parent.color==RED){
            //如果父结点是祖父节点的左儿子
            if (parentOf(e)==leftOf(parentOf(parentOf(e)))){
                Entry<K,V> right=rightOf(parentOf(parentOf(e)));
                //case1 父亲节点和叔叔节点都是红色
                if (colorOf(right)==RED){
                    e.parent.color=BLACK;
                    right.color=BLACK;
                    parentOf(parentOf(e)).color=RED;
                    e=parentOf(parentOf(e));
                }else {
                    //父节点是红色但叔节点是黑色
                    if (e==rightOf(parentOf(e))){
                        //进行左旋转
                        e=e.parent;
                        leftRoate(e);
                    }
                    setColor(parentOf(e),BLACK);
                    setColor(parentOf(parentOf(e)),RED);
                    //右旋转
                    rightRoate(parentOf(parentOf(e)));
                }
            }else {
                //父节点是祖父节点的右边孩子
                Entry left=leftOf(parentOf(parentOf(e)));
                if (colorOf(left)==RED){
                    setColor(e.parent,BLACK);
                    setColor(left,BLACK);
                    setColor(parentOf(parentOf(e)),RED);
                    e=parentOf(parentOf(e));
                }else {
                    if (e==leftOf(parentOf(e))){
                        e=e.parent;
                        rightRoate(e);
                    }
                    setColor(parentOf(e),BLACK);
                    setColor(parentOf(parentOf(e)),RED);
                    leftRoate(parentOf(parentOf(e)));
                }
            }
        }
        setColor(root,BLACK);
    }

    private void setColor(Entry e,int color){
        if (e!=null){
            e.color=color;
        }
    }
    private int colorOf(Entry entry){
        return entry==null?BLACK:entry.color;
    }
    private Entry<K,V> leftOf(Entry e){
        return e==null?null:e.left;
    }

    private Entry<K,V> rightOf(Entry e){
        return e==null?null:e.right;
    }

    private Entry<K,V> parentOf(Entry<K,V> entry){
        return entry==null?entry:entry.parent;
    }
    //查找
    private Entry<K,V> getEntry(K key){
        if (root==null){
            return null;
        }
        Entry<K,V> p=root;
        while (p!=null){
            int c=compare(key,p.key);
            if (c==0){
                return p;
            }else if (c<0){
                p=p.left;
            }else {
                p=p.right;
            }
        }
        return null;
    }

    //左旋转
    private void leftRoate(Entry<K,V> entry){
        if (entry==null){
            return;
        }
        Entry<K,V> mid=entry.right;
        entry.right=mid.left;
        if (mid.left!=null){
            mid.left.parent=entry;
        }
        mid.parent=entry.parent;
        if (entry.parent==null){
            root=mid;
        }else if (parentOf(entry).left==entry){
            parentOf(entry).left=mid;
        }else{
            parentOf(entry).right=mid;
        }
        mid.left=entry;
        entry.parent=mid;
        return;
    }

    //右旋转
    private void rightRoate(Entry<K,V> entry){
        if (entry==null){
            return;
        }
        Entry mid=entry.left;
        entry.left=mid.right;
        if (mid.right!=null){
            mid.right.parent=entry;
        }
        mid.parent=entry.parent;
        if (parentOf(entry)==null){
            root=mid;
        }else if (parentOf(entry).left==entry){
            parentOf(entry).left=entry;
        }else {
            parentOf(entry).right=entry;
        }
        mid.right=entry;
        entry.parent=mid;
        return;
    }

    public int size(){
        return size;
    }

    public static void main(String[] args) {
       test1();
       test2();
       test3();
    }

    //测试二叉搜索树的速度
    private static void test1(){
        BST<Integer,String> bst= new BST<Integer, String>();
        long t1=System.currentTimeMillis();
        for (int i=0;i<10000;i++){
            bst.put(i,""+i);
        }
        long t2=System.currentTimeMillis();
        System.out.println("二叉搜索树的插入时间: "+(t2-t1));
        for (int i=0;i<10000;i++){
            bst.get(i);
            if (!bst.get(i).equals(""+i)){
                System.out.println("二叉搜索树有问题");
                return;
            }
        }
        long t3=System.currentTimeMillis();
        System.out.println("二叉搜索树的查找时间: "+(t3-t2));
    }


    private static void test2(){
        AVL<Integer,String> avl= new AVL<Integer, String>();
        long t1=System.currentTimeMillis();
        for (int i=0;i<10000;i++){
            avl.put(i,""+i);
        }
        long t2=System.currentTimeMillis();
        System.out.println("AVL树的插入时间: "+(t2-t1));
        for (int i=0;i<10000;i++){
            if (!avl.get(i).equals(""+i)){
                System.out.println("AVL树有问题");
                return;
            }
        }
        long t3=System.currentTimeMillis();
        System.out.println("AVL树的查询时间: "+(t3-t2));
    }

    private static void test3(){
        RBT<Integer,String> rbt= new RBT<Integer, String>();
        long t1=System.currentTimeMillis();
        for (int i=0;i<10000;i++){
            rbt.put(i,""+i);
        }
        long t2=System.currentTimeMillis();
        System.out.println("红黑树的插入时间: "+(t2-t1));
        for (int i=0;i<10000;i++){
            if (!rbt.get(i).equals(""+i)){
                System.out.println("红黑树有问题");
                return;
            }
        }
        long t3=System.currentTimeMillis();
        System.out.println("红黑树的查询时间: "+(t3-t2));
    }
}
