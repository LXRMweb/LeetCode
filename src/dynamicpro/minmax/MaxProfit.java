package dynamicpro.minmax;

import sun.nio.cs.ext.MS874;

/** Description: 买卖股票的最佳时机
 *
 * https://www.lintcode.com/problem/149/
 *
 * 描述
 * 假设有一个数组，它的第i个元素是一支给定的股票在第i天的价格。如果你最多只允许完成一次交易(例如,一次买卖股票),设计一个算法来找出最大利润。
 *
 * 样例
 * 样例1
 *
 * 输入: [3, 2, 3, 1, 2]
 * 输出: 1
 * 说明：你可以在第三天买入，第四天卖出，利润是 2 - 1 = 1
 * 样例2
 *
 * 输入: [1, 2, 3, 4, 5]
 * 输出: 4
 * 说明：你可以在第0天买入，第四天卖出，利润是 5 - 1 = 4
 * 样例3
 *
 * 输入: [5, 4, 3, 2, 1]
 * 输出: 0
 * 说明：你可以不进行任何操作然后也得不到任何利润
 * @author created by Meiyu Chen at 2021-6-7 15:40, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class MaxProfit {
    public static void main(String[] args) {
        MaxProfit test = new MaxProfit();
        int[] arr1 = {3,2,3,1,2};
        System.out.println(test.maxProfit(arr1) == 1);
        int[] arr2 = {1,2,3,4,5};
        System.out.println(test.maxProfit(arr2) == 4);
        int[] arr3 = {5,4,3,2,1};
        System.out.println(test.maxProfit(arr3) == 0);
    }

    /** Description: 性能优化，降低空间复杂度
     * 思路：滚动数组
     * @author created by Meiyu Chen at 2021-6-7 15:54, v1.0
     */
    public int maxProfit(int[] prices) {
        if (prices.length<2) return 0;
        int n = prices.length;
        // f[i]: 前i-1天的最低价格
        int fLast =  prices[0];
//        int[] f = new int[n];
        int rst = 0;
//        f[0] = prices[0];
        for (int i = 1; i < n; i++) {
            fLast = Math.min(fLast,prices[i]);
            rst = Math.max(prices[i]-fLast,rst);
        }
        return rst;
    }

    /** 动态规划算法
     * 思路：枚举每一天卖出可以获得的最大利润
     * 如：第i天卖出可以获得的最大利润
     *      max = prices[i] - f[i]
     * step1， 确定状态
     *      f[i] 前i-1天的最低价
     * step2，转移方程
     *      f[i] = min(f[i-1],prices[i])
     * step3, 初始条件
     *      f[0] = prices[0]
     * step4, 计算顺序
     *      原则：后面的计算直接使用前面计算出的值
     *      顺序：从小到大
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfitV1(int[] prices) {
        if (prices.length<2) return 0;
        int n = prices.length;
        // f[i]: 前i-1天的最低价格
        int[] f = new int[n];
        int rst = 0;
        f[0] = prices[0];
        for (int i = 1; i < n; i++) {
            f[i] = Math.min(f[i-1],prices[i]);
            rst = Math.max(prices[i]-f[i-1],rst);
        }
        return rst;
    }
}
