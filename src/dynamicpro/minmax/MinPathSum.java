package dynamicpro.minmax;

/** Description: 最小数字和和
 *
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * 示例 2：
 *
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 *  
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-path-sum
 *
 * @author created by Meiyu Chen at 2021-6-4 11:19, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class MinPathSum {
    public static void main(String[] args) {
        MinPathSum test = new MinPathSum();
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(test.minPathSum(grid) == 7);
        int[][] grid2 = {{1,2,3},{4,5,6}};
        System.out.println(test.minPathSum(grid2) == 12);
    }
    /** Description: 在V1的基础上进行性能优化
     *   思路：由于计算f[i][j]时，
     *          只会用到上面一行j列及j列之后各个列的元素的值
     *          以及i行0,1,2..., j-1列的数值
     *          除了上述值之外，其他数值都已经无用了，可以不用存储在内存中
     *          由此可见，可以使用长度为n的一维数组暂存中间的计算结果
     *          这样可以将空间复杂度由O(M*N)降低至O（N）
     *
     * @author created by Meiyu Chen at 2021-6-4 13:39, v1.0
     */
    public int minPathSum(int[][] grid) {
        /*m == grid.length
            n == grid[i].length
            1 <= m, n <= 200*/
        int m = grid.length;
        int n = grid[0].length;

        /*暂存到达上面一行j列及j列之后，&& 本行j列之前的各个格子的路径上的最小数字和
        * 其中，f[0],f[1],...,f[j-1]分别表示到达左边格子(i,0),(i,1)... (i,j-1)路径上的最小数字和
        * f[j],f[j+1],....f[n-1]分别表示到达上面格子（i-1，j）,(i-1,j+1).....(i-1,n-1)路径上的最小数字和
        * */
        int[] f = new int[n];
        for (int i = 0; i < f.length; i++) {
            f[i] = 0;
        }
        // 到达左边一格的最小数字和
        int minLeft = Integer.MAX_VALUE;
        // 到达上面一格的最小数字和
        int minUp = Integer.MAX_VALUE;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (row == 0 && col == 0) {
                    // 初始值
                    f[0] = grid[0][0];
                } else {
                    // 更新到达上面一格的路径最小数字和,如果上面没有格子，那么到达上面一格的路径设为∞
                    minUp = row > 0 ? f[col]:Integer.MAX_VALUE;
                    // 更新到达左边一格的路径最小数字和,如果左边没有格子，那么到达左边一格的路径设为∞
                    minLeft = col>0?f[col-1]:Integer.MAX_VALUE;
                    // 到达（i,j）的最小数字和 = min(到达上面一格的最小数字和，达到左边一格的最小数字和) + 格子(i,j)上的数字
                    f[col] = Math.min(minLeft,minUp) + grid[row][col];
                }
            }
        }
        return f[n-1];
    }


    /** Description: 动态规划算法
     *
     * step1，确定状态
     *      f[i][j] 左上角到达（i,j）路径上的最小数字和
     * step2， 转移方程
     *      f[i][j] = Math.min(f[i-1][j], f[i][j-1])
     * step3, 初始值和边界条件
     *      f[0][0] = grid[0][0]
     * step4, 计算顺序
     *      原则：计算后面的值时可以直接使用前面已经计算出来的值
     *      从上之下，从左至右
     * @author created by Meiyu Chen at 2021-6-4 11:22, v1.0
     */
    public int minPathSumV1(int[][] grid) {
        /*m == grid.length
            n == grid[i].length
            1 <= m, n <= 200*/
        int m = grid.length;
        int n = grid[0].length;

        // 确定状态：f[i][j]-左上角到达（i,j）路径上的最小数字和
        int[][] f = new int[m][n];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (row == 0 && col == 0) {
                    f[0][0] = grid[0][0];
                } else {
                    f[row][col] = Integer.MAX_VALUE;
                    if (row > 0) {
                        f[row][col] = Math.min(f[row][col], f[row-1][col]);
                    }
                    if (col>0) {
                        f[row][col] = Math.min(f[row][col],f[row][col-1]);
                    }
                    f[row][col] += grid[row][col];
                }

            }
        }

        return f[m-1][n-1];
    }
}
