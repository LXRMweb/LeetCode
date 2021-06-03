package dynamicpro.minmax;

/** Description: 房屋染色
 * 描述
 * 这里有n个房子在一列直线上，现在我们需要给房屋染色，分别有红色蓝色和绿色。每个房屋染不同的颜色费用也不同，你需要设计一种染色方案使得相邻的房屋颜色不同，并且费用最小，返回最小的费用。
 *
 * 费用通过一个nx3 的矩阵给出，比如cost[0][0]表示房屋0染红色的费用，cost[1][2]表示房屋1染绿色的费用，依此类推。找到油漆所有房子的最低成本。
 *
 * 所有费用都是正整数
 *
 * 样例
 * 样例 1:
 *
 * 输入: [[14,2,11],[11,14,5],[14,3,10]]
 * 输出: 10
 * 解释: 第一个屋子染蓝色，第二个染绿色，第三个染蓝色，最小花费：2 + 5 + 3 = 10.
 * 样例 2:
 *
 * 输入: [[1,2,3],[1,4,6]]
 * 输出: 3
 * @author created by Meiyu Chen at 2021-6-3 14:21, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class MinCost {
    public static void main(String[] args) {
        MinCost test = new MinCost();
        int[][] cost = {{14,2,11},{11,14,5},{14,3,10}};
        System.out.println(test.minCost(cost) == 10);
        int[][] cost2 = {{1,2,3},{1,4,6}};
        System.out.println(test.minCost(cost2) == 3);
    }

    /** 动态规划法解题
     * 解题关键： 序列 + 状态（如本题中，状态就是第i栋房子的颜色）
     *
     * n栋房子用m种颜色染色
     * 1. 确定状态
     *      f[i][j] 第i栋房子油漆成第j种颜色，所需的最少花费
     *      如：f[n-1][0]代表第n栋房子油漆成第1种颜色所需要的最少花费
     *      如：f[n-1][1]代表第n栋房子油漆成第2种颜色所需要的最少花费
     *      如：f[n-1][2]代表第n栋房子油漆成第3种颜色所需要的最少花费
     *      ...
     *      如：f[n-1][m-1]代表第n栋房子油漆成第m种颜色所需要的最少花费
     *      最终所需要的最小花费 = min {f[n-1][0],f[n-1][1]...., f[n-1][m-1]}
     * 2. 转移方程
     *      f[i][j] = Min {f[i-1][k], 其中k属于[0,m),且k!=j}
     *              = Min {f[i-1][0], f[i-1][1] ..., f[i-1][j-1], f[i-1][j+1],....f[i-1][m-1]}
     * 3.
     * 4. 计算顺序
     *      后面的计算使用到前面计算出的值
     *      从上至下
     * @param costs: n x 3 cost matrix
     * @return: An integer, the minimum cost to paint all houses
     */
    public int minCost(int[][] costs) {
        // 房屋总数目
        int n = costs.length;
        if (n<1) return 0;
        // 颜色总数目
        int m = costs[0].length;

        int[][] f = new int[n][m];

        for (int house = 0; house < n; house++) {
            // house 的颜色
            for (int color = 0; color < m; color++) {
                if (house==0) {
                    f[0][color] = costs[0][color];
                } else {
                    f[house][color] = Integer.MAX_VALUE;
                    // house前面的那栋房子的颜色
                    for (int color2 = 0; color2 < m; color2++) {
                        // 相邻的房子颜色不能相同
                        if (color2!=color) {
                            f[house][color] = Math.min(f[house-1][color2]+costs[house][color],f[house][color]);
                        }
                    }
                }
            }
        }
        int rst = Integer.MAX_VALUE;
        // 最后一栋房子的颜色
        for (int color = 0; color < m; color++) {
            rst = Math.min(f[n-1][color],rst);
        }
        // write your code here
        return rst;
    }
}
