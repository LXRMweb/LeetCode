package dynamicpro.minmax;

/** Description: 最小连续子序列长度
 * 参考：
 *      https://www.lintcode.com/problem/397/
 *
 * 描述
 * 给定一个整数数组（下标从 0 到 n-1， n 表示整个数组的规模），请找出该数组中的最长上升连续子序列。（最长上升连续子序列可以定义为从右到左或从左到右的序列。）
 *
 * 样例
 * 样例 1：
 *
 * 输入：[5, 4, 2, 1, 3]
 * 输出：4
 * 解释：
 * 给定 [5, 4, 2, 1, 3]，其最长上升连续子序列（LICS）为 [5, 4, 2, 1]，返回 4。
 * 样例 2：
 *
 * 输入：[5, 1, 2, 3, 4]
 * 输出：4
 * 解释：
 * 给定 [5, 1, 2, 3, 4]，其最长上升连续子序列（LICS）为 [1, 2, 3, 4]，返回 4。
 * 挑战
 * 使用 O(n) 时间和 O(1) 额外空间来解决
 * @author created by Meiyu Chen at 2021-6-4 11:13, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class LongestIncreasingContinuousSubsequence {
    public static void main(String[] args) {
        LongestIncreasingContinuousSubsequence test = new LongestIncreasingContinuousSubsequence();
        int[] i1 = {5,4,2,1,3};
        System.out.println(test.longestIncreasingContinuousSubsequence(i1)==4);
        int[] i2 = {5,1,2,3,4};
        System.out.println(test.longestIncreasingContinuousSubsequence(i2) == 4);
        int[] i3 = {10};
        System.out.println(test.longestIncreasingContinuousSubsequence(i3) == 1);
    }

    /** Description: 在V1的基础上进行性能优化，降低空间复杂度，由O（n)降低到O（1）
     * 性能优化思路：
     *      注意，状态转移方程中，计算f[i]时只用到了f[i-1],前面的f[0],f[1]....f[i-2]都没有用到
     *      所以，其实我们根本就不用去存储f[0],f[1]....f[i-2]
     *      只需要存储f[i-1]就行了，
     *      这样的话，时间复杂度可以由f(n)降至f(1)
     * @author created by Meiyu Chen at 2021-6-4 9:15, v1.0
     *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
     *          [TODO-修改内容概述]
     * @param A
     * @return int
     * @throws
     */
    public int longestIncreasingContinuousSubsequence(int[] A) {
        // write your code here
        int n = A.length;
        if (n<1) return 0;
        // step1,确定状态：fAscendingLeft以A[i-1]结尾的最长连续上升子序列长度
        int fAscendingLeft = 1;
        // step1,确定状态：fDescending[i]- 以A[i]结尾的最长连续下降子序列长度
        int fDescendingLeft = 1;
        // 最长连续上升/下降子序列长度
        int max = 1;
        for (int i = 1; i < n; i++) {
            if (A[i-1]<A[i]) {
                fAscendingLeft += 1;
            }else{
                fAscendingLeft = 1;
            }
            if(A[i-1]>A[i]){
                fDescendingLeft += 1;
            }else{
                fDescendingLeft = 1;
            }
            max = Math.max(max,Math.max(fAscendingLeft,fDescendingLeft));
        }

        return max;
    }

    /** 动态规划算法：
     * 咱们先只考虑“最长连续上升子序列”
     * step1，确定状态
     *      f[i] 以A[i]为最后一个元素的最长上升子序列的长度
     * step2，转移方程
     *      f[i] = f[i-1] + 1, 如果A[i-1] < A[i]
     *             1, 如果A[i-1] >= A[i]
     *      最终返回 max{f[0],f[1],f[2].... f[n-1]}
     * step3, 初始值和边界条件
     *      f[0] = 1
     *      边界条件：最长上升子序列A[i-1] < A[i]
     * step4，计算顺序
     *      原则：计算后面的值的时候直接使用前面计算得到的值
     * 性能：时间复杂度O（n）,空间复杂度O（n）
     * @param A: An array of Integer
     * @return: an integer
     */
    public int longestIncreasingContinuousSubsequenceV1(int[] A) {
        // write your code here
        int n = A.length;
        if (n<1) return 0;
        // step1,确定状态：fAscending[i]- 以A[i]结尾的最长连续上升子序列长度
        int[] fAscending = new int[n];
        // step1,确定状态：fDescending[i]- 以A[i]结尾的最长连续下降子序列长度
        int[] fDescending = new int[n];
        fAscending[0] = 1;
        fDescending[0] = 1;
        for (int i = 1; i < n; i++) {
            if (A[i-1]<A[i]) {
                fAscending[i] = fAscending[i-1]+1;
            }else{
                fAscending[i] = 1;
            }
            if(A[i-1]>A[i]){
                fDescending[i] = fDescending[i-1]+1;
            }else{
                fDescending[i] = 1;
            }
        }

        // 找出最长连续上升/下降子序列长度
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max,fAscending[i]);
        }
        for (int i = 0; i < n; i++) {
            max = Math.max(max,fDescending[i]);
        }

        return max;
    }
}
