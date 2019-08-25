源码分析：
     
     
    //重要参数
      private transient volatile int sizeCtl;
      <!--表示的是表的初始化或者扩容
          当该值为-1的时候，表明正在初始化
          当值为-(1+n) n:表示活动的扩容线程
      -->
    static final int MOVED     = -1;
    
    // hash for forwarding nodes 表示正在转移
    
    static final int TREEBIN   = -2; 
    
    // hash for roots of trees表示已经树化
    
    static final int RESERVED  = -3; 
    
    // hash for transient reservations短暂保留的hash值
    
    static final int HASH_BITS = 0x7fffffff; 
    // usable bits of normal node hash
      
    //put方法
     public V put(K key, V value) {
     
       //putVal的第三个参数设置为false
       //当设置为false的时候表示这个value一定会设置
       //true的时候，只有当这个key的value为空的时候才会设置
       
        return putVal(key, value, false);
    }
    
      final V putVal(K key, V value, boolean onlyIfAbsent) {
        //如果key为空或者value为空，则抛出异常
        if (key == null || value == null) throw new NullPointerException();
        //计算出该节点的哈希值
        int hash = spread(key.hashCode());
        int binCount = 0;
        for (Node<K,V>[] tab = table;;) {
            Node<K,V> f; int n, i, fh;
            //如果表为空，则进行初始化
            if (tab == null || (n = tab.length) == 0)
                tab = initTable();
             //如果表不为空，计算出该节点在链表中的位置并且该位置没有节点存在  
            else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
                //通过cas方式加入该节点，在这时没有加锁
                if (casTabAt(tab, i, null,
                             new Node<K,V>(hash, key, value, null)))
                    break;                   // no lock when adding to empty bin
            }
            //当哈希值等于-1时，表示正在转移节点，即正在扩容，那么当前线程也会去帮助进行扩容
            else if ((fh = f.hash) == MOVED)
                tab = helpTransfer(tab, f);
            //如果当前位置有节点存在，则加锁
            else {
                V oldVal = null;
                synchronized (f) {
                //取出当前位置上的节点与要放入的节点相比较
                    if (tabAt(tab, i) == f) {
                        //如果表不是树(-2)、没有正在扩容(-1)等情况
                        if (fh >= 0) {
                            binCount = 1;
                            //遍历该链表
                            for (Node<K,V> e = f;; ++binCount) {
                                K ek;
                                //如果要放入的节点与当前节点的hash值相同并且key值也相同
                                if (e.hash == hash &&
                                    ((ek = e.key) == key ||
                                     (ek != null && key.equals(ek)))) {
                                     //替换value值
                                    oldVal = e.val;
                                    if (!onlyIfAbsent)
                                        e.val = value;
                                    break;
                                }
                                //没有key相同的节点，则找到尾节点进行插入
                                Node<K,V> pred = e;
                                if ((e = e.next) == null) {
                                    pred.next = new Node<K,V>(hash, key,
                                                              value, null);
                                    break;
                                }
                            }
                        }
                        //该位置链表已经转换为红黑树的情况
                        else if (f instanceof TreeBin) {
                            Node<K,V> p;
                            binCount = 2;
                            if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,
                                                           value)) != null) {
                                oldVal = p.val;
                                if (!onlyIfAbsent)
                                    p.val = value;
                            }
                        }
                    }
                }
                if (binCount != 0) {
                    if (binCount >= TREEIFY_THRESHOLD)
                        treeifyBin(tab, i);
                    if (oldVal != null)
                        return oldVal;
                    break;
                }
            }
        }
        addCount(1L, binCount);
        return null;
    }
      static final int spread(int h) {
        return (h ^ (h >>> 16)) & HASH_BITS;
    }