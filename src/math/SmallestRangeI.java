package math;

import java.lang.reflect.Array;
import java.util.Arrays;

/** Description: 最小差值I
 * 给你一个整数数组 A，请你给数组中的每个元素 A[i] 都加上一个任意数字 x （-K <= x <= K），从而得到一个新数组 B 。
 *
 * 返回数组 B 的最大值和最小值之间可能存在的最小差值。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：A = [1], K = 0
 * 输出：0
 * 解释：B = [1]
 * 示例 2：
 *
 * 输入：A = [0,10], K = 2
 * 输出：6
 * 解释：B = [2,8]
 * 示例 3：
 *
 * 输入：A = [1,3,6], K = 3
 * 输出：0
 * 解释：B = [3,3,3] 或 B = [4,4,4]
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 * 0 <= K <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-range-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author created by Meiyu Chen at 2021-5-27 14:47, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class SmallestRangeI {
    public static void main(String[] args) {
        SmallestRangeI test = new SmallestRangeI();
        int[] A = {1};
        int K = 0;
        System.out.println(test.smallestRangeI(A,K) == 0);
        int[] A2 = {0,10};
        int K2 = 2;
        System.out.println(test.smallestRangeI(A2,K2) == 6);
        int[] A3 = {1,3,6};
        int K3 = 3;
        System.out.println(test.smallestRangeI(A3,K3) == 0);
    }
    public int smallestRangeI(int[] nums, int k) {
        if (nums.length == 1) {
            return 0;
        }
        int max = nums[0],min=nums[0];
        for (int num : nums) {
            max = Math.max(num,max);
            min = Math.min(num,min);
        }
        return Math.max(0,max-min-2*k);
    }
    public int smallestRangeIV1(int[] nums, int k) {
        if (nums.length == 1) {
            return 0;
        }
        int rst = 0;
        int max = nums[0],min=nums[0];
        for (int num : nums) {
            if (num>max) {
                max = num;
            }
            if (num<min){
                min=num;
            }
        }
        return (max-min>2*k)?(max-min-2*k):0;
    }
}
