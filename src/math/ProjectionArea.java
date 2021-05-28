package math;

/** Description: 三维形体投影面积
 *
 * 在 N * N 的网格中，我们放置了一些与 x，y，z 三轴对齐的 1 * 1 * 1 立方体。
 * 每个值 v = grid[i][j] 表示 v 个正方体叠放在单元格 (i, j) 上。
 * 现在，我们查看这些立方体在 xy、yz 和 zx 平面上的投影。
 * 投影就像影子，将三维形体映射到一个二维平面上。
 * 在这里，从顶部、前面和侧面看立方体时，我们会看到“影子”。
 * 返回所有三个投影的总面积。
 *
 * 示例 1：
 * 输入：[[2]]
 * 输出：5
 * 示例 2：
 * 输入：[[1,2],[3,4]]
 * 输出：17
 * 解释：
 * 这里有该形体在三个轴对齐平面上的三个投影(“阴影部分”)。
 * 示例 3：
 * 输入：[[1,0],[0,2]]
 * 输出：8
 * 示例 4：
 * 输入：[[1,1,1],[1,0,1],[1,1,1]]
 * 输出：14
 * 示例 5：
 * 输入：[[2,2,2],[2,1,2],[2,2,2]]
 * 输出：21
 *
 * 提示：
 * 1 <= grid.length = grid[0].length <= 50
 * 0 <= grid[i][j] <= 50
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/projection-area-of-3d-shapes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author created by Meiyu Chen at 2021-5-28 9:09, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class ProjectionArea {
    public static void main(String[] args) {
        ProjectionArea test = new ProjectionArea();
        int[][] grid = {{2}};
        int[][] grid2 = {{1,2},{3,4}};
        int[][] grid3 = {{1,0},{0,2}};
        int[][] grid4 = {{1,1,1},{1,0,1},{1,1,1}};
        int[][] grid5 = {{2,2,2},{2,1,2},{2,2,2}};
        System.out.println(test.projectionArea(grid) == 5);
        System.out.println(test.projectionArea(grid2) == 17);
        System.out.println(test.projectionArea(grid3) == 8);
        System.out.println(test.projectionArea(grid4) == 14);
        System.out.println(test.projectionArea(grid5) == 21);

    }
    public int projectionArea(int[][] grid) {
        // 题目说了：grid.length = grid[0].length
        int N = grid.length;
        int area = 0;
        int colMax = 0;
        int rowMax = 0;

        for (int row = 0; row < N; row++) {
            // 清空行面积
            rowMax = 0;
            colMax = 0;
            for (int col = 0; col < N; col++) {
                if (grid[row][col] != 0) {
                    area++;
                }
                // Mark：行索引不变，改变列索引，就是遍历一行，找出一行中的最大值
                rowMax = Math.max(rowMax,grid[row][col]);
                // Mark: 列索引不变，改变行索引，就是遍历一列，找出一列中的最大值
                // 但是，这么做是有前提条件的：前提条件就是二维数组的行数和列数相等。若不等，这么做就有可能爆出运行时异常：IndexOutOfBoundary
                colMax = Math.max(colMax,grid[col][row]);
            }
            area+=rowMax+colMax;
        }
        return area;
    }
    /** Description: 减少耗时、内存资源消耗
     * @author created by Meiyu Chen at 2021-5-28 10:35, v1.0
     */
    public int projectionAreaV2(int[][] grid) {
        int area = 0;
        int[] colMax = new int[grid[0].length];
        for (int i = 0; i < colMax.length; i++) {
            colMax[i] = 0;
        }
        int rowMax = 0;

        for (int row = 0; row < grid.length; row++) {
            // 清空行面积
            rowMax = 0;
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] != 0) {
                    area++;
                }
                rowMax = Math.max(rowMax,grid[row][col]);
                colMax[col] = Math.max(colMax[col],grid[row][col]);
            }
            area+=rowMax;
        }

        int colArea = 0;
        for (int max : colMax) {
            colArea+=max;
        }
        return area+colArea;
    }
    public int projectionAreaV1(int[][] grid) {
        int area = 0;
        int countZero = 0;
        int[] colMax = new int[grid[0].length];
        for (int i = 0; i < colMax.length; i++) {
            colMax[i] = 0;
        }
        int rowMax = 0;
        int rowArea = 0;

        for (int row = 0; row < grid.length; row++) {
            // 清空行面积
            rowMax = 0;
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 0) {
                    countZero++;
                }
                rowMax = Math.max(rowMax,grid[row][col]);
                colMax[col] = Math.max(colMax[col],grid[row][col]);
            }
            rowArea+=rowMax;
        }

        int colArea = 0;
        for (int max : colMax) {
            colArea+=max;
        }
        return grid.length*grid[0].length-countZero + rowArea+colArea;
    }
}
