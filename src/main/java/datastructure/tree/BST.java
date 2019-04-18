package datastructure.tree;

import java.util.Comparator;

/**
 * 查找算法的数据结构实现----------二叉搜索树
 */
public class BST<K extends Comparable,V> {
    private int size;
    private Entry<K,V> root;
    private Comparator comparator;
    public BST() {
    }

    public BST(Comparator comparator) {
        this.comparator = comparator;
    }

    private int compare(K key1, K key2){
       if (comparator==null){
           return key1.compareTo(key2);
       }
       return comparator.compare(key1,key2);
    }

    //entry类
    private class Entry<K extends Comparable,V>{
        private K key;
        private V value;
        private Entry<K,V> left,right;

        public Entry(K key, V value, Entry<K, V> left, Entry<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    //插入
    public V put(K key,V value){
        Entry<K,V> p=putEntry(key,value);
        if (p==null){
            return null;
        }else {
            size++;
            return p.value;
        }
    }

    private Entry<K,V> putEntry(K key, V value){
        if (root==null){
            size++;
            root=new Entry<K, V>(key,value,null,null);
            return root;
        }
        Entry<K,V> p=root;
        while (p!=null){
            int c=compare(key,p.key);
            if (c==0){
                p.value=value;
                return null;
            }else if (c<0){
                if (p.left==null){
                    p.left=new Entry<K, V>(key,value,null,null);
                    return p.left;
                }
                p=p.left;
            }else {
                if (p.right==null){
                    p.right=new Entry<K, V>(key,value,null,null);
                    return p.right;
                }
                p=p.right;
            }
        }
        return null;
    }

    //查找
    public V get(K key){
        if (root==null){
            return null;
        }
        Entry<K,V> p=root;
        while (p!=null){
            int r=compare(key,p.key);
            if (r==0){
                return p.value;
            }else if (r<0){
                p=p.left;
            }else {
                p=p.right;
            }
        }
        return null;
    }

    //删除
    public void delete(K key){
        deleteEntry(root,key);
    }

    private Entry<K,V> deleteEntry(Entry<K,V> root,K key){
        if (root==null){
            return null;
        }
        int c=compare(key,root.key);
        if (c==0){
            if (root.left==null&&root.right==null){
                root=null;
                size--;
            }else if (root.left==null||root.right==null){
                if (root.left==null){
                    size--;
                    return root.right;
                }else {
                    size--;
                    return root.left;
                }
            }else {
                Entry<K,V> p=getMaxEntry(root.left);
                root.key=p.key;
                root.value=p.value;
                root.left=deleteEntry(root.left,p.key);
                size--;
            }
        }else if (c<0){
            root.left=deleteEntry(root.left,key);
        }else {
            root.right=deleteEntry(root.right,key);
        }
        return root;
    }
    //获取树节点个数
    public int size(){
        return size;
    }

    public Entry<K,V> getMinEntry(){
        Entry<K,V> p=root;
        if (p==null){
            return null;
        }
        while (p.left!=null){
            p=p.left;
        }
        return p;
    }
    public Entry<K,V> getMaxEntry(Entry<K,V> root){
        Entry<K,V> p=root;
        if (p==null){
            return null;
        }
        while (p.right!=null){
            p=p.right;
        }
        return p;
    }
}
