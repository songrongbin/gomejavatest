package com.bins.arithmetic.chapterone;

/**
 * Created by songrongbin on 2017/2/24.
 */
public class Zoutaijie {
    // 梯有N阶，上楼可以一步上一阶，也可以一次上二阶。编一个程序，计算共有多少种不同的走法。如果上20阶会有几种走法
    public static long result[] = new long[100];

    public static void main(String[] args) {
        result[0] = result[1] = 1;
        for (int i = 2; i < result.length; i++)
            result[i] = -1;
//s不能太大，否则int溢出
        int s = 30;
//动态规划
        long startTime = System.currentTimeMillis();
        System.out.println("动态规划解决：" + fun1(s));
        long endTime = System.currentTimeMillis();
        System.out.println("动态规划解决-程序运行时间：" + (endTime - startTime) + "ms");

//数组叠加
        long startTime2 = System.currentTimeMillis();
        System.out.println("数组叠加实现：" + fun2(s));
        long endTime2 = System.currentTimeMillis();
        System.out.println("数组叠加实现-程序运行时间：" + (endTime2 - startTime2) + "ms");

//递归方法
        long startTime1 = System.currentTimeMillis();
        System.out.println("递归方法解决：" );
        System.out.println("fun(s)：" + fun(s));
        long endTime1 = System.currentTimeMillis();
        System.out.println("递归方法解决-程序运行时间：" + (endTime1 - startTime1) + "ms");
    }


    public static long fun(int s) {
        if (s == 0 || s == 1)
            return 1;
        else {
            return fun(s - 1) + fun(s - 2);
        }

    }

    public static long fun1(int s) {
        if (result[s] >= 0) {
            return result[s];
        } else {
            result[s] = (fun1(s - 1) + fun1(s - 2));
            return result[s];
        }
    }


    public static long fun2(int s) {
        long result_1[] = new long[s + 1];//注意这个要大一个，多了个第0个
        result_1[0] = result_1[1] = 1;
        for (int i = 2; i <= s; i++)
            result_1[i] = result_1[i - 1] + result_1[i - 2];
        return result_1[s];//s就是第s+1个
    }
}
