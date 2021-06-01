package dynamicpro.minmax;

import java.util.Arrays;

/** Description: 零钱兑换
 *
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 你可以认为每种硬币的数量是无限的。
 *
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 *
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：coins = [1], amount = 0
 * 输出：0
 * 示例 4：
 *
 * 输入：coins = [1], amount = 1
 * 输出：1
 * 示例 5：
 *
 * 输入：coins = [1], amount = 2
 * 输出：2
 *  
 *
 * 提示：
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author created by Meiyu Chen at 2021-6-1 10:55, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class CoinChange {
    public static void main(String[] args) {
        CoinChange test = new CoinChange();
        int[] coins = {1,2,5};
        int amount = 11;
        System.out.println(test.coinChange(coins,amount) == 3);
        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println(test.coinChange(coins2,amount2) == -1);
        int[] coins3 = {1};
        int amount3 = 0;
        System.out.println(test.coinChange(coins3,amount3) == 0);
        int[] coins4 = {1};
        int amount4 = 1;
        System.out.println(test.coinChange(coins4,amount4) == 1);
        int[] coins5 = {1};
        int amount5 = 2;
        System.out.println(test.coinChange(coins5,amount5) == 2);
    }

    public int coinChange(int[] coins, int amount) {
        int[] f = new int[amount + 1];

        // 由于coins[i]>=1, 所以要凑成amount元钱最多要用到amount个硬币
        int max = amount + 1;
        Arrays.fill(f,max);

        // 初始化：最少用0个硬币兑换0元
        f[0] = 0;

        // 边界情况与转移方程
        for (int i = 1; i < amount+1; i++) {
            // f[i] = min{f[i-coins[0]]+1, f[i-coins[1]]+1, f[i-coins[2]]+1, .. , f[i-coins[length-1]]+1 }
            for (int coin : coins) {
                /*数学公式转化成code有些需要特别关注的点：
                 *   数组下标不能为负数（实际也不可能会出现需要兑换的总面值为负的情况）
                 *   数学公式中∞+1=∞，但是Integer.MAX_VALUE+1有可能溢出变成最小值
                 *   amount<=10^4, coins[i]>=1, 所以最大用10^4个硬币，所以不用考虑溢出的情况
                 * */
                if (i-coin>=0) {
                    // 最后一枚硬币是coin时，所需要的最小硬币数
                    // 这个时候f[i-coin]肯定已经计算出来了
                    f[i] = Math.min(f[i-coin]+1,f[i]);
                }
            }
        }

        return f[amount] > amount?-1:f[amount];
    }

    /** Description: 动态规划法
     * 解题思路：假设拼出amount元钱至少需要k枚硬币，那么拼出amount-coins[i]就至少需要k-1枚硬币
     *      假设拼出amount元钱的最后一枚硬币是coins[i]
     *      最后一枚硬币可能是coins[]数组中的任何一枚硬币
     *      f(amount) = min{f(amount-coins[0])+1, f(amount-coins[1])+1,...f(amount-coins[length-1])+1}
     * 动态规划解题步骤：
     *      step1，确定状态
     *          最后一步：最后一枚硬币是coins[i]
     *          子问题：拼出“amount-最后一枚硬币”所需要的最少硬币数，是拼出amount元钱所需要的最少硬币数的”子问题“
     *          所以状态 :
     *              * f(amount) = min{f(amount-coins[0])+1, f(amount-coins[1])+1,...f(amount-coins[length-1])+1}
     *              * 自变量：amount,因变量f: 拼出amount元钱所需要的最少硬币数f(amount)
     *              * 子问题：f(amount-coins[i])
     *      step2: 转移方程
     *          f[amount] = min{f[amount-coins[i]]+1,其中i=0,1,..coins.length}
     *          转化成了数组形式
     *      step3：边界值和初始值
     *          边界值：
     *              amount-coins[i]>=0, 不允许为负值，硬币面值不能为负，且兑换总金额不能为负
     *              f[amount-coins[i]]不允许为正无穷，不允许使用无穷多个硬币兑换
     *          初始值：
     *              使用公式计算不出来的，就给定初始值
     *              f[0] = min{f(-coins[0])+1,f(-coins[1])+1,f(-coins[2])+1,...f(-coins[length-1])+1}
     *                     = min{∞，∞，∞，∞，∞，∞}
     *                     = ∞，用公式计算不出来，需要我们直接给定初始值
     *              f[0] = 0
     *       step4, 计算顺序
     *          好的顺序可以降低算法时间复杂度
     * 编程实现：
     *      注意，数学公式到code的映射关系，并不完全等价
     * @author created by Meiyu Chen at 2021-6-1 11:00, v1.0
     */
    public int coinChangeV1(int[] coins, int amount) {
        int[] f = new int[amount + 1];
        int n = coins.length;

        // 初始化：最少用0个硬币兑换0元
        f[0] = 0;
        // 边界情况与转移方程
        for (int i = 1; i < amount+1; i++) {
            // 默认需要∞个硬币兑换
            f[i] = Integer.MAX_VALUE;
            // f[i] = min{f[i-coins[0]]+1, f[i-coins[1]]+1, f[i-coins[2]]+1, .. , f[i-coins[length-1]]+1 }
            for (int coin : coins) {
                /*数学公式转化成code有些需要特别关注的点：
                *   数组下标不能为负数（实际也不可能会出现需要兑换的总面值为负的情况）
                *   数学公式中的∞对应于code中的Integer.MAX_VALUE
                *   数学公式中∞+1=∞，但是Integer.MAX_VALUE+1有可能溢出变成最小值*/
                if (i-coin>=0 && f[i-coin]<Integer.MAX_VALUE) {
                    // 最后一枚硬币是coin时，所需要的最小硬币数
                    f[i] = Math.min(f[i-coin]+1,f[i]);
                }
            }
        }

        return f[amount] == Integer.MAX_VALUE?-1:f[amount];
    }
}
