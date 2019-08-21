      
      final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
				   
	    //tab[]为数组，p为桶
        Node<K,V>[] tab; Node<K,V> p; int n, i;
		
	//如果数组为空，则调用resize()函数创建一个
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;

       //如果存储位置没有元素，则直接插入
        index=  (n - 1) & （n-1）
                  在扩容时，hash扩容都是2的幂次方，减一之后二进制位就全都为1，因此最大限度利用了hash值，更好的散列，让hash值均匀的分布在桶中
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);

       //发生碰撞的情况
        else {
            Node<K,V> e; K k;
           //第一种情况：key相同，value覆盖
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;

            //第二种：判断链表是否为红黑树，如果是，插入
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);

            //第三种：链表为正常链表
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        //如果当前链表的数量大于等于TREEIFY_THRESHOLD（8） - 1,则转换为红黑树
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }

                   //如果key存在，返回当前的节点
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
           //将value值进行覆盖
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }





         hash方法：
     hashcode本身为32位，所以就是进行hashcode的高16位与低16位异或，降低hash碰撞

    static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


     扩容：
     final Node<K,V>[] resize() {

     //创建一个oldTab数组用来保存以前的数组
    Node<K,V>[] oldTab = table;

    //获取原来数组的容量
    int oldCap = (oldTab == null) ? 0 : oldTab.length;

    //获取原来数组扩容的临界值
    int oldThr = threshold;
    int newCap, newThr = 0;
     if (oldCap > 0) {
        //如果原来的数组长度大于MAXIMUM_CAPACITY（1<<30）
          将扩容临界值提高到整型最大值
        if (oldCap >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return oldTab;
        }
        //否则将新容量扩大为原来的2倍后的值小于最大容量并且原来数组的长度>=DEFAULT_INITIAL_CAPACITY（16），数组进行扩容，并将临界值也扩大2倍
        else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                 oldCap >= DEFAULT_INITIAL_CAPACITY)
            newThr = oldThr << 1; // double threshold
    }
    //这个else说明旧数组长度为0但是扩容临界值不为0，将新数组初始容量设置为旧数组扩容的临界值
    else if (oldThr > 0) // initial capacity was placed in threshold
        newCap = oldThr;
    //新数组的初始化容量设置为默认值
    else {               // zero initial threshold signifies using defaults
        newCap = DEFAULT_INITIAL_CAPACITY;
        newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
    }
     //如果新的扩容临界值等于0（说明为上面else if）的情况，此时 newCap = oldThr
    if (newThr == 0) {
        float ft = (float)newCap * loadFactor;
        //计算新的扩容临界值
        newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                  (int)ft : Integer.MAX_VALUE);
    }
    threshold = newThr;
    @SuppressWarnings({"rawtypes","unchecked"})
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
     //改变table值为新的值table值
    table = newTab;
    if (oldTab != null) { 
     //遍历旧数组，重新计算hash值放入新的数组中
        for (int j = 0; j < oldCap; ++j) {
            Node<K,V> e;
            if ((e = oldTab[j]) != null) {
                oldTab[j] = null;
                if (e.next == null)
                    newTab[e.hash & (newCap - 1)] = e;
                 //如果e为红黑树
                else if (e instanceof TreeNode)
                 
                  ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                //链表重排
                else { // preserve order
                    Node<K,V> loHead = null, loTail = null;
                    Node<K,V> hiHead = null, hiTail = null;
                    Node<K,V> next;
                    do {
                        next = e.next;
                         //(e.hash & oldCap) == 0为被分到相同位置的节点
                        if ((e.hash & oldCap) == 0) {
                            if (loTail == null)
                                loHead = e;
                            else
                                loTail.next = e;
                            loTail = e;
                        }
                        else {
                            if (hiTail == null)
                                hiHead = e;
                            else
                                hiTail.next = e;
                            hiTail = e;
                        }
                    } while ((e = next) != null);
                    if (loTail != null) {
                        loTail.next = null;
                        newTab[j] = loHead;
                    }
                    if (hiTail != null) {
                        hiTail.next = null;
                        //将其他节点放置到（原下标+原数组大小的位置上）
                        newTab[j + oldCap] = hiHead;
                    }
                }
            }
        }
    }
    return newTab;
    }
