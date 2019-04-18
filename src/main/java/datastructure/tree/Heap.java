package datastructure.tree;

import java.util.Collection;
import java.util.Iterator;

/**
 * 数据结构 堆(最大堆)
 */
public class Heap<E extends Comparable> {
    Object[] objects;
    int size=0;
    //初始容量
    private static final int DEFAULT_CAPACITY=11;
    //最大容量
    private static final int MAX_CAPACITY=Integer.MAX_VALUE-1;

    public Heap(){
       this(DEFAULT_CAPACITY);
    }
    public Heap(int capacity){
        objects= new Object[capacity];
    }

    public Heap(Collection<E> collection){
        this(collection.size());
        create(collection);
    }
    //扩容
    private void grow(){

    }

    private int compare(E e1,E e2){
        return e1.compareTo(e2);
    }
    //插入
    private E add(E e){
        int pos=++size;
        //扩容判断
        if (pos>=objects.length){
            grow();
            if (pos>=objects.length){
                return null;
            }
        }
        //插入到末尾
        objects[pos]=e;
        int parent=pos/2;
        while (parent>1&&compare((E) objects[parent],e)<0){
            //如果父节点比子节点小则，交换位置
            objects[pos]=objects[parent];
            objects[parent]=e;
            pos=parent;
            parent=pos/2;
        }
        return e;
    }
    //建堆
    private void create(Collection<E> collection){
        Iterator<E> iterator=collection.iterator();
        int i=1;
        while (iterator.hasNext()){
            objects[i++]=iterator.next();
        }
        int start=collection.size()/2;
        for (int j=start;j>1;j--){
            if (compare((E)objects[j],(E)objects[j/2])>0){
                Object temp=objects[j/2];
                objects[j/2]=objects[j];
                objects[j]=temp;
            }
        }
    }
    //删除
    public E delete(){
        Object res=objects[1];
        objects[1]=objects[size--];
        int parent=1;
        int child=0;
        while (parent*2<=size){//如果左孩子还存在
            child=parent*2;//左孩子
            if (child+1<=size){
                child=compare((E)objects[child],(E)objects[child+1])>0?child:child+1;
            }
            //交换父子元素
            Object temp=objects[parent];
            objects[parent]=objects[child];
            objects[child]=temp;
            parent=child;
        }
        return (E) res;
    }
    //获取最大值
    public E get(){
        return (E) objects[1];
    }
}
