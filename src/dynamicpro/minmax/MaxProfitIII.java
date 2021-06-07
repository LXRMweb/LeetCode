package dynamicpro.minmax;

/** Description: 买卖股票的最佳时机 III
 * https://www.lintcode.com/problem/151/
 *
 * 描述
 * 假设你有一个数组，它的第i个元素是一支给定的股票在第i天的价格。设计一个算法来找到最大的利润。你最多可以完成两笔交易。
 *
 * 你不可以同时参与多笔交易(你必须在再次购买前出售掉之前的股票)
 *
 * 样例
 * 样例 1
 *
 * 输入 : [4,4,6,1,1,4,2,5]
 * 输出 : 6
 * @author created by Meiyu Chen at 2021-6-7 16:30, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class MaxProfitIII {
    public static void main(String[] args) {
        MaxProfitIII test = new MaxProfitIII();
        int[] prices = {4,4,6,1,1,4,2,5};
        System.out.println(test.maxProfit(prices) == 6);
    }
    /** 思路：
     * 先找只能买卖一次的情况下的最大利润，和对应的买卖时间
     * 然后去除掉买卖期间的所有数组元素，计算子数组的最大利润
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {

        return 0;
    }
}
