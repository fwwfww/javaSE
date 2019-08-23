      
      1.ArrayList、LinkedList、Vector的关系与区别（源码）：
         a.ArrayList基于数组实现List,采用懒加载策略，当第一次调用add方法时，数组才会初始化为10（默认值）；ArrayList扩容为原来的1.5倍；ArrayList采用异步操作，线程不安全，性能较高；
        b.Vector（JDK1.0）也是基于数组实现的list，当产生Vector对象时，就初始化为大小为10的数组；Vectork扩容是扩容为原来的2倍；Vector采用同步方法保证线程安全，性能较低（读读互斥）；
       c.LinkedList基于双向循环链表实现的List，采用异步处理，线程不安全。在任意位置插入或删除时考虑使用LinkedList或需要使用队列的场合；

      2.请描述fail-fast策略  ConcurrentModificationException
         什么是快速失败策略：优先考虑异常情况，当异常产生时，直接抛出，程序终止；
         在迭代输出的过程中修改了集合的结构（remove、add）抛出此异常，为了保证多线程场景下多线程取得数据的正确性。         
         如何产生：当modCount != expectedModCount时
              modCount记录当前集合被修改的次数
              expectedModCount迭代器内部记录当前集合修改的次数，当取得集合迭代器时赋值为当前的modCount;
         如何解决异常：
       a.迭代输出时尽量不要修改集合结构；
       b.使用juc包下的线程安全集合，如CopyOnWriteArrayList,ConcurrentHashMap(fail-safe集合)

      3.Set与Map有什么关系
       set实际上就是Map,Set将元素存储到了内部Map的Key,共享一个空的Object作为value;
	  4. hashcode与equals:
      hashcode:将任意一个对象按照特定的哈希算法变为一个整数（对象中的所有属性值均参与运算）
       equals:比较两个对象是否相等（属性值）
       equals相等，hashcode一定相等
       hashcode相等，equals不一定相等（参考哈希表的设计）


      5.   如何实现第三方类的比较（内部排序、外部排序）
        内部排序：Comparable
                     当一个类实现了Comparable接口，表示该类具备天然可比较特性
                     int compareTo(Object antherObj)
        外部排序：Comparator（策略模式）
                     类本身不具备可比较的特性（人类），专门有一个类实现了    Comparator（福布斯排行榜、长寿村）来比较该类的大小（比较器）
                     int compare(Object obj1,Object obj2)

      要想将自定义的类存储到TreeSet(Treemap)中，要么元素本身可比较（实现了comparable接口），要么通过构造方法传入该类的比较器（实现了Comparator接口）


    6. HashMap、Hashtable、TreeMap的关系与区别:
    底层结构的区别：
     HashMap:哈希表+红黑树（JDK1.8,JDK1.8之前哈希表）
     Hashtable:哈希表
     TreeMap:红黑树
    线程安全性：
     只有Hashtable是线程安全的（使用Synchronised同步方法，读读互斥）
    关于null：
     Hashtable  key与value均不能为空
     HashMap  key与value均可以为空
     TreeMap   key不为null，value可以为null
    
     concurrentHashMap在JDK1.7与JDK1.8的区别
            1.7：默认初始化为16个Segment，初始化后不会扩容
                    每个Segment下面还是一个哈希表结构，可以扩容（扩容以  Segment为单位）
                    Hashtable锁的是当前对象，即整个哈希表。（一张表，一把锁）
                   JDK1.7 concurrentHashMap锁的是当前操作的Segment（一张表，16把锁，将锁的粒度细化，不同的Segment之间可以异步）
            1.8：取消了原先Segment的设计，现在的结构与HashMap结构相同。
                     锁的是Hash表的数组元素，将锁进一步细化。进一步提高读写效率。



     7.HashMap内部实现原理：
            元素到底在哪个数组下标位置存储：内部的哈希运算
            异或运算将高16位与低16位都参与异或运算，减少哈希冲突
    
          树化逻辑:
          1.链表个数达到树化阈值（7）&&哈希表的tab.length>=64
          
      为何树化：
           当链表过长会导致查找性能降低（O(n)）,因此将其树化可以大大提高查    找速度（O(logn)） 
     扩容:当前数组元素个数 > 容量*负载因子
     负载因子：0.75
     容量：默认16
     为何容量必须为2^n: 减少hash冲突，让哈希数组的每个索引下标都会被访问到