package math;

import java.math.BigDecimal;

/** Description: 最大三角形面积
 *
 * 给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。
 *
 * 示例:
 * 输入: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
 * 输出: 2
 * 解释:
 * 这五个点如下图所示。组成的橙色三角形是最大的，面积为2。
 *
 *
 * 注意:
 *
 * 3 <= points.length <= 50.
 * 不存在重复的点。
 *  -50 <= points[i][j] <= 50.
 * 结果误差值在 10^-6 以内都认为是正确答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-triangle-area
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author created by Meiyu Chen at 2021-6-7 17:18, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class LargestTriangleArea {
    public static void main(String[] args) {
        LargestTriangleArea test = new LargestTriangleArea();
        int[][] points = {{0,0},{0,1},{1,0},{0,2},{2,0}};
        System.out.println(test.largestTriangleArea(points));
        System.out.println(test.largestTriangleArea(points)-2<0.000001);
        int[][] points2 = {{35,-23},{-12,-48},{-34,-40},{21,-25},{-35,-44},{24,1},{16,-9},{41,4},{-36,-49},{42,-49},{-37,-20},{-35,11},{-2,-36},{18,21},{18,8},{-24,14},{-23,-11},{-8,44},{-19,-3},{0,-10},{-21,-4},{23,18},{20,11},{-42,24},{6,-19}};
        System.out.println(test.largestTriangleArea(points2));
    }
    /** Description: 暴力求解，遍历每一个三角形
     * 面积公式：
     *      [海伦公式](https://baike.baidu.com/item/%E6%B5%B7%E4%BC%A6%E5%85%AC%E5%BC%8F)
     *      面积 = sqrt(p*(p-a)(p-b)(p-c))
     *              其中，p= 0.5*(a+b+c)
     *              a,b,c是三个边的边长
     * @author created by Meiyu Chen at 2021-6-7 17:23, v1.0
     */
    public double largestTriangleArea(int[][] points) {
        double rst = 0;
        // 顶点个数
        int n = points.length;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                for (int k = j+1; k < n; k++) {
                    rst = Math.max(rst,area(points[i],points[j],points[k]));
                }
            }
        }
        return rst;
    }

    /** Description: 求三角形面积
     *      [海伦公式](https://baike.baidu.com/item/%E6%B5%B7%E4%BC%A6%E5%85%AC%E5%BC%8F)
     *      *      面积 = sqrt(p*(p-a)(p-b)(p-c))
     *      *              其中，p= 0.5*(a+b+c)
     *      *              a,b,c是三个边的边长
     * @author created by Meiyu Chen at 2021-6-7 17:29, v1.0
     */
    private double area(int[] point, int[] point1, int[] point2) {
        double rst = 0;
        double a = lineLength(point,point1);
        double b = lineLength(point,point2);
        double c = lineLength(point2,point1);
        double p = 0.5*(a+b+c);
        rst = Math.sqrt(p*(p-a)*(p-b)*(p-c));
        // 要考虑到三个点在同一条直线上的情况，这时候求出的面积是NaN
        return Double.isNaN(rst)?0:rst;
    }

    /** Description: 求边长
     * @author created by Meiyu Chen at 2021-6-7 17:33, v1.0
     */
    private double lineLength(int[] point, int[] point1) {
        return Math.sqrt(Math.pow(point[0]-point1[0],2)+Math.pow(point[1]-point1[1],2));
    }
}
