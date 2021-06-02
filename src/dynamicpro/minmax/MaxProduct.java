package dynamicpro.minmax;

/** Description: 乘积最大子序列
 *
 * 参考：
 *  1. https://www.lintcode.com/problem/191/
 *
 * 191 · 乘积最大子序列
 * Algorithms
 * 中等
 * 通过率
 * 35%
 *
 * 题目题解笔记讨论排名
 * 描述
 * 找出一个序列中乘积最大的连续子序列（至少包含一个数）。
 *
 * 数组长度不超过20000 乘积最大的子序列的积，小于2147483647
 *
 * 样例
 * 样例 1:
 *
 * 输入:[2,3,-2,4]
 * 输出:6
 * 样例 2:
 *
 * 输入:[-1,2,4,1]
 * 输出:8
 * @author created by Meiyu Chen at 2021-6-2 9:44, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class MaxProduct {
    public static void main(String[] args) {
        MaxProduct test = new MaxProduct();
        int[] arr = {2,3,-2,4};
        System.out.println(test.maxProduct(arr)==6);
        int[] arr2 = {-1,2,4,1};
        System.out.println(test.maxProduct(arr2) == 8);
        System.out.println(Integer.MAX_VALUE);
    }
    /**
     * @param nums: An array of integers
     * @return: An integer
     */
    public int maxProduct(int[] nums) {
        // write your code here
        return nums.length;
    }
}
