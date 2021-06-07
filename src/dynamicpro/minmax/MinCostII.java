package dynamicpro.minmax;

/** Description: 房屋染色II
 *
 * 链接： https://www.lintcode.com/problem/516/
 *
 * 描述
 * 这里有n个房子在一列直线上，现在我们需要给房屋染色，共有k种颜色。每个房屋染不同的颜色费用也不同，你希望每两个相邻的房屋颜色不同
 *
 * 费用通过一个nxk 的矩阵给出，比如cost[0][0]表示房屋0染颜色0的费用，cost[1][2]表示房屋1染颜色2的费用。找到油漆所有房子的最低成本。
 *
 * 所有费用都是正整数
 *
 * 样例
 * 样例1
 *
 * 输入:
 * costs = [[14,2,11],[11,14,5],[14,3,10]]
 * 输出: 10
 * 说明:
 * 三个屋子分别使用第1,2,1种颜色，总花费是10。
 * 样例2
 *
 * 输入:
 * costs = [[5]]
 * 输出: 5
 * 说明：
 * 只有一种颜色，一个房子，花费为5
 * 挑战
 * 用O(nk)的时间复杂度解决
 * @author created by Meiyu Chen at 2021-6-7 9:53, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class MinCostII {
    public static void main(String[] args) {
        MinCostII test = new MinCostII();
        int[][] costs = {{14,2,11},{11,14,5},{14,3,10}};
        System.out.println(test.minCostII(costs) == 10);
        int[][] costs2 = {{5}};
        System.out.println(test.minCostII(costs2) == 5);
        int[][] costs3 = {{1,2,3},{1,4,6}};
        System.out.println(test.minCostII(costs3) == 3);
    }

    /** Description: 性能优化 - 降低时间复杂度 - 由O(N*M*M)降低至O(N*M)
     * 思路：观察for可得，V2的时间复杂度是O（N*M*M）
     *      仔细分析code可知，“label1”处，每次都是求去掉某一种颜色之后的最小值，有许多重复计算，考虑是否可以对此处进行优化
     *      优化label1处的代码，使其时间复杂度由O(M)变成O（1），优化思路概述如下：
     *          首先求出所有元素的最小值、次小值
     *          然后去除某个元素，如果去除的元素等于最小值，那么去除之后剩余元素的最小值 = 次小值
     *                          如果去除的元素大于最小值，那么去除该元素之后剩余元素的最小值 = 最小值
     * @author created by Meiyu Chen at 2021-6-7 11:16, v1.0
     */
    public int minCostII(int[][] costs) {
        if (costs.length<1) return 0;
        if (costs[0].length<1) return 0;

        // n栋房子
        int houseNum = costs.length;
        // m种颜色
        int colorNum = costs[0].length;

        // 确定状态:f[i][j]表示第i栋房子染成颜色j时，前i栋房子最少需要花费多少钱
        int[][] f = new int[2][colorNum];
        // 保存当前房子染成颜色j时前i栋房子所需要的最少花费
        int currHouse = 0;
        // 保存前一栋房子染成颜色k时，前i-1栋房子所需要的最少花费
        int lastHouse = 1-currHouse;
        int rst = Integer.MAX_VALUE;
        // 前i-1栋房子需要的最少花费
        int minCostLast = Integer.MAX_VALUE;
        // 前i-1栋房子最少花费对应的第i-1栋房子的颜色
        int minCostColorIdx = 0;
        // 前i-1栋房子需要的次少花费
        int minCost2Last = Integer.MAX_VALUE;
        // 前i-1栋房子次少花费对应的第i-1栋房子的颜色
        int minCost2ColorIdx = 0;
//        int[][] f = new int[houseNum][colorNum];
        for (int houseIdx = 0; houseIdx < houseNum; houseIdx++) {
            lastHouse = currHouse;
            currHouse = 1-lastHouse;

            if (houseIdx>0) {
                minCost2ColorIdx = 0;
                minCost2Last = Integer.MAX_VALUE;
                minCostColorIdx = 0;
                minCostLast = Integer.MAX_VALUE;
                for (int color = 0; color < colorNum; color++) {
                    if (f[lastHouse][color]<=minCostLast){
                        // 次小等于当前的最小
                        minCost2Last = minCostLast;
                        minCost2ColorIdx = minCostColorIdx;
                        // 更新最小
                        minCostLast = f[lastHouse][color];
                        minCostColorIdx = color;
                    } else if(f[lastHouse][color]<minCost2Last){
                        // 更新次小
                        minCost2Last = f[lastHouse][color];
                        minCost2ColorIdx = color;
                    }

                }
            }
            // 前i-1栋房子的最小和次小花费，以及最小和次小花费对应的第i-1栋房子的颜色下标
            for (int colorIdx = 0; colorIdx < colorNum; colorIdx++) {
                if (houseIdx==0) {
                    // 第一栋房子
//                    f[0][colorIdx] = costs[0][colorIdx];
                    f[currHouse][colorIdx] = costs[0][colorIdx];
                } else {
                    f[currHouse][colorIdx] = Integer.MAX_VALUE;
//                    f[houseIdx][colorIdx] = Integer.MAX_VALUE;
//                    // label1,时间复杂度可优化点
//                    for (int k = 0; k < colorNum; k++) {
//                        if (k!=colorIdx) {
////                            f[houseIdx][colorIdx] = Math.min(f[houseIdx][colorIdx],f[houseIdx-1][k]);
//                            f[currHouse][colorIdx] = Math.min(f[currHouse][colorIdx],f[lastHouse][k]);
//                        }
//                    }
                    if (f[lastHouse][colorIdx]>minCostLast) {
                        f[currHouse][colorIdx] = minCostLast;
                    } else {
                        f[currHouse][colorIdx] = minCost2Last;
                    }
                    f[currHouse][colorIdx] += costs[houseIdx][colorIdx];
                }
                if (houseIdx==houseNum-1){
                    rst = Math.min(rst,f[currHouse][colorIdx]);
                }
            }
        }

        return rst;
    }


    /** Description: 性能优化 -- “滚动数组法”降低空间复杂度： 由O(n*m)降低至O(2*m)
     * 思路：观察转移方程可知，计算第i栋房子染成某种颜色时前i栋房子所需的最小花费，只需要用到第i-1栋房子的花费
     *      所以，没有必要存储所有f[i][j],只需保留上一行的值即可，这样空间复杂度可以由O(n*m)降低至O(2*m)
     *      滚动数组法
     * @author created by Meiyu Chen at 2021-6-7 10:18, v1.0
     */
    public int minCostIIV2(int[][] costs) {
        if (costs.length<1) return 0;
        if (costs[0].length<1) return 0;

        // n栋房子
        int houseNum = costs.length;
        // m种颜色
        int colorNum = costs[0].length;

        // 确定状态:f[i][j]表示第i栋房子染成颜色j时，前i栋房子最少需要花费多少钱
        int[][] f = new int[2][colorNum];
        // 保存当前房子染成颜色j时前i栋房子所需要的最少花费
        int currHouse = 0;
        // 保存前一栋房子染成颜色k时，前i-1栋房子所需要的最少花费
        int lastHouse = 1-currHouse;
        int rst = Integer.MAX_VALUE;
//        int[][] f = new int[houseNum][colorNum];
        for (int houseIdx = 0; houseIdx < houseNum; houseIdx++) {
            lastHouse = currHouse;
            currHouse = 1-lastHouse;
            for (int colorIdx = 0; colorIdx < colorNum; colorIdx++) {
                if (houseIdx==0) {
                    // 第一栋房子
//                    f[0][colorIdx] = costs[0][colorIdx];
                    f[currHouse][colorIdx] = costs[0][colorIdx];
                } else {
                    f[currHouse][colorIdx] = Integer.MAX_VALUE;
//                    f[houseIdx][colorIdx] = Integer.MAX_VALUE;
                    for (int k = 0; k < colorNum; k++) {
                        if (k!=colorIdx) {
//                            f[houseIdx][colorIdx] = Math.min(f[houseIdx][colorIdx],f[houseIdx-1][k]);
                            f[currHouse][colorIdx] = Math.min(f[currHouse][colorIdx],f[lastHouse][k]);
                        }
                    }
                    f[currHouse][colorIdx] += costs[houseIdx][colorIdx];
                }
                if (houseIdx==houseNum-1){
                    rst = Math.min(rst,f[currHouse][colorIdx]);
                }
            }
        }

        return rst;
    }

    /** 动态规划算法：
     * 序列型动态规划：f[i] 表示“前i个”
     * step1，确定状态
     *      f[i][j] 第i栋房子染成颜色j时，前i栋房子最少需要花费多少钱
     * step2，转移方程
     *      f[i][j] = cost[i][j] + min{f[i-1][k],其中k∈[0,m),且k!=j}
     * step3，初始状态
     *      f[0][j] = cost[0][j] 第一栋房子
     * step4，计算顺序
     *      原则：后面的计算使用前面已经计算出来的值
     *      顺序：从前至后，i由小至大
     * @param costs: n x k cost matrix
     * @return: an integer, the minimum cost to paint all houses
     */
    public int minCostIIV1(int[][] costs) {
        if (costs.length<1) return 0;
        if (costs[0].length<1) return 0;

        // n栋房子
        int houseNum = costs.length;
        // m种颜色
        int colorNum = costs[0].length;

        // 确定状态
        int[][] f = new int[houseNum][colorNum];
        for (int houseIdx = 0; houseIdx < houseNum; houseIdx++) {
            for (int colorIdx = 0; colorIdx < colorNum; colorIdx++) {
                if (houseIdx==0) {
                    // 第一栋房子
                    f[0][colorIdx] = costs[0][colorIdx];
                } else {
                    f[houseIdx][colorIdx] = Integer.MAX_VALUE;
                    for (int k = 0; k < colorNum; k++) {
                        if (k!=colorIdx) {
                            f[houseIdx][colorIdx] = Math.min(f[houseIdx][colorIdx],f[houseIdx-1][k]);
                        }
                    }
                    f[houseIdx][colorIdx] += costs[houseIdx][colorIdx];
                }
            }
        }

        int rst = Integer.MAX_VALUE;
        for (int color = 0; color < colorNum; color++) {
            rst = Math.min(rst,f[houseNum-1][color]);
        }
        return rst;
    }
}
