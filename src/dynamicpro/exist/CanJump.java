package dynamicpro.exist;

/** Description: 跳跃游戏
 *
 * 描述
 * 给出一个非负整数数组，你最初定位在数组的第一个位置。
 * 数组中的每个元素代表你在那个位置可以跳跃的最大长度。
 * 判断你是否能到达数组的最后一个位置。
 * 数组A的长度不超过5000，每个元素的大小不超过5000
 * 样例
 * 样例 1：
 * 输入：
 * A = [2,3,1,1,4]
 * 输出：
 * true
 * 解释：
 * 0 -> 1 -> 4（这里的数字为下标）是一种合理的方案。
 * 样例 2：
 * 输入：
 * A = [3,2,1,0,4]
 * 输出：
 * false
 * 解释：
 * 不存在任何方案能够到达终点。
 * 挑战
 * 这个问题有两个方法，一个是贪心和 动态规划。
 * 贪心方法时间复杂度为O(N)O(N)。
 * 动态规划方法的时间复杂度为为O(n^2)O(n^2)
 *
 * 为能够让大家使用两种方法通过测试，我们设置的是小型数据集。这仅仅是为了让大家学会如何使用动态规划的方式解决此问题。如果您用动态规划的方式完成它，你可以尝试贪心法，以使其再次通过一次。
 * 链接： https://www.lintcode.com/problem/116/
 * @author created by Meiyu Chen at 2021-6-1 17:55, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class CanJump {
    public static void main(String[] args) {
        CanJump test = new CanJump();
        int[] A = {2,3,1,1,4};
        System.out.println(test.canJump(A) == true);
        int[] A1 = {3,2,1,0,4};
        System.out.println(test.canJump(A1) == false);
        int[] A2 = {0,8,2,0,9};
        System.out.println(test.canJump(A2) == false);
    }

    /** Description: 贪心算法，时间复杂度是O（n）
     * todo 使用贪心算法实现
     * @author created by Meiyu Chen at 2021-6-2 9:38, v1.0
     */
    public boolean canJump(int[] A) {

        // write your code here
        return false;
    }

    /** 动态规划算法： 时间复杂度O（n^2）
     * 思路：能到达位置n = OR(能到达位置i AND n-i <= a[i]) , 其中 0<=i<n
     * 动态规划解题步骤：
     *      step1，确定状态
     *          f(n) 能否到达位置n
     *      step2, 转移方程
     *          f(n) = OR {f(i) AND i + a[i] >= n, 其中0<=i<n}
     *          其中f(i)表示是否能到达位置i
     *          i+a[i] >= n 表示能从位置i跳到位置n
     *          所以若满足下面的条件，就说明能到达位置n： n前面的所有位置中有一个位置i是可达的，且可以从该位置跳到位置n
     *      step3，初始值和边界值
     *          f(1) = true, 因为1前面没有其他位置，不能用公式计算出来，所以给定初始值
     *          枚举都是在给定范围内，所以没有边界条件
     *      step4，计算顺序
     *          从左至右
     *
     * @param A: A list of integers
     * @return: A boolean
     */
    public boolean canJumpV1(int[] A) {
        int n = A.length;
        // 状态：f[i]表示能否到达位置i
        boolean[] f = new boolean[n];

        // 初始值（用公式计算不出来的就给定初始值）
        f[0] = true;
        for (int i = 1; i < n; i++) {
            // 默认假定不能到达位置i
            f[i] = false;
            // previous stone j
            // last jump is from j to i
            // 是否可以到达f[i]: 若存在一个i之前的位置j,青蛙可以到达j,且青蛙可以从j跳到i,则说明可以达到位置i,否则无法到达位置i
            for (int j = 0; j < i; j++) {
                // 遍历i之前的所有位置，看是否能找到满足条件的点
                if (f[j] && (j + A[j] >= i)) {
                    // 若存在一个i之前的位置j,青蛙可以到达j,且青蛙可以从j跳到i,则说明可以达到位置i,否则无法到达位置i
                    f[i] = true;
                    break;
                }
            }
        }

        // write your code here
        return f[n-1];
    }
}
