package dynamicpro.count;

/** Description: 不同路径II(带障碍物)
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 *
 *
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：2
 * 解释：
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 * 示例 2：
 *
 *
 * 输入：obstacleGrid = [[0,1],[0,0]]
 * 输出：1
 *  
 *
 * 提示：
 *
 * m == obstacleGrid.length
 * n == obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] 为 0 或 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author created by Meiyu Chen at 2021-6-2 11:13, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class UniquePathsWithObstacles {
    public static void main(String[] args) {
        UniquePathsWithObstacles test = new UniquePathsWithObstacles();
        int[][] obstacleGrid = {{0,0,0},{0,1,0},{0,0,0}};
        System.out.println(test.uniquePathsWithObstacles(obstacleGrid) == 2);
        int[][] obstacleGrid2 = {{0,1},{0,0}};
        System.out.println(test.uniquePathsWithObstacles(obstacleGrid2) == 1);
    }
    /** Description: 动态规划
     * 解题思路：由于只能向右或者向下走，所以到达右下角只有两种方式：从上面一格或者左边一格到达
     *          step1，确定状态: f[i][j] 表示到达（i,j）的路径总数
     *          step2，转移方程
     *              f[i][j] = 0,            有障碍时（即：obstracleGrid[i][j]==1时）
     *                        f[0][j-1],    无障碍，且第一行（即i==0时）
     *                        f[i-1][0]     无障碍，且第一列（即j==0时）
     *                        f[i-1][j] + f[i][j-1] 其他
     *          step3, 初始条件
     *              f[0][0] = obstracleGrid[0][0] == 1 ? 0 : 1
     *          step4, 计算顺序
     *              原则：后面的计算用到前面计算得到的值
     *              顺序：从上至下，从左至右
     * @author created by Meiyu Chen at 2021-6-2 13:58, v1.0
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 1<=m,n<=100
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // 起始位置和结束位置是障碍物，到达不了结束位置
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) {
            return 0;
        }

        // f[i][j] : 到达位置（i,j）的路径数
        int[][] f = new int[m][n];


        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (obstacleGrid[row][col] == 1) {
                    // 有障碍物，到不了该位置，到达该位置的路径总数为0
                    f[row][col] = 0;
                } else {
                    if(row==0&&col==0){
                        // 初始化
                        f[0][0] = 1;
                    }else if (row == 0 && col>0) {
                        f[row][col] = f[row][col-1];
                    } else if(col == 0&&row>0){
                        f[row][col] = f[row-1][col];
                    }else{
                        f[row][col]=f[row][col-1] + f[row-1][col];
                    }
                }

            }

        }
        return f[m-1][n-1];
    }
}
