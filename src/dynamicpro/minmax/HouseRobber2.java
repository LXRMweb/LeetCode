package dynamicpro.minmax;

import java.lang.reflect.Array;
import java.util.Arrays;

/** Description: 打劫房屋II
 *
 * https://www.lintcode.com/problem/534/
 *
 * 描述
 * 在上次打劫完一条街道之后，窃贼又发现了一个新的可以打劫的地方，但这次所有的房子围成了一个圈，这就意味着第一间房子和最后一间房子是挨着的。每个房子都存放着特定金额的钱。你面临的唯一约束条件是：相邻的房子装着相互联系的防盗系统，且 当相邻的两个房子同一天被打劫时，该系统会自动报警。
 *
 * 给定一个非负整数列表，表示每个房子中存放的钱， 算一算，如果今晚去打劫，在不触动报警装置的情况下, 你最多可以得到多少钱 。
 *
 * 这题是House Robber的扩展，只不过是由直线变成了圈
 *
 * 样例
 * 样例1
 *
 * 输入: nums = [3,6,4]
 * 输出: 6
 * 样例2
 *
 * 输入: nums = [2,3,2,3]
 * 输出: 6
 *
 * @author created by Meiyu Chen at 2021-6-7 14:36, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class HouseRobber2 {
    public static void main(String[] args) {
        HouseRobber2 test = new HouseRobber2();
        int[] A = {3,6,4};
        System.out.println(test.houseRobber2(A) == 6);
        int[] A1 = {2,3,2,3};
        System.out.println(test.houseRobber2(A1) == 6);
    }
    /** 动态规划
     * 思路： 圈比较难处理，看能不能想办法将其转换成序列
     *      由于第1幢和第n幢房子不能同时偷，所以一共有三种情况
     *          偷第一幢不偷第n幢
     *          不偷第一幢偷第n幢
     *          不偷第一幢也不偷第n幢
     *      对应的情况下，分别去掉第一幢/第n幢房子，圈就可以转化成序列了
     * 序列求最值，请参考{@link HouseRobber#houseRobberV1(int[])}
     * @param nums: An array of non-negative integers.
     * @return: The maximum amount of money you can rob tonight
     */
    public int houseRobber2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0],nums[1]);
        }
        int n = nums.length;
        HouseRobber util = new HouseRobber();
        // 一定不偷第n幢：将第n幢房子去掉，圈就变成了一个普通的序列
        int[] a = Arrays.copyOfRange(nums, 0, n - 1);
        long max1 = util.houseRobber(a);
        // 一定不偷第一幢：将第一幢房子去掉，圈就变成了一个普通的序列
        int[] a1 = Arrays.copyOfRange(nums, 1, n);
        long max2 = util.houseRobber(a1);
//        // 不偷第1幢，也不偷第n幢
//        long max3 =0;
//        if (n>2) {
//            int[] a2 = Arrays.copyOfRange(nums, 1, n - 1);
//            max3 = util.houseRobber(a2);
//        }
//        return (int)Math.max(max1,Math.max(max2,max3));
        return (int)Math.max(max1,max2);
    }
}
