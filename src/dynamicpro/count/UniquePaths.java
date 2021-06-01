package dynamicpro.count;

/** Description: 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 问总共有多少条不同的路径？
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：m = 3, n = 7
 * 输出：28
 * 示例 2：
 *
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 * 示例 3：
 *
 * 输入：m = 7, n = 3
 * 输出：28
 * 示例 4：
 *
 * 输入：m = 3, n = 3
 * 输出：6
 *  
 *
 * 提示：
 *
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author created by Meiyu Chen at 2021-6-1 16:30, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class UniquePaths {
    public static void main(String[] args) {
        UniquePaths test = new UniquePaths();
        int m1=3,n1=7;
        System.out.println(test.uniquePaths(m1,n1) == 28);
        int m2=3,n2=2;
        System.out.println(test.uniquePaths(m2,n2) == 3);
        int m3=7,n3=3;
        System.out.println(test.uniquePaths(m3,n3) == 28);
        int m4=3,n4=3;
        System.out.println(test.uniquePaths(m4,n4) == 6);
    }

    /** Description: 动态规划算法求解
     * 思路：到达右下角只有两种方式，从左面或上面一格到达
     *      假设到达左面一格的路径有K种，到达上面一格的路径有M种，那么到达右下角的路径就有K+M种
     *      备注：加法原理
     *          不重复：到达左边一格的路径和到达上面一格的路径不可能重复（因为只能往右或下走）
     *          无遗漏：只可能经由左边或上边一格到达右下角，没有其他情况
     *          满足无重复和无遗漏条件，所以可以使用加法原理
     *  动态规划算法解题步骤：
     *      step1，确定状态
     *          f(i,j) 到达点（i,j）的所有路径总数目
     *      step2， 转移方程
     *          f[i,j] = f[i-1,j] + f[i,j-1]
     *          到达（i,j）点的路径总数 = 到达左边一格的路径总数 + 达到上面一点的路径总数
     *      step3, 初始条件和边界条件
     *          f[0,j] = 1, j=0,1,2, ... , n-1
     *          f[i,0] = 1, i=0,1,2, ... , m-1
     *          第一行，没有上面的格子，用公式算不出来，所以需要给定初始值
     *          第一列，没有左边的格子，用公式算不出来，所以需要给定初始值
     *      step4,计算顺序
     *          确定计算顺序的原则：计算后面的值时，确保前面的值已经计算出来了，减小时间复杂度
     *          本题计算顺序应为：
     *              从上之下，从左至右（先计算第一行，再计算第二行，再计算...；计算某一行时，从左至右依次计算）
     *              这样f[i,j] = f[i-1,j] + f[i,j-1]
     *              计算f[i,j]时，需要用到的两个值(f[i-1,j] 和 f[i,j-1])分别在前面的行和列中计算过，可以直接拿来使用
     * @author created by Meiyu Chen at 2021-6-1 16:36, v1.0
     */
    public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];

        // 计算顺序：from top to bottom
        for (int row = 0; row < m; row++) {
            // 计算顺序： from left to right
            for (int col = 0; col < n; col++) {
                if (row == 0 || col == 0) {
                    f[row][col] = 1;
                } else {
                    // 按照从上之下，从左至右的顺序安排计算，使得计算f[row][col]所需要的两个数值分别在前面的行和列中计算过，可以直接拿来使用
                    f[row][col] = f[row-1][col] + f[row][col-1];
                }
            }

        }


        return f[m-1][n-1];
    }
}
