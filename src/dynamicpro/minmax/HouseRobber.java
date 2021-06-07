package dynamicpro.minmax;

import sun.nio.cs.ext.MS874;

/** Description: 打劫房屋
 *
 * https://www.lintcode.com/problem/392/
 *
 * 描述
 * 假设你是一个专业的窃贼，准备沿着一条街打劫房屋。每个房子都存放着特定金额的钱。你面临的唯一约束条件是：相邻的房子装着相互联系的防盗系统，且 当相邻的两个房子同一天被打劫时，该系统会自动报警。
 *
 * 给定一个非负整数列表，表示每个房子中存放的钱， 算一算，如果今晚去打劫，在不触动报警装置的情况下, 你最多可以得到多少钱 。
 *
 * 样例
 * 样例 1:
 *
 * 输入: [3, 8, 4]
 * 输出: 8
 * 解释: 仅仅打劫第二个房子.
 * 样例 2:
 *
 * 输入: [5, 2, 1, 3]
 * 输出: 8
 * 解释: 抢第一个和最后一个房子
 * 挑战
 * O(n) 时间复杂度 且 O(1) 存储。
 * @author created by Meiyu Chen at 2021-6-7 13:59, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class HouseRobber {
    public static void main(String[] args) {
        HouseRobber test = new HouseRobber();
        int[] A = {3,8,4};
        System.out.println(test.houseRobber(A) == 8);
        int[] A1 = {5,2,1,3};
        System.out.println(test.houseRobber(A1) == 8);
        int[] A2 = {5};
        System.out.println(test.houseRobber(A2) == 5);
    }

    /** Description: 性能优化，在V1的基础上进行性能优化，降低空间复杂度
     * 思路：由转移方程可知，计算f[i]的值时，只用到前面两个值f[i]和f[i-1],所以不必存储所有的f[i]
     * 可以将空间复杂度由O(n)降低为O(1)
     * @author created by Meiyu Chen at 2021-6-7 14:11, v1.0
     */
    public long houseRobber(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        int n = A.length;
        long rst = A[0];
        // f[1]:前i-1栋房子可以偷到的最大金币数
        // f[0]:前i-2栋房子可以偷到的最大金币数
        long[] f = new long[2];
//        long[] f = new long[n]; // 降低空间复杂度，不必存储所有的f[i]
        for (int i = 0; i < n; i++) {
            if (i==0) {
                f[0]= A[0];
//                f[0] = A[0];
            } else if (i==1){
                f[1] =  Math.max(A[0],A[1]);
//                f[1] = Math.max(A[0],A[1]);
            } else {
                rst = Math.max(f[1], A[i] + f[0]);
                f[0] = f[1];
                f[1] = rst;
            }
        }
        // write your code here
        return rst;
    }

    /** 动态规划算法：
     * step1，确定状态
     *      f[i] 前i栋房子所能偷到的最大金币数
     * step2，转移方程
     *      f[i] = A[i] + f[i-2] 偷第i栋房子
     *             f[i-1] 不偷第i栋房子
     *           = max {f[i-1], A[i]+f[i-2]}
     * step3, 初始值和边界条件
     *      f[0] = A[0]
     *      f[1] = max {A[0], A[1]}
     * step4, 计算顺序
     *      原则：后面的计算直接使用前面计算出来的值
     *      顺序：从左至右
     * @param A: An array of non-negative integers
     * @return: The maximum amount of money you can rob tonight
     */
    public long houseRobberV1(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        int n = A.length;
        // debug： 注意，题目给定的返回值是long类型的，所以你的数组也要是long类型的，否则有可能导致值溢出
//        int[] f = new int[n]; // debug:数据类型不对，有可能求出的和会溢出哦
        long[] f = new long[n];
        for (int i = 0; i < n; i++) {
            if (i==0) {
                f[0] = A[0];
            } else if (i==1){
                f[1] = Math.max(A[0],A[1]);
            } else {
                f[i] = Math.max(f[i-1], A[i] + f[i-2]);
            }
        }
        // write your code here
        return f[n-1];
    }
}
