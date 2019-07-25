#动态规划#

动态规划就是将大问题转化为小问题的分治过程，保存小问题的处理结果，供后面更大规模问题直接使用这些结果。

**动态规划的特点：**
- 动态规划就是将原来的问题分解成了几个相似的子问题；
- 所有的子问题只需要解决一次；
- 存储子问题的解。

动态规划的本质是对问题的状态的定义和状态转移方程的定义；

**动态规划问题一般从四个角度考虑：**
- 状态的定义
- 状态间的转移方程定义
- 状态的初始化
- 返回结果

**状态的定义一定要形成递推关系**

例如：
   Fibonacci数列：

   题目描述：输入一个整数n，输出斐波那契数列的第n项。

   分析：
   状态：F(n)
   状态递推：F(n) = F(n-1)+F(n-2)
   初始值:F(1)=F(2)=1
   返回值：F(n)


   `
    public class Test {
    public static int fibonacci(int n){
        if(n <= 0){
            return 0;
        }
        if(n == 1 || n == 2){
            return 1;
        }
        int[] res = new int[n+1];
        res[0] = 0;
        res[1] = 1;
        for (int i = 2; i <= n; i++) {
            res[i] = res[i-1] + res[i-2];
        }
        return res[n];
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(0));
        System.out.println(fibonacci(2));
    }
    }`


  这种实现的缺点是造成空间的浪费，因为我们只要最后一个结果值，不需要中间值。


  ` 
    public class Test {
    public static int fibonacci(int n){
        if(n <= 0){
            return 0;
        }
        if(n == 1 || n == 2){
            return 1;
        }
        int fn1 = 1;
        int fn2 = 1;
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = fn1 + fn2;
            fn1 = fn2;
            fn2 = result;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(6));
        System.out.println(fibonacci(5));
    }
    }`
  就好啦！！！