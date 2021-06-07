package dynamicpro.minmax;

/** Description: 买卖股票的最佳时机II
 *
 * https://www.lintcode.com/problem/150/
 *
 * 描述
 * 给定一个数组 prices 表示一支股票每天的价格.
 *
 * 交易次数不限, 不过你不能同时参与多个交易 (也就是说, 如果你已经持有这支股票, 在再次购买之前, 你必须先卖掉它).
 *
 * 设计一个算法求出最大的利润.
 *
 * 样例
 * 样例 1:
 *
 * 输入: [2, 1, 2, 0, 1]
 * 输出: 2
 * 解释:
 *     1. 在第 2 天以 1 的价格买入, 然后在第 3 天以 2 的价格卖出, 利润 1
 *     2. 在第 4 天以 0 的价格买入, 然后在第 5 天以 1 的价格卖出, 利润 1
 *     总利润 2.
 * 样例 2:
 *
 * 输入: [4, 3, 2, 1]
 * 输出: 0
 * 解释: 不进行任何交易, 利润为0.
 *
 * @author created by Meiyu Chen at 2021-6-7 16:11, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class MaxProfitII {
    public static void main(String[] args) {
        MaxProfitII test = new MaxProfitII();
        int[] prices = {2,1,2,0,1};
        System.out.println(test.maxProfit(prices) == 2);
        int[] prices1 = {4,3,2,1};
        System.out.println(test.maxProfit(prices1)==0);
    }
    /** 解题思路:
     *      所有上升线段的最低点买入 ，最高点卖出
     *      等价于
     *      上升线段每天买入，第二天卖出
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        // write your code here
        if (prices.length<2) {
            return 0;
        }
        int rst = 0;
        int increase = 0;
        for (int i = 1; i < prices.length; i++) {
            increase = prices[i] - prices[i - 1];
            rst+= increase>0?increase:0;
        }
        return rst;
    }
}
