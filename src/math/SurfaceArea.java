package math;

/**
 * Description: 三维形体的表面积
 * 给你一个 n * n 的网格 grid ，上面放置着一些 1 x 1 x 1 的正方体。
 * <p>
 * 每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。
 * <p>
 * 放置好正方体后，任何直接相邻的正方体都会互相粘在一起，形成一些不规则的三维形体。
 * <p>
 * 请你返回最终这些形体的总表面积。
 * <p>
 * 注意：每个形体的底面也需要计入表面积中。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：grid = [[2]]
 * 输出：10
 * 示例 2：
 * <p>
 * <p>
 * 输入：grid = [[1,2],[3,4]]
 * 输出：34
 * 示例 3：
 * <p>
 * <p>
 * 输入：grid = [[1,0],[0,2]]
 * 输出：16
 * 示例 4：
 * <p>
 * <p>
 * 输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：32
 * 示例 5：
 * <p>
 * <p>
 * 输入：grid = [[2,2,2],[2,1,2],[2,2,2]]
 * 输出：46
 *  
 * <p>
 * 提示：
 * <p>
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 50
 * 0 <= grid[i][j] <= 50
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/surface-area-of-3d-shapes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author created by Meiyu Chen at 2021-5-28 13:25, v1.0
 * <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 * <br>    [TODO-修改内容概述]
 */
public class SurfaceArea {
    public static void main(String[] args) {
        SurfaceArea test = new SurfaceArea();
        int[][] grid = {{2}};
        System.out.println(test.surfaceArea(grid) == 10);
        int[][] grid2 = {{1, 2}, {3, 4}};
        System.out.println(test.surfaceArea(grid2) == 34);
        int[][] grid3 = {{1, 0}, {0, 2}};
        System.out.println(test.surfaceArea(grid3) == 16);
        int[][] grid4 = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        System.out.println(test.surfaceArea(grid4) == 32);
        int[][] grid5 = {{2, 2, 2}, {2, 1, 2}, {2, 2, 2}};
        System.out.println(test.surfaceArea(grid5) == 46);
    }

    /** Description: 重构，优化（以期减少资源消耗）
     * @author created by Meiyu Chen at 2021-5-28 14:17, v1.0
     */
    public int surfaceArea(int[][] grid) {
        int N = grid.length;
        if (N == 1) {
            return grid[0][0]*4 + 2;
        }
        // N>=2
        int area = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                // 上下面积贡献
                if (grid[row][col] > 0) {
                    area += 2;
                }
                int neighborhood = 0;
                // 前后面积贡献
                if (row == 0 || row == N-1){
                    neighborhood = row == 0?1:N-2;
                    area += grid[row][col] + Math.max(0, grid[row][col] - grid[neighborhood][col]);
                } else {
                    // 中间的行
                    area += Math.max(0, grid[row][col] - grid[row + 1][col]) + Math.max(0, grid[row][col] - grid[row - 1][col]);
                }
                // 左右面积贡献
                if (col==0||col==N-1) {
                    neighborhood = col == 0?1:N-2;
                    area += grid[row][col] + Math.max(0, grid[row][col] - grid[row][neighborhood]);
                } else {
                    // 中间的列
                    area += Math.max(0, grid[row][col] - grid[row][col - 1]) + Math.max(0, grid[row][col] - grid[row][col + 1]);
                }
            }
        }
        return area;
    }

    /** Description:
     * 思路：求每一摞立方体对表面积所做的贡献
     *      若该位置立方体个数>0，则对上下表面积的贡献是2
     *      若该位置的立方体个数大于左相邻立方体个数，则对左表面积的贡献是：该位置立方体个数 - 左相邻立方体个数
     *      若该位置立方体个数小于 or 等于左相邻立方体个数，则对做表面积的贡献是0
     *      对右、前、后的表面积的贡献求法同左
     * @author created by Meiyu Chen at 2021-5-28 14:13, v1.0
     */
    public int surfaceAreaV1(int[][] grid) {
        int N = grid.length;
        if (N == 1) {
            return grid[0][0]*4 + 2;
        }
        // N>=2
        int area = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                // 上下面积贡献
                if (grid[row][col] > 0) {
                    area += 2;
                }
                // 前后面积贡献
                if (row == 0) {
                    // 第一行
                    area += grid[0][col] + Math.max(0, grid[0][col] - grid[1][col]);
                } else if (row == N - 1) {
                    // 最后一行
                    area += grid[N - 1][col] + Math.max(0, grid[N - 1][col] - grid[N - 2][col]);
                } else {
                    // 中间的行
                    area += Math.max(0, grid[row][col] - grid[row + 1][col]) + Math.max(0, grid[row][col] - grid[row - 1][col]);
                }
                // 左右面积贡献
                if (col == 0) {
                    // 第一列
                    area += grid[row][0] + Math.max(0, grid[row][0] - grid[row][1]);
                } else if (col == N - 1) {
                    // 最后一列
                    area += grid[row][N - 1] + Math.max(0, grid[row][N - 1] - grid[row][N - 2]);
                } else {
                    // 中间的列
                    area += Math.max(0, grid[row][col] - grid[row][col - 1]) + Math.max(0, grid[row][col] - grid[row][col + 1]);
                }
            }
        }
        return area;
    }
}
