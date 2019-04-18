package datastructure.tree;

import java.util.Comparator;

/**
 * 查找算法的数据结构实现 AVL树
 * @param <K>
 * @param <V>
 */
public class AVL<K extends Comparable,V> {
    private int size;
    private Entry<K,V> root;
    private Comparator<K> comparator;
    //entry类
    private class Entry<K extends Comparable,V>{
        private K key;
        private V value;
        private Entry<K,V> left,right;
        private int height;

        public Entry(K key, V value, Entry<K, V> left, Entry<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private int compare(K key1,K key2){
        if (comparator!=null){
            return comparator.compare(key1,key2);
        }
        return key1.compareTo(key2);
    }

    public void put(K key,V value){
        root=putEntry(root,key,value);
    }

    private Entry<K,V> putEntry(Entry<K,V> root,K key,V value){
        if (root==null){
            size++;
            return new Entry<K, V>(key,value,null,null);
        }else{
            int c=compare(key,root.key);
            if (c==0){
                root.value=value;
            }else if (c<0){
                root.left=putEntry(root.left,key,value);
                //判断avl是否被破坏，因为刚刚插入的是左边，所以左边高度一定大于右边
                if (getHeight(root.left)-getHeight(root.right)==2){
                    if (compare(key,root.left.key)>0){
                        root=LRRotate(root);//LR
                    }else if (compare(key,root.left.key)<0){
                        root=LLRotate(root);//LL
                    }
                }
            }else {
                root.right=putEntry(root.right,key,value);
                if (getHeight(root.right)-getHeight(root.left)==2){
                    if (compare(key,root.right.key)<0){
                        root=RLRotate(root);
                    }else if (compare(key,root.right.key)>0){
                        root=RRRotate(root);
                    }
                }
            }
        }
        root.height=getHeight(root);
        return root;
    }

    public void delete(K key){
        root=deleteEntry(root,key);
    }

    private Entry<K,V> deleteEntry(Entry<K,V> root,K key){
        if (root!=null){
            int c=compare(key,root.key);
            if (c<0){
                //左子树上删除
                root.left=deleteEntry(root.left,key);
                //相当于在右子树上插入节点，导致不平衡
                if (getHeight(root.right)-getHeight(root.left)==2){
                   if (getHeight(root.right.left)>getHeight(root.right.right)){
                       root=RLRotate(root);//LL
                   }else {
                       root=RRRotate(root);
                   }
                }
            }else if (c>0){
                //右子树上删除
                root.right=deleteEntry(root.right,key);
                if (getHeight(root.left)-getHeight(root.right)==2){
                   if (getHeight(root.left.right)>getHeight(root.left.left)){
                       root=LRRotate(root);
                   }else {
                       root=LLRotate(root);
                   }
                }
            }else {
                size--;
                //开始删除
                if (root.left!=null&&root.right!=null){
                    Entry<K,V> entry=getMaxEntry(root.left);
                    root.key=entry.key;
                    root.value=entry.value;
                    deleteEntry(root.left,entry.key);
                }else if (root.left!=null||root.right!=null){
                    if (root.left==null){
                        return root.right;
                    }
                    if (root.right==null){
                        return root.left;
                    }
                }else if(root.left==null&&root.right==null){
                    return null;
                }
            }
        }
        root.height=getHeight(root);
        return root;
    }
    private Entry<K,V> getMaxEntry(Entry<K,V> root){
        Entry<K,V> p=root;
        while (p.right!=null){
            p=p.right;
        }
        return p;
    }

    private Entry<K,V> getMinEntry(Entry<K,V> root){
        Entry<K,V> p=root;
        while (p.left!=null){
            p=p.left;
        }
        return p;
    }


    public V get(K key){
        Entry<K,V> entry=getEntry(key);
        if (entry==null){
            return null;
        }else {
            return entry.value;
        }
    }

    public int size(){
        return size;
    }

    private Entry<K,V> getEntry(K key){
        Entry<K,V> p=root;
        if (p==null){
            return null;
        }
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

    private int getHeight(Entry<K,V> entry){
        if (entry==null){
            return -1;
        }
        int lh=entry.left==null?-1:entry.left.height;
        int rh=entry.right==null?-1:entry.right.height;
        return Math.max(lh,rh)+1;
    }

    public Entry<K,V> LLRotate(Entry<K,V> entry){
        Entry<K,V> newRoot=entry.left;
        entry.left=newRoot.right;
        newRoot.right=entry;

        entry.height=getHeight(entry);
        newRoot.height=getHeight(newRoot);
        return newRoot;
    }

    public Entry RRRotate(Entry<K,V> entry){
        Entry<K,V> newRoot=entry.right;
        entry.right=newRoot.left;
        newRoot.left=entry;
        entry.height=getHeight(entry);
        newRoot.height=getHeight(newRoot);
        return newRoot;
    }

    public Entry RLRotate(Entry<K,V> entry){
        entry.right=LLRotate(entry.right);
        return RRRotate(entry);
    }

    public Entry LRRotate(Entry<K,V> entry){
        entry.left=RRRotate(entry.left);
        return LLRotate(entry);
    }

    public static void main(String[] args) {
        AVL<Integer,String> avl=new AVL<Integer, String>();
        avl.put(1,"1");
        avl.put(2,"2");
        avl.put(3,"3");
        avl.put(4,"4");
        avl.delete(1);
        avl.delete(2);
        avl.delete(3);
        avl.delete(4);
        System.out.println(avl.size);
    }
}
